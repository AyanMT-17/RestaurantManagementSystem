package model;

public class Employee {
    private String name;
    private String role;
    private int employeeId;

    public Employee(int employeeId, String name, String role) {
        if (employeeId <= 0) throw new IllegalArgumentException("Employee ID must be positive.");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
        if (role == null || role.isEmpty()) throw new IllegalArgumentException("Role cannot be empty.");

        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
    }

    public int getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getRole() { return role; }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
        this.name = name;
    }

    public void setRole(String role) {
        if (role == null || role.isEmpty()) throw new IllegalArgumentException("Role cannot be empty.");
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee #" + employeeId + ": " + name + " (" + role + ")";
    }
}
