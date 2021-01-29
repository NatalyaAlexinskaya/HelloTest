FROM openjdk:8-jre-alpine
WORKDIR /home/test
COPY target/Rest-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]