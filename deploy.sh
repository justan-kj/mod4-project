#!/bin/bash

docker stop mod4-app || true
docker rm mod4-app || true
docker pull afawafa/mod4-project-docker:latest
docker run -d --name mod4-app -p 8080:8080 afawafa/mod4-project-docker:latest
