package com.example.academiacx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AcademiaCxApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcademiaCxApplication.class, args);
    }

}
