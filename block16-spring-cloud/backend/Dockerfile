FROM openjdk:17-jdk-alpine
COPY /target/backend-0.0.1-SNAPSHOT.jar /tmp/docker.jar
COPY docker.sh /tmp/
ENTRYPOINT "/tmp/docker.sh"