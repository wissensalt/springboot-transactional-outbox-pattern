FROM openjdk:8-alpine3.9

ADD target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]