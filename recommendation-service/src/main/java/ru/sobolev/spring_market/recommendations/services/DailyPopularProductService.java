package ru.sobolev.spring_market.recommendations.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sobolev.spring_market.api.core.ProductDto;
import ru.sobolev.spring_market.recommendations.entities.DailyPopularProduct;
import ru.sobolev.spring_market.recommendations.integrations.CoreServiceIntegration;
import ru.sobolev.spring_market.recommendations.repositories.DailyPopularProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyPopularProductService {

    private final CoreServiceIntegration coreServiceIntegration;
    private final DailyPopularProductRepository dailyPopularProductRepository;

    @Transactional
    public void deleteOldEntries() {
        dailyPopularProductRepository.deleteOldEntries();
    }

    public List<ProductDto> getFirstFivePopularItem() {
        return coreServiceIntegration.getProductDto(findFirstFifeEntries());
    }

    public List<Long> findFirstFifeEntries() {
        return dailyPopularProductRepository.findFirstFiveWithManyEntries();
    }

    public void addItem(Long productId) {
        DailyPopularProduct item = new DailyPopularProduct(productId);
        dailyPopularProductRepository.save(item);
    }

}
