# Stage 1: Build the application
FROM eclipse-temurin:17-jdk-focal AS build
WORKDIR /app

# Copy the gradle executable and configuration
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradle.properties .
COPY gradle/libs.versions.toml gradle/

# Give execution permission to gradlew
RUN chmod +x gradlew

# Download dependencies (this layer will be cached)
RUN ./gradlew --no-daemon dependencies

# Copy the source code and build the fat jar
COPY src src
RUN ./gradlew buildFatJar --no-daemon

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-focal
WORKDIR /app

# Copy the fat jar from the build stage
COPY --from=build /app/build/libs/*-all.jar quizSphere.jar

# Expose the port the app runs on (default is 8080, but can be overridden by PORT env var)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "quizSphere.jar"]
