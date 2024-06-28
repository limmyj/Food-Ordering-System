/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASSIGNMENT;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Athelene
 */
public class Customer extends User{
    private static String customerId;
    private static String name;
    private String email;
    private String Contact;
    private String balance;
    private int contact_number;
    private static double creditBalance;
    private static int OrderId = 1;
    private static List<Order> orderHistory = new ArrayList<>();
    File_Operation fop;
    
    public Customer(String Id, String pw, String un, String em, String cn, String credit) 
    {
	super(Id, pw);
        customerId = Id;
	name = un;
	email = em;
	Contact = cn;
	balance = credit; 
	fop = new File_Operation();
    }
	
    public Customer(String Id, String pw) 
    {
	super(Id, pw);
	email = null;
	name = null;
	contact_number = 0;
	fop = new File_Operation();
    }
    
    public Customer(String Id, String pw,String name, double initialCredit) 
    {   
        super(Id, pw);
        this.name = name;
        this.creditBalance = initialCredit;
        this.orderHistory = new ArrayList<>();
    } 
        // Getter methods
    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public int getContact() {
        return contact_number;
    }
    
    public String getBalance() {
        return balance;
    }
    
    public String getContactNum() {
        return Contact;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    // Setter methods
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }
    
           
    	// Read all Menu from Menu.txt 
        public static void view_Menu() {
            System.out.println("Reading data from items.txt ...");
            System.out.println("----------------- ALL MENU --------------------");
            Customer_File_Operation.Read_Data_From_Vendor_Menu("items.txt");
            System.out.println("*******************************************************************************");
        }
        
        // Customer provide review into Review.txt
	public static void Provide_review(String customerId) {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Enter your order ID: ");
            int orderId = scanner.nextInt();
            scanner.nextLine();
            
            // Customer inputs their review
            System.out.print("Enter your review towards the food: ");
            String review = scanner.nextLine();

            Customer_File_Operation.writeReview("Review.txt", customerId, orderId, review);
            System.out.println("The review has been inserted successfully.");
	}

    public static void placeOrder(String CustomerID) {
            System.out.println("***********************************************");
            System.out.println("\tPlace Order Page");
            System.out.println("***********************************************");
            String customerID = CustomerID;
            System.out.println("Reading data from Menu.txt ...");
            Customer_File_Operation.Read_Data_From_Vendor_Menu("items.txt");
            
            Scanner scanner = new Scanner(System.in);
            // Get user input for item name
            System.out.print("Enter the name of the item you want to order from: ");
            String selectedItem = scanner.nextLine().trim();
            List<MenuItem> menuItems = Customer_File_Operation.getMenuItems(selectedItem);
            
            if (menuItems.isEmpty()) {
            System.out.println("No menu items found for the selected restaurant. Exiting order placement.");
            return;
        }
            // Get customer input for quantity
            System.out.print("Enter the quantity of the item between 1-5: ");
            int quantity = scanner.nextInt();

            if (quantity <= 0 || quantity >= 5) {
                System.out.println("Invalid quantity. Exiting order placement.");
                return;
            }
            
        // Get customer input for order type
        System.out.println("\nSelect Order Type:");
        System.out.println("1. Dine-In");
        System.out.println("2. Takeaway");
        System.out.println("3. Delivery");

        System.out.print("Select order type (1-3): ");
        int orderTypeIndex = scanner.nextInt();

        if (orderTypeIndex < 1 || orderTypeIndex > 3) {
            System.out.println("Invalid order type selection. Exiting order placement.");
            return;
        }

        String orderType;
        String Deliveryaddress = "";
        switch (orderTypeIndex) {
            case 1:
                orderType = "Dine-In";
                Deliveryaddress = "null";
                break;
            case 2:
                orderType = "Takeaway";
                Deliveryaddress = "null";
                break;
            case 3:
                orderType = "Delivery";
                scanner.nextLine(); // Consume the newline character
                // Prompt for delivery address
                System.out.print("Enter the location for delivery the food: ");
                Deliveryaddress = scanner.nextLine().trim();
                break;
            default:
                orderType = "Unknown";
                break;
        }
        
        Date currentDate = new Date(); // Use the current date and time
        // Create an order and add it to the order history
        int orderID = Customer_File_Operation.generateNewOrderId();
        for (MenuItem item : menuItems) {
        String SelectedItem = item.getItemName();
        double Price = item.getPrice();
        String Address = item.getAddress();
        Order order = new Order(orderID, SelectedItem, Price, quantity, Address, orderType, currentDate);
        orderHistory.add(order);
        }
        
        double CreditBalance = Customer_File_Operation.getCreditBalance(customerID);
        double orderAmount = Customer_File_Operation.calculateOrderAmount(customerID, Customer_File_Operation.getMenuItems(selectedItem), quantity);
        
        if (CreditBalance >= orderAmount) {

            for (MenuItem item : menuItems) {
                double Price = item.getPrice();
                String Address = item.getAddress();
            
            System.out.println("\nOrder placed successfully!");
            System.out.println("Order details:");
            System.out.println("Order ID:" + orderID);
            System.out.println("Restaurant: " + selectedItem);
            System.out.println("Order Type: " + orderType);
            System.out.println("Food Price: RM" + Price);
            System.out.println("Quantity: " + quantity);
            System.out.println("Restaurant Address: " + Address);
        
        if (orderType.equals("Delivery")) {
            System.out.println("Delivery Address: " + Deliveryaddress);
        }
        System.out.println("Time: " + currentDate);
        System.out.println("Order Amount: " + orderAmount);
        Customer_File_Operation.Customer_Place_Order(orderID, selectedItem, Price, quantity, Address, orderType, currentDate, orderAmount,Deliveryaddress, customerID);
        
        System.out.println("\nOrder placed successfully!");
            }
        }            
        else 
        {
            System.out.println("Insufficient credit balance. Order cannot be placed.");
        }
    }
  
