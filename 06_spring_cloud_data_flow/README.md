# Spring Cloud Data Flow

This is part of Spring Cloud Data Flow tutorial

## Requirements
[Docker](https://www.docker.com/)

## Start SCDF
````bash
docker-compose -f .\docker-compose.yaml -f .\docker-compose-kafka.yaml -f .\docker-compose-mariadb.yaml -f .\docker-compose-akhq.yaml up -d
````
## Build and Start Sales Generator
````Bash
.\sales-generator\gradlew build
.\sales-generator\gradlew docker
docker-compose -f .\docker-compose-sales-generator.yaml up -d
````

## Shutdown All
````
docker-compose down
````


## Access to User Interfaces
[Spring Cloud Data Flow Dashboard](http://localhost:9393/dashboard/index.html)

[AKHK](http://localhost:8080)
