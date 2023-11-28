# Use the official OpenJDK 17 as a parent image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory to /app
WORKDIR /app

COPY src/main/resources/application.yml /app/application.yml

# Copy the JAR file into the container at /app
COPY target/gerenciador-0.0.1-SNAPSHOT.jar /app/gerenciador-0.0.1-SNAPSHOT.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "gerenciador-0.0.1-SNAPSHOT.jar"]
