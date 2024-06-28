package ASSIGNMENT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static ASSIGNMENT.File_Operation.UserList;

public class Customer_File_Operation {
    // Customer - Write food review
    public static void writeReview(String filename, String customerId, int orderId, String review) {
        File fileinput = new File(filename);
        try {
            FileWriter fw = new FileWriter(fileinput, true);
            BufferedWriter bw = new BufferedWriter(fw);
            try (PrintWriter pw = new PrintWriter(bw)) {
                String line = customerId + "/" + orderId + "/" + review;
                bw.newLine();
                pw.write(line);
            }
        } catch (IOException ex) {
            System.out.println("File does not exist: " + ex.getMessage());
        }
    }
    
    
    // Customers read menu file
    public static void Read_Data_From_Vendor_Menu(String filename) {
    	UserList.clear();
        System.out.printf("%-15s%-18s%-12s%-15s%-15s%n", "Item Name", "Food Details", "Pricing", "Quantity", "Address");
        System.out.println("*******************************************************************************");
    	try {
    		FileReader fr = new FileReader(filename);
                try (BufferedReader br = new BufferedReader(fr)) {
                    String line;
                    while ((line = br.readLine())!= null)
                    {
                        String[] column = line.split(",");
                        if(column.length == 7) {
                            String itemName = column[1].trim();
                            String description = column[2].trim();
                            double Price = Double.parseDouble(column[3].trim());
                            int Quantity = Integer.parseInt(column[4].trim());
                            String Address = column[5].trim();
                            System.out.printf("%-15s%-18s%-12.2f%-15d%-15s%n", itemName, description, Price, Quantity, Address);
                        }
                    }
                }
    	}
    	catch(IOException ex) {
    		System.out.println("Error in reading item file: " + ex.getMessage());
    	}
    }
    
    public static List<MenuItem> getMenuItems(String selectedItem) {
        List<MenuItem> menuItems = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("items.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length == 7) {
                    String itemName = columns[1].trim();
                    double price = Double.parseDouble(columns[3].trim());
                    int quantity = Integer.parseInt(columns[4].trim());
                    String address = columns[5].trim();

                    if (itemName.equalsIgnoreCase(selectedItem)) {
                        // Add the menu item to the list
                        MenuItem menuItem = new MenuItem(itemName, price, quantity, address);
                        menuItems.add(menuItem);
                    }
                }
            }
        } catch (IOException ex) {
            System.err.println("Error in reading the menu file: " + ex.getMessage());
        }

        return menuItems;
    }
     
