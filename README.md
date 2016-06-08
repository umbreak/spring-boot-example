# spring-boot-example
Spring boot sample with JPA (in embedded H2 DB) and Spring Security with Basic Auth

##Description & identification of the involved parts
Application which simulates an API to an aggregator of `market surveys providers`. Each of those information providers contains a list of surveys and each `survey` is done to a particular `target` (person).

With this API the `informatoin-requester` (authenticated client) can obtain information about surveys according some filtering capabilities. 

##Install

```
mvn clean install
```

##Run the backend

```
cd backend
mvn spring-boot:run
```

Further explanation of each module:
* [Backend & DB structure](https://github.com/umbreak/spring-boot-example/tree/master/backend)
* [Information Requester (Client)](https://github.com/umbreak/spring-boot-example/tree/master/information-requester)
