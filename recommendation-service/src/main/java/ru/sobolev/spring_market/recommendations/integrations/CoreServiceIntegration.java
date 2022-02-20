package ru.sobolev.spring_market.recommendations.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.sobolev.spring_market.api.core.ProductDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {
    private final WebClient coreServiceWebClient;

    public List<ProductDto> getProductDto(List<Long> productsId) {
        List<ProductDto> products = coreServiceWebClient.post()
                .uri("/api/v1/products/id_values")
                .bodyValue(productsId)
                .retrieve()
                .bodyToMono(List.class)
                .block();
        return products;
    }
}
