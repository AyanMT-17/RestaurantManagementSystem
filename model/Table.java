package model;

public class Table {
    private int tableNumber;
    private int seatingCapacity;
    private boolean isAvailable;

    public Table(int tableNumber, int seatingCapacity) {
        this.tableNumber = tableNumber;
        this.seatingCapacity = seatingCapacity;
        this.isAvailable = true; // All tables start as available
    }

    // Getters and Setters
    public int getTableNumber() { return tableNumber; }
    public int getSeatingCapacity() { return seatingCapacity; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return "Table #" + tableNumber + " (Seats: " + seatingCapacity + ") - " +
               (isAvailable ? "Available" : "Reserved");
    }
}
