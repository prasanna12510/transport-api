# Spring Boot Application for Transport-API Portal
transport-api using spring boot
automated deployment of infrastructure (networking,monitoring,config management) using Terraform   

## Built With

* 	[Maven](https://maven.apache.org/) - Dependency Management
* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[Swagger](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.

## External Tools Used

* [Postman](https://www.getpostman.com/) - API Development Environment (Testing Documentation)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.cloudsre.services.transport_service` class from your IDE.

- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Eclipse
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
build and test with docker locally
```shell
mvn dockerfile:build install
```

### Actuator

To monitor and manage your application

|  URL |  Method |
|----------|--------------|
|`http://localhost:8080`  						         | GET |
|`http://localhost:8080/actuator/`             | GET |
|`http://localhost:8080/actuator/health`       | GET |
|`http://localhost:8080/actuator/info`      	 | GET |
|`http://localhost:8080/actuator/prometheus`   | GET |
|`http://localhost:8080/actuator/httptrace`    | GET |


### Transport API URLs

|  URL |  Method | Remarks |
|----------|--------------|--------------|
|`http://localhost:8081/city-navigation/v1/health`                           | GET |
|`http://localhost:8081/city-navigation/v1/lines`                            | GET |
|`http://localhost:8081/city-navigation/v1/check-delay`                      | GET |
|`http://localhost:8081/city-navigation/v1/next-line`                        | GET |

#### Find a vehicle for a given time and X & Y coordinates
![Alt text](https://github.com/prasanna12510/transport-api/blob/master/doc/img/getLines.png?raw=true "getLines")

#### Return the vehicle arriving next at a given stop
![Alt text](https://github.com/prasanna12510/transport-api/blob/master/doc/img/getNextArrival.png?raw=true "getNextArrival")

#### Indicate if a given line is currently delayed
![Alt text](https://github.com/prasanna12510/transport-api/blob/master/doc/img/getDelay.png?raw=true "getDelay")

### Transport API Deployed URL
https://transport-api.mywebapplication.ml/city-navigation/v1/health

## Documentation
* [Swagger](http://localhost:8081/swagger-ui.html) - Documentation & Testing

## packages

- `application` — to communicate with the database;
- `domain` — to hold our entities;
- `domain.initializers` — to load data from csv files to entities;
- `domain.infrastructure` — repository
- `exception` — security configuration;
- `presentation` — to hold our business logic;
- `utils` — helper functions;

- `resources/` - Contains all the static resources, templates and property files.
- `resources/application.properties` - It contains application-wide properties. Spring reads the properties defined in this file to configure your application. You can define server’s default port, server’s context path, database URLs etc, in this file.


- `pom.xml` - contains all the project dependencies
