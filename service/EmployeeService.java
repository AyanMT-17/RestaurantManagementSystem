package service;

import java.util.ArrayList;
import java.util.List;
import model.Employee;

public class EmployeeService {
    private List<Employee> emp = new ArrayList<>();

    // Method to add an employee to the list
    public void addEmployee(int employeeId, String name, String role) {
        Employee employee = new Employee(employeeId, name, role);
        emp.add(employee);
    }

    // Method to get the list of employees
    public List<Employee> getAllEmployees() {
        return emp;
    }

    // Method to find an employee by ID
    public Employee findEmployeeById(int employeeId) {
        for (Employee employee : emp) {
            if (employee.getEmployeeId() == employeeId) {
                return employee;
            }
        }
        return null; // Returns null if no employee with given ID is found
    }
    
    @Override
    public String toString() {
        return "EmployeeService{" +
               "employees=" + emp +
               '}';
    }
}
