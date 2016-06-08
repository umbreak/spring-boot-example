# Backend
REST backend with Basic Auth and 2 resources:

##Resources
In local, to access the resource use a base url = http://localhost:8181

| URL     | METHOD | REQUEST BODY | RESPONSE   |
| :------- | ----: | ----: | :---: |
| /survey/searches | POST | [Request Object](https://github.com/umbreak/spring-boot-example/blob/master/models/src/main/java/model/MarketSurveysRequest.java) |  [Response Object](https://github.com/umbreak/spring-boot-example/blob/master/models/src/main/java/model/MarketSurveysResponse.java)    |
| /provider | GET |  |  [Response Object](https://github.com/umbreak/spring-boot-example/blob/master/models/src/main/java/model/InformationProvidersResponse.java)    |

##DB Structure
