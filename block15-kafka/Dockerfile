FROM openjdk:17-jdk-alpine
ENV TOPICNAME MyTopic1
ENV TOPICNAME2 MyTopic2
ENV GROUPID GrupoBosonit
COPY /target/block15-kafka-0.0.1-SNAPSHOT.jar /tmp/kafkatest.jar
COPY app.sh /tmp/
ENTRYPOINT "/tmp/app.sh"