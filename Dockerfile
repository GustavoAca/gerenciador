FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/gerenciador-${PROJECT_VERSION}.jar /app/gerenciador-1.0.0.jar

ENV JAVA_OPTS="-Xmx256m -Xms128m"

RUN adduser -D gerenciador
USER gerenciador

CMD ["java", "-jar", "gerenciador-1.0.0.jar", "--spring.profiles.active=prod", ">", "/app/app.log"]

RUN rm -rf /var/cache/apk/*


EXPOSE 8080