## Project Description
Promontory Mortgage Path Message Service

## The project structure
- mortgage-path-message-svcAPI
- env_files
- .gitignore
- build.gradle
- CHANGELOG.md
- docker-compose.yml
- Dockerfile
- gradle.properties
- README.md
- settings.gradle


## How to build the project
The recommended way will be to use "Gradle build tool" (https://gradle.org) to build the project.
Please use the gradle wrapper (invoke by running 'gradlew' in a bash)

## How to execute the project
- Inside of container: You need to have Docker installed,
it will create and start docker image
navigate to project directory and run in a bash : docker-compose up --build

- Inside of IDE: 
navigate to project directory and run in a bash : "./gradlew clean build bootRun" or "gradlew clean build bootRun"
  
- Run java jar:
build the project
navigate to project directory and run in a bash : "java -jar volumes/deployments/mortgage-path-message-svcAPI-0.0.1-SNAPSHOT.jar"


## How to run tests
- navigate to project directory and run in a bash : "gradlew clean test" or "gradlew clean build"
Test result report could be found in dir:
  mortgage-path-message-svc/mortgage-path-message-svcAPI/build/reports/tests/test/index.html

## Pointers of relevant code logic
1. Start point - MessageSvcSpringBootApplication class
2. MessageResource class - resource class
3. AbstractJaxRsResourceConfig class - jaxrs config
4. PromontoryMessageServiceApplication class - resource registration
5. mortgage-path-message-svcAPI/build.gradle - dependencies and gradle tasks  
6. application.properties file - application and services configuration
7. Dockerfile - file to create application docker image

## Available endpoints to consume:
Post - http://localhost:8080/messages/
Request sample:     
        {
        "message": "String"
        }
Response sample:
        {
        "digest": "b2ef230e7f4f315a28cdcc863028da31f7110f3209feb76e76fed0f37b3d8580"
        }
-------------------------------------------------------------------------------------
GET - http://localhost:8080/messages/b2ef230e7f4f315a28cdcc863028da31f7110f3209feb76e76fed0f37b3d8580
Response sample:
        {
        "digest": "String"
        }
-------------------------------------------------------------------------------------