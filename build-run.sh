#!/usr/bin/env bash

mvn clean install
docker build -t lenny/address-service:${1} .
docker stop address-service
docker rm address-service
docker run -d --name address-service -p 8083:8080 maria/address-service:${1}
