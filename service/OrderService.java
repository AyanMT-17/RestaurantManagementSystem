package service;

import java.util.ArrayList;
import java.util.List;
import model.MenuItem;
import model.Order;

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

    public void displayOrder(Order order) {
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Items:");
        for (MenuItem item : order.getItems()) {
            System.out.println(" - " + item.getName() + ": $" + item.getPrice());
        }
        System.out.println("Total: $" + calculateOrderTotal(order));
    }

    public void updateOrderStatus(int orderId, String status) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                order.setStatus(status);
            }
        }
    }


    public double calculateOrderTotal(Order order) {
        double total = 0.0;
        for (MenuItem item : order.getItems()) {  // Assuming getItems() returns a List<MenuItem>
            total += item.getPrice();
        }
        return total;
    }
}

