package ru.sobolev.spring_market.cart.converters;

import org.springframework.stereotype.Component;
import ru.sobolev.spring_market.api.dto.Cart;
import ru.sobolev.spring_market.api.dto.CartDto;

@Component
public class CartConverter {
    public CartDto cartToDto(Cart cart) {
        return new CartDto(cart.getItems(), cart.getTotalPrice());
    }
}
