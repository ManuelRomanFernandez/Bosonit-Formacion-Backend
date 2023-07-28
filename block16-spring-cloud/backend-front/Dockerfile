FROM openjdk:17-jdk-alpine
COPY /target/backend-front-0.0.1-SNAPSHOT.jar /tmp/backend-front.jar
COPY backend-front.sh /tmp/
ENTRYPOINT "/tmp/backend-front.sh"