package model;

public class Employee {
    private String name;
    private String role;
    private int employeeId;

    public Employee(int employeeId, String name, String role) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
    }

    public int getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getRole() { return role; }

    @Override
    public String toString() {
        return "Employee #" + employeeId + ": " + name + " (" + role + ")";
    }
}
