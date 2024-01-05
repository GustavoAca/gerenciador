FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/gerenciador-1.0.0.jar /app/gerenciador-1.0.0.jar

EXPOSE 8080