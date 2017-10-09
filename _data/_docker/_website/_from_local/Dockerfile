FROM openherbarium/component_hugo:latest
MAINTAINER Sven Ruppert <sven.ruppert@gmail.com>
LABEL Open Herbarium website with Hugo - step browse local

USER root

EXPOSE 1313
#mount source foleder into open-herbarium.github.io
# f.e. --mount type=bind,source="$(pwd)"/target,target=/app,readonly
#docker run --rm -ti -p1313:1313 --mount type=bind,source="$(pwd)",target=/app,readonly -t openherbarium/website_from_local
WORKDIR /app

ENTRYPOINT \
          hugo server --bind 0.0.0.0


