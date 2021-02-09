FROM openjdk:8-jre-alpine
WORKDIR /home/test
COPY target/Kafka-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]