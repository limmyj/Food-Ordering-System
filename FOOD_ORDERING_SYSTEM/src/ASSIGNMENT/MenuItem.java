/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASSIGNMENT;

/**
 *
 * @author wwjie
 */
public class MenuItem {
    private String itemName;
    private String description;
    private double price;
    private int quantity;
    private String address;

    // Constructor with arguments
    public MenuItem(String itemName,double price, int quantity, String address) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
    }
    
    //get method
    public String getItemName() {
        return itemName;
    }
    
    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getAddress() {
        return address;
    }
}
