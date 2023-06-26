# Kafka by Command Line Interface

## What is needed
Have Docker on your system
Download apache Kafka 2.13-3.4.1 from [Apache Kafka downloads page](https://kafka.apache.org/downloads). Other version may work, adapt Dockerfile acordingly.

## Start Kafka servers
1. open a terminal on this folder
2. build the docker images
````bash
docker compose build
````
3. start Kafka
````bash
docker compose up -d
````

## Connect to container to interact with Kafka
It is provided a Docker container for anyone who wishs to interact with Kafka by means command line. This container is called **devcontainer**.
1. Find the container devcontainer
Run `docker container ls` and you'll get something like:
````bash
CONTAINER ID        IMAGE                                           COMMAND                  CREATED             STATUS                PORTS                                                      NAMES
62bb01e1a65f        tchiotludo/akhq                                 "docker-entrypoint.s…"   2 days ago          Up 2 days (healthy)   0.0.0.0:8080->8080/tcp                                     kafka_cli-akhq-1
7a962b6c485d        bitnami/kafka:3.4                               "/opt/bitnami/script…"   2 days ago          Up 2 days             0.0.0.0:9092->9092/tcp                                     kafka_cli-broker-1
1d6ca1327be0        kafka_cli-devcontainer                          "tail -f /dev/null"      2 days ago          Up 2 days                                                                        kafka_cli-devcontainer-1
26cec401c717        bitnami/zookeeper:3.8                           "/opt/bitnami/script…"   2 days ago          Up 2 days             2888/tcp, 3888/tcp, 0.0.0.0:2181->2181/tcp, 8080/tcp       kafka_cli-zookeeper-1
3800f0d8838d        cr.portainer.io/portainer/portainer-ce:latest   "/portainer"             17 months ago       Up 6 days             0.0.0.0:8000->8000/tcp, 0.0.0.0:9443->9443/tcp, 9000/tcp   portainer

````
The container of interest is named **kafka_cli-devcontainer-1**.

2. Create a bash session on the **kafka_cli-devcontainer-1** 
````bash
docker container exec -it kafka_cli-devcontainer-1 /bin/bash
````

## Kafka's Gui
There's also a way to interact with Kafka by means of a GUI. 
1. Open a browser
2. Go to `http://localhost:8080`
3. A page is rendered