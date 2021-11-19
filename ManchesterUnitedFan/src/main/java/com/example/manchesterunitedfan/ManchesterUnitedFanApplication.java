package com.example.manchesterunitedfan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ManchesterUnitedFanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManchesterUnitedFanApplication.class, args);
    }

}
