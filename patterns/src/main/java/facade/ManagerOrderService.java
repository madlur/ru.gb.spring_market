package facade;

import document.Order;
import factories.Manager;
import service.ManagerService;
import service.OrderService;

import java.util.List;

public class ManagerOrderService implements ManagerOrder {

    private OrderService orderService;
    private ManagerService managerService;

    @Override
    public List<Order> getManagerOrder(String name) {
        Manager manager = managerService.getManagerByName(name);
        return orderService.getOrderByManager(manager);
    }
}
