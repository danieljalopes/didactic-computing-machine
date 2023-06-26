# Kafka 101 - SPRING to Produce and Consume Kafka messages

## What is needed
Have Docker on your system
Download apache Kafka 2.13-3.4.1 from [Apache Kafka downloads page](https://kafka.apache.org/downloads). Other version may work, adapt Dockerfile acordingly.

## Start Kafka cluster
On a command terminal execute
````bash
# builds Dockerfile needed for this cluster to 
docker compose build

# starts the cluster
docker compose up -d
````
## Start Producer
This a Spring Boot application so look for java class `ProducerApplication.java` and it will start to produce messages.

## Start Consumer
This a Spring Boot application so look for java class `ProducerApplication.java` and it will start to consume messages.

## Kafka's Gui
There's also a way to interact with Kafka by means of a GUI. 
1. Open a browser
2. Go to `http://localhost:8080`
3. A page is rendered

References
https://www.youtube.com/watch?v=L--VuzFiYrM
https://rmoff.net/2018/08/02/kafka-listeners-explained/