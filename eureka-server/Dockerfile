# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the fat jar into the container at /app
COPY target/eureka-server-1.0-SNAPSHOT.jar /app/eureka-server.jar

# Make port 8761 available to the world outside this container
EXPOSE 8761

# Run the jar file
CMD ["java", "-jar", "eureka-server.jar", "server", "config.yml"]
