package ru.sobolev.spring_market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RecommendationServiceIntegration {
    private final WebClient recommendationServiceWebClient;

    public void addProductToRecService(List<Long> productsId){
        recommendationServiceWebClient.post()
                .uri("/api/v1/monthly_popular")
                .bodyValue(productsId)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

}
