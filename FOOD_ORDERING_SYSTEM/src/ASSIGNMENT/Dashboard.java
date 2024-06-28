/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASSIGNMENT;

import static ASSIGNMENT.Main.VendorPage;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Athelene
 */
public class Dashboard 
{
    Scanner scanner = new Scanner(System.in);
    // Method to calculate total revenue for a specific vendor
    public static double calculateVendorTotalRevenue(List<Item> items, String vendorId) 
    {
        double totalRevenue = 0.0;
        for (Item item : items) 
        {
            if (item.getVendorId().equals(vendorId)) 
            {
                totalRevenue += item.calculateRevenue();
            }
        }
        return totalRevenue;
    }

    // Method to display the revenue dashboard
    public static void displayRevenueDashboard(List<Item> items, String vendorId, Scanner scanner) 
    {
        System.out.println("");
        System.out.println("****************************************");
        System.out.println("\tVendor Revenue Dashboard");
        System.out.println("****************************************");

        System.out.println("\nVendor-wise Revenue:");
        double vendorRevenue = calculateVendorTotalRevenue(items, vendorId);
        System.out.println("\nVendor ID: " + vendorId + "\nTotal Revenue: $" + vendorRevenue);
        while (true) 
        {
            try 
            {
                System.out.println("\nPress [1] to return back");
                int selection = scanner.nextInt();

                if (selection == 1) 
                {
                    // Assuming VendorPage is a method in the same class
                    VendorPage(vendorId);
                    break;
                } 
                else 
                {
                    System.out.println("Invalid selection. Please enter [1] to return back to the Main Page.");
                }
            } 
            catch (InputMismatchException ex) 
            {
                System.out.println("Invalid input format. Please enter an integer input!");
                scanner.nextLine(); // Consume the invalid input to avoid an infinite loop
            }
        }
        // Close the scanner when done to avoid resource leak
        scanner.close();
    }
}