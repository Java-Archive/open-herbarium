FROM openjdk:8-jdk-alpine

MAINTAINER Sven Ruppert <sven.ruppert@gmail.com>
LABEL Open Herbarium - ImageService

USER root
WORKDIR /app

#COPY ../../../target/herbarium-m-app-srv-metadatasrv-rest-endpoint-0.0.1-SNAPSHOT.jar /app/fat.jar

#EXPOSE 7080
#EXPOSE 7081

ENTRYPOINT java -jar fat.jar -Xmx128m -Dorg.rapidpm.microservice.security.shiro.active=false
