package ru.sobolev.spring_market.core.structure_patterns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import ru.sobolev.spring_market.api.core.ProductDto;
import ru.sobolev.spring_market.core.controllers.ProductsController;

public class ProductsServiceProxy implements ProxyPatternRealization {

    @Autowired
    private ProductsController productService;

    @Autowired
    private RedisTemplate redisTemplate;

    public Page<ProductDto> getAllProducts(Integer page, Integer minPrice, Integer maxPrice, String partTitle, String category) {
        String key = "getPageableFromRequest_minPrice=" + minPrice + "maxPrice=" + maxPrice + "_page=" + page + "_category=" + category + "_partTitle=" + partTitle;
        if (redisTemplate.hasKey(key)) {
            return (Page<ProductDto>) redisTemplate.opsForValue().get(key);
        } else {
            return productService.getAllProducts(page, minPrice, maxPrice, partTitle, category);
        }
    }
}
