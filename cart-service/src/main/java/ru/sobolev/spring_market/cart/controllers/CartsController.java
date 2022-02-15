package ru.sobolev.spring_market.cart.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.sobolev.spring_market.api.dto.Cart;
import ru.sobolev.spring_market.api.dto.CartDto;
import ru.sobolev.spring_market.api.dto.ProductDto;
import ru.sobolev.spring_market.api.dto.StringResponse;
import ru.sobolev.spring_market.cart.converters.CartConverter;
import ru.sobolev.spring_market.cart.services.CartService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartsController {
    private final CartService cartService;
    private final RestTemplate restTemplate;
    private final CartConverter cartConverter;

//    @GetMapping
//    public CartDto getCartForOrder (@RequestParam String username){
//        String cartKey = cartService.getCartUuidFromSuffix(username);
//        return cartConverter.cartToDto(cartService.getCurrentCart(cartKey));
//    }
//    @GetMapping("/clear")
//    public void clearCart(@RequestParam String username){
//        String cartKey = cartService.getCartUuidFromSuffix(username);
//        cartService.clearCart(cartKey);
//    }

    @GetMapping("/{uuid}")
    public Cart getCart(@RequestHeader(required = false) String username, @PathVariable String uuid) {
        return cartService.getCurrentCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/generate")
    public StringResponse getCart() {
        return new StringResponse(cartService.generateCartUuid());
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void add(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        ProductDto productDto = restTemplate.getForObject("http://localhost:5555/core/api/v1/products/" + productId, ProductDto.class);
        cartService.addToCart(getCurrentCartUuid(username, uuid), productDto);
    }

    @GetMapping("/{uuid}/decrement/{productId}")
    public void decrement(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/remove/{productId}")
    public void remove(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/clear")
    public void clear(Principal principal, @PathVariable String uuid) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/{uuid}/merge")
    public void merge(Principal principal, @PathVariable String uuid) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        cartService.merge(
                getCurrentCartUuid(username, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    private String getCurrentCartUuid(String username, String uuid) {
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
}


