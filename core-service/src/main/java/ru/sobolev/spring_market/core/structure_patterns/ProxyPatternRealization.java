package ru.sobolev.spring_market.core.structure_patterns;

import org.springframework.data.domain.Page;
import ru.sobolev.spring_market.api.core.ProductDto;
import ru.sobolev.spring_market.core.entities.Product;

public interface ProxyPatternRealization {

    Page<ProductDto> getAllProducts( Integer page, Integer minPrice, Integer maxPrice, String partTitle, String category);
}
