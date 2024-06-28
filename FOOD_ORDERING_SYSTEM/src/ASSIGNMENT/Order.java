/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASSIGNMENT;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Athelene
 */
public class Order 
{
    private String CustomerId;
    private int orderId;
    private String itemName;
    private double itemPrice;
    private String ordertype;
    private int quantity;
    private String status;
    private String address;
    private String DeliveryAddress;
    private Date date;
    private String formattedDate;
    private String orderStatus;
    private Customer customer;
    private double totalAmount;
    private List<String> orderDetails;
    private List<Order> orders;
    private static final double FixedDeliveryFee = 4.0;
    private static int orderIdCounter = 1;
    private List<String> notifications;

    public Order() 
    {
        this.orders = new ArrayList<>();
    }
        
    public Order(int orderId, String itemName, double itemPrice, int quantity, String address, String Orderstatus,
                 Date date, String orderType, String DeliveryStatus,double totalAmount,String DeliveryAddress, String CustomerID)
    {
        this.orderId = orderId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.address = address;
        this.orderStatus = Orderstatus;
        this.date = date;
        this.ordertype = orderType;
        this.status = DeliveryStatus;
        this.totalAmount = totalAmount;
        this.DeliveryAddress = DeliveryAddress;
        this.CustomerId = CustomerID;
    }
    
    public Order(int orderId, String itemName, double itemPrice, int quantity, String status, Date date) 
    {
        this.orderId = orderId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.status = status;
        this.date = date;
        this.customer = customer;
        this.orderDetails = new ArrayList<>();
    }
    
    public Order(int orderId, String itemName, double itemPrice, int quantity, String address, String ordertype, Date date) 
    {
        this.orderId = orderId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.address = address;
        this.ordertype = ordertype;
        this.date = date;
    }
    
        public Order(int orderId, String itemName, double itemPrice, int quantity, String address, String ordertype, double totalAmount) 
    {
        this.orderId = orderId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.address = address;
        this.ordertype = ordertype;
        this.totalAmount = totalAmount;
    }
    
    public Order(int id, String item, String orderType) 
    {
        this.orderId = id;
        this.itemName = item;
        this.ordertype = orderType;
        this.orderDetails = new ArrayList<>();
    }
           
    public int getOrderId() 
    {
        return orderId;
    }
    
    public String getName() 
    {
        return itemName;
    }

    public double getPrice() 
    {
        return itemPrice;
    }
    
    public int getQuantity() 
    {
        return quantity;
    }
    
    public String getStatus() 
    {
        return status;
    }
    
    public Date getDate() 
    {
        return date;
    }
    
    public Customer getCustomer() 
    {
        return customer;
    }

    public List<String> getOrderDetails() 
    {
        return orderDetails;
    }

    public void addOrderDetail(String orderDetail) 
    {
        orderDetails.add(orderDetail);
    }
    
    public void setStatus(String status) 
    {
        this.status = status;
    }
    
    public String toString() 
    {
        return "Order: \nOrder ID = " + orderId + "\nName = " + itemName +"\nPrice = " + itemPrice + "\nQuantity = " + quantity;
    }
    
    public String getOrderStatus() 
    {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) 
    {
        this.orderStatus = orderStatus;
    }
        
    public String getOrderType() 
    {
        return ordertype;
    }

    public void setOrderType(String ordertype) 
    {
        this.ordertype = ordertype;
    }
        
    public String getCustomerId() 
    {
        return CustomerId;
    }

    public void setCustomerId(String CustomerId) 
    {
        this.CustomerId = CustomerId;
    }
    
    public double getDeliveryFees() 
    {
        return FixedDeliveryFee;
    }
    
    public double getTotalAmountWithDelivery(double price, int quantity) 
    {
        double TotalAmount = price*quantity;
        return TotalAmount + FixedDeliveryFee;
    }  
    
    public List<String> getNotifications() 
    {
        return notifications;
    }
    
    public void addNotification(String notification) 
    {
        if (this.notifications != null) {
            this.notifications.clear();
            this.notifications.add(notification);
        } else {
            // Initialize the list if it's null
            this.notifications = new ArrayList<>();
            this.notifications.add(notification);
        }
    }
    
    // Clear notifications method
    public void clearNotifications() 
    {
       if (this.notifications != null) {
        this.notifications.clear();
        }
    }
}
