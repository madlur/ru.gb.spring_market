package ru.sobolev.spring_market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sobolev.spring_market.api.dto.Cart;
import ru.sobolev.spring_market.core.dto.OrderDetailsDto;
import ru.sobolev.spring_market.core.entities.Order;
import ru.sobolev.spring_market.core.entities.OrderItem;
import ru.sobolev.spring_market.core.repositories.OrdersRepository;
import ru.sobolev.spring_market.api.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final ProductsService productsService;

    @Transactional
    public void createOrder(String username, OrderDetailsDto orderDetailsDto, Cart cart) {
        Order order = new Order(username, cart.getTotalPrice(), orderDetailsDto.getAddress(), orderDetailsDto.getPhone());

        List<OrderItem> items = cart.getItems().stream()
                .map(o -> {
                    OrderItem item = new OrderItem();
                    item.setOrder(order);
                    item.setQuantity(o.getQuantity());
                    item.setPricePerProduct(o.getPricePerProduct());
                    item.setPrice(o.getPrice());
                    item.setProduct(productsService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
                    return item;
                }).collect(Collectors.toList());
        order.setItems(items);
        ordersRepository.save(order);
    }

    public List<Order> findOrdersByUsername(String username) {
        return ordersRepository.findAllByUsername(username);
    }
}
