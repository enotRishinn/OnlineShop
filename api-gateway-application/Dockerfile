FROM openjdk:8-jre-alpine
COPY ./build/libs/api-gateway-application-0.1-SNAPSHOT.jar /api-gateway-application-0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "api-gateway-application-0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]