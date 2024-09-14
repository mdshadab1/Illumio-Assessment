package com.illumio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class AppConfig {
    @Value("${app.untagged.label:Untagged}")
    private String untaggedLabel;

    @Bean
    public String untaggedLabel() {
        return untaggedLabel;
    }
}