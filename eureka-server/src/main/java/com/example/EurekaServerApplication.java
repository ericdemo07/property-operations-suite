package com.example;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        // This is a Spring Boot application, so we don't need to put anything here.
        // The Application class is here to fulfill Dropwizard's expectations,
        // but the main logic is handled by Spring Boot.
    }
}
