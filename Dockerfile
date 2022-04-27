FROM  maven:3.5-alpine as builder

COPY  ./  /app

RUN cd /app && mvn package
FROM openjdk:8-alpine
COPY --from=builder target/mange-empolye-0.0.1-SNAPSHOT.jar  /otp/app/employ.jar
