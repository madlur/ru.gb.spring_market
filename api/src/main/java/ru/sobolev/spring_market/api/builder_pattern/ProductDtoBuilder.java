package ru.sobolev.spring_market.api.builder_pattern;

import ru.sobolev.spring_market.api.core.ProductDto;

import java.math.BigDecimal;

public class ProductDtoBuilder {

    private final ProductDto productDto;

    public ProductDtoBuilder() {
        this.productDto = new ProductDto();
    }

    public ProductDtoBuilder id(Long productId) {
        productDto.setId(productId);
        return this;
    }

    public ProductDtoBuilder title(String title) {
        productDto.setTitle(title);
        return this;
    }

    public ProductDtoBuilder price(BigDecimal price) {
        productDto.setPrice(price);
        return this;
    }


    public ProductDto build() {
        return this.productDto;
    }
}
