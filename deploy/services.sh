#!/bin/bash
./cloud_sql_proxy -instances=mioblog:us-central1:mio-pg=tcp:5432 -credential_file=/credential.json &
java -Dspring.config.location=/external.properties -Dspring.profiles.active=DOCKER -jar /app.jar
