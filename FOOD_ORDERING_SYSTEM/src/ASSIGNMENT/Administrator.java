/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASSIGNMENT;

public class Administrator extends User implements Notification 
{
    static File_Operation fop;
    // Constructor
    public Administrator(String id, String pw) 
    {
            super(id, pw);
            fop = new File_Operation();
    }

    // Create New Vendor
    public static void create_vendor(String id, String pw, String types) 
    {
        // Check if the user with the given ID already exists
        if (File_Operation.isUserExist("Users.txt", id)) 
        {
            System.out.println("User with ID " + id + " already exists. Please choose a different ID.");
            return;  // Exit the method, as user creation failed
        }

        // User does not exist, proceed with creating the new customer
	File_Operation.writeData("Vendors.txt", id, pw);
	File_Operation.writeData_User("Users.txt", id, pw, types);
	System.out.println("A new Vendor user is created successfully.");
    }

    // Create New Customer
    public static void create_Customer(String id, String pw, String types) 
    {
	// Check if the user with the given ID already exists
	if (File_Operation.isUserExist("Users.txt", id))
        {
	    System.out.println("User with ID " + id + " already exists. Please choose a different ID.");
	    return;  // Exit the method, as user creation failed
	}

	// User does not exist, proceed with creating the new customer
	File_Operation.writeData_Customer("Customers.txt", id, pw);
	File_Operation.writeData_User("Users.txt", id, pw, types);
	System.out.println("A new Customer user is created successfully.");
    }
	
    // Create New Runner
    public static void create_Runner(String id, String pw, String types) 
    {
	// Check if the user with the given ID already exists
	if (File_Operation.isUserExist("Users.txt", id))
        {
	    System.out.println("User with ID " + id + " already exists. Please choose a different ID.");
	    return;  // Exit the method, as user creation failed
	}

	// User does not exist, proceed with creating the new customer
	File_Operation.writeData("Runners.txt", id, pw);
	File_Operation.writeData_User("Users.txt", id, pw, types);
	System.out.println("A new Runner user is created successfully.");
    }
	
    // Read all Customers from Customers.txt
    public static void read_all_Customers() 
    {
        System.out.println("Reading data from Customers.txt ...");
        File_Operation.Read_Data_From_File_Customers("Customers.txt");
    }
	
    // Read all Vendors from Vendors.txt
    public static void read_all_Vendors() 
    {
        System.out.println("Reading data from Vendors.txt ...");
        File_Operation.Read_Data_From_File_Vendors("Vendors.txt");
    }
	
    // Read all Runners from Runners.txt
    public static void read_all_Runners() 
    {
        System.out.println("Reading data from Runners.txt ...");
        File_Operation.Read_Data_From_File_Runners("Runners.txt");
    }
	
    // Read specific Customer data
    public static void read_specific_Customers(String id) 
    {
        File_Operation.Search_Specific_Data("Customers.txt", id);
    }
	
    // Read specific Vendor data
    public static void read_specific_Vendors(String id) 
    {
        File_Operation.Search_Specific_Data("Vendors.txt", id);
    }
	
    // Read specific Runner data
    public static void read_specific_Runners(String id) 
    {
        File_Operation.Search_Specific_Data("Runners.txt", id);
    }
	
    // Update Customer details - Get the current Customer details
    public static void update_Customer(String id) 
    {
        File_Operation.Update_User_Details_Customer("Customers.txt", id);
    }
	
    // Update Customer details - Update to the latest Customer details
    public static void update_Customer2(String id, String newUsername, String newEmail, int newContact) 
    {
        File_Operation.updateUserData_Customer("Customers.txt", id, newUsername, newEmail, newContact);
    }
	
    // Update Vendor details - Get the current Vendor details
    public static void update_Vendor(String id) 
    {
        File_Operation.Update_User_Details("Vendors.txt", id);
    }
	
    // Update Vendor details - Update to the latest Vendor details
    public static void update_Vendor2(String id,String newUsername, String newEmail, int newContact) 
    {
        File_Operation.updateUserData("Vendors.txt", id, newUsername, newEmail, newContact);
    }
	
    // Update Runner details - Get the current Runner details
    public static void update_Runner(String id) 
    {
        File_Operation.Update_User_Details("Runners.txt", id);
    }
	
    // Update Runner details - Update to the latest Runner details
    public static void update_Runner2(String id ,String newUsername, String newEmail, int newContact) 
    {
        File_Operation.updateUserData("Runners.txt", id, newUsername, newEmail, newContact);
    }
	
    // Delete Customer 
    public static void delete_Customer(String id) 
    {
        File_Operation.deleteUser(id);
        File_Operation.deleteFromUserTypesFiles(id, "Customers.txt");		
    }
	
    // Delete Vendor 
    public static void delete_Vendor(String id) 
    {
        File_Operation.deleteUser(id);
        File_Operation.deleteFromUserTypesFiles(id, "Vendors.txt");
    }
	
    // Delete Runner
    public static void delete_Runner(String id) 
    {
        File_Operation.deleteUser(id);
        File_Operation.deleteFromUserTypesFiles(id, "Runners.txt");
    }


    // Get Customer Credit Details
    public static void getCreditDetails(String id) 
    {
        File_Operation.ShowCreditDetails(id);
    }
	
    // Top-up Credit and update the Customers.txt file
    public static void topupCredit(String id, int amount) 
    {
        System.out.println("Top-up credit in progress... Wait a moment.");
        File_Operation.TopUpCredit(id, amount);
    }
	
    // Override Notification interface method - Generate and Send Receipt
    static void generateAndSendReceipt(String id, int amount) 
    {
        System.out.println("Notification interface: Generating and sending receipt.");
        Notification.generateAndSendReceipt(id, amount);
    }

    // To view the receipt generated
    public static void viewLatestReceipt(String id) 
    {
        File_Operation.viewLatestReceipt(id);
    }

    // To view receipt history for customers
    public static void viewReceipt(String id) 
    {
        File_Operation.viewReceipt(id);
    }

    @Override
    public void viewProfile()
    {
        File_Operation.Read_Admin();
    }
}
