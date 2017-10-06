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

RUN git clone https://sven.ruppert%40gmail.com:geheim88@git.loe.auf.uni-rostock.de/bheeren/open-herbarium-homepage.git

CMD ["git" , "pull", "https://sven.ruppert%40gmail.com:geheim88@git.loe.auf.uni-rostock.de/bheeren/open-herbarium-homepage.git" ]

WORKDIR /root/open-herbarium-homepage

ENTRYPOINT hugo server --bind 0.0.0.0