package com.example.aftas;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.example.aftas")
@Data
public class CustomProperties {
    private String apiUrl;
}
