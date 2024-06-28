/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASSIGNMENT;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Athelene
 */
public class OrderFile 
{
    private static final String FILE_PATH = "Orders.txt";
    private List<Order> orders;
    private List<String> notifications;
    
    public OrderFile() 
    {
        this.orders = new ArrayList<>();
        this.notifications = new ArrayList<>();
        loadOrdersFromFile();
    }
    
    private void loadOrdersFromFile() 
    {
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split("/");
                if (parts.length >= 6) { // Check for at least 6 elements
                int orderId = Integer.parseInt(parts[1]);
                String itemName = parts[2];
                double itemPrice = Double.parseDouble(parts[3]);
                int quantity = Integer.parseInt(parts[4]);
                String status = parts[6];
                Date date = parseDate(parts[7]); // Assuming parts[5] is the date string
                orders.add(new Order(orderId, itemName, itemPrice, quantity, status, date));}
                else 
                {
                    // Handle the case where the line does not have enough elements
                    System.out.println("Invalid format on line: " + line);
                }
            }
        } 
        catch (IOException e) 
        {
            // Handle exceptions (e.g., file not found, format error)
            e.printStackTrace();
        }
    }
    
    private void saveOrdersToFile() 
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Order order : orders) 
            {
                // Write Order details to the file
                writer.write(order.toString());
                writer.newLine();
            }
            writer.close();
        } 
        catch (IOException e) 
        {
            // Handle exceptions (e.g., file not found, write error)
            e.printStackTrace();
        }
    }
    
// Method to accept an order and update its status
public void acceptOrder(int orderId) {
    Order order = getOrderById(orderId);

    if (order != null) {
        String currentStatus = order.getOrderStatus();

        // Check if the order is already accepted
        if ("Accepted".equals(currentStatus)) {
            System.out.println("Order ID " + order.getOrderId() + " is already accepted.");
        } else {
            // Update order status to accepted
            order.setOrderStatus("Accepted");
            String orderid = Integer.toString(orderId);

            // Use Customer_File_Operation to update the order status in the file
            Customer_File_Operation.Update_Order_Status(orderid, "Accepted");

            // Display and record acceptance details
            System.out.println("\nOrder ID " + order.getOrderId() + " is accepted.");
        }
    } else {
        System.out.println("Order not found.");
    }
}
    
    
    public void cancelOrder(int orderId) 
    {
        Order order = getOrderById(orderId);

        if (order != null) 
        {
            String currentStatus = order.getOrderStatus();
        if ("Cancelled".equals(currentStatus)) {
            System.out.println("Order ID " + order.getOrderId() + " is already cancelled.");
        } else {
            // Update order status to accepted
            order.setOrderStatus("Cancelled");
            String orderid = Integer.toString(orderId);

            // Use Customer_File_Operation to update the order status in the file
            Customer_File_Operation.Update_Order_Status(orderid, "Cancelled");

            // Display and record acceptance details
            System.out.println("\nOrder ID " + order.getOrderId() + " is cancelled.");
        }
        } 
        else 
        {
            System.out.println("Order not found.");
        }
    }
    
    public void updateOrderStatus(int orderId, String newStatus) {
        // Implement the logic to update the order status in your orders list or file
        Order order = getOrderById(orderId);
        
        if (order != null) {
            order.setStatus(newStatus);
            // Update the order status in the file or wherever you store orders
            // This could be similar to the logic you have in your acceptOrder method
            String orderid = Integer.toString(orderId);
            Customer_File_Operation.Update_Order_Status(orderid, newStatus);

            System.out.println("Order ID: " + order.getOrderId() + " updated to status " + newStatus);
        } else {
            System.out.println("Order not found.");
        }
    }
    
    private Order getOrderById(int orderId) 
    {
        for (Order order : orders) 
        {
            if (order.getOrderId() == orderId) 
            {
                return order;
            }
        }
        return null;
    }
    
    public void displayOrderHistory(String period) 
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        switch (period.toLowerCase()) 
        {
            case "daily":
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                break;
            case "monthly":
                calendar.add(Calendar.MONTH, -1);
                break;
            case "quarterly":
                calendar.add(Calendar.MONTH, -3);
                break;
            default:
                System.out.println("Invalid period. Supported periods: daily, monthly, quarterly");
                return;
        }
        
        Date startDate = calendar.getTime();
        for (Order order : orders) 
        {
            if (order.getDate().after(startDate)) 
            {
                System.out.println(order);
            }
        }
    }
    
        private Date parseDate(String dateString) 
    {
        try 
        {
            String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            // Adjust the date format based on your actual date string format
            return dateFormat.parse(dateString);
        } 
        catch (ParseException e) 
        {
            System.out.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }
    
    public void sendNotification(String message) 
    {
        notifications.add(message);
    }
    
    public void checkorderStatus() 
    {
        for (Order order : orders) 
        {
            order.clearNotifications();
            if ("Accepted".equals(order.getStatus())||"Cancelled".equals(order.getStatus())) 
            {
                String notification = "\nThe order : " + order.getOrderId() + ", Item Name: " + order.getName() +". \nOrder Status: " + order.getStatus();
                // Add notification to the item
                order.addNotification(notification);
            }
        }
    }
    
    public void displayNotifications() 
    {
        for (Order order : orders)  
        {
        List<String> orderNotifications = order.getNotifications();
        if (orderNotifications != null) 
        {
            for (String notification : orderNotifications) 
            {
                System.out.println(notification);
            }
        } 
        }
    }
    
    // Method to check for accepted orders and send notifications
    public void checkAcceptedOrdersAndNotify() 
    {
        for (Order order : orders) 
        {
            if ("Accepted".equals(order.getOrderStatus())) 
            {
                String notification = "\nOrder ID " + order.getOrderId() + " has been accepted by the vendor.";
                // Add notification to the order
                order.addNotification(notification);
            }
        }
    }

    // Display notifications for all orders
    public void displayOrderNotifications() 
    {
        for (Order order : orders) 
        {
            List<String> orderNotifications = order.getNotifications();
            for (String notification : orderNotifications) 
            {
                System.out.println(notification);
            }
        }
    }
    
    // Method to check for cancelled orders and send notifications
    public void checkCancelledOrdersAndNotify() 
    {
        for (Order order : orders) 
        {
            if ("Cancelled".equals(order.getOrderStatus())) 
            {
                String notification = "\nOrder ID " + order.getOrderId() + " has been cancelled by the vendor.";
                // Add notification to the order
                order.addNotification(notification);
            }
        }
    }

    // Display notifications for all orders
    public void displayCancelledOrderNotifications() 
    {
        for (Order order : orders) 
        {
            List<String> orderNotifications = order.getNotifications();
            for (String notification : orderNotifications) 
            {
                System.out.println(notification);
            }
        }
    }
}