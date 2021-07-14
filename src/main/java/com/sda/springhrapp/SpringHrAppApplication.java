package com.sda.springhrapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringHrAppApplication {

    public static void main(String[] args) {
        log.info("SpringHRApp started.");
        SpringApplication.run(SpringHrAppApplication.class, args);
        log.info("SpringHRApp ended.");
    }

}
