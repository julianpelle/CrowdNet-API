package com.utn.CapitalConnection.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Solo permite CORS para endpoints específicos de EntrepreneurshipController y ReviewController
        registry.addMapping("/entrepreneurships/**") // rutas de EntrepreneurshipController
                .allowedOrigins("*") // Permitir cualquier origen
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH"); // Métodos permitidos

        registry.addMapping("/reviews/**") // rutas de ReviewController
                .allowedOrigins("*") // Permitir cualquier origen
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH"); // Métodos permitidos
    }
}