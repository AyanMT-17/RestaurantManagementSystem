package service;

import model.Table;
import java.util.ArrayList;
import java.util.List;

public class TableService {
    private List<Table> tables = new ArrayList<>();

    // Initialize tables (you could also load these from a database or configuration file)
    public TableService() {
        // Add tables with different capacities
        tables.add(new Table(1, 4));
        tables.add(new Table(2, 2));
        tables.add(new Table(3, 6));
        tables.add(new Table(4, 4));
    }

    public List<Table> getTables() {
        return tables;
    }

    public Table findTableByNumber(int tableNumber) {
        return tables.stream()
                .filter(table -> table.getTableNumber() == tableNumber)
                .findFirst()
                .orElse(null);
    }

    public boolean reserveTable(int tableNumber) {
        Table table = findTableByNumber(tableNumber);
        if (table != null && table.isAvailable()) {
            table.setAvailable(false);
            return true;
        }
        return false;
    }

    public boolean releaseTable(int tableNumber) {
        Table table = findTableByNumber(tableNumber);
        if (table != null && !table.isAvailable()) {
            table.setAvailable(true);
            return true;
        }
        return false;
    }

    public void displayTables() {
        System.out.println("Tables:");
        for (Table table : tables) {
            System.out.println(table);
        }
    }
}
