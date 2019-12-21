FROM alpine/git as hugo
MAINTAINER Sven Ruppert <sven.ruppert@gmail.com>
LABEL Open Herbarium website with Hugo - show develop branch

USER root

ENV HUGO_VERSION 0.29
ENV HUGO_BINARY hugo_${HUGO_VERSION}_linux-64bit

RUN apk update && apk add py-pygments && apk add bash && rm -rf /var/cache/apk/*

# Download and Install hugo
RUN mkdir /usr/local/hugo
ADD https://github.com/gohugoio/hugo/releases/download/v${HUGO_VERSION}/${HUGO_BINARY}.tar.gz /usr/local/hugo/
RUN tar xzf /usr/local/hugo/${HUGO_BINARY}.tar.gz -C /usr/local/hugo/ \
	&& ln -s /usr/local/hugo/hugo /usr/local/bin/hugo \
	&& rm /usr/local/hugo/${HUGO_BINARY}.tar.gz

#RUN git clone -b develop https://github.com/open-herbarium/open-herbarium.github.io.git

WORKDIR /app

EXPOSE 1313
ENTRYPOINT \
    git clone --depth=1 -b develop https://github.com/open-herbarium/open-herbarium.github.io.git && \
    cd /app/open-herbarium.github.io && \
    hugo server --bind 0.0.0.0

