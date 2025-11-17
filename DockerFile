# Imagen base
FROM eclipse-temurin:21-jdk

# Directorio de la app dentro del contenedor
WORKDIR /app

# Copiar el JAR generado por Gradle
COPY build/libs/pokemon-api-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto que usa Spring Boot
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java","-jar","app.jar"]
