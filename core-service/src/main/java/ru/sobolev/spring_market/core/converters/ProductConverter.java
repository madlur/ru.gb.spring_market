package ru.sobolev.spring_market.core.converters;


import org.springframework.stereotype.Component;
import ru.sobolev.spring_market.api.core.ProductDto;
import ru.sobolev.spring_market.core.entities.Product;

@Component
public class ProductConverter {

    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }
}
