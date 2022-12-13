package com.ivan.labdb4.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    private final ModelMapper mapper = new ModelMapper();

    @Bean
    public ModelMapper getModelMapper() {
        return mapper;
    }
}
