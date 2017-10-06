# Dockerfile for the website open-herbarium.org

THIS REPO MUST BE SHIFTED TO GITHUB

## how to use
use this directory as working directory.
build the image with the following command


```
docker build -t hugo .
```
With this command you will create an Image that will be able to serve
the lates website version from the git repo and master branch

```docker
FROM ubuntu:latest

MAINTAINER Sven Ruppert <sven.ruppert@gmail.com>
LABEL Open Herbarium website with Hugo
USER root
RUN \
  apt-get update && \
  apt-get -y upgrade && \
  apt-get -y install hugo && \
  apt-get -y install git

#RUN \
#  apt-get -y install hugo && \
#  apt-get -y install git

# Set environment variables.
ENV HOME /root

# Define working directory.
WORKDIR /root

# Define default command.
CMD ["bash"]

#hugo server port
EXPOSE 1313

RUN git clone https://username:password@git.loe.auf.uni-rostock.de/bheeren/open-herbarium-homepage.git

CMD ["git" , "pull", "https://username:password@git.loe.auf.uni-rostock.de/bheeren/open-herbarium-homepage.git" ]

WORKDIR /root/open-herbarium-homepage

ENTRYPOINT hugo server --bind 0.0.0.0
```

With the following docker command you can run the 
image.

```
docker run --rm -ti -p 1313:1313 --name hugo_openherbarium -i -t hugo
```

This will create a container for one run, exposes the port 1313 to server thw website.
After you killed the server process inside the opened terminal from the docker container
the server and container will disappear.

if you want to delete the image as well, use the following command.

```
docker image rm hugo
```