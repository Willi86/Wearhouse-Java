# Build the Application with Maven
FROM maven:3.9.9-eclipse-temurin-22 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the source code into the container
COPY . .

# Build the application
RUN mvn clean package && ls -al /app/target


# Deploy the Application
FROM quay.io/wildfly/wildfly:wildfly-33.0.2.Final AS runtime

# Copy the .war file from the build stage into the WildFly deployments directory
COPY --from=build /app/target/warehouse-app-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/

# Expose the default WildFly port (8080)
EXPOSE 8080

# Start WildFly when the container starts
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
