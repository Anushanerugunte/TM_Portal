# Use a base image with Java 11 installed
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/SpringBootEmailExamples-1.0.jar app.jar

# Copy the image file into the container
COPY src/main/resources/brthday.jpg brthday.jpg
COPY src/main/resources/Msyslogo.jpg Msyslogo.jpg
COPY src/main/resources/anniversary.jpg anniversary.jpg

# Expose the port on which your Spring Boot application will run (adjust as needed)
EXPOSE 8080

# Define the entry point to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
