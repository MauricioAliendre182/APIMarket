package com.project.market.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    // We will include a Bean
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Define the URL patterns for which CORS should be allowed
                        .allowedOrigins("http://localhost:4200") // Replace with your frontend domain
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(true) // If you need to allow cookies
                        .allowedHeaders(HttpHeaders.CONTENT_TYPE,
                                        HttpHeaders.AUTHORIZATION);
            }
        };
    }
}
