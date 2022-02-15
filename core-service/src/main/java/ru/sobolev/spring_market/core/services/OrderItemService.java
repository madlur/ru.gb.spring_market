package ru.sobolev.spring_market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sobolev.spring_market.core.entities.OrderItem;
import ru.sobolev.spring_market.core.repositories.OrderItemRepository;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
