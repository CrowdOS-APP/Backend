package com.crowdos.backend.datainterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.crowdos.backend.controller")
public class DatainterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatainterfaceApplication.class, args);
    }

}
