package com.example.blurtle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class BlurtleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlurtleApplication.class, args);
    }

}
