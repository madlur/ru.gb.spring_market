package ru.sobolev.spring_market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sobolev.spring_market.core.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
