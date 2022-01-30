FROM openjdk:11
ARG JAR_FILE=target/graduation-project-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} graduation-project.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "graduation-project.jar"]