# Dockerfile for the website open-herbarium.org

## use images
```
docker run --rm -ti -p 1313:1313 -t openherbarium/website_from_develop
docker run --rm -ti -p 1313:1313 --mount type=bind,source="$(pwd)",target=/app/,readonly -t openherbarium/website_from_local
```




## create images
Go to the relative directory **_data/_docker/*** 
and start the following commands to build the images by yourself

```
docker build -t openherbarium/component_hugo   ./_component/hugo/ 
docker build -t openherbarium/website_from_develop  ./_website/_from_git_develop/ 
docker build -t openherbarium/website_from_local  ./_website/_from_local

```


## tag images
If you want to push the images to your own registry after you modified them.
Tag the images first like shown in the following listing.

```
docker tag openherbarium/component_hugo   openherbarium/component_hugo :0001
docker tag openherbarium/website_from_develop  openherbarium/website_from_develop:0001
docker tag openherbarium/website_from_local  openherbarium/website_from_local:0001
docker tag openherbarium/component_microkernel  openherbarium/component_microkernel:0001
```

## push images

```
docker push openherbarium/component_hugo 
docker push openherbarium/website_from_develop
docker push openherbarium/website_from_local
docker push openherbarium/component_microkernel
```


## clean images
```
docker image prune

```

## login into a registry

```
export DOCKER_ID_USER="username
docker login
```




