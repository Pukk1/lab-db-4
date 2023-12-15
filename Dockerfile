FROM openjdk:17
COPY target/lab-db-4-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]
