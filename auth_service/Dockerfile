# Use the builder stage from Dockerfile-build
FROM maven-build:local AS build

# Step 2: Use minimal Java runtime
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=build /build/auth_service/target/auth_service-0.0.1-SNAPSHOT.jar auth_service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "auth_service.jar"]