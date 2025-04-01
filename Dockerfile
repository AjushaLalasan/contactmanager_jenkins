FROM openjdk:17-jdk-slim
WORKDIR /apps
COPY target/contactmanager-0.0.1-SNAPSHOT.jar contactmanager.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "contactmanager.jar"]
