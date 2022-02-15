package ru.sobolev.spring_market.core.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.sobolev.spring_market.core.entities.Product;


public class ProductsSpecifications {
    public static Specification<Product> priceGreaterOrEqualsThan(Integer price) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> priceLessThanOrEqualsThan(Integer price) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> titleLike(String titlePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }

    public static Specification<Product> categoryLike(String category) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("category").get("name"), String.format("%%%s%%", category));
    }
}
