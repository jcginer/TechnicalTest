FROM openjdk:8
EXPOSE 9101
ADD ./uster-ws-0.0.1-SNAPSHOT.jar /uster_service.jar
ENTRYPOINT ["java", "-jar", "/uster_service.jar"]
