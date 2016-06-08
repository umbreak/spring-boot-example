# Information-requester (API Client)

It exposes the two methods that the API provides

```java
MarketSurveysResponse findSurveys(MarketSurveysRequest request);
InformationProvidersResponse listProviders();
```

##Examples

```java
 //provider requested
        Identifier provider=new Identifier(1, "Provider A");

        //survey filter
        Survey.Request surveyRequest=new Survey.Request.Builder(
                new Target.Request.Builder().ageRange(40,60).gender(Gender.Male).build()
                ,"science").build();

        MarketSurveysRequest request=new MarketSurveysRequest.Builder(
                provider,surveyRequest).build();

        MarketSurveysResponse response = backend.findSurveys(request);
```
