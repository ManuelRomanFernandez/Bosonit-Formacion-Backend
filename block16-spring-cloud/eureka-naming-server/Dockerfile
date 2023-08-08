FROM openjdk:17-jdk-alpine
COPY /target/eureka-naming-server-0.0.1-SNAPSHOT.jar /tmp/eureka.jar
COPY eureka.sh /tmp/
ENTRYPOINT "/tmp/eureka.sh"