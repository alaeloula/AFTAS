package com.example.aftas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AftasApplication implements CommandLineRunner {
    @Autowired
    private CustomProperties properties;

    public static void main(String[] args) {
        SpringApplication.run(AftasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(properties.getApiUrl());
    }
}
