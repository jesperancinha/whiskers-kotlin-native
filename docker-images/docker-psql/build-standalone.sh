#!/usr/bin/env bash

docker-compose down

docker stop postgres-standalone

docker rm postgres-standalone

docker build . -t postgres-image

docker run --name postgres-standalone -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=admin -e POSTGRES_MULTIPLE_DATABASES=vsa -p 5432:5432 -d postgres-image
