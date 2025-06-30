package com.example.Log_service.Config;

import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class swaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Microservicio de Logs - EcoMarket")
                .version("1.0")
                .description("API para gestionar logs de servicios en EcoMarket"));
    }

}
