package com.microservices.limits_service.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-service")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Configuration {
    private int minimum;
    private int maximum;
}