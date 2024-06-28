/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASSIGNMENT;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 *
 * @author Athelene
 */
public class Main 
{
    public static String id;
    public static String pw;
    public static int choice;
    public static int count = 0;
    static File_Operation fop;
    private static List<Vendor> vendors = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    
    // Login Page
    public static void LoginPage() 
    {
	Scanner sc = new Scanner(System.in);
	String id, pw;
	int attempt = 0;

	while (attempt <= 3) 
        {
            System.out.println("******************************************");
            System.out.println("\t\tLogin Page");
            System.out.println("******************************************");
            System.out.println("Enter the following information:\n");
            System.out.print("ID: ");
            id = sc.next();
            System.out.print("Password: ");
            pw = sc.next();

            if (fop.searchDataLogin("Users.txt", id, pw)) 
            {
                String userType = fop.getUserType("Users.txt", id, pw);
                System.out.println("Log in successfully!");

                // Navigate based on user type
                switch (userType) 
                {
                    case "Administrator":
                        AdminPage();
                        break;
                        
                    case "Customer":
                        CustomerPage(id);
                        break;
                        
                    case "Vendor":
                        VendorPage(id);
                        break;
                        
                    case "Runner":
                        RunnerPage();
                        break;
	                
                    default:
                    System.out.println("Unknown user type");
                }
                break;
            } 
            else 
            {
                System.out.println("User not found or incorrect password. Try again!");
                System.out.println("You have " + (3 - attempt) + " attempts left.");
                System.out.println();
                attempt++;
            }
        }

        if (attempt > 3) 
        {
            System.out.println("Out of attempts.");
        }
    }
	
    // Admin Page
    public static void AdminPage() 
    {
	Scanner sc = new Scanner(System.in);
	System.out.println("");
	System.out.println("***********************************************");
	System.out.println("\t\tWelcome, Admin!");
	System.out.println("***********************************************");
	System.out.println("Choose the number for correspond feature.\n");
        System.out.println("[1] Create\t        [2] Read\t[3] Update\t[4] Delete ");
	System.out.println("[5] Top-up Credit\t[6] View Receipt\t[7] View Profile ");
        System.out.println("[8] Log Out");
	System.out.println();
        System.out.print("Select the feature: ");
	try 
        {
            choice = sc.nextInt();
		
            if (choice == 1) 
            {
				Create();
			}
			else if (choice == 2) {
				ReadPage();
			}
			else if (choice == 3) {
				UpdatePage();
			}
			else if (choice == 4) {
				DeletePage();
			}
			else if (choice == 5) {
				topUpPage();
			}
			else if (choice == 6) {
				ViewReceiptPage();
			}
                        else if (choice == 7){
                                viewAdminPage();
                        }
			else if (choice == 8) {
				System.out.println("Logging out...");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("Logged out successfully!");
				System.out.println();
				System.out.println();
				LoginPage();
	
			}
			else{
				System.out.println("Invalid number!");
				AdminPage();
			}
		} catch(InputMismatchException ex) {
			System.out.println("Invalid input data type, try again.");
			AdminPage();
		}
	}
	
    // Vendor Page
    public static void VendorPage(String vendorId) 
    {
        ItemFile itemManager = new ItemFile();
        OrderFile orderManager = new OrderFile();
        Scanner scanner = new Scanner(System.in);
        Scanner scannerOrder = new Scanner(System.in);
        
        List<Vendor> vendors = File_Operation.Read_Data_From_File_Vendors1("vendors.txt");

            System.out.println("");
            System.out.println("**************************************");
            System.out.println("\tWelcome, Vendor " + vendorId + "!");
            System.out.println("**************************************");
            
            // Display notifications
            itemManager.checkLowStockAndNotify();
            System.out.println("\nNotifications:");
            itemManager.displayNotifications();
            
            System.out.println("\nChoose the number for correspond feature.\n");
            System.out.println("[1] Manage Items");
            System.out.println("[2] Manager Orders");
            System.out.println("[3] Revenue Dashboard");
            System.out.println("[4] Customer Review");
            System.out.println("[5] Profile Page");
            System.out.println("[6] Notifications");
            System.out.println("[7] Log Out");
            System.out.println();
            System.out.print("Select the feature: ");

            try 
            {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) 
                {
                    case 1:
                        manageItems(itemManager, scanner, vendorId);
                        break;

                    case 2:
                        manageOrders(orderManager, itemManager, scannerOrder, vendorId);
                        break;
                        
                    case 3:
                        // Assuming you have a method to get the items for a specific vendor
                        List<Item> vendorItems = itemManager.getVendorItems(vendorId);
                        Dashboard.displayRevenueDashboard(vendorItems, vendorId, scanner);
                        break;
                        
                    case 4:
                        viewCustomerReviews(vendorId, scanner);
                        break;

                    case 5:
                        viewVendorProfilePage(vendors, vendorId, scanner);
                        break;
                        
                    case 6:
                        viewNotificationsPage(itemManager, scanner, vendorId);
                        break;
                        
                    case 7:
                        System.out.println("Logging out...");
                        System.out.println("");
                        System.out.println("");
                        System.out.println("");
                        System.out.println("");
                        System.out.println("Logged out successfully!");
                        System.out.println();
                        System.out.println();
                LoginPage();
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }            
            } 
            catch (NumberFormatException e) 
            {
                e.printStackTrace();
            }

        // Close scanners
        scanner.close();
        scannerOrder.close(); 
    }
        