    public static void checkOrderStatus(String customerid) {
        OrderHistory order = new OrderHistory();
        order.Load_Order_Status(customerid);
    }

    public static void checkOrderHistory(String customerid) {
        OrderHistory order = new OrderHistory();
        order.Load_Order_History(customerid);
    }

    public void reloadCredit(double amount) {
    double currentCreditBalance = getCreditBalance(); 

    double updatedCreditBalance = currentCreditBalance + amount;
    setCreditBalance(updatedCreditBalance); 

    System.out.println("Credit Reloaded: " + amount);
    System.out.println("Updated Credit Balance: " + updatedCreditBalance);
    }

     public static void reorder(String customerid) {
        System.out.println("***********************************************");
        System.out.println("\tCustomer Reorder Page");
        System.out.println("***********************************************");
         Scanner scanner = new Scanner(System.in);
         OrderHistory order = new OrderHistory();
         order.Load_Order_History(customerid);
         System.out.print("Enter the order id to reorder the same order: ");
         String orderid = scanner.nextLine();
         order.Reorder_Order_History(customerid, orderid);
}
    
    public static void View_review(String customerId) 
    {
       Customer_File_Operation.View_review(customerId);
    }
 
        // Customer - Cancel Order Page
	public static void CancelOrderPage(String CustomerID) 
        {
            String customerID = CustomerID;
            try (Scanner scanner = new Scanner(System.in)) 
            { 
                System.out.println("***********************************************");
                System.out.println("\tCancel Order Page");
                System.out.println("***********************************************");

                OrderHistory orderhistory = new OrderHistory();
                orderhistory.Load_Order_History(customerID);
                System.out.println("***********************************************************");
                System.out.println("Customers are not allowed to cancel the order have been approved.");
            
            int orderID;
            try 
            {
                System.out.print("Enter the Order ID you want to cancel: ");
                orderID = scanner.nextInt();
            } 
            catch (InputMismatchException ex) {
                System.out.println("Invalid input data type. Please enter a valid Order ID.");
                scanner.nextLine(); 
                return;
            }
                Order orderToCancel = orderhistory.Find_OrderID(orderID);

                if (orderToCancel != null) {
                    orderhistory.cancelOrder(orderID,customerID);
                } else {
                    System.out.println("Order not found. Please enter a valid Order ID.");
                    scanner.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }    
        
        public void viewProfile() 
        {
        System.out.println("Vendor Profile Details:");
        System.out.println("ID: " + getCustomerId());
        System.out.println("Username: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Contact Number: " + getContactNum());
        // Add other details as needed
        }
    }    

