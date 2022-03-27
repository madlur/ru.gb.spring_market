package ru.sobolev.spring_market.core.structure_patterns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import ru.sobolev.spring_market.core.entities.Product;
import ru.sobolev.spring_market.core.services.ProductsService;

public class ProductsServiceProxy {

    @Autowired
    private ProductsService productService;

    @Autowired
    private RedisTemplate redisTemplate;

    public Page<Product> getAll(Integer minPrice, Integer maxPrice, String partTitle, String category, Integer page) {
        String key = "getPageableFromRequest_minPrice=" + minPrice + "maxPrice=" + maxPrice + "_page=" + page + "_category=" + category + "_partTitle=" + partTitle;
        if (redisTemplate.hasKey(key)) {
            return (Page<Product>) redisTemplate.opsForValue().get(key);
        } else {
            return productService.findAll(minPrice,maxPrice,partTitle,category,page);
        }
    }
}
