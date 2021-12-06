FROM openjdk:8
EXPOSE 8080
ADD uster-ws\target\uster-ws*.jar uster-ws.jar
ENTRYPOINT ["java", "-jar", "/uster-ws.jar"]
