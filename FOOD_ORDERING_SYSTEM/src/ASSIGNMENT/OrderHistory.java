
package ASSIGNMENT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderHistory {
    private static final String FILE_PATH = "Orders.txt";
    private List<Order> orders;
    private static final String ITEM_FILE_PATH = "items.txt";
    private Map<String, String> itemVendorMap;

    public OrderHistory() 
    {
        this.orders = new ArrayList<>();
        Read_Order_File();
        this.itemVendorMap = new HashMap<>();
        Read_Item_File();
    }
    
    public List<Order> getOrders() 
    {
        return orders;
    }
    
  private void Read_Order_File() 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("Orders.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] Data = line.split("/"); 
                if (Data.length >= 7) {
                    String customerid = Data[0].trim();
                    int orderId = Integer.parseInt(Data[1].trim());
                    String ItemName = Data[2].trim();
                    double Price = Double.parseDouble(Data[3].trim());
                    int Quantity = Integer.parseInt(Data[4].trim());
                    String Address = Data[5].trim();
                    String Status1 = Data[6].trim();
                    Date date1 = parseDate(Data[7].trim());
                    String orderType = Data[8].trim();
                    String orderstatus = Data[9].trim();
                    double orderamount = Double.parseDouble(Data[10].trim());
                    String Deliveryaddress = Data[11].trim();
                    orders.add(new Order(orderId, ItemName, Price, Quantity, Address,Status1, date1,orderType,orderstatus,orderamount,Deliveryaddress, customerid));
                    }
                }
            }
        
        catch (IOException e) 
        {
            // Handle exceptions (e.g., file not found, format error)
            e.printStackTrace();
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
       
        
    // Method to display the order history 
    public void Load_Order_History(String CustomerID) 
    {
        String customerID = CustomerID;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            System.out.printf("%-15s%-10s%-20s%-15s%-15s%-15s%n", "Customer ID", "Order ID", "Item Name", "Status", "Order Type", "Order Amount");
            System.out.println("**************************************************************************************");
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] Data = line.split("/"); 
                if (Data[0].trim().equals(customerID)) {
                    int orderId = Integer.parseInt(Data[1].trim());
                    String itemName = Data[2].trim();
                    double Price = Double.parseDouble(Data[3].trim());
                    int quantity = Integer.parseInt(Data[4].trim());
                    String address = Data[5].trim();
                    String Status = Data[6].trim();
                    String ordertype = Data[8].trim();
                    double orderamount = Double.parseDouble(Data[10].trim());
                    orders.add(new Order(orderId, itemName, Price, quantity, address, ordertype, orderamount));
                    System.out.printf("%-15s%-10d%-20s%-15s%-15s%-15.2f%n", customerID, orderId, itemName, Status, ordertype, orderamount);
                } 
            }
        }   
        catch (IOException e) {
            System.err.println("Error in reading the file: " + e.getMessage());
        }
    }
    
        // Method to display the order history 
    public void Load_Order_Status(String CustomerID) 
    {
        String customerID = CustomerID;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            System.out.println("Below show the order Status for each Order ID:");
            System.out.printf("%-15s%-10s%-15s%-15s%-15s%n", "Customer ID", "Order ID", "Status", "Order Type", "Order Amount");
            System.out.println("*********************************************************************************");
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] Data = line.split("/"); 
                if (Data[0].trim().equals(customerID)) {
                    int orderId = Integer.parseInt(Data[1].trim());
                    String itemName = Data[2].trim();
                    double Price = Double.parseDouble(Data[3].trim());
                    int quantity = Integer.parseInt(Data[4].trim());
                    String address = Data[5].trim();
                    String Status = Data[6].trim();
                    String ordertype = Data[8].trim();
                    double orderamount = Double.parseDouble(Data[10].trim());
                    orders.add(new Order(orderId, itemName, Price, quantity, address, ordertype, orderamount));
                    System.out.printf("%-15s%-10d%-15s%-15s%-15.2f%n", customerID, orderId, Status, ordertype, orderamount);
                } 
            }
        }   
        catch (IOException e) {
            System.err.println("Error in reading the file: " + e.getMessage());
        }
    }

    // Method to find an order by orderId
    public Order Find_OrderID( int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }
    
     public void cancelOrder(int orderId, String CustomerID) {
        Order order = Find_OrderID(orderId);
        if (order != null) {
            // Get order details from the loaded orders list
            String orderStatus = order.getOrderStatus();
            String orderType = order.getOrderType();
            double price = order.getPrice();
            int quantity = order.getQuantity();
            
            if ("Pending".equals(orderStatus) && "Delivery".equals(orderType)) {
                double deliveryFees = order.getDeliveryFees();
                double refundAmount = order.getTotalAmountWithDelivery(price,quantity) - deliveryFees;

                // Update customer's credit balance
                Customer_File_Operation.refundCreditBalance(CustomerID, refundAmount);

                // Update order status to canceled
                order.setOrderStatus("Canceled");
                String orderid = Integer.toString(orderId);
                Customer_File_Operation.Update_Order_Status(orderid, "Canceled");

                // Display and record cancellation details
                System.out.println("Order ID: " + order.getOrderId() + " canceled.");
                System.out.println("Refund Amount: " + refundAmount);

                Customer_File_Operation.update_Order_Cancellation_Details(order.getOrderId(), refundAmount);
            } else {
                System.out.println("Order cancellation not allowed at this stage.");
            }
        }
    }
     
    // Method to display the order history 
    public void Reorder_Order_History(String Customerid, String OrderID) 
    {
        String customerID =Customerid;
        String Orderid = OrderID;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] Data = line.split("/"); 
                if (Data.length >= 12 && Data[1].trim().equals(Orderid)) {
                    int orderId = Customer_File_Operation.generateNewOrderId();
                    String ItemName = Data[2].trim();
                    double Price = Double.parseDouble(Data[3].trim());
                    int Quantity = Integer.parseInt(Data[4].trim());
                    String Address = Data[5].trim();
                    String Status1 = Data[6].trim();
                    Date currentDate = new Date();
                    String orderType = Data[8].trim();
                    double orderamount = Double.parseDouble(Data[10].trim());
                    String Deliveryaddress = Data[11].trim();
                    orders.add(new Order(orderId, ItemName, Price, Quantity, Address, Status1, orderamount));
                    System.out.println("New order :");
                    System.out.println("***********************************************");
                    System.out.println("Customer ID: "+ customerID +", Order ID: " + orderId +", Item Name: " + ItemName + ", Order Status: " + Status1 + ", Order Type: " + orderType);
                    System.out.println("***********************************************");
                    Customer_File_Operation.Customer_Place_Order(orderId, ItemName, Price, Quantity, Address, orderType, currentDate, orderamount, Deliveryaddress, customerID);
                } 
            }
        }   
        catch (IOException e) {
            System.err.println("Error in reading the file: " + e.getMessage());
        }
    }

    
    private void Read_Item_File() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ITEM_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] data = line.split(",");
                if (data.length >= 7) 
                {
                    String itemName = data[1].trim();
                    String vendorId = data[6].trim();
                    itemVendorMap.put(itemName, vendorId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public void displayPendingOrders(String vendorId) {
    System.out.println("\nPending Orders for Vendor " + vendorId + ":");
    System.out.println("\n**********************************************************************");
    System.out.printf("%-10s%-20s%-15s%-15s%-15s%n", "Order ID", "Item Name", "Quantity", "Status", "Order Type");
    System.out.println("**********************************************************************");

    for (Order order : orders) {
        String itemName = order.getName();
        String orderVendorId = getVendorIdForItem(itemName);
        if (orderVendorId != null && orderVendorId.equals(vendorId) && order.getOrderStatus().equalsIgnoreCase("Pending")) {
            System.out.printf("%-10d%-20s%-15s%-15s%-15s%n",
                    order.getOrderId(),
                    itemName,
                    order.getQuantity(),
                    order.getOrderStatus(),
                    order.getOrderType());
        }
    }
}
    
    public void displayStatus(String vendorId) 
    {
    System.out.println("\nOrders for Vendor " + vendorId + ":");
    System.out.println("\n**********************************************************************");
    System.out.printf("%-10s%-20s%-15s%-15s%-15s%n", "Order ID", "Item Name", "Quantity", "Status", "Order Type");
    System.out.println("**********************************************************************");

    for (Order order : orders) 
    {
        String itemName = order.getName();
        String orderVendorId = getVendorIdForItem(itemName);
        if (orderVendorId != null && orderVendorId.equals(vendorId)) {
            System.out.printf("%-10d%-20s%-15s%-15s%-15s%n",
                    order.getOrderId(),
                    itemName,
                    order.getQuantity(),
                    order.getOrderStatus(),
                    order.getOrderType());
        }
    }
    }

    private String getVendorIdForItem(String itemName) 
    {
        return itemVendorMap.get(itemName);
    }
}
