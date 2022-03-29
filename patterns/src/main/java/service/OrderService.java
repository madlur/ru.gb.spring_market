package service;

import document.Order;
import factories.Manager;

import java.util.List;

public interface OrderService {
    Order getOrderById(Long id);

    List<Order> getOrderByManager(Manager manager);
}
