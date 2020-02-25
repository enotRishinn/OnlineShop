FROM openjdk:8-jre-alpine
COPY ./eureka-application.jar /eureka-application.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "eureka-application.jar", "--spring.profiles.active=docker"]