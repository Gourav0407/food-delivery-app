# ==========================================
# STAGE 1: Build & Compile (Maven + JDK)
# ==========================================
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app

# Copy dependency definition first to leverage Docker layer caching
COPY pom.xml .
COPY src ./src

# Build the executable JAR without running test suites
RUN mvn clean package -DskipTests

# ==========================================
# STAGE 2: Lightweight Production Image (JRE only)
# ==========================================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy only the compiled JAR from Stage 1
COPY --from=build /app/target/*.jar zomato-copy.jar

# Expose Spring Boot's default port
EXPOSE 8080

# Execute the application
ENTRYPOINT ["java", "-jar", "zomato-copy.jar"]