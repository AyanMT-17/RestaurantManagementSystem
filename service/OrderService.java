package service;

import model.MenuItem;
import model.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders = new ArrayList<>();

    public Order createOrder() {
        Order newOrder = new Order();
        orders.add(newOrder);
        return newOrder;
    }

    public void addItemToOrder(Order order, MenuItem item) {
        order.addItem(item);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void displayOrders() {
        System.out.println("Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public void updateOrderStatus(int orderId, String status) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                order.setStatus(status);
            }
        }
    }
}
