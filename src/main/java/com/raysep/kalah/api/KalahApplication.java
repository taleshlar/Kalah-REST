package com.raysep.kalah.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.raysep.kalah.api.config.ApplicationProperties;

/**
 * Main class.
 */
@EnableConfigurationProperties(ApplicationProperties.class)
@SpringBootApplication(scanBasePackages = "com.raysep.kalah.api")
public class KalahApplication {

    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        SpringApplication.run(KalahApplication.class, args);
    }

}

