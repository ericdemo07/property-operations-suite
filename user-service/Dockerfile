# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the fat jar into the container at /app
COPY target/user-service-1.0-SNAPSHOT.jar /app/user-service.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "user-service.jar", "server", "config.yml"]
