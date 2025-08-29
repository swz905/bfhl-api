FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the Java source file
COPY SimpleBfhlServer.java .

# Compile the Java application
RUN javac SimpleBfhlServer.java

# Expose port 8080
EXPOSE 8080

# Set environment variable for port (Render will override this)
ENV PORT=8080

# Run the application
CMD ["java", "SimpleBfhlServer"]
