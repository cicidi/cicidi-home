package com.cicidi.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAutoConfiguration


@PropertySource("classpath:application.yml")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
