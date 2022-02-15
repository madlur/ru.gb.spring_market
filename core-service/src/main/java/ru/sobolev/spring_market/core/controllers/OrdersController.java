package ru.sobolev.spring_market.core.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.sobolev.spring_market.api.dto.Cart;
import ru.sobolev.spring_market.core.converters.OrderConverter;
import ru.sobolev.spring_market.core.dto.OrderDetailsDto;
import ru.sobolev.spring_market.core.dto.OrderDto;
import ru.sobolev.spring_market.core.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;
    private final RestTemplate restTemplate;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username, @RequestBody OrderDetailsDto orderDetailsDto) {
        Cart cart = restTemplate.getForObject("http://localhost:8080/web-market-cart/api/v1/cart?username={username}", Cart.class, username);
        orderService.createOrder(username, orderDetailsDto, cart);
        restTemplate.getForObject("http://localhost:8080/web-market-cart/api/v1/cart/clear?username={username}", Void.class, username);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(@RequestHeader String username) {
        return orderService.findOrdersByUsername(username).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}