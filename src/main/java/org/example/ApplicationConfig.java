package org.example;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")  // Sets the base path for your REST endpoints
public class ApplicationConfig extends Application {
}
