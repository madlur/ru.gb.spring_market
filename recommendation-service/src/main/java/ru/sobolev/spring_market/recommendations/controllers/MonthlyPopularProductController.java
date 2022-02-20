package ru.sobolev.spring_market.recommendations.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sobolev.spring_market.api.core.ProductDto;
import ru.sobolev.spring_market.recommendations.services.MonthlyPopularProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/monthly_popular")
@RequiredArgsConstructor
public class MonthlyPopularProductController {
    private final MonthlyPopularProductService monthlyPopularProductService;

    @GetMapping
    public List<ProductDto> getFirstFivePopularItem() {
        return monthlyPopularProductService.getFirstFivePopularItem();
    }

    @PostMapping()
    public void addItem(@RequestBody List<Long> productId) {
        monthlyPopularProductService.addItems(productId);
    }
}
