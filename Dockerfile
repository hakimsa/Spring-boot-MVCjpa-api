# Employee Management App - Multi-stage Dockerfile
# Build arguments
ARG BUILD_DATE
ARG VCS_REF
ARG MAVEN_VERSION=3.9.4
ARG JDK_VERSION=11

# ===== STAGE 1: Build Dependencies =====
FROM maven:${MAVEN_VERSION}-openjdk-${JDK_VERSION}-slim AS dependencies

WORKDIR /build

# Copy only pom.xml first to leverage Docker layer caching
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Download dependencies
RUN chmod +x mvnw && \
    ./mvnw dependency:go-offline -B

# ===== STAGE 2: Build Application =====
FROM dependencies AS build

# Copy source code
COPY src src

# Build application
RUN ./mvnw clean package -DskipTests=true -B && \
    mkdir -p target/dependency && \
    (cd target/dependency; jar -xf ../*.jar)

# ===== STAGE 3: Security Scan =====
FROM build AS security

# Install security tools
RUN apt-get update && \
    apt-get install -y wget && \
    wget -O - https://github.com/aquasecurity/trivy/releases/latest/download/trivy_0.45.0_Linux-64bit.tar.gz | tar zxvf - -C /usr/local/bin

# Scan the built application
RUN trivy fs --exit-code 0 --severity HIGH,CRITICAL /build/target

# ===== STAGE 4: Runtime Image =====
FROM openjdk:${JDK_VERSION}-jre-slim AS runtime

# Build metadata
LABEL maintainer="your-team@company.com" \
      org.opencontainers.image.title="Employee Management App" \
      org.opencontainers.image.description="Spring Boot Employee Management System" \
      org.opencontainers.image.version="1.0.0" \
      org.opencontainers.image.created="${BUILD_DATE}" \
      org.opencontainers.image.revision="${VCS_REF}" \
      org.opencontainers.image.vendor="Your Organization" \
      org.opencontainers.image.licenses="MIT"

# Install security updates and required tools
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        curl \
        ca-certificates \
        tzdata && \
    apt-get upgrade -y && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Create non-root user
RUN groupadd -r app -g 1001 && \
    useradd -r -g app -u 1001 -m -c "Application User" -d /app -s /bin/bash app

# Set timezone
ENV TZ=UTC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Create application directory
WORKDIR /app

# Copy built application from build stage
COPY --from=build --chown=app:app /build/target/dependency/BOOT-INF/lib /app/lib
COPY --from=build --chown=app:app /build/target/dependency/META-INF /app/META-INF
COPY --from=build --chown=app:app /build/target/dependency/BOOT-INF/classes /app

# Copy additional files
COPY --chown=app:app docker-entrypoint.sh /app/
COPY --chown=app:app healthcheck.sh /app/

# Make scripts executable
RUN chmod +x /app/docker-entrypoint.sh /app/healthcheck.sh

# Switch to non-root user
USER app

# Expose port
EXPOSE 8080

# Environment variables
ENV JAVA_OPTS="-Xms256m -Xmx512m -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0" \
    SPRING_PROFILES_ACTIVE=production \
    SERVER_PORT=8080 \
    MANAGEMENT_SERVER_PORT=8081

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD /app/healthcheck.sh

# Use custom entrypoint
ENTRYPOINT ["/app/docker-entrypoint.sh"]

# Default command
CMD ["java", "-cp", "/app:/app/lib/*", "com.hakim.mangeempolye.MangeEmpolyeApplication"]