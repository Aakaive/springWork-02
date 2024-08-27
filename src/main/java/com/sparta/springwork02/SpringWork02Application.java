package com.sparta.springwork02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringWork02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringWork02Application.class, args);
    }

}
