# Step 1: Use an official Java runtime as a parent image
FROM alpine/java:21-jdk

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the JAR file into the container
COPY target/flightreservation-0.0.1-SNAPSHOT.jar /app/flightreservation-0.0.1-SNAPSHOT.jar

# Step 4: Run the application when the container launches
ENTRYPOINT ["java", "-jar", "/app/flightreservation-0.0.1-SNAPSHOT.jar"]

