FROM eclipse-temurin:17-jdk-alpine
COPY target/bulbeniback-0.0.1-SNAPSHOT.jar bulbeniback.jar
ENTRYPOINT ["java","-jar","/bulbeniback.jar"]
EXPOSE 8080

