FROM openjdk:8-jdk-alpine
MAINTAINER gin
ADD uster-ws/target/uster-ws*.jar uster-service-1.0.0.jar
ENTRYPOINT ["java","-jar","/uster-service-1.0.0.jar"]
