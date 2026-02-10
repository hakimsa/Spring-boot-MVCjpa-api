# Etapa 1: build con Maven
FROM maven:3.9.2-eclipse-temurin-11 AS builder
WORKDIR /app

# Copiar pom.xml y src
COPY pom.xml .
COPY src ./src

# Build del jar
RUN mvn clean package -DskipTests

# Etapa 2: imagen final ligera con JRE
FROM eclipse-temurin:11-jre-jammy
WORKDIR /app

# Copiar el jar desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Puerto expuesto
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java","-jar","app.jar"]
