package facade;

import document.Order;

import java.util.List;

public interface ManagerOrder {

    List<Order> getManagerOrder(String name);
}
