package ru.sobolev.spring_market.recommendations.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sobolev.spring_market.api.core.ProductDto;
import ru.sobolev.spring_market.recommendations.entities.MonthlyPopularProduct;
import ru.sobolev.spring_market.recommendations.integrations.CoreServiceIntegration;
import ru.sobolev.spring_market.recommendations.repositories.MonthlyPopularProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonthlyPopularProductService {

    private final MonthlyPopularProductRepository monthlyPopularProductRepository;
    private final CoreServiceIntegration coreServiceIntegration;

    @Transactional
    public void deleteOldEntries() {
        monthlyPopularProductRepository.deleteOldEntries();
    }

    public void addItem(Long productId) {
        MonthlyPopularProduct item = new MonthlyPopularProduct(productId);
        monthlyPopularProductRepository.save(item);
    }

    @Transactional
    public void addItems(List<Long> productsId) {
        productsId.stream().forEach(id -> addItem(id));
    }

    public List<ProductDto> getFirstFivePopularItem() {
        return coreServiceIntegration.getProductDto(findFirstFifeEntries());
    }

    public List<Long> findFirstFifeEntries() {
        return monthlyPopularProductRepository.findFirstFiveWithManyEntries();
    }
}
