# Use an official OpenJDK runtime as a parent image
FROM openjdk:21

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file to the container
COPY target/flightreservation-0.0.1-SNAPSHOT.jar /app/flightreservation-0.0.1-SNAPSHOT.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app/flightreservation-0.0.1-SNAPSHOT.jar"]