FROM openjdk:17-jdk-alpine
COPY /target/api-gateway-0.0.1-SNAPSHOT.jar /tmp/gatewary-service.jar
COPY api-gateway.sh /tmp/
ENTRYPOINT "/tmp/api-gateway.sh"