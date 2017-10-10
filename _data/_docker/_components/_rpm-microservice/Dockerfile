FROM openjdk:8-jdk-alpine

MAINTAINER Sven Ruppert <sven.ruppert@gmail.com>
LABEL RapidPM Microservice

USER root
WORKDIR /app

EXPOSE 7080
EXPOSE 7081

ENTRYPOINT java -jar fat.jar -Xmx${XMX} -Dorg.rapidpm.microservice.security.shiro.active=false
