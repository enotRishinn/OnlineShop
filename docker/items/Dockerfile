FROM openjdk:8-jre-alpine
COPY ./items-application.jar /items-application.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "items-application.jar", "--spring.profiles.active=docker"]