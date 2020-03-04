package com.altimetrik.microservices.limitsservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties("limits.service")
@Getter
@Setter
public class BaseConfig {
    private int min;
    private int max;
}
