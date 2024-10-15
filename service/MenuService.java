package service;

import model.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MenuService {
    private List<MenuItem> menuItems = new ArrayList<>();

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    public void updateMenuItem(String name, double newPrice) {
        for (MenuItem item : menuItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                item.setPrice(newPrice);
            }
        }
    }

    public void removeMenuItem(String name) {
        menuItems.removeIf(item -> item.getName().equalsIgnoreCase(name));
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void displayMenu() {
        System.out.println("Menu:");
        for (MenuItem item : menuItems) {
            System.out.println(item);
        }
    }
}
