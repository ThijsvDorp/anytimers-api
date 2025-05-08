# Use a specific version of Gradle and JDK for the build stage
FROM gradle:8-jdk17 AS build

# Set the working directory
WORKDIR /home/gradle/src

# Copy only the necessary files to leverage caching
COPY --chown=gradle:gradle build.gradle settings.gradle /home/gradle/src/
COPY --chown=gradle:gradle src /home/gradle/src/src

# Build the project, skipping tests
RUN gradle build --no-daemon -x test

# Use a smaller base image for the final stage
FROM openjdk:17-slim

# Expose the application port
EXPOSE 8080

# Create the application directory
RUN mkdir /app

# Copy the built JAR file from the build stage
COPY --from=build /home/gradle/src/build/libs/*SNAPSHOT.jar /app/anytimers.jar

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "/app/anytimers.jar"]