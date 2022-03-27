package ru.sobolev.spring_market.core.services;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sobolev.spring_market.api.builder_pattern.ProductDtoBuilder;
import ru.sobolev.spring_market.api.core.ProductDto;
import ru.sobolev.spring_market.core.entities.Product;
import ru.sobolev.spring_market.core.repositories.ProductsRepository;
import ru.sobolev.spring_market.core.repositories.specifications.ProductsSpecifications;
import ru.sobolev.spring_market.api.exceptions.ResourceNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;

    public Page<Product> findAll(Integer minPrice, Integer maxPrice, String partTitle, String category, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.titleLike(partTitle));
        }
        if (category != null) {
            spec = spec.and(ProductsSpecifications.categoryLike(category));
        }

        return productsRepository.findAll(spec, PageRequest.of(page - 1, 8));
    }


    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }

    public Product save(Product product) {
        return productsRepository.save(product);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productsRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить продукта, не надйен в базе, id: " + productDto.getId()));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        return product;
    }

    public List<Product> getProductsIdFromList(List<Long> productsId) {
        List<Product> products = productsId.stream().map(id -> findById(id).get()).collect(Collectors.toList());
        return products;
    }

    public ProductDto addProduct(Product product){
        ProductDtoBuilder builder = new ProductDtoBuilder();
        productsRepository.save(product);
        return builder.id(product.getId()).title(product.getTitle()).price(product.getPrice()).build();
    }
}
