/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASSIGNMENT;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Athelene
 */
public class TaskFile 
{
    private static final String FILE_PATH = "tasks.txt";

    public void saveOrderToFile(Order order) 
    {
        try 
        {
            // Check if the order status is "Accepted"
            if ("Accepted".equals(order.getStatus())) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
                // Write order details to the file
                writer.write("Order ID: " + order.getOrderId());
                writer.newLine();
                Customer customer = order.getCustomer();
                writer.write("Customer Username: " + customer.getName());
                writer.newLine();
                writer.write("Customer Contact Number: " + customer.getContact());
                writer.newLine();
                //writer.write("Customer Address: " + customer.getAddress());
                //writer.newLine();
                writer.write("Order Details:");
                writer.newLine();
                for (String detail : order.getOrderDetails()) {
                    writer.write("- " + detail);
                    writer.newLine();
                }
                writer.newLine(); // Separate orders
                writer.close();
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
