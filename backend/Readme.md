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
##DB The backend has a Scheduled task (SubscriptionScheduledTasks.java running every 24hours) which will do as follows:
1. Check all the subscriptions.
2. Find out if the subscription should be sent now (depending on the frequency and the last date sent previously).
3. In affirmative case, perform the request the information requester has been subscribed for.
4. Send the response from the request to the iformation requester through the desired channel. Note that this part is not fully implemented: There are several classes implementing the interface ChannelSenderProvider, buthe implementation is missing. However, is a prove of concept.Structure
Every `information-requester` has some roles and every `information provider` can be accessed through some roles. So, not all `information-requesters` can access all `information providers`.

At the same time, each `survey` belong to an `information provider`.

For a more detailed informatino about the DB structure and the relationships, check here:
![DB design](https://github.com/umbreak/spring-boot-example/raw/master/backend/database_schema.png)

The database is created every time the backend is started (and dropped every time too, just for test purposes). The content can be found in the file `schema-h2.sql` and `data.sql`.

###DB Connection (H2 console)
To connect to the DB console go to the url `http://localhost:8082/` and set the field `JDBC URL:` to `jdbc:h2:mem:dataSource`

##Subscription to survey's targets
The `information-requester`, when performing a search query to the API on a specific survey and filtered for specific target, can point out that he(she wants to be subscribed for that query. In this case, the backend would have to report to the `information-requester` with this information through the channel chosen by the information-requester.

The backend has a Scheduled task (SubscriptionScheduledTasks.java running every 24hours) which will do as follows:

1. Check all the subscriptions.
2. Find out if the subscription should be sent now (depending on the frequency and the last date sent previously).
3. In affirmative case, perform the request the information requester has been subscribed for.
4. Send the response to the iformation requester through the desired channel. Note that this part is not fully implemented: There are several classes implementing the interface ChannelSenderProvider, buthe implementation is missing. However, is a prove of concept.
