package ru.sobolev.spring_market.test.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import ru.sobolev.spring_market.api.dto.Cart;
import ru.sobolev.spring_market.api.dto.ProductDto;
import ru.sobolev.spring_market.cart.services.CartService;
import ru.sobolev.spring_market.core.entities.Product;
import ru.sobolev.spring_market.core.services.ProductsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CartService.class)
public class CartServiceTest {
    private final String testCartKey = "test_cart";

    @Autowired
    private CartService cartService;

    @MockBean
    private ProductsService productsService;
    @Mock
    private ValueOperations valueOperations;

    @MockBean
    private RedisTemplate<String, Object> redisTemplate;

    @BeforeEach
    public void initTestCart() {
        Cart cart = new Cart();
        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.doReturn(cart).when(valueOperations).get(testCartKey);
    }

    @Test
    public void addProductsToCart() {
        Product product = new Product(1L, "TestProduct1", 100);
        Mockito.doReturn(Optional.of(product)).when(productsService).findById(1L);
        cartService.addToCart(testCartKey, new ProductDto(1L, "TestProduct1", 100));
        cartService.addToCart(testCartKey, new ProductDto(1L, "TestProduct1", 100));
        cartService.addToCart(testCartKey, new ProductDto(1L, "TestProduct1", 100));
        assertEquals(1, cartService.getCurrentCart(testCartKey).getItems().size());
    }

    @Test
    public void correctlyMergingGuestAndAuthorizedUserCarts() {
        Cart guestCart = new Cart();

        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.doReturn(guestCart).when(valueOperations).get("guest_cart");

        Product product1 = new Product(1L, "TestProduct1", 100);
        Product product2 = new Product(2L, "TestProduct2", 200);
        Product product3 = new Product(3L, "TestProduct3", 300);
        Product product4 = new Product(4L, "TestProduct4", 400);
        Mockito.doReturn(Optional.of(product1)).when(productsService).findById(1L);
        Mockito.doReturn(Optional.of(product2)).when(productsService).findById(2L);
        Mockito.doReturn(Optional.of(product3)).when(productsService).findById(3L);
        Mockito.doReturn(Optional.of(product4)).when(productsService).findById(4L);


        cartService.addToCart("guest_cart", new ProductDto(1L, "TestProduct1", 100));
        cartService.addToCart("guest_cart", new ProductDto(2L, "TestProduct2", 200));
        cartService.addToCart("guest_cart", new ProductDto(3L, "TestProduct3", 300));
        cartService.addToCart(testCartKey, new ProductDto(3L, "TestProduct3", 300));
        cartService.addToCart(testCartKey, new ProductDto(4L, "TestProduct3", 400));

        cartService.merge(testCartKey, "guest_cart");
        assertEquals(4, cartService.getCurrentCart(testCartKey).getItems().size());
    }
}
