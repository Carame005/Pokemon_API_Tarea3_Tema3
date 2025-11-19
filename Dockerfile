# Etapa 1: Construcción con Gradle
FROM gradle:8.7-jdk21 AS builder
WORKDIR /app

COPY . .
RUN gradle build -x test

# Etapa 2: Imagen final ligera
FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=builder /app/build/libs/*SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
