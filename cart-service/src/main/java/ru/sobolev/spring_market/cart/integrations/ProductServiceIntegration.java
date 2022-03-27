package ru.sobolev.spring_market.cart.integrations;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.sobolev.spring_market.api.core.ProductDto;
import ru.sobolev.spring_market.api.exceptions.ResourceNotFoundException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

    private final WebClient coreServiceWebClient;

    @Value("${integrations.core-service.url}")
    private String productServiceUrl;

    public Optional<ProductDto> findById(Long id) {
        ProductDto productDto = coreServiceWebClient.get()
                .uri("/api/v1/products/" + id)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    throw new ResourceNotFoundException("Продукт не найден");
                })
                .bodyToMono(ProductDto.class)
                .block();
        return Optional.ofNullable(productDto);
    }
}