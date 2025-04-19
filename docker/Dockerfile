# Etapa 1: Build
FROM eclipse-temurin:21-jdk-alpine AS builder
LABEL authors="Andre"

WORKDIR /build

COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle

COPY src/ src/

RUN chmod +x gradlew
RUN ./gradlew build -x test

FROM alpine/java:21-jre

WORKDIR /app

COPY --from=builder /build/build/libs/JIp-0.0.1-SNAPSHOT.jar api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "api.jar"]
