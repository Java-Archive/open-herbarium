FROM openjdk:8-jdk-alpine

MAINTAINER Sven Ruppert <sven.ruppert@gmail.com>
LABEL Open Herbarium - Vaadin UI

USER root
WORKDIR /app

ENTRYPOINT java -jar fat.jar -Xmx128m -Dorg.rapidpm.microservice.security.shiro.active=false
