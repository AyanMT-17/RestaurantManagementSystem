import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import model.Customer;
import model.MenuItem;
import model.Order;
import model.Table;
import service.EmployeeService;
import service.MenuService;
import service.OrderService;
import service.TableService;

public class RestaurantManagementApp {
    public static void main(String[] args) {
        // Initialize services
        MenuService menuService = new MenuService();
        OrderService orderService = new OrderService();
        TableService tableService = new TableService();
        
        EmployeeService employeeService = new EmployeeService(); // Initialize EmployeeService
        HashMap<Table, Order> olist = new HashMap<>();  // Store orders by table
        HashMap<Integer,Customer> tlist=new HashMap<>(); // Store table by customer name

        // Sample data
        menuService.addMenuItem(new MenuItem("Pasta", 10.99, "Main Course"));
        menuService.addMenuItem(new MenuItem("Ice Cream", 4.99, "Dessert"));

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nRestaurant Management System");
            System.out.println("1. View Menu");
            System.out.println("2. Add Menu Item");
            System.out.println("3. Create Order");
            System.out.println("4. View Orders");
            System.out.println("5. Update Order Status");
            System.out.println("6. View Tables");
            System.out.println("7. Reserve Table");
            System.out.println("8. Release Table");
            System.out.println("9. Generate Bill");
            System.out.println("10. Add Employee");
            System.out.println("11. Display All Employee");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    menuService.displayMenu();
                    break;
                case 2:
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    menuService.addMenuItem(new MenuItem(name, price, category));
                    System.out.println("Item added to menu.");
                    break;
                case 3:
                    System.out.print("Enter table number for the order: ");
                    int tableNumber = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    Table table = tableService.findTableByNumber(tableNumber);

                    if (table != null) {
                        Order order = olist.getOrDefault(table, orderService.createOrder());
                        menuService.displayMenu();
                        System.out.print("Enter item name to add to order: ");
                        String itemName = scanner.nextLine();
                        MenuItem item = menuService.getMenuItems().stream()
                                .filter(i -> i.getName().equalsIgnoreCase(itemName))
                                .findFirst()
                                .orElse(null);

                        if (item != null) {
                            orderService.addItemToOrder(order, item);
                            olist.put(table, order);
                            System.out.println("Item added to order.");
                        } else {
                            System.out.println("Item not found in menu.");
                        }
                    } else {
                        System.out.println("Table not found.");
                    }
                    break;
                case 4:
                    boolean f=false;
                    for(Map.Entry<Table,Order>i: olist.entrySet())
                    {   
                        if(i.getKey()!=null)
                        {f=true;}
                        System.out.println(i.getKey());
                        orderService.displayOrder(i.getValue());
                    }
                    if(f==false)
                    {System.out.println("No orders to be displayed");}
                    
                    break;
                case 5:
                    System.out.print("Enter order ID: ");
                    int orderId = scanner.nextInt(); 
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter new status (Pending, Completed, Delivered): ");
                    String status = scanner.nextLine();
                    orderService.updateOrderStatus(orderId, status);
                    System.out.println("Order status updated.");
                    break;
                case 6:
                    tableService.displayTables();
                    break;
                case 7:
                    
                    System.out.print("Enter name of customer: ");
                    String name1=scanner.nextLine();
                    System.out.print("Enter phone number of customer: ");
                    String num=scanner.nextLine();
                    Customer customer=new Customer(name1, num);
                    System.out.print("Enter table number to reserve: ");
                    int tableNumberToReserve = scanner.nextInt();
                    tlist.put(tableNumberToReserve,customer);
                    if (tableService.reserveTable(tableNumberToReserve)) {
                        System.out.println("Table #" + tableNumberToReserve + " reserved.");
                    } else {
                        System.out.println("Table #" + tableNumberToReserve + " is already reserved or does not exist.");
                    }
                    break;
                case 8:
                    System.out.print("Enter table number to release: ");
                    int tableNumberToRelease = scanner.nextInt();
                    if (tableService.releaseTable(tableNumberToRelease)) {
                        System.out.println("Table #" + tableNumberToRelease + " is now available.");
                    } else {
                        System.out.println("Table #" + tableNumberToRelease + " is already available or does not exist.");
                    }
                    tlist.remove(tableNumberToRelease);
                    break;
                case 9:
                    System.out.print("Enter table number for billing: ");
                    int billingTableNumber = scanner.nextInt();
                    Table billingTable = tableService.findTableByNumber(billingTableNumber);

                    if (billingTable != null && olist.containsKey(billingTable)) {
                        Order billingOrder = olist.get(billingTable);
                        double total = orderService.calculateOrderTotal(billingOrder);
                        orderService.displayOrder(billingOrder);
                        System.out.println("Total bill for Table #" + billingTableNumber + ": $" + total);
                    } else {
                        System.out.println("No order found for this table.");
                    }
                    olist.remove(billingTable);
                    break;
                case 10:
                    System.out.print("Enter Employee ID: ");
                    int employeeId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter Employee Name: ");
                    String employeeName = scanner.nextLine();
                    System.out.print("Enter Employee Role: ");
                    String employeeRole = scanner.nextLine();
                    employeeService.addEmployee(employeeId, employeeName, employeeRole);
                    System.out.println("Employee added.");
                    break;
                case 11:
                    System.out.println("All Employees:");
                    employeeService.getAllEmployees().forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        scanner.close();
    }
}