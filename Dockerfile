FROM gradle:8.7-jdk21 AS builder
WORKDIR /app

COPY --chown=gradle:gradle . .

RUN gradle build -x test

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

CMD ["sh", "-c", "java -Dserver.address=0.0.0.0 -Dserver.port=${PORT:-8080} -jar /app/app.jar"]
