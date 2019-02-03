FROM java:8
EXPOSE 80
RUN apt-get update && apt-get install -y wget && rm -rf /var/lib/apt/lists/* && wget https://dl.google.com/cloudsql/cloud_sql_proxy.linux.amd64 -O cloud_sql_proxy && chmod +x cloud_sql_proxy
ADD  msandeep.io-0.0.1-SNAPSHOT.jar app.jar
ADD  external.properties external.properties
ENV  -Dspring.profiles.active DOCKER
ADD  services.sh services.sh
ADD  credential.json credential.json
RUN  chmod +x services.sh
ENTRYPOINT ./services.sh
