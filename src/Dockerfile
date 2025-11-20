# Etapa 1: compilar con Maven
FROM maven:3.8.6-openjdk-11 AS builder

WORKDIR /app

# Copiar pom.xml y descargar dependencias (cachea para builds futuros)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código y compilar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: imagen ligera con JDK/JRE
FROM openjdk:11-jdk-slim

WORKDIR /app

# Copiar el JAR desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Puerto expuesto (ajusta si tu app usa otro)
EXPOSE 8089

# Comando para arrancar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
