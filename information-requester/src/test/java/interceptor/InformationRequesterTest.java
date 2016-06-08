package interceptor;

import com.neovisionaries.i18n.CurrencyCode;
import connector.InformationRequester;
import connector.RestTemplateFactory;
import connector.SurveyClientException;
import model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


//Note: This test will run only when the backend it is running!!
//That is why by default I set it to Ignore
@Ignore
public class InformationRequesterTest {

    private final String host = "localhost";
    private final String user = "user1";
    private final String pass = "secret1";
    private final int port = 8181;
    private final boolean secure = false;

    private InformationRequester backend;

    @Before
    public void init(){
        backend=new RestTemplateFactory.Builder(host, user, pass).isSecure(secure).port(port).build();
    }

    @Test
    //curl -u 'user1:secret1' -H "Content-Type: application/json" -X POST -d '{"provider":{"id":1,"name":"Provider A"},"surveyer":"Male","age":[40,60]}}}' "http://localhost:8181/survey/searches"
    public void testSurveyRequest1() {
        //provider requested
        Identifier provider=new Identifier(1, "Provider A");

        //survey filter
        Survey.Request surveyRequest=new Survey.Request.Builder(
                new Target.Request.Builder().ageRange(40,60).gender(Gender.Male).build()
                ,"science").build();

        MarketSurveysRequest request=new MarketSurveysRequest.Builder(
                provider,surveyRequest).build();

        MarketSurveysResponse response = backend.findSurveys(request);
        Assert.assertEquals(response.getProvider().getId(), provider.getId());
        Assert.assertEquals(response.getProvider().getName(), provider.getName());

        for (Survey.Response survey : response.getSurvey()) {
            Assert.assertTrue(survey.getSubject().toLowerCase().contains("science"));
            for (Target.Response target : survey.getTargets()) {
                Assert.assertEquals(target.getGender(), Gender.Male);
                Assert.assertTrue(target.getAge() >= 40 && target.getAge() <= 60);
            }
        }

    }

    @Test
    public void testSurveyRequest3() {
        //provider requested
        Identifier provider=new Identifier(1, "Provider A");

        //survey filter
        Survey.Request surveyRequest=new Survey.Request.Builder(
                new Target.Request.Builder().incomeRange(CurrencyCode.EUR,1400,2000).build()
                ,"studies").build();

        MarketSurveysRequest request=new MarketSurveysRequest.Builder(
                provider,surveyRequest).build();

        MarketSurveysResponse response = backend.findSurveys(request);
        Assert.assertEquals(response.getProvider().getId(), provider.getId());
        Assert.assertEquals(response.getProvider().getName(), provider.getName());

        for (Survey.Response survey : response.getSurvey()) {
            Assert.assertTrue(survey.getSubject().toLowerCase().contains("studies"));
            for (Target.Response target : survey.getTargets()) {
                Assert.assertEquals(target.getIncome().getCurrency(), CurrencyCode.EUR);
                Assert.assertTrue(target.getIncome().getAmount() >= 1400 && target.getIncome().getAmount() <= 2000);
            }
        }

    }
    
    @Test
    //curl -u 'user1:secret1' -X GET "http://localhost:8181/provider"
    public void testProvidersList(){
        InformationProvidersResponse informationProvidersResponse = backend.listProviders();
        Assert.assertEquals(informationProvidersResponse.getProvider().size(),1);
        Identifier provider = informationProvidersResponse.getProvider().get(0);
        Assert.assertEquals(provider.getId(),1);
        Assert.assertEquals(provider.getName(),"Provider A");
    }

    @Test
    //curl -u 'user4:secret4' -X GET "http://localhost:8181/provider"
    public void testProvidersListOtherUser(){
        InformationRequester backend = new RestTemplateFactory.Builder(host, "user4", "secret4").isSecure(secure).port(port).build();

        InformationProvidersResponse informationProvidersResponse = backend.listProviders();
        Assert.assertEquals(informationProvidersResponse.getProvider().size(),3);
        for (Identifier identifier : informationProvidersResponse.getProvider()) {
            Assert.assertTrue(identifier.getName().equals("Provider A")
            || identifier.getName().equals("Provider B") || identifier.getName().equals("Provider C"));
            Assert.assertTrue(identifier.getId() == 1 || identifier.getId() == 2 || identifier.getId() == 3);
        }
    }

    @Test(expected = SurveyClientException.class)
    //This should crash because the provider 2 is not allowed for the role USER (which the suer 1 has)
    //curl -u 'user1:secret1' -H "Content-Type: application/json" -X POST -d '{"provider":{"id":2,"name":"Provider B"},"surveyer":"Male","age":[40,60]}}}' "http://localhost:8181/survey/searches"
    public void testSurveyRequest2() {
        //provider requested
        Identifier provider=new Identifier(2, "Provider B");

        //survey filter
        Survey.Request surveyRequest=new Survey.Request.Builder(
                new Target.Request.Builder().ageRange(40,60).gender(Gender.Male).build()
                ,"science").build();

        MarketSurveysRequest request=new MarketSurveysRequest.Builder(
                provider,surveyRequest).build();

        backend.findSurveys(request);

    }

    @Test(expected = SurveyClientException.class)
    public void wrongCredentails(){
        //provider requested
        Identifier provider=new Identifier(1, "Provider A");

        //survey filter
        Survey.Request surveyRequest=new Survey.Request.Builder(
                new Target.Request.Builder().ageRange(40,60).gender(Gender.Male).build()
                ,"science").build();

        MarketSurveysRequest request=new MarketSurveysRequest.Builder(
                provider,surveyRequest).build();

        InformationRequester backend = new RestTemplateFactory.Builder(host, user, "dsfddfdf").isSecure(secure).port(port).build();
        backend.findSurveys(request);

    }
}
