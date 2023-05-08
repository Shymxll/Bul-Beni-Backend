#create docker file for the image java 17 and copy the jar file to the image and run the jar file when the container is created
FROM openjdk:17
COPY target/bulbeniback-0.0.1-SNAPSHOT.jar employee-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/bulbeniback-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
