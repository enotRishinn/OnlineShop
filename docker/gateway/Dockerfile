FROM openjdk:8-jre-alpine
COPY ./api-gateway.jar /api-gateway.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "api-gateway.jar", "--spring.profiles.active=docker"]