package com.employee.employeebackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/actuator-config.properties")
public class actuatorConfig {

}
