# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the application's jar to the container
COPY target/universal-pet-care-0.0.1-SNAPSHOT.jar app.jar

# Make port 9192 available to the world outside this container
EXPOSE 9192

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]