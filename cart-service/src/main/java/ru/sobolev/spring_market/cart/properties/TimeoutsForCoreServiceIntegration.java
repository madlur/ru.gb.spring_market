package ru.sobolev.spring_market.cart.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.core-service.timeouts")
@Data
public class TimeoutsForCoreServiceIntegration {
    private Integer readTimeout;
    private Integer writeTimeout;
    private Integer connectTimeout;
}
