# Backend
REST backend with Basic Auth and 2 resources:

##Resources
In local, to access the resource use a base url = http://localhost:8181

| URL     | METHOD | REQUEST BODY | RESPONSE   |
| :------- | ----: | ----: | :---: |
| /survey/searches | POST | [Request Object](https://github.com/umbreak/spring-boot-example/blob/master/models/src/main/java/model/MarketSurveysRequest.java) |  [Response Object](https://github.com/umbreak/spring-boot-example/blob/master/models/src/main/java/model/MarketSurveysResponse.java)    |
| /provider | GET |  |  [Response Object](https://github.com/umbreak/spring-boot-example/blob/master/models/src/main/java/model/InformationProvidersResponse.java)    |

##Curl calls example:

```
curl -u 'user1:secret1' -H "Content-Type: application/json" -X POST -d '{"provider":{"id":1,"name":"Provider A"},"survey":{"subject":"science","target":{"gender":"Male","age":[40,60]}}}' "http://localhost:8181/survey/searches"
```
```
curl -u 'user2:secret2' -H "Content-Type: application/json" -X POST -d '{"provider":{"id":1,"name":"Provider A"},"survey":{"subject":"stud","target":{"income": {"currency": "EUR","range":[1500,3000]}}}, "subscription": {"channel": "FTP", "frequency": "Dayly"}}' "http://localhost:8181/survey/searches"
```
```
curl -u 'user3:secret3' -X GET "http://localhost:8181/provider"
```
##DB Structure
Every `information-requester` has some roles and every `information provider` can be accessed through some roles. So, not all `information-requesters` can access all `information providers`.

At the same time, each `survey` belong to an `information provider`.

For a more detailed informatino about the DB structure and the relationships, check here:
![DB design](https://github.com/umbreak/spring-boot-example/raw/master/backend/database_schema.png)

The database is created every time the backend is started (and dropped every time too, just for test purposes). The content can be found in the file `schema-h2.sql` and `data.sql`.
