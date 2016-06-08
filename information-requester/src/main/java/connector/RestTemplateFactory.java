
package connector;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;

public class RestTemplateFactory {

    private final UriComponents uriComponents;

    private final String host;
    private final Integer port;
    private final int timeout;

    private final boolean secure;
    private final String user;
    private final String pass;

    public static class Builder {
        //Required parameters
        private final String host;
        private final String user;
        private final String pass;
        //Default http port
        private Integer port = null;
        //default timeout (in seconds)
        private int timeout = 5;

        private boolean secure = false;

        public Builder(String host, String user, String pass) {
            this.host = host;
            this.user = user;
            this.pass = pass;
        }

        public Builder port(int val) {
            port = val;
            return this;
        }

        public Builder isSecure(boolean val) {
            secure = val;
            return this;
        }

        public Builder timeout(int val) {
            timeout = val;
            return this;
        }

        public InformationRequester build() {
            return new RestTemplateFactory(this).buildTemplate();
        }

    }

    private RestTemplateFactory(Builder builder) {
        host = builder.host;
        port = builder.port;
        secure = builder.secure;
        timeout = builder.timeout;
        user = builder.user;
        pass = builder.pass;

        UriComponentsBuilder host = UriComponentsBuilder.newInstance().scheme(secure ? "https" : "http")
                .host(this.host);
        if (port != null) host.port(port);
        uriComponents = host.build();
    }


    private InformationRequester buildTemplate() {
        HttpHost targetHost = new HttpHost(this.host, this.port, secure ? "https" : "http");

        final BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();

        credentialsProvider.setCredentials(new AuthScope(this.host, this.port, AuthScope.ANY_REALM), new UsernamePasswordCredentials(this.user, this.pass));

        // Create AuthCache instance
        AuthCache authCache = new BasicAuthCache();
        // Generate BASIC scheme object and add it to the local auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(targetHost, basicAuth);
        // Add AuthCache to the execution context
        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credentialsProvider);
        context.setAuthCache(authCache);

        RequestConfig.Builder requestBuilder = RequestConfig.custom();
        requestBuilder = requestBuilder.setAuthenticationEnabled(true)
                .setConnectionRequestTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000);


        final CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(requestBuilder.build())
                .setDefaultCredentialsProvider(credentialsProvider).build();
        final ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(client);

        String auth = this.user + ":" + this.pass;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.UTF_8));
        String header="Basic " + new String(encodedAuth, StandardCharsets.UTF_8);

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        return new InformationRequester(restTemplate, uriComponents.toUri(),header);
    }


}
