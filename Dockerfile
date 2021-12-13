FROM openjdk:8-jdk-alpine
MAINTAINER gin
COPY uster-ws/target/uster-ws-0.0.1-SNAPSHOT.jar uster-service-1.0.0.jar
ENTRYPOINT ["java","-jar","/uster-service-1.0.0.jar"]
