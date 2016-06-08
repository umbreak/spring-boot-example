package connector;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.ErrorResponse;
import model.InformationProvidersResponse;
import model.MarketSurveysRequest;
import model.MarketSurveysResponse;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

/**
 * This class provides the basic implementation of a REST HTTP Client against the Backend.
 */
public class InformationRequester {
    public final RestTemplate restTemplate;
    public final URI uri;
    private final String authHeader;
    private ObjectMapper mapper=new ObjectMapper();

    protected InformationRequester(RestTemplate restTemplate, URI uri, String authHeader) {
        this.restTemplate = restTemplate;
        this.uri = uri;
        this.mapper=new ObjectMapper();
        this.authHeader = authHeader;
    }

    public MarketSurveysResponse findSurveys(MarketSurveysRequest request){
        URI uri = UriComponentsBuilder.fromUri(this.uri).path("/survey/searches").build().toUri();
        try {
            ResponseEntity<MarketSurveysResponse> entityWrapper = restTemplate.
                    exchange(uri, HttpMethod.POST, new HttpEntity<>(request,createHeaders()), MarketSurveysResponse.class);
            return entityWrapper.getBody();

        } catch (HttpClientErrorException ex) {
            tryFetchErrorAndConvertToException(ex);
            return null;
        }
    }

    public InformationProvidersResponse listProviders(){
        URI uri = UriComponentsBuilder.fromUri(this.uri).path("/provider").build().toUri();
        try {
            ResponseEntity<InformationProvidersResponse> entityWrapper = restTemplate.
                    exchange(uri, HttpMethod.GET, new HttpEntity<>(createHeaders()), InformationProvidersResponse.class);
            return entityWrapper.getBody();

        } catch (HttpClientErrorException ex) {
            tryFetchErrorAndConvertToException(ex);
            return null;        }
    }


    private void tryFetchErrorAndConvertToException(HttpClientErrorException ex){
        try {
            ErrorResponse errorResponse = tryFetchingErrorResponse(ex.getResponseBodyAsString());
            convertErrorResponseToException(errorResponse, ex.getStatusCode());
        } catch (IOException e) {
            throw new SurveyClientException(ex.getMessage(), null,ex.getStatusCode());
        }
    }

    private ErrorResponse tryFetchingErrorResponse(String response) throws IOException {
        return mapper.readValue(response,ErrorResponse.class);
    }
    private void convertErrorResponseToException(ErrorResponse errorResponse, HttpStatus status){
        throw  new SurveyClientException(errorResponse.getMessage(),errorResponse.getError(),status);
    }

    private HttpHeaders createHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", authHeader);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
