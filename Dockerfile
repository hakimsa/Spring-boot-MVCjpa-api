

FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar archivos de Maven Wrapper
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Dar permisos de ejecución
RUN chmod +x ./mvnw

# Descargar dependencias (sin especificar ruta .m2)
RUN ./mvnw dependency:go-offline -B

# Copiar código fuente
COPY src ./src

# Compilar aplicación
RUN ./mvnw clean package -DskipTests

# Exponer puerto
EXPOSE 8080

# Ejecutar aplicación
CMD ["java", "-jar", "target/*.jar"]