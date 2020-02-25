FROM openjdk:8-jre-alpine
COPY ./orders-application/build/libs/orders-application-0.1-SNAPSHOT.jar /orders-application-0.1-SNAPSHOT.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "orders-application-0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]