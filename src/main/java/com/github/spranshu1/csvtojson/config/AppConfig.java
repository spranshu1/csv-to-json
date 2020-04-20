package com.github.spranshu1.csvtojson.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

}
