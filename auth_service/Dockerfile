# Use a Maven image for building
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory for the build process
WORKDIR /build

# Copy the parent POM into the Docker container
COPY ./pom.xml ./pom.xml

# Copy the current module's directory into the expected location
COPY ./auth_service ./auth_service

# Resolve all parent and module dependencies
RUN mvn -f ./pom.xml dependency:go-offline -DskipTests

# Build the current module
RUN mvn -f ./auth_service/pom.xml clean package -DskipTests

# Use a minimal Java image to run the service
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /build/auth_service/target/auth_service-0.0.1-SNAPSHOT.jar auth_service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "auth_service.jar"]
