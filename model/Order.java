package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int orderCounter = 1;
    private int orderId;
    private List<MenuItem> items;
    private double totalPrice;
    private String status;

    public Order() {
        this.orderId = orderCounter++;
        this.items = new ArrayList<>();
        this.status = "Pending";
        this.totalPrice = 0.0;
    }

    public void addItem(MenuItem item) {
        items.add(item);
        totalPrice += item.getPrice();
    }
    public int getOrderId() { return orderId; }
    public List<MenuItem> getItems() { return items; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Order #" + orderId + " | Total: $" + totalPrice + " | Status: " + status;
    }
}