public static double calculateOrderAmount(String id, List<MenuItem> selectedItems, int quantity) {
    double totalAmount = 0.0;

    try (PrintWriter wr = new PrintWriter(new FileWriter("foodcard.txt", true))) {
        try (BufferedReader br = new BufferedReader(new FileReader("Customers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("/");
                for (String word : data) {
                    if (word.equals(id) && data.length == 6) {
                        String userid = data[0].trim();
                        String username = data[2].trim();
                        double creditBalance = Double.parseDouble(data[5].trim());

                        // Calculate total order amount
                        for (MenuItem item : selectedItems) {
                            double price = item.getPrice();
                            double itemAmount = price * quantity;
                            totalAmount += itemAmount;
                        }

                        // Check if credit balance is sufficient
                        if (creditBalance >= totalAmount) {
                            wr.println("Customer ID: " + userid);
                            wr.println("Customer Name: " + username);
                            wr.println("Credit Balance: " + creditBalance);

                            // Record each selected item
                            for (MenuItem item : selectedItems) {
                                double price = item.getPrice();
                                double itemAmount = price * quantity;

                                // Display and record item details
                                wr.println("  - Item: " + item.getItemName());
                                wr.println("    Price: " + price);
                                wr.println("    Quantity: " + quantity);
                                wr.println("    Item Amount: " + itemAmount);
                            }

                            // Display and record total order amount
                            wr.println("Total Order Amount: " + totalAmount);
                            wr.println();

                            // Deduct order amount from credit balance
                            creditBalance -= totalAmount;

                            // Update credit balance in the file
                            Update_Credit_Balance(id, creditBalance);

                        } else {
                            System.out.println("Insufficient credit balance. Order cannot be processed.");
                        }

                        break;
                    }
                }
            }
        }
    } catch (IOException | NumberFormatException e) {
        System.err.println("Error processing order: " + e.getMessage());
    }

    return totalAmount;
}
        
    public static int generateNewOrderId() {
    int newOrderId = 1; // Default starting order ID

    try (BufferedReader reader = new BufferedReader(new FileReader("Orders.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split("/");

            if (data.length >= 2) {
                int orderId = Integer.parseInt(data[1].trim());
                newOrderId = Math.max(newOrderId, orderId + 1);
            } else {
                System.out.println("Invalid format on line: " + line);
            }
        }
    } catch (IOException e) {
        System.err.println("Error generating new order ID: " + e.getMessage());
    }

    return newOrderId;
}


    // Customer - Write data for into order.txt
    public static void Customer_Place_Order(int orderId, String itemName, double itemPrice, int quantity, String pickupaddress, String ordertype, Date date, double totalamount, String deliveryAddress, String CustomerID) 
    {
    	try 
        {
            FileWriter fw = new FileWriter("Orders.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
                try (PrintWriter pw = new PrintWriter(bw)) {
                    String line = CustomerID + "/"+ orderId + "/" + itemName + "/" + itemPrice + "/" + quantity + "/" + pickupaddress + "/"+"Pending" + "/"+ date + "/"+ ordertype + "/"+ "Pending"+ "/" + totalamount + "/" +deliveryAddress;
                    bw.newLine();
                    pw.write(line);
                }
    	}
    	catch(IOException ex)
        {
            System.out.println("File does not exist: " + ex.getMessage());
    	}
    }
       
    // Method to refund credit balance
    public static void refundCreditBalance(String customerId, double refundAmount) 
    {
        try (BufferedReader br = new BufferedReader(new FileReader("Customers.txt"))) {
            StringBuilder updatedData = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split("/");
                if (data.length == 6 && data[0].trim().equals(customerId)) {
                    double currentCreditBalance = Double.parseDouble(data[5].trim());
                    double updatedCreditBalance = currentCreditBalance + refundAmount;
                    data[5] = String.valueOf(updatedCreditBalance);
                }

                updatedData.append(String.join("/", data)).append("\n");
            }

            // Update the customer data in the file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("Customers.txt"))) {
                bw.write(updatedData.toString());
            }
        } catch (IOException e) {
            System.err.println("Error updating credit balance: " + e.getMessage());
        }
    }
    
    // Method to update order cancellation details in a file
    public static void update_Order_Cancellation_Details(int orderId, double refundAmount) 
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter("order_cancellations.txt", true))) {
            String cancellationDetails = "Order ID: " + orderId + ", Refund Amount: " + refundAmount;
            writer.println(cancellationDetails);
            System.out.println("Cancellation details recorded in Order Cancellation text file.");
        }   
        catch (IOException e) {
            System.err.println("Error writing cancellation details to file: " + e.getMessage());
        }
    }
    
    public static void Check_Transaction_History(){
        try (BufferedReader reader = new BufferedReader(new FileReader("foodcard.txt"))) {
        String line;
        System.out.println("------------- All Transaction History ----------------");
            while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        } 
        catch (IOException e) {
            System.err.println("Error reading foodcard.txt: " + e.getMessage());
        }
    }
    
    public static void Update_Credit_Balance(String customerId, double newCreditBalance) {
        try {
            // Read all lines from Customers.txt
            List<String> lines = Files.readAllLines(Paths.get("Customers.txt"));

            for (int i = 0; i < lines.size(); i++) {
                String[] data = lines.get(i).split("/");
                if (data.length == 6 && data[0].trim().equals(customerId)) {
                    // Update the credit balance
                    data[5] = " " + newCreditBalance;

                    // Join the data back into a line
                    lines.set(i, String.join("/", data));
                    break;
                }
            }

            // Write the modified lines back to Customers.txt
            Files.write(Paths.get("Customers.txt"), lines);
        } catch (IOException e) {
            System.err.println("Error updating credit balance: " + e.getMessage());
        }
    }
    
    public static void Update_Order_Status(String orderid, String orderstatus){
    try{
            List<String> lines = Files.readAllLines(Paths.get("Orders.txt"));

            for (int i = 0; i < lines.size(); i++) {
                String[] data = lines.get(i).split("/");
                if (data[1].trim().equals(orderid)) {
                    // Update the order status
                    data[6] = " " + orderstatus;

                    // Join the data back into a line
                    lines.set(i, String.join("/", data));
                    break;
                }
            }

            // Write the modified lines back to Customers.txt
            Files.write(Paths.get("Orders.txt"), lines);
        } catch (IOException e) {
            System.err.println("Error updating credit balance: " + e.getMessage());
        }
    }
    
    public static void View_review(String customerId) {
    try (BufferedReader customerReader = new BufferedReader(new FileReader("Customers.txt"));
         BufferedReader reviewReader = new BufferedReader(new FileReader("Review.txt"))) {

        // Find the customer name based on the provided customer ID
        String customerName = null;
        String customerLine;
        while ((customerLine = customerReader.readLine()) != null) {
            String[] customerData = customerLine.split("/");
            if (customerData.length >= 6 && customerData[0].trim().equals(customerId)) {
                customerName = customerData[2].trim(); 
                break;
            }
        }

        if (customerName != null) {
            System.out.println("----------------- All Review ---------------------");
            System.out.println("Reviews from Customer ID: " + customerId + " (" + customerName + ")");
            String reviewLine;
            boolean found = false;

            while ((reviewLine = reviewReader.readLine()) != null) {
                String[] reviewData = reviewLine.split("/");

                if (reviewData.length >= 3 && reviewData[0].trim().equals(customerId)) {
                    // Extract data from the line
                    int orderId = Integer.parseInt(reviewData[1].trim());
                    String review = reviewData[2].trim();

                    // Display the information
                    System.out.println("Order ID: " + orderId);
                    System.out.println("Review: " + review);
                    System.out.println("***********************************************");

                    found = true;
                }
            }

            if (!found) {
                System.out.println("No reviews found for Customer ID: " + customerId);
            }
        } else {
            System.out.println("Customer not found for ID: " + customerId);
        }
        } catch (IOException e) {
            System.err.println("Error viewing review: " + e.getMessage());
        }
}
        public static Double getCreditBalance(String customerId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("/");
                if (data.length == 6 && data[0].trim().equals(customerId)) {
                    return Double.valueOf(data[5].trim());
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading credit balance: " + e.getMessage());
        }
        return null;
    }

}
