/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASSIGNMENT;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/**
 *
 * @author Athelene
 */
public class ItemFile 
{
    private static final String FILE_PATH = "items.txt";
    private static List<Item> items;
    private List<String> notifications;
    private static final int LOW_STOCK_THRESHOLD = 10;
    
    public ItemFile() 
    {
        this.items = new ArrayList<>();
        this.notifications = new ArrayList<>();
        loadItemsFromFile();
    }

    private void loadItemsFromFile() 
    {
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) 
            {
                if (line.trim().isEmpty()) 
                {
                    // Skip processing empty lines
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length >= 7) 
                {
                    int itemId = Integer.parseInt(parts[0]);
                    String itemName = parts[1];
                    String itemDesc = parts[2];
                    double itemPrice = Double.parseDouble(parts[3]);
                    int itemQuantity = Integer.parseInt(parts[4]);
                    String itemAddress = parts[5];
                    String vendorId = parts[6];
                    items.add(new Item(itemId, itemName, itemDesc, itemPrice, itemQuantity, itemAddress, vendorId));
                } 
                else 
                {
                    // Handle the case where the line does not have enough elements
                    System.out.println("Invalid format on line: " + line);
                }
            }
        } 
        catch (IOException e) 
        {
            // Handle exceptions
            e.printStackTrace();
        }
    }  

    private void saveItemsToFile() 
    {
        try  
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Item item : items) 
            {
                writer.write(item.getItemId() + "," + item.getName() + "," + item.getDesc() + "," + item.getPrice() + "," + item.getQuantity() + "," + item.getAddress() + "," + item.getVendorId());
                writer.newLine();
            }
            writer.close();
        } 
        catch (IOException e) 
        {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    public void createItem(String itemName, String itemDesc, double itemPrice, int itemQuantity, String itemAddress, String vendorId) 
    {
        int newItemId = generateNewItemId();
        items.add(new Item(newItemId, itemName, itemDesc, itemPrice, itemQuantity, itemAddress, vendorId));
        saveItemsToFile();
        System.out.println("Item created successfully.");
    }
    
    private boolean isItemIdUsed(int itemId) 
    {
        for (Item item : items) 
        {
            if (item.getItemId() == itemId) 
            {
                return true; // Item ID is already used
            }
        }
        return false; // Item ID is not used
    }

    public Item readItem(int itemId) 
    {
        for (Item item : items) 
        {
            if (item.getItemId() == itemId) 
            {
                return item;
            }
        }
        // If itemId is not found, return null
        return null;
    }
    
    public void displayAllItems(String vendorId) 
    {
        String VendorId = vendorId;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) 
        {
            System.out.println("");
            System.out.println("***************************************************************");
            System.out.printf("%-15s%-20s%-15s%-15s%n", "Item ID", "Item Name", "Item Price", "Item Quantity");
            System.out.println("***************************************************************");
            
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(",");
                if (parts[6].trim().equals(VendorId))
                {
                    int itemId = Integer.parseInt(parts[0]);
                    String itemName = parts[1];
                    double itemPrice = Double.parseDouble(parts[3]);
                    int itemQuantity = Integer.parseInt(parts[4]);
                    System.out.printf("%-15d%-20s%-15.2f%-15d%n", itemId, itemName, itemPrice, itemQuantity);
                } 
            }
        }   
        catch (IOException e) 
        {
            System.err.println("Error in reading the file: " + e.getMessage());
        }
    }

