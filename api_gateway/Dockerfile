# Use the builder stage from Dockerfile-build
FROM maven-build:local AS build

# Step 2: Use minimal Java runtime
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=build /build/api_gateway/target/api_gateway-0.0.1-SNAPSHOT.jar api_gateway.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "api_gateway.jar"]