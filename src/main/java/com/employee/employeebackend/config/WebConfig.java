package com.employee.employeebackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "OPTIONS", "HEAD",
                        "PUT", "PATCH", "DELETE", "TRACE")
                .allowedHeaders("*")
                .maxAge(86400)
                .exposedHeaders("Authorization");
    }
}
