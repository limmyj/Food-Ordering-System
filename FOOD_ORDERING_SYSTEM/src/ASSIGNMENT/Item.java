/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASSIGNMENT;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Athelene
 */
public class Item 
{
    private int itemId;
    private String name;
    private String desc;
    private double price;
    private int quantity;
    private String address;
    private String vendorId;
    private List<String> notifications;
    private List<String> notificationsDetails;

    public Item(int itemID, String name, String desc, double price, int quantity, String address, String vendorId) 
    {
        this.itemId = itemID;
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.quantity = quantity;
        this.address = address;
        this.vendorId = vendorId;
        this.notifications = new ArrayList<>();
        this.notificationsDetails = new ArrayList<>();
    }
    
    public String getName() 
    {
        return name;
    }

    public double getPrice() 
    {
        return price;
    }

    public String getDesc() 
    {
        return desc;
    }
    
    public int getQuantity() 
    {
        return quantity;
    }
    
    public int getItemId() 
    {
        return itemId;
    }
    
    public String getAddress() 
    {
        return address;
    }

    public String getVendorId() 
    {
        return vendorId;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }

    public void setDesc(String desc) 
    {
        this.desc = desc;
    }

    public void setPrice(double price) 
    {
        this.price = price;
    }

    public void setQuantity(int quantity) 
    {
        this.quantity = quantity;
    }
    
    public void setAddress(String address) 
    {
        this.address = address;
    }
    
    public String toString() 
    {
        return "Item ID: " + itemId + "\nName: " + name + "\nDescription: " + desc +"\nPrice: " + price + "\nQuantity: " + quantity + "\nRestaurant: " + address;
    }
    
    // Method to calculate the total revenue for an item
    public double calculateRevenue() 
    {
        return getPrice() * getQuantity();
    }
    
    public List<String> getNotifications() 
    {
        return notifications;
    }
    
    public List<String> getNotifications1() 
    {
        return notificationsDetails;
    }

    public void addNotification(String notification) 
    {
        notifications.clear();
        notifications.add(notification);
    }
    
    public void addNotification1(String notification) 
    {
        notificationsDetails.clear();
        notificationsDetails.add(notification);
    }
    
    // Clear notifications method
    public void clearNotifications() 
    {
        notifications.clear();
        notificationsDetails.clear();
    }
}
