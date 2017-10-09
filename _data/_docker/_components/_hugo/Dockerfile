FROM alpine:latest
MAINTAINER Sven Ruppert <sven.ruppert@gmail.com>
LABEL Open Herbarium website with Hugo - step install hugo

USER root
WORKDIR /app

ENV HUGO_VERSION 0.29
ENV HUGO_BINARY hugo_${HUGO_VERSION}_linux-64bit

RUN apk update && apk add py-pygments && apk add bash && rm -rf /var/cache/apk/*

# Download and Install hugo
RUN mkdir /usr/local/hugo
ADD https://github.com/gohugoio/hugo/releases/download/v${HUGO_VERSION}/${HUGO_BINARY}.tar.gz /usr/local/hugo/
RUN tar xzf /usr/local/hugo/${HUGO_BINARY}.tar.gz -C /usr/local/hugo/ \
	&& ln -s /usr/local/hugo/hugo /usr/local/bin/hugo \
	&& rm /usr/local/hugo/${HUGO_BINARY}.tar.gz
EXPOSE 1313
