FROM openjdk:8-jre-alpine
COPY ./items-application/build/libs/items-application-0.1-SNAPSHOT.jar /items-application-0.1-SNAPSHOT.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "items-application-0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]