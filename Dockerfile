FROM openjdk:8
EXPOSE 9101
COPY target/uster-ws-0.0.1-SNAPSHOT.jar /uster-ws-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/uster_service.jar"]