// Customer Page
	public static void CustomerPage(String id) {
        OrderFile order = new OrderFile();    
        Scanner scanner = new Scanner(System.in);    
        List<Customer> Customers = File_Operation.Read_Data_From_File_Customer1("Customers.txt",id); 
        
	Scanner sc = new Scanner(System.in);
        String CustomerID = id;
	System.out.println("");
	System.out.println("***********************************************");
	System.out.println("\t\tWelcome, Customer main page!");
	System.out.println("***********************************************");
        
        //Display Notification 
        order.checkorderStatus();
        System.out.println("Notifications:");
        order.displayNotifications();
        
	System.out.println("Choose the number for correspond feature.\n");
	System.out.println("[1] View Menu\t        [2] Review\t[3] Place or Cancel Order ");
	System.out.println("[4] Check Order Details[5] Generate Transaction Receipt[6] Reorder");
	System.out.println("[7] View Profile\t[8] Log Out");
	System.out.println();
	System.out.print("Select the feature: ");        
        
        try 
        {
            choice = sc.nextInt();
		
            if (choice == 1) 
            {
                Customer.view_Menu();
                Scanner sc2 = new Scanner(System.in);
                System.out.println("Enter [1] to back to the customer main page.");
                String num = sc2.nextLine();
                if ("1".equals(num))
                {
                    CustomerPage(CustomerID);
                }
                else 
                {
                    LoginPage();
                }
            }
            else if (choice == 2) 
            {
                ReviewPage(CustomerID);
            }
            else if (choice == 3) 
            {
                PlaceOrCancelOrderPage(CustomerID);
            }
            else if (choice == 4) 
            {
                OrderDetailsPage(CustomerID);
            }
            else if (choice == 5) 
            {
                Customer_File_Operation.Check_Transaction_History();
                Scanner sc2 = new Scanner(System.in);
                System.out.println("Enter [1] to back to the customer main page.");
                String num = sc2.nextLine();
                if ("1".equals(num))
                {
                    CustomerPage(CustomerID);
                }
                else 
                {
                    LoginPage();
                }
            }
            else if (choice == 6) 
            {
                Customer.reorder(CustomerID);
                Scanner sc2 = new Scanner(System.in);
                System.out.println("Enter [1] to back to the customer main page.");
                String num = sc2.nextLine();
                if ("1".equals(num))
                {
                    CustomerPage(CustomerID);
                }
                else 
                {
                    LoginPage();
                }
            }
            else if (choice == 7) 
            {
                viewCustomerProfilePage(Customers,CustomerID,scanner);
            }
            else if (choice == 8) 
            { 
                System.out.println("Logging out...");
                System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println("Logged out successfully!");
                System.out.println();
                System.out.println();
                LoginPage();
            }
            else
            {
                System.out.println("Invalid number!");
                CustomerPage(CustomerID);
            }
        } 
        catch(InputMismatchException ex) 
        {
            System.out.println("Invalid input data type, try again.");
        }
    }
	
    // Runner Page
    public static void RunnerPage() 
    {
        Scanner scanner = new Scanner(System.in);
        String runnerId;
        
        // Prompt for Runner ID
        System.out.print("Welcome to the Delivery System!\n");
        System.out.print("Enter Runner ID: ");
        runnerId = scanner.next();

        // Validate Runner ID
        if (File_Operation.isValidRunnerId(runnerId)) {
            Runner runner = new Runner(runnerId); // Initialize the Runner object with validated ID 
            boolean exit = false;

            // Extract delivery tasks at the start of the program
            File_Operation.extractDeliveryTasks();

        while (!exit) {
            System.out.println("");
            System.out.println("***********************************************");
            System.out.println("\t\tWelcome, Delivery Runner " + runnerId + "!");
            System.out.println("***********************************************");
            System.out.println("Choose the number for correspond feature.\n");
            System.out.println("Runner Features:");
            System.out.println("[1] View Available Tasks");
            System.out.println("[2] Update Available Tasks from Orders");
            System.out.println("[3] Update Task Acceptance Status");
            System.out.println("[4] Update Task Operational Status");
            System.out.println("[5] Check Task History");
            System.out.println("[6] View Earnings");
            System.out.println("[7] Read Customer Reviews");
            System.out.println("[8] Reset Tasks to Original");
            System.out.println("[9] View Profile");
            System.out.println("[10] Log Out");
            System.out.print("Select the feature: ");

            int choice = scanner.nextInt();
            String taskId; // Declare taskId here
            
            switch (choice) {
                case 1:
                    // Extract delivery tasks before viewing them
                    File_Operation.displayOrders();
                    runner.viewAvailableTasks();
                    break;
                    
                case 2:
                    File_Operation.displayOrders();
                    File_Operation.extractDeliveryTasks();
                    break;
                    
                case 3:
                    boolean continueAcceptanceUpdate = true;
                    while (continueAcceptanceUpdate) {
                        runner.displayTasksWithIDs();
                        System.out.print("Enter Task ID for acceptance status update: ");
                        taskId = scanner.next();
                        System.out.println("Select Status: [1] Accepted [2] Declined");
                        int acceptanceChoice = scanner.nextInt();
                        switch (acceptanceChoice) {
                            case 1:
                                runner.updateTaskAcceptanceStatus(taskId, "Accepted");
                                File_Operation.displayTasks(runnerId);
                                break;
                            case 2:
                                runner.updateTaskAcceptanceStatus(taskId, "Declined");
                                File_Operation.displayTasks(runnerId);
                                break;
                            default:
                                System.out.println("Invalid status selected.");
                                break;
                        }
                        System.out.print("Do you want to update another task? (yes/no): ");
                        String continueResponse = scanner.next().toLowerCase();
                        continueAcceptanceUpdate = continueResponse.equals("yes");
                    }
                    break;
                    
                case 4:
                    boolean continueOperationalUpdate = true;
                    while (continueOperationalUpdate) {
                        runner.displayTasksWithIDs();
                        System.out.print("Enter Task ID for operational status update: ");
                        taskId = scanner.next();
                        System.out.println("Select Status: [1] On Delivery [2] Completed");
                        int statusChoice = scanner.nextInt();
                        String status = "";
                        switch (statusChoice) {
                            case 1:
                                status = "On Delivery";
                                break;
                            case 2:
                                status = "Completed";
                                break;
                            default:
                                System.out.println("Invalid status selected.");
                                break;
                        }
                        if (!status.isEmpty()) {
                            runner.updateTaskOperationalStatus(taskId, status);
                            File_Operation.displayTasks(runnerId);
                            File_Operation.displayOrders();
                        }
                        System.out.print("Do you want to update another task? (yes/no): ");
                        String continueResponse = scanner.next().toLowerCase();
                        continueOperationalUpdate = continueResponse.equals("yes");
                    }
                    break;
                    
                case 5:
                    File_Operation.displayTasks(runnerId);
                    runner.checkTaskHistory();
                    break;
                    
                case 6:
                    boolean continueViewingEarnings = true;
                    while (continueViewingEarnings) {
                        System.out.println("Select the period for earnings:");
                        System.out.println("1. Daily");
                        System.out.println("2. Monthly");
                        System.out.println("3. Yearly");
                        int periodChoice = scanner.nextInt();
                        String period = "";
                        switch (periodChoice) {
                            case 1:
                                period = "daily";
                                break;
                            case 2:
                                period = "monthly";
                                break;
                            case 3:
                                period = "yearly";
                                break;
                            default:
                                System.out.println("Invalid selection.");
                                break;
                        }
                        if (!period.isEmpty()) {
                            runner.viewEarnings(period);
                        }
                        System.out.print("Do you want to view earnings for another period? (yes/no): ");
                        String continueResponse = scanner.next().toLowerCase();
                        continueViewingEarnings = continueResponse.equals("yes");
                    }
                    break;
                    
                case 7:
                    System.out.println("Customer Reviews:");
                    File_Operation.displayReviews();
                    break;
                    
                case 8:  
                    File_Operation.resetTasksToOriginal();
                    File_Operation.displayTasks(runnerId);
                    File_Operation.displayOrders();
                    System.out.println("Tasks have been reset to the original state.");
                    break;
                    
                case 9:
                    runner.displayProfile();
                    break;
                    
                case 10:
                    exit = true;
                    System.out.println("Exiting Runner Operations.");
                    break;
                    
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
       
    } else {
        // This block executes if the entered Runner ID is not valid
        System.out.println("Invalid Runner ID: " + runnerId); // Debug statement
        System.out.println("Invalid Runner ID. Please try again.");
        }  
        
        scanner.close();
        
    }  
	
    // Admin - Create Page
    public static void Create() 
    {
	int selection;
	String id = " ", pw = " ";
	Scanner sc = new Scanner(System.in);
	while (true) 
        {
	    System.out.println();
            System.out.println("Which user do you want to create?");
            System.out.println("[1] Customer\t [2] Vendor\t [3] Runner");
            System.out.println("[4] Back");
            System.out.print("Selection: ");
            selection = sc.nextInt();
            sc.nextLine();  // Consume the newline character after the integer input

            if (selection == 1) 
            {
                Scanner sc2 = new Scanner(System.in);
	        System.out.print("New Customer ID: ");
	        id = sc2.nextLine();

	        // Check if the ID is empty
	        while (id == null || id.isEmpty()) 
                {
	            System.out.println("ID cannot be null or empty.");
	            System.out.print("New Customer ID: ");
	            id = sc2.nextLine();
	        }

	        System.out.println();
	        System.out.print("New Customer Password: ");
	        pw = sc2.nextLine();

	        // Check if the password is empty
	        while (pw == null || pw.isEmpty())
                {
	            System.out.println("Password cannot be null or empty.");
	            System.out.print("New Customer Password: ");
	            pw = sc2.nextLine();
	        }
	        // Create the customer
	    	Administrator.create_Customer(id, pw, "Customer");
	        Create();
	        break;
	            
	    } 
            else if (selection == 2) 
            {
	        Scanner sc2 = new Scanner(System.in);
	        System.out.print("New Vendor ID: ");
	        id = sc2.nextLine();

	        // Check if the ID is empty
	        while (id == null || id.isEmpty()) 
                {
	            System.out.println("ID cannot be null or empty.");
	            System.out.print("New Vendor ID: ");
	            id = sc2.nextLine();
	        }

	        System.out.println();
	        System.out.print("New Vendor Password: ");
	        pw = sc2.nextLine();

	        // Check if the password is empty
	        while (pw == null || pw.isEmpty())
                {
	            System.out.println("Password cannot be null or empty.");
	            System.out.print("New Vendor Password: ");
	            pw = sc.nextLine();
	        }        
	        Administrator.create_vendor(id, pw, "Vendor");
                Create();
	        break;
	    } 
            else if (selection == 3) 
            {
	        Scanner sc2 = new Scanner(System.in);
	        System.out.print("New Runner ID: ");
	        id = sc2.nextLine();

	        // Check if the ID is empty
	        while (id == null || id.isEmpty()) 
                {
	            System.out.println("ID cannot be null or empty.");
	            System.out.print("New Runner ID: ");
	            id = sc2.nextLine();
	        }

	        System.out.println();
	        System.out.print("New Runner Password: ");
	        pw = sc2.nextLine();

	        // Check if the password is empty
	        while (pw == null || pw.isEmpty()) 
                {
	            System.out.println("Password cannot be null or empty.");
	            System.out.print("New Runner Password: ");
	            pw = sc2.nextLine();	            
	        }
	        Administrator.create_Runner(id, pw, "Runner");
	        Create();
	        break;

	    } 
            else if (selection == 4) 
            {
	        AdminPage();
	        break;
            } 
            else 
            {
	        System.out.println("Invalid Selection.");
	    }
        }
    }

    // Admin - Create User Id and Password
    public static void Create_User_Id_Password() 
    {
	Scanner sc = new Scanner(System.in);
	System.out.print("ID: ");
        id = sc.nextLine();

        // Check if the ID is empty
        while (id == null || id.isEmpty()) 
        {
            System.out.println("ID cannot be null or empty.");
            System.out.print("ID: ");
            id = sc.nextLine();
        }

        System.out.println();
        System.out.print("Password: ");
        pw = sc.nextLine();

        // Check if the password is empty
        while (pw == null || pw.isEmpty()) 
        {
            System.out.println("Password cannot be null or empty.");
            System.out.print("Password: ");
            pw = sc.nextLine();
        }
    }
	
    // Admin - Read User Page
    public static void ReadPage() 
    {
	int selection;
	Scanner sc = new Scanner(System.in);
		
	while(true) 
        {
            System.out.println("");
            System.out.println("");
            System.out.println("****************************************************");
            System.out.println("\tRead Page - Choose which user to read?");
            System.out.println("****************************************************");
            System.out.println("[1] Customers\t[2] Vendors\t[3] Runners");
            System.out.println("[4] Back");
            System.out.println();
            System.out.print("Selection: ");
            
            selection = sc.nextInt();
			
            switch (selection) 
            {
		case 1:
                    System.out.println("");
                    System.out.println("");
                    Administrator.read_all_Customers();
                    System.out.println();
                    System.out.println("[1] Find Specific User");
                    System.out.println("Press any other keys to return back.");
                    Scanner sc2 = new Scanner(System.in);
                    int selection2 = sc2.nextInt();
                    if(selection2 == 1)
                    {
			System.out.print("ID: ");
			Scanner sc3 = new Scanner(System.in);
			String tosearch = sc3.nextLine();
			Administrator.read_specific_Customers(tosearch);
                    }	
                    else
                    {
                        System.out.println("");
                    }
                    break;
                    
                case 2:
                    System.out.println("");
                    System.out.println("");
                    Administrator.read_all_Vendors();
                    System.out.println();
                    System.out.println("[1] Find Specific User");
                    System.out.println("Press any other number keys to return back.");
                    Scanner sc4 = new Scanner(System.in);
                    int selection3 = sc4.nextInt();
                    if(selection3 == 1)
                    {
			System.out.print("ID: ");
			Scanner sc5 = new Scanner(System.in);
			String tosearch = sc5.nextLine();
			Administrator.read_specific_Vendors(tosearch);
                    }
				
                    else
                    {
			System.out.println("");
                    }
                    break;
			
                case 3:
                    System.out.println("");
                    System.out.println("");
                    Administrator.read_all_Runners();
                    System.out.println();
                    System.out.println("[1] Find Specific User");
                    System.out.println("Press any other number keys to return back.");
                    Scanner sc6 = new Scanner(System.in);
                    int selection4 = sc6.nextInt();
                    if(selection4 == 1)
                    {
			System.out.print("ID: ");
			Scanner sc7 = new Scanner(System.in);
			String tosearch = sc7.nextLine();
			Administrator.read_specific_Runners(tosearch);
                    }
				
                    else
                    {
			System.out.println("");
                    }
                    break;
			
                case 4:
                    AdminPage();
                    return;
			
                default:
                    System.out.println("Invalid selection, please try again...");
                    break;

            }	
        }
    }
	
    // Admin - Update Page
    public static void UpdatePage() 
    {
	Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("*******************************************");
        System.out.println("\t    Update User Details ");
        System.out.println("*******************************************");
        System.out.println("");
        System.out.println("[1] Customers\t[2] Vendors\t[3] Runners");
        System.out.println("[4] Back");
        System.out.println("");
        System.out.println("Which user do you want to update?");
        System.out.print("Selection: ");

        try 
        {
            int selection = scan.nextInt();
            scan.nextLine();
            
            if (selection == 1) 
            {
                UpdateCustomerPage();
            } 
            else if (selection == 2) 
            {
	            UpdateVendorPage();
	    } 
            else if (selection == 3) 
            {
	            UpdateRunnerPage();
	    } 
            else if (selection == 4) 
            {
	            AdminPage();
	    } 
            else 
            {
                System.out.println("Invalid selection. Please try again.");
                UpdatePage();
            }

        }
        catch(InputMismatchException e) 
        {
            System.out.println("Wrong input data type.");
            scan.nextLine(); // consume the newline character
            UpdatePage();
        }
        catch(NoSuchElementException e)
        {
            System.out.println("An error occured: " + e.getStackTrace());
        }
    }
		
    // Admin - Update Customer Page  
    public static void UpdateCustomerPage() 
    {
	Scanner sc = new Scanner(System.in);
	System.out.println();
        System.out.print("Enter the Customer ID who would like to update the details: ");
        String updateid = sc.nextLine();
        if(File_Operation.isUserExist("Customers.txt", updateid)) 
        {
            Administrator.update_Customer(updateid);
            try 
            {
                System.out.println();
            	System.out.print("Enter new username: ");
                String newUsername = sc.nextLine();
                System.out.println();
                System.out.print("Enter new email: ");
                String newEmail = sc.nextLine();
                System.out.println();
                System.out.print("Enter new contact number: ");
                while(!sc.hasNextInt()) 
                {
                    System.out.println("Please enter a valid integer:");
                    sc.next(); // consume the invalid input
                }
                int newContact = sc.nextInt();
                System.out.println("Updating to the latest Customer details...");
                
        	Administrator.update_Customer2(updateid, newUsername, newEmail, newContact);
        	UpdatePage();
            }
            catch(InputMismatchException e) 
            {
        	System.out.println("Invalid input data type.");
            }
        }
        else 
        {
            System.out.println("Customer Not Found!");
            UpdateCustomerPage();
            UpdatePage();
        }
    }
	
    // Admin - Update Vendor Page
    public static void UpdateVendorPage()
    {
	Scanner sc = new Scanner(System.in);
	System.out.println();
        System.out.print("Enter the Vendor ID who would like to update the details: ");
        String updateid = sc.nextLine();
        if(File_Operation.isUserExist("Vendors.txt", updateid)) 
        {
            Administrator.update_Vendor(updateid);
            try 
            {
                System.out.println();
            	System.out.print("Enter new username: ");
                String newUsername = sc.nextLine();
                System.out.println();
                System.out.print("Enter new email: ");
                String newEmail = sc.nextLine();
                System.out.println();
                System.out.print("Enter new contact number: ");
                while(!sc.hasNextInt()) 
                {
                    System.out.println("Please enter a valid integer:");
                    sc.next(); // consume the invalid input
                }
                int newContact = sc.nextInt();
                System.out.println("Updating to the latest Vendor details...");
                
        	Administrator.update_Vendor2(updateid, newUsername, newEmail, newContact);
        	UpdatePage();
            }
            catch(InputMismatchException e) 
            {
        	System.out.println("Invalid input data type.");
            }
        }
        else 
        {
            System.out.println("Vendor Not Found!");
            UpdateVendorPage();
            UpdatePage();
        }	
    }

    // Admin - Update Runner Page
    public static void UpdateRunnerPage() 
    {
	Scanner sc = new Scanner(System.in);
	System.out.println();
        System.out.print("Enter the Runner ID who would like to update the details: ");
        String updateid = sc.nextLine();
        if(File_Operation.isUserExist("Runners.txt", updateid)) 
        {
            Administrator.update_Runner(updateid);
            try 
            {
                System.out.println();
            	System.out.print("Enter new username: ");
                String newUsername = sc.nextLine();
                System.out.println();
                System.out.print("Enter new email: ");
                String newEmail = sc.nextLine();
                System.out.println();
                System.out.print("Enter new contact number: ");
                while(!sc.hasNextInt()) 
                {
                    System.out.println("Please enter a valid integer:");
                    sc.next(); // consume the invalid input
                }
                int newContact = sc.nextInt();
                System.out.println("Updating to the latest Runner details...");
                
        	Administrator.update_Runner2(updateid, newUsername, newEmail, newContact);
        	UpdatePage();
            }
            catch(InputMismatchException e) 
            {
        	System.out.println("Invalid input data type.");
            }
            UpdatePage();
        }
        else 
        {
            System.out.println("Runner Not Found!");
            UpdateRunnerPage();
            UpdatePage();
        }	
    }

    // Admin - Delete Page
    public static void DeletePage() 
    {
	Scanner sc = new Scanner(System.in);
	System.out.println("*******************************************");
	System.out.println("\t\tDelete Page");
	System.out.println("*******************************************");
	System.out.println("Which types of user do you want to delete?");
	System.out.println("[1] Customers\t[2] Vendors\t[3] Runners");
	System.out.println("[4] Back");
	System.out.println("Selection: ");
	try 
        {
            int selection = sc.nextInt();
            sc.nextLine();
			
            if(selection == 1) 
            {
		System.out.print("Enter the Customer ID to delete: ");
		String id = sc.nextLine();
		if(File_Operation.isUserExist("Users.txt", id)) 
                {
                    Administrator.delete_Customer(id);
                    DeletePage();
		}
		else 
                {
                    System.out.println("Customers does not exist.!");;
                    DeletePage();
		}		
            }
            else if (selection == 2) 
            {
		System.out.print("Enter the Vendor ID to delete: ");
		String id = sc.nextLine();
		if(File_Operation.isUserExist("Users.txt", id)) 
                {
                    Administrator.delete_Vendor(id);
                    DeletePage();
		}
		else 
                {
                    System.out.println("Vendor does not exist.!");;
                    DeletePage();
		}			
            }
            else if (selection == 3) 
            {
		System.out.print("Enter the Runner ID to delete: ");
		String id = sc.nextLine();
		if(File_Operation.isUserExist("Users.txt", id)) 
                {
                    Administrator.delete_Runner(id);
                    DeletePage();
		}
		else 
                {
                    System.out.println("Runner does not exist.!");;
                    DeletePage();
		}
            }
            else if (selection == 4) 
            {
		AdminPage();
            }
            else 
            {
		System.out.println("Invalid selection. Please try again.");
		DeletePage();
            }
	} 
        catch(InputMismatchException ex)
	{
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine();  // Consume the invalid input
            DeletePage();
        }
    }
	    
    // Admin - Top-up Page
    public static void topUpPage() 
    {
         Scanner sc = new Scanner(System.in);

	System.out.println();
        System.out.println("***************************************");
	System.out.println("\tTop-Up Customer Credit");
	System.out.println("***************************************");
	System.out.println("[1] Continue\t[2] Back ");
	System.out.print("Selection: ");

	try 
        {
            int selection = sc.nextInt();
            sc.nextLine();

            switch (selection) 
            {
                case 1:
                    handleTopUp(sc);
                    break;
	       
                case 2:
                    AdminPage();
                    break;
	            
                default:
                    System.out.println("Invalid selection. Please try again:");
                    topUpPage();
            }
        } 
        catch (InputMismatchException ex) 
        {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine(); // Consume the invalid input
            topUpPage();
        }
    }

    // Admin - Ask whether top up or back to last page
    private static void handleTopUp(Scanner sc) 
    {
        System.out.print("Customer ID: ");
        String custId = sc.nextLine();

	if (File_Operation.isUserExist("Customers.txt", custId)) 
        {
            Administrator.getCreditDetails(custId);
            System.out.println("[1] Top-up\t[2] Back");
            System.out.print("Selection: ");

            try 
            {
                int selection = sc.nextInt();
                sc.nextLine();

                switch (selection) 
                {
                    case 1:
                        handleAmountInput(sc, custId);
                        break;
	          
                    case 2:
                        topUpPage();
                        break;
	                
                    default:
                        System.out.println("Invalid selection.");
                        handleTopUp(sc);
                }
            } 
            catch (InputMismatchException ex) 
            {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // Consume the invalid input
                handleTopUp(sc);
            }
        } 
        else 
        {
            System.out.println("Customer not found");
            topUpPage();
        }
    }

    // Admin - Prompt top-up amount
    private static void handleAmountInput(Scanner sc, String custId)
    {
        System.out.print("Enter top-up amount: ");
        try 
        {
            int amount = sc.nextInt();

            if (amount <= 0) 
            {
                System.out.println("Top-up amount cannot be negative!");
                handleAmountInput(sc, custId);
                System.out.print("");
            } 
            else 
            {
                Administrator.topupCredit(custId, amount);
                Administrator.generateAndSendReceipt(custId, amount);
                ViewLatestReceipt(sc, custId, amount);
            }
        } 
        catch (InputMismatchException ex) 
        {
            System.out.println("Invalid input. Please enter an integer!");
            sc.nextLine(); // Consume the invalid input
            topUpPage();
        }
    }
    
	// Admin - View Latest Receipt, after top-up for customers
    public static void ViewLatestReceipt(Scanner sc, String id, int amount) 
    {
	while(true) 
        {
            try 
            {
		System.out.println("[1] View Receipt\t[2] Back");
		System.out.print("Selection: ");
		int selection = sc.nextInt();
			
		if (selection == 1) 
                {
                    Administrator.viewLatestReceipt(id);
                    topUpPage();
                    break;
                }
		else if (selection == 2) 
                {
                    topUpPage();
                    return;
		}
		else 
                {
                    System.out.println("Invalid selection. Please select [1] to view the receipt or [2] to return back to the Top-Up Page.");
		}
            }
            catch (InputMismatchException ex) 
            {
                System.out.println("Invalid input. Please enter integer value");
                sc.nextLine();
            }
	}
    }

    // Admin - View Receipt Page
    public static void ViewReceiptPage() 
    {
	Scanner sc = new Scanner(System.in);
	System.out.println("*********************************************");
	System.out.println("\t\tView Receipt");
	System.out.println("*********************************************");
	System.out.println("[1] View Receipt sent to specific customer");
	System.out.println("[2] Back");
	while (true)
            try 
            {
		System.out.print("Selection:");
		int selection = sc.nextInt();
				
		if(selection == 1) 
                {
                    viewReceiptPage2();
		}
		else if (selection == 2) 
                {
                    AdminPage();
                    break;
		}
		else 
                {
                    System.out.println("Invalid selection. Please choose [1] to view receipt or [2] to return back to admin main page.");
		}
            }
            catch(InputMismatchException ex) 
            {
		System.out.println("Invalid input data type, please try again");
		sc.nextLine();
            }
    }
	
    public static void viewReceiptPage2() 
    {
	Scanner sc = new Scanner(System.in);

	while(true) 
        {
            try 
            {
		System.out.print("Customer ID:");

		String id = sc.nextLine();
		if(File_Operation.isUserExist("Customers.txt", id)) 
                {
                    Administrator.viewReceipt(id);
                    ViewReceiptPage();
		}
		else 
                {
                    System.out.println("Customer Not Found!");
		}
            }
            catch(InputMismatchException ex) 
            {
		System.out.println("Invalid input data type.");
		sc.nextLine();
            }
	}
    }
    
    // Admin - View Profile Page
    public static void viewAdminPage() 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("********************************************");
        System.out.println("\t\tProfile Page");
        System.out.println("********************************************");
        Administrator a1 = new Administrator("A0001", "admin");
        a1.viewProfile();

        while (true) 
        {
            try 
            {
                System.out.println("Press [1] to return back");
                int selection = sc.nextInt();

                if (selection == 1) 
                {
                    AdminPage();
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
                sc.nextLine(); // Consume the invalid input to avoid an infinite loop
            }
        }
        sc.close(); // Close the scanner when done to avoid resource leak
    }

    
    // Vendor - Manage the Items
    private static void manageItems(ItemFile itemManager, Scanner scanner, String vendorId) 
    {       
            System.out.println("");
            System.out.println("************************************************");
            System.out.println("\t\tUpdate Menu Page");
            System.out.println("************************************************");
            
            System.out.println("\nChoose the number for correspond feature.");
            System.out.println("\n[1] Create New Item");
            System.out.println("[2] View Item");
            System.out.println("[3] Update Existing Item");
            System.out.println("[4] Delete Item");
            System.out.println("[5] Back");
            System.out.println();
            System.out.print("Select the feature: ");

            try 
            {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) 
                {
                    case 1:
                        createItemPage(itemManager, scanner, vendorId);
                        break;
                    
                    case 2:
                        viewItemPage(itemManager, scanner, vendorId);
                        break;
                    
                    case 3:
                        updateItemPage(itemManager, scanner, vendorId);
                        break;
                    
                    case 4:
                        deleteItemPage(itemManager, scanner, vendorId);
                        break;

                    case 5:
                        System.out.println("Returning to Main Menu...");
                        VendorPage(vendorId);
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } 
            catch (NumberFormatException e) 
            {
                e.printStackTrace();
            }
    }
    
    // Vendor - Manage Orders
    private static void manageOrders(OrderFile orderManager, ItemFile itemManager, Scanner scannerOrder, String vendorId) 
    {
            System.out.println("");
            System.out.println("*************************************************");
            System.out.println("\t\tManage Orders Page");
            System.out.println("*************************************************");
            
            System.out.println("\nChoose the number for correspond feature.");
            System.out.println("\n[1] Accept Order");
            System.out.println("[2] Cancel Order");
            System.out.println("[3] Update Order");
            System.out.println("[4] Order History");     
            System.out.println("[5] Back"); 
            System.out.println();
            System.out.print("Select the feature: ");
            OrderHistory orderHistory = new OrderHistory();
            
            try 
            {
                int choice = Integer.parseInt(scannerOrder.nextLine());

                switch (choice) 
                {
                    case 1:
                        acceptOrderPage(orderManager, itemManager, scannerOrder, vendorId);
                        break;

                    case 2:
                        cancelOrderPage(orderManager, itemManager, scannerOrder, vendorId);
                        break;

                    case 3:
                        updateOrderPage(orderManager, itemManager, scannerOrder, vendorId);
                        break;

                    case 4:
                        viewOrderHistoryPage(orderManager, itemManager, scannerOrder, vendorId);
                        break;

                    case 5:
                        System.out.println("Returning to Main Menu...");
                        VendorPage(vendorId);
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } 
            catch (NumberFormatException e) 
            {
                e.printStackTrace();
            }
    }

    // Vendor - Create Item
    private static void createItemPage(ItemFile itemManager, Scanner scanner, String vendorId)
    {
        System.out.println("");
        System.out.println("************************************************");
        System.out.println("\t\tCreate Item Page");
        System.out.println("************************************************");
        
        int createItemId = itemManager.generateNewItemId();
        System.out.print("\nEnter Item Name: ");
        String createItemName = scanner.nextLine();
        System.out.print("Enter Item Description: ");
        String createItemDesc = scanner.nextLine();
        System.out.print("Enter Item Price: ");
        double createItemPrice = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter Item Quantity: ");
        int createItemQuantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Item Restaurant: ");
        String createItemAddress = scanner.nextLine();
        itemManager.createItem(createItemName, createItemDesc, createItemPrice, createItemQuantity, createItemAddress, vendorId);
        
        while (true) 
        {
            try 
            {
                System.out.println("\nPress [1] to return back or [2] to continue.");
                int selection = Integer.parseInt(scanner.nextLine());

                if (selection == 1) 
                {
                    manageItems(itemManager, scanner, vendorId);
                    break;
                } 
                if (selection == 2) 
                {
                    // Continue creating items
                    createItemPage(itemManager, scanner, vendorId);
                }
                else 
                {
                    System.out.println("Invalid selection. Please enter [1] to return back to the Main Page or [2] to continue.");
                }
            } 
            catch (InputMismatchException ex) 
            {
                System.out.println("Invalid input format. Please enter an integer input!");
                scanner.nextLine(); // Consume the invalid input to avoid an infinite loop
            }
        }
    }
    
    // Vendor - Update Item
    private static void updateItemPage(ItemFile itemManager, Scanner scanner, String vendorId)
    {
        System.out.println("");
        System.out.println("***********************************************");
        System.out.println("\t\tUpdate Item Page");
        System.out.println("***********************************************");
        itemManager.displayAllItems(vendorId);
        
        System.out.print("\nEnter Item ID to Update: ");
        int updateItemId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter New Item Name: ");
        String updateItemName = scanner.nextLine();
        System.out.print("Enter New Item Description: ");
        String updateItemDesc = scanner.nextLine();
        System.out.print("Enter New Item Price: ");
        double updateItemPrice = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter New Item Quantity: ");
        int updateItemQuantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter New Item Restaurant: ");
        String updateItemAddress = scanner.nextLine();
        itemManager.updateItem(updateItemId, updateItemName, updateItemDesc, updateItemPrice, updateItemQuantity, updateItemAddress);
        
        while (true) 
        {
            try 
            {
                System.out.println("\nPress [1] to return back or [2] to continue.");
                int selection = Integer.parseInt(scanner.nextLine());

                if (selection == 1) 
                {
                    manageItems(itemManager, scanner, vendorId);
                    break;
                } 
                if (selection == 2) 
                {
                    // Continue creating items
                    updateItemPage(itemManager, scanner, vendorId);
                }
                else 
                {
                    System.out.println("Invalid selection. Please enter [1] to return back to the Main Page or [2] to continue.");
                }
            } 
            catch (InputMismatchException ex) 
            {
                System.out.println("Invalid input format. Please enter an integer input!");
                scanner.nextLine(); // Consume the invalid input to avoid an infinite loop
            }
        }
    }
    
    // Vendor - Delete Item
    private static void deleteItemPage(ItemFile itemManager, Scanner scanner, String vendorId)
    {
        System.out.println("");
        System.out.println("********************************************");
        System.out.println("\t\tDelete Item Page");
        System.out.println("********************************************");
        itemManager.displayAllItems(vendorId);
        
        System.out.print("\nEnter Item ID to Delete: ");
        int deleteItemId = Integer.parseInt(scanner.nextLine());
        itemManager.deleteItem(deleteItemId);
        
        while (true) 
        {
            try 
            {
                System.out.println("\nPress [1] to return back or [2] to continue.");
                int selection = Integer.parseInt(scanner.nextLine());

                if (selection == 1) 
                {
                    manageItems(itemManager, scanner, vendorId);
                    break;
                } 
                if (selection == 2) 
                {
                    // Continue creating items
                    deleteItemPage(itemManager, scanner, vendorId);
                }
                else 
                {
                    System.out.println("Invalid selection. Please enter [1] to return back to the Main Page or [2] to continue.");
                }
            } 
            catch (InputMismatchException ex) 
            {
                System.out.println("Invalid input format. Please enter an integer input!");
                scanner.nextLine(); // Consume the invalid input to avoid an infinite loop
            }
        }
    }
    
    // Vendor - View Item
    public static void viewItemPage(ItemFile itemManager, Scanner scanner, String vendorId)
    {
        System.out.println("");
        System.out.println("********************************************");
        System.out.println("\t\tView Item Page");
        System.out.println("********************************************");
        // Display all items
        itemManager.displayAllItems(vendorId);
                        
        while (true) 
        {
            // Ask the user to enter the item ID to read details or enter "back" to exit
            System.out.print("\nEnter Item ID to Read Details or type 'back' to exit: ");
            String userInput = scanner.nextLine();

            // Ignoring lower case and upper case differences
            if (userInput.equalsIgnoreCase("back")) 
            {
                manageItems(itemManager, scanner, vendorId);
                break;
            }

            try 
            {
                int readItemId = Integer.parseInt(userInput);
                Item readItem = itemManager.readItem(readItemId);

                if (readItem != null) 
                {
                    System.out.println("\nItem: \n" + readItem);
                } 
                else 
                {
                    System.out.println("Item not found.");
                }
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Invalid input. Please enter a valid Item ID or 'back'.");
            }
        }
    }
    
    // Vendor - Accept Order
    public static void acceptOrderPage(OrderFile orderManager, ItemFile itemManager, Scanner scannerOrder, String vendorId)
    {
        System.out.println("");
        System.out.println("*************************************************");
        System.out.println("\t\tAccept Order Page");
        System.out.println("*************************************************");
        OrderHistory orderHistory = new OrderHistory();
        
        orderHistory.displayPendingOrders(vendorId);
        System.out.print("\nEnter Order ID to Accept: ");
        int acceptOrderId = Integer.parseInt(scannerOrder.nextLine());
        orderManager.acceptOrder(acceptOrderId);
        
        while (true) 
        {
            try 
            {
                System.out.println("\nPress [1] to return back or [2] to continue.");
                int selection = Integer.parseInt(scannerOrder.nextLine());

                if (selection == 1) 
                {
                    manageOrders(orderManager, itemManager, scannerOrder, vendorId);
                    break;
                } 
                if (selection == 2) 
                {
                    // Continue creating items
                    acceptOrderPage(orderManager, itemManager, scannerOrder, vendorId);
                }
                else 
                {
                    System.out.println("Invalid selection. Please enter [1] to return back to the Main Page or [2] to continue.");
                }
            } 
            catch (InputMismatchException ex) 
            {
                System.out.println("Invalid input format. Please enter an integer input!");
                scannerOrder.nextLine(); // Consume the invalid input to avoid an infinite loop
            }
        }
    }
    
    // Vendor - Cancel Order
    public static void cancelOrderPage(OrderFile orderManager, ItemFile itemManager, Scanner scannerOrder, String vendorId)
    {
        System.out.println("");
        System.out.println("*************************************************");
        System.out.println("\t\tCancel Order Page");
        System.out.println("*************************************************");
        OrderHistory orderHistory = new OrderHistory();
        
        orderHistory.displayPendingOrders(vendorId);
        System.out.print("\nEnter Order ID to Cancel: ");
        int cancelOrderId = Integer.parseInt(scannerOrder.nextLine());
        orderManager.cancelOrder(cancelOrderId);
        
        while (true) 
        {
            try 
            {
                System.out.println("\nPress [1] to return back or [2] to continue.");
                int selection = Integer.parseInt(scannerOrder.nextLine());

                if (selection == 1) 
                {
                    manageOrders(orderManager, itemManager, scannerOrder, vendorId);
                    break;
                } 
                if (selection == 2) 
                {
                    cancelOrderPage(orderManager, itemManager, scannerOrder, vendorId);
                }
                else 
                {
                    System.out.println("Invalid selection. Please enter [1] to return back to the Main Page or [2] to continue.");
                }
            } 
            catch (InputMismatchException ex) 
            {
                System.out.println("Invalid input format. Please enter an integer input!");
                scannerOrder.nextLine(); // Consume the invalid input to avoid an infinite loop
            }
        }
    }
    
    // Vendor - Update Order
    public static void updateOrderPage(OrderFile orderManager, ItemFile itemManager, Scanner scannerOrder, String vendorId)
    {
        System.out.println("");
        System.out.println("*************************************************");
        System.out.println("\t\tUpdate Order Page");
        System.out.println("*************************************************");
        OrderHistory orderHistory = new OrderHistory();
        
        orderHistory.displayStatus(vendorId);
        System.out.print("\nEnter Order ID to Update Status: ");
        int updateStatusOrderId = Integer.parseInt(scannerOrder.nextLine());
        System.out.print("Enter the new status: ");
        String newStatus = scannerOrder.nextLine();
        orderManager.updateOrderStatus(updateStatusOrderId, newStatus);
        
        while (true) 
        {
            try 
            {
                System.out.println("\nPress [1] to return back or [2] to continue.");
                int selection = Integer.parseInt(scannerOrder.nextLine());

                if (selection == 1) 
                {
                    manageOrders(orderManager, itemManager, scannerOrder, vendorId);
                    break;
                } 
                if (selection == 2) 
                {
                    updateOrderPage(orderManager, itemManager, scannerOrder, vendorId);
                }
                else 
                {
                    System.out.println("Invalid selection. Please enter [1] to return back to the Main Page or [2] to continue.");
                }
            } 
            catch (InputMismatchException ex) 
            {
                System.out.println("Invalid input format. Please enter an integer input!");
                scannerOrder.nextLine(); // Consume the invalid input to avoid an infinite loop
            }
        }
    }
    
    // Vendor - View Profile Page
    public static void viewVendorProfilePage(List<Vendor> vendors, String vendorId, Scanner scanner) 
    {
        System.out.println("");
        System.out.println("********************************************");
        System.out.println("\t\tProfile Page");
        System.out.println("********************************************");

        for (Vendor vendor : vendors)
        {
            if (vendor.getId() != null && vendor.getId().equals(vendorId)) 
            {
                System.out.println("\nVendor Profile Details:");
                System.out.println("\nID: " + vendor.getId());
                System.out.println("Name: " + vendor.getUsername());
                System.out.println("Contact Number: " + vendor.getContact());
                System.out.println("Email: " + vendor.getEmail());
                // Add other details as needed
                break;  // Stop searching once the vendor is found
            }
        }

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
    
    // Vendor - View Customer Review
    public static void viewCustomerReviews(String vendorId, Scanner scanner) 
    {
        try (BufferedReader reviewReader = new BufferedReader(new FileReader("CustomerReview.txt"))) 
        {
            System.out.println("");
            System.out.println("***********************************************");
            System.out.println("\t\tCustomer Review");
            System.out.println("***********************************************");

            System.out.println("Reviews for Vendor ID: " + vendorId);

            String reviewLine;
            boolean found = false;

            while ((reviewLine = reviewReader.readLine()) != null) 
            {
                String[] reviewData = reviewLine.split("/");

                if (reviewData.length >= 4 && reviewData[2].trim().equals(vendorId)) 
                {
                    // Extract data from the line
                    String orderId = reviewData[0].trim();
                    String customerId = reviewData[1].trim();
                    int rating = Integer.parseInt(reviewData[3].trim());
                    String reviewText = reviewData[4].trim();

                    // Display the information
                    System.out.println("\nOrder ID: " + orderId);
                    System.out.println("Customer ID: " + customerId);
                    System.out.println("Rating: " + rating);
                    System.out.println("Review: " + reviewText);
                    System.out.println("***********************************************");

                    found = true;
                }
            }

            if (!found) 
            {
                System.out.println("No reviews found for Vendor ID: " + vendorId);
            }
            
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
        catch (IOException e) 
        {
            System.err.println("Error viewing reviews: " + e.getMessage());
        }
    }
    
    // Vendor - View Order History
    public static void viewOrderHistoryPage(OrderFile orderManager, ItemFile itemManager, Scanner scannerOrder, String vendorId)
    {
        System.out.println("");
        System.out.println("*********************************************");
        System.out.println("\t\tOrder History");
        System.out.println("*********************************************");
        
        System.out.println("\nChoose the number for correspond feature.");
        System.out.println("\n[1] Daily Order");
        System.out.println("[2] Monthly Order");
        System.out.println("[3] Quarterly Order");       
        System.out.println("[4] Back");
        System.out.println();
        System.out.print("Select the feature: ");
        
            try 
            {
                int choice = Integer.parseInt(scannerOrder.nextLine());

                switch (choice) 
                {
                    case 1:
                        itemManager.viewOrderHistory(vendorId, "daily");
                        System.out.println("\nEnter [1] to back to the Order History page or [2] to back to the Manage Orders Page.");
                        Scanner s2 = new Scanner(System.in);
                        String num = s2.nextLine();
                        if ("1".equals(num))
                        {
                            viewOrderHistoryPage(orderManager, itemManager, scannerOrder, vendorId);
                        }
                        else if ("2".equals(num))
                        {
                            manageOrders(orderManager, itemManager, scannerOrder, vendorId);
                        }
                        break;

                    case 2:
                        itemManager.viewOrderHistory(vendorId, "monthly");
                        System.out.println("\nEnter [1] to back to the Order History page or [2] to back to the Manage Orders Page.");
                        Scanner s3 = new Scanner(System.in);
                        String num1 = s3.nextLine();
                        if ("1".equals(num1))
                        {
                            viewOrderHistoryPage(orderManager, itemManager, scannerOrder, vendorId);
                        }
                        else if ("2".equals(num1))
                        {
                            manageOrders(orderManager, itemManager, scannerOrder, vendorId);
                        }
                        break;

                    case 3:
                        itemManager.viewOrderHistory(vendorId, "quarterly");
                        System.out.println("\nEnter [1] to back to the Order History page or [2] to back to the Manage Orders Page.");
                        Scanner s4 = new Scanner(System.in);
                        String num2 = s4.nextLine();
                        if ("1".equals(num2))
                        {
                            viewOrderHistoryPage(orderManager, itemManager, scannerOrder, vendorId);
                        }
                        else if ("2".equals(num2))
                        {
                            manageOrders(orderManager, itemManager, scannerOrder, vendorId);
                        }
                        break;

                    case 4:
                        manageOrders(orderManager, itemManager, scannerOrder, vendorId);
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } 
            catch (NumberFormatException e) 
            {
                e.printStackTrace();
            }
    }
    
    // Vendor - View Detail Notification
    public static void viewNotificationsPage(ItemFile itemManager, Scanner scanner, String vendorId)
    {
        System.out.println("");
        System.out.println("*********************************************");
        System.out.println("\t\tNotifications");
        System.out.println("*********************************************");
        
        // Display notifications
        itemManager.checkLowStockAndNotify1();
        System.out.println("\nNotifications:");
        itemManager.displayNotifications1();
        
        while (true) 
        {
            try 
            {
                System.out.println("\nPress [1] to return back.");
                int selection = Integer.parseInt(scanner.nextLine());

                if (selection == 1) 
                {
                    VendorPage(vendorId);
                    break;
                } 
                else 
                {
                    System.out.println("Invalid selection. Please enter [1] to return back to the Main Page or [2] to continue.");
                }
            } 
            catch (InputMismatchException ex) 
            {
                System.out.println("Invalid input format. Please enter an integer input!");
                scanner.nextLine(); // Consume the invalid input to avoid an infinite loop
            }
        }
    }
    
    // Customer - Review Page
        public static void ReviewPage(String id) {
	Scanner sc = new Scanner(System.in);
        String CustomerID = id;
	System.out.println("");
	System.out.println("***********************************************");
	System.out.println("\t   Review Page");
	System.out.println("***********************************************");
	System.out.println("Choose the number for correspond feature.\n");
        System.out.println("[1] Provide Review\t [2] View Review\t");
        System.out.println("[3] Back");
        System.out.print("Select the feature: ");
        try 
        {
            choice = sc.nextInt();
            if (choice == 1) 
            {
                Customer.Provide_review(CustomerID);
                Scanner sc2 = new Scanner(System.in);
                System.out.println("Enter [1] to back to the review page.");
                String num = sc2.nextLine();
                if ("1".equals(num))
                {
                    ReviewPage(CustomerID);
                }
                else 
                {
                    CustomerPage(CustomerID);
                }
                sc2.close();
            }
            else if (choice == 2) 
            {
                Customer.View_review(CustomerID);
                Scanner sc2 = new Scanner(System.in);
                System.out.println("Enter [1] to back to the customer main page.");
                String num = sc2.nextLine();
                if ("1".equals(num))
                {
                    CustomerPage(CustomerID);
                }
                else 
                {
                    LoginPage();
                }
                sc2.close();
            }
            else if (choice == 3) 
            {
	            CustomerPage(CustomerID);
            }
            else 
            {
	            System.out.println("Invalid Selection.");
	    }
        }
        catch(InputMismatchException ex) 
        {
            System.out.println("Invalid input data type, try again.");
        }
        sc.close();
    }
        
        
        // Customer - Place Or Cancel Order Page
        public static void PlaceOrCancelOrderPage(String id) {
        try 
        (Scanner sc = new Scanner(System.in)) {
            String CustomerID = id;
            System.out.println("");
            System.out.println("***********************************************");
            System.out.println("\tPlace or Cancel Order");
            System.out.println("***********************************************");
            System.out.println("Choose the number for correspond feature.\n");
            System.out.println("[1] Place Order\t [2] Cancel Order\t");
            System.out.println("[3] Back");
            System.out.print("Select the feature: ");
            try
            {
                choice = sc.nextInt();
                sc.nextLine();
                
                switch (choice) {
                    case 1:
                        Customer.placeOrder(CustomerID);
                        CustomerPage(CustomerID);
                        break;
                    case 2:
                        Customer.CancelOrderPage(CustomerID);
                        CustomerPage(CustomerID);
                        break;
                    case 3:
                        CustomerPage(CustomerID);
                        break;
                    default:
                        System.out.println("Invalid Selection.");
                }
            }catch (InputMismatchException ex) {
                System.out.println("Invalid input data type. Please enter a number.");
            } catch (NoSuchElementException ex) {
                System.out.println("No line found. Please enter a valid input.");
            }
        }    
    }      
        
        public static void OrderDetailsPage(String id) {
        try 
        (Scanner sc = new Scanner(System.in)) {
            String CustomerID = id;
            System.out.println("");
            System.out.println("***********************************************");
            System.out.println("\tView Order details Page");
            System.out.println("***********************************************");
            System.out.println("Choose the number for the following details.\n");
            System.out.println("[1] View Order History\t [2] View Order Status\t");
            System.out.println("[3] Back");
            System.out.print("Select the feature: ");
            try
            {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        Customer.checkOrderHistory(CustomerID);
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("Enter [1] to back to the customer main page.");
                        String num = sc2.nextLine();
                        if ("1".equals(num))
                        {
                            CustomerPage(CustomerID);
                        }
                        else
                        {
                            LoginPage();
                        }
                        break;
                    case 2:
                        Customer.checkOrderStatus(CustomerID);
                        Scanner sc3 = new Scanner(System.in);
                        System.out.println("Enter [1] to back to the customer main page.");
                        String num1 = sc3.nextLine();
                        if ("1".equals(num1))
                        {
                            CustomerPage(CustomerID);
                        }
                        else
                        {
                            LoginPage();
                        }
                        break;
                    case 3:
                        CustomerPage(CustomerID);
                        break;
                    default:
                        System.out.println("Invalid Selection.");
                }
            }
            catch(InputMismatchException ex)
            {
                System.out.println("Invalid input data type, try again.");
            }
        }
    }
     
        // Customer - View Profile Page
    public static void viewCustomerProfilePage(List<Customer> Customers, String CustomerId, Scanner scanner) 
    {
        System.out.println("********************************************");
        System.out.println("\t\tProfile Page");
        System.out.println("********************************************");
        
        for (Customer customer : Customers)
        { 
            if (customer.getCustomerId() != null && customer.getCustomerId().equals(CustomerId)) 
            { 
                System.out.println("\nCustomer Profile Details:");
                System.out.println("ID: " + customer.getCustomerId());
                System.out.println("Name: " + customer.getName());
                System.out.println("Contact Number: " + customer.getContactNum());
                System.out.println("Email: " + customer.getEmail());
                // Add other details as needed
                break;  
            }
        }

        while (true) 
        {
            try 
            {
                System.out.println("Press [1] to return back");
                int selection = scanner.nextInt();

                if (selection == 1) 
                {
                    // Assuming VendorPage is a method in the same class
                    CustomerPage(CustomerId);
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

    public static void main(String[] args) 
    {
        fop = new File_Operation();
        LoginPage();
    }	
}