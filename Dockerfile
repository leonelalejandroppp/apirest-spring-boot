# Usa una imagen de Maven para construir la aplicación
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# Copia solo el archivo POM para descargar las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia el código fuente y realiza la construcción del JAR
COPY src src
RUN mvn package -DskipTests

# Usa una imagen más ligera de OpenJDK para ejecutar la aplicación
#FROM openjdk:17-jdk-alpine
#si no funciona la imagen, ocupar la de arriba
FROM thingsboard/openjdk17:bookworm-slim
WORKDIR /app

# Copia solo el archivo JAR construido
COPY --from=build /app/target/apirest-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que se ejecuta la aplicación
EXPOSE 8181

# Comando para ejecutar la aplicación cuando se inicia el contenedor
CMD ["java", "-jar", "app.jar"]