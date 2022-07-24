package com.logistic.log.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //classe que configura bens spring para tornar disponível para ser utilizado em outra classe como ponto de injeção
public class ModelMapperConfig {


    @Bean
   public  ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
