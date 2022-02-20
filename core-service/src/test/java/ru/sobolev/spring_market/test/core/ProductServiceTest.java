package ru.sobolev.spring_market.test.core;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.sobolev.spring_market.api.dto.ProductDto;
import ru.sobolev.spring_market.core.entities.Product;
import ru.sobolev.spring_market.core.repositories.ProductsRepository;
import ru.sobolev.spring_market.core.services.ProductsService;

import java.util.Optional;

@SpringBootTest(classes = {ProductsService.class})
public class ProductServiceTest {
    @Autowired
    private ProductsService productsService;
    @MockBean
    private ProductsRepository productsRepository;

    @Test
    public void findProductByIdTest() {
        Product product = new Product(3L, "Сыр Российский", 300);
        Mockito.doReturn(Optional.of(product)).when(productsRepository).findById(3L);
        Product getProduct = productsService.findById(3L).get();
        Assertions.assertEquals(product, product);
    }

    @Test
    public void updateProductTest() {
        Product product = new Product(1L, "Хлеб", 100);
        Product updatedProduct = new Product(1L, "Сыр Российский", 300);

        ProductDto cheese = new ProductDto(1L, "Сыр Российский", 300);
        Mockito.doReturn(Optional.of(product)).when(productsRepository).findById(1L);
        Product newApple = productsService.update(cheese);
        Assertions.assertEquals(updatedProduct, newApple);
    }
}