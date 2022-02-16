package ru.sobolev.spring_market.cart.converters;

import org.springframework.stereotype.Component;
import ru.sobolev.spring_market.api.carts.CartItemDto;
import ru.sobolev.spring_market.cart.models.Cart;
import ru.sobolev.spring_market.api.carts.CartDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartConverter {
    public CartDto modelToDto(Cart cart) {
        List<CartItemDto> cartItemDtos = cart.getItems().stream().map(it ->
                new CartItemDto(it.getProductId(), it.getProductTitle(), it.getQuantity(), it.getPricePerProduct(), it.getPrice())
        ).collect(Collectors.toList());
        return new CartDto(cartItemDtos, cart.getTotalPrice());
    }
}