public void updateItem(int itemId, String newName, String newDesc, double newPrice, int newQuantity, String newAddress) 
{
    Item itemToUpdate = null;

    for (Item item : items) 
    {
        if (item.getItemId() == itemId) 
        {
            // Update item details
            item.setName(newName);
            item.setDesc(newDesc);
            item.setPrice(newPrice);
            item.setQuantity(newQuantity);
            item.setAddress(newAddress);

            itemToUpdate = item;
            break;  // Exit the loop once the item is found and updated
        }
    }   

    if (itemToUpdate != null) 
    {
        saveItemsToFile();
        checkLowStockAndNotify();
        System.out.println("Item updated successfully.");
    } 
    else 
    {
        System.out.println("Item ID " + itemId + " not found. Please enter a valid item ID.");
    }
}



    public void deleteItem(int itemId) 
    {
        Item itemToRemove = null;
        for (Item item : items) 
        {
            if (item.getItemId() == itemId) 
            {
                itemToRemove = item;
                break;  // Exit the loop once the item is found
            }
        }

        if (itemToRemove != null) 
        {
            items.remove(itemToRemove);
            saveItemsToFile();
            System.out.println("Item deleted successfully.");
        } 
        else 
        {
            System.out.println("Item ID " + itemId + " not found. Please enter a valid item ID.");
        }
    }
    
    public List<Item> getItems() 
    {
        return items;
    }
    
    public void sendNotification(String message) 
    {
        notifications.add(message);
    }
    
    // Method to check for low stock and send notifications
    public void checkLowStockAndNotify() 
    {
        for (Item item : items) 
        {
            item.clearNotifications();
            if (item.getQuantity() < LOW_STOCK_THRESHOLD) 
            {
                String notification = "Low stock alert for ItemID: " + item.getItemId();
                // Add notification to the item
                item.addNotification(notification);
            }
        }
    }
    
    // Display notifications for all items
    public void displayNotifications() 
    {
        for (Item item : items) 
        {
            List<String> itemNotifications = item.getNotifications();
            for (String notification : itemNotifications) 
            {
                System.out.println(notification);
            }
        }
    }
    
    // Method to check for low stock and send notifications
    public void checkLowStockAndNotify1() 
    {
        for (Item item : items) 
        {
            item.clearNotifications();
            if (item.getQuantity() < LOW_STOCK_THRESHOLD) 
            {
                String notification = "\nLow stock alert for ItemID: " + item.getItemId() + "\nName: " + item.getName() +". \nCurrent stock level: " + item.getQuantity();
                // Add notification to the item
                item.addNotification1(notification);
            }
        }
    }

    // Display notifications for all items
    public void displayNotifications1() 
    {
        for (Item item : items) 
        {
            List<String> itemNotifications = item.getNotifications1();
            for (String notification : itemNotifications) 
            {
                System.out.println(notification);
            }
        }
    }
    
    // ID auto increment
    public static int generateNewItemId()
    {
        int newItemId = 1;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");
                if (parts.length >= 1 && !parts[0].isEmpty()) 
                {
                    int itemId = Integer.parseInt(parts[0]);
                    newItemId = Math.max(newItemId, itemId + 1);
                } 
                else 
                {
                    // Handle the case where the line does not have enough elements
                    System.out.println("Invalid format on line: " + line);
                }
            }
        }
        catch (IOException e) 
        {
            // Handle exceptions
            e.printStackTrace();
            System.err.println("Error generating new item ID: " + e.getMessage());
        }
        return newItemId;
    }
    
    public List<Item> getVendorItems(String vendorId) 
    {
        List<Item> vendorItems = new ArrayList<>();
        for (Item item : items) 
        {
            if (item.getVendorId().equals(vendorId)) 
            {
                vendorItems.add(item);
            }
        }
        return vendorItems;
    }
    
    public Item getItemByName(String itemName) 
    {
        for (Item item : items) 
        {
            if (item.getName().equals(itemName)) 
            {
                return item;
            }
        }
        return null;  // Item not found
    }
    
    public void viewOrderHistory(String vendorId, String period) 
    {
        OrderHistory orderHistory = new OrderHistory();
        List<Item> vendorItems = getVendorItems(vendorId);

        System.out.println("\nOrder History for Vendor " + vendorId + " - " + period + ":");
        System.out.println("\n**********************************************************************************************************************");
        System.out.printf("%-10s%-20s%-15s%-15s%-15s%-15s%-15s%n", "Order ID", "Item Name","Price", "Quantity", "Order Status", "Order Type", "Order Date");
        System.out.println("**********************************************************************************************************************");
        Calendar calendar = Calendar.getInstance();

        for (Order order : orderHistory.getOrders()) 
        {
            for (Item item : vendorItems) 
            {
                if (item.getName().equalsIgnoreCase(order.getName())) 
                {
                    // Check the order date based on the specified period
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

                    // Compare the order date with the calculated start date
                    if (order.getDate().after(calendar.getTime())) 
                    {
                            System.out.printf("%-10d%-20s%-15s%-15s%-15s%-15s%-15s%n",
                                    order.getOrderId(),
                                    order.getName(),
                                    order.getPrice(),
                                    order.getQuantity(),
                                    order.getOrderStatus(),
                                    order.getOrderType(),
                                    order.getDate());
                    }

                    // Reset the calendar for the next iteration
                    calendar = Calendar.getInstance();

                    break;
                }
            }
        }
    }
    public void deductOrderedQuantity(String itemName, int orderedQuantity) 
    {
        for (Item item : items) 
        {
            if (item.getName().equalsIgnoreCase(itemName)) 
            {
                int availableQuantity = item.getQuantity();
                if (availableQuantity >= orderedQuantity) 
                {
                    // Deduct the ordered quantity from available quantity
                    item.setQuantity(availableQuantity - orderedQuantity);
                    saveItemsToFile();
                    System.out.println("Ordered quantity deducted from available quantity.");
                } 
                else 
                {
                    System.out.println("Error: Ordered quantity exceeds available quantity.");
                }
                return; // No need to continue searching
            }
        }
        // If the item is not found
        System.out.println("Error: Item not found in the menu.");
    }
}