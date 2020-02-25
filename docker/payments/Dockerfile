FROM openjdk:8-jre-alpine
COPY ./payments-application.jar /payments-application.jar
EXPOSE 9002
ENTRYPOINT ["java", "-jar", "payments-application.jar", "--spring.profiles.active=docker"]