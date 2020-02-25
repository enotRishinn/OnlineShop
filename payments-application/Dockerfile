FROM openjdk:8-jre-alpine
COPY ./payments-application/build/libs/payments-application-0.1-SNAPSHOT.jar /payments-application-0.1-SNAPSHOT.jar
EXPOSE 9002
ENTRYPOINT ["java", "-jar", "payments-application-0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]
