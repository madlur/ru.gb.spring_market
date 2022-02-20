package ru.sobolev.spring_market.cart.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RecommendationServerIntegration {

    private final RestTemplate restTemplate;

    @Value("${integrations.recommendation-service.url}")
    private String recommendationServiceUrl;


    public void addProductToRecommendationService(Long id) {
        restTemplate.getForObject(recommendationServiceUrl + "/api/v1/dayli_popular/" + id, Object.class);
    }
}
