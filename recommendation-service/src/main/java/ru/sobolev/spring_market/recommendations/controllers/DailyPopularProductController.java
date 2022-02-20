package ru.sobolev.spring_market.recommendations.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sobolev.spring_market.api.core.ProductDto;
import ru.sobolev.spring_market.recommendations.services.DailyPopularProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dayli_popular")
@RequiredArgsConstructor
public class DailyPopularProductController {
    private final DailyPopularProductService dailyPopularProductService;

    @GetMapping
    public List<ProductDto> getFirstFivePopularItem() {
        return dailyPopularProductService.getFirstFivePopularItem();
    }

    @GetMapping("/{productId}")
    public void addItem(@PathVariable Long productId) {
        dailyPopularProductService.addItem(productId);
    }
}
