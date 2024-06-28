/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASSIGNMENT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class File_Operation 
{
    // Create ArrayList for different types of Users
    public static ArrayList<User> UserList = new ArrayList<>();  
    
    // Admin - Write data for Vendor and Runner
    public static void writeData(String filename, String id, String password) 
    {
    	File fileinput = new File(filename);
    	try 
        {
            FileWriter fw = new FileWriter(fileinput, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String line = id + "/" + password + "/" + "null" + "/" + "null" + "/" + 0;
            bw.newLine();
            pw.write(line);
            pw.close();
    	}
    	catch(IOException ex)
        {
            System.out.println("File does not exist: " + ex.getMessage());
    	}
    }
    
    // Admin - Write data for Customer only as Customer has a credit column
    public static void writeData_Customer(String filename, String id, String password)
    {
    	File fileinput = new File(filename);
    	try 
        {
    		
            FileWriter fw = new FileWriter(fileinput, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String line = id + "/" + password + "/" + "null" + "/" + "null" + "/" + 0 + "/" + 0;
            bw.newLine();
            pw.write(line);
            pw.close();
            System.out.println("Customer data has been written into Customers.txt!");
    	}
    	catch(IOException ex)
        {
             System.out.println("File does not exist: " + ex.getMessage() );
    	}  		
    }
	
    // Admin - Write data into Users.txt
    public static void writeData_User(String filename, String id, String password, String types) 
    {
    	File fileinput = new File(filename);
    	try 
        {
            FileWriter fw = new FileWriter(fileinput, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String line = id + "/" + password + "/" + types;
            bw.newLine();
            pw.write(line);
            pw.close();
            System.out.println("New user data has been written into Users.txt!");
    	}
    	catch(IOException ex)
        {
            System.out.println("File does not exist: " + ex.getMessage());
    	}
    }

    // Admin - Read Customer Data
    public static void Read_Data_From_File_Customers(String filename) 
    {
    	UserList.clear();
    	try 
        {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine())!= null)
            {
                String[] column = line.split("/");
                if(column.length == 6) 
                {
                    String id = column[0].trim();
                    String password = column[1].trim();
                    String username = column[2].trim();
                    String email = column[3].trim();
                    int contact_num = Integer.parseInt(column[4].trim());
                    int credit = Integer.parseInt(column[5].trim());
                    System.out.println("ID: " + id + " Password: " + password + " username: " + 
                    username + " email: " + email + " contact_number: " + contact_num + " credit: " + credit);
                    System.out.println("");
                }
            }
            br.close();
    	}
    	catch(IOException ex) 
        {
            System.out.println("Error in reading customer file: " + ex.getMessage());
    	}
    }

    // Admin - Read Vendor Data
    public static void Read_Data_From_File_Vendors(String filename)
    {
        UserList.clear();
        try 
        {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] column = line.split("/");
                if (column.length == 5) 
                {
                    String id = column[0].trim();
                    String password = column[1].trim();
                    String username = column[2].trim();
                    String email = column[3].trim();
                    String contactNum = column[4].trim();

                    System.out.println("ID: " + id + " Password: " + password + " username: " + 
                    username + " email: " + email + " contact_number: " + contactNum);
                    System.out.println("");
                }
            }
            br.close();
        } 
        catch (IOException ex) 
        {
            System.out.println("Error in reading vendor file: " + ex.getMessage());
        }
    }
    
    public static List<Vendor> Read_Data_From_File_Vendors1(String filename)
    {
        List<Vendor> vendors = new ArrayList<>();
        try 
        {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] column = line.split("/");
                if (column.length == 5) 
                {
                    String id = column[0].trim();
                    String password = column[1].trim();
                    String username = column[2].trim();
                    String email = column[3].trim();
                    String contactNum = column[4].trim();
                    
                    Vendor vendor = new Vendor(id, password, email, username, contactNum);
                    vendors.add(vendor);
                }
            }
            br.close();
        } 
        catch (IOException ex) 
        {
            System.out.println("Error in reading vendor file: " + ex.getMessage());
        }
        return vendors;
    }
    
    // Read Admin Information - Admin Info only has 1 and always on top
    public static void Read_Admin()
    {
       try (BufferedReader br = new BufferedReader(new FileReader("Users.txt"))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] data = line.split("/");
                for (String word : data) 
                {
                    if (word.equals("A0001")) 
                    {
                    	if(data.length == 3) 
                        {
                            String userid = data[0].trim();
                            String password = data[1].trim();
                            String type = data[2].trim();
                            System.out.println("ID: " + userid + "\nPassword: " + password + "\nTypes: " + type);
                        }
                    	
                    	else 
                        {
                            System.out.println("Text file with certain format is not found. Please check the latest txt file to check the latest format.");
                        }
                        System.out.println("User Found!");
                        return;  // Exit the method once the ID is found
                    }
                }
            }
        System.out.println("User Not Found!");
        } 
        catch (IOException ex) 
        {
            System.out.println("Error in reading admin file: " + ex.getMessage());
        }
    }
    
    // Admin - Read Runner Data
    public static void Read_Data_From_File_Runners(String filename) 
    {
    	UserList.clear();
    	try 
        {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine())!= null)
            {
    		String[] column = line.split("/");
    		if(column.length == 5)
                {
                    String id = column[0].trim();
                    String password = column[1].trim();
                    String username = column[2].trim();
                    String email = column[3].trim();
                    int contact_num = Integer.parseInt(column[4].trim());
                    System.out.println("ID: " + id + " Password: " + password + " username: " + 
                    username + " email: " + email + " contact_number: " + contact_num );
                    System.out.println("");

    		}
            }
            br.close();
        }
    	catch(IOException ex)
        {
    		System.out.println("Error in reading customer file: " + ex.getMessage());
    	}
    }
    
    // Admin - Search specific user data
    public static void Search_Specific_Data(String filename, String id) 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] data = line.split("/");
                for (String word : data) 
                {
                    if (word.equals(id)) 
                    {
                    	if(data.length == 5) 
                        {
                            String userid = data[0].trim();
                            String password = data[1].trim();
                            String username = data[2].trim();
                            String email = data[3].trim();
                            int contact = Integer.parseInt(data[4].trim());
                            System.out.println("ID: " + userid + " Password: " + password + " username: " + 
                            username + " email: " + email + " contact_number: " + contact);
                        }
                    	else if (data.length == 6) 
                        {
                            String userid = data[0].trim();
                            String password = data[1].trim();
                            String username = data[2].trim();
                            String email = data[3].trim();
                            int contact = Integer.parseInt(data[4].trim());
                            int credit = Integer.parseInt(data[5].trim());
                            System.out.println("ID: " + userid + " Password: " + password + " username: " + 
                            username + " email: " + email + " contact_number: " + contact + " credit: " + credit);
                    	}
                    	else 
                        {
                            System.out.println("Text file with certain format is not found. Please check the latest txt file to check the latest format.");
                        }
                        System.out.println("User Found!");
                        return;  // Exit the method once the ID is found
                    }
                }
            }
        System.out.println("User Not Found!");
        } 
        catch (IOException ex) 
        {
            System.out.println("Error in reading customer file: " + ex.getMessage());
        }
    }

    // Admin - Verify existing user when creating user
    public static boolean isUserExist(String filename, String id) 
    {
        try 
        {
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] data = line.split("/");
                for (String word : data) 
                {
                    if (word.equals(id)) 
                    {
                        // User with the given ID already exists
                        return true;
                    }
                }
            }
        } 
        catch (IOException ex) 
        {
            System.out.println("Error in reading user file: " + ex.getMessage());
        }
        // User with the given ID does not exist
        return false;
    }
    
    // Admin - Update details for Vendors and Runners
    public static void Update_User_Details(String filename, String id) 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] data = line.split("/");
                for (String word : data)
                {
                    if (word.equals(id)) 
                    {
                        String userid = data[0].trim();
                        String password = data[1].trim();
                        String username = data[2].trim();
                        String email = data[3].trim();
                        int contact = Integer.parseInt(data[4].trim());

                        // Display existing details
                        System.out.println();
                        System.out.println("Current Details:");
                        System.out.println("ID: " + userid + " Password: " + password + " Username: " +
                        username + " Email: " + email + " Contact Number: " + contact);                            
                        break;
                    }
                }
            }
        }
        catch (IOException ex) 
        {
            System.out.println("Error in reading/updating user file: " + ex.getMessage());
        }
    }
            
    // Admin - Update details for Customers
    public static void Update_User_Details_Customer(String filename, String id) 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] data = line.split("/");
                for (String word : data) 
                {
                    if (word.equals(id)) 
                    {
                        String userid = data[0].trim();
                        String password = data[1].trim();
                        String username = data[2].trim();
                        String email = data[3].trim();
                        int contact = Integer.parseInt(data[4].trim());
                        int credit = Integer.parseInt(data[5].trim());

                        // Display existing details
                        System.out.println();
                        System.out.println("Current Details:");
                        System.out.println("ID: " + userid + " Password: " + password + " Username: " +
                        username + " Email: " + email + " Contact Number: " + contact + " Credit: " + credit);
                        break;
                    }
                }
            }
        }                
        catch (IOException ex) 
        {
            System.out.println("Error in reading/updating user file: " + ex.getMessage());
        }
    }
           
    // Admin - Update for both Vendors and Runners due to same format in txt files
    public static void updateUserData(String filename, String id, String newUsername, String newEmail, int newContact) 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(filename));
        BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt", true))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] data = line.split("/");
                if (data[0].trim().equals(id)) 
                {
                    // Update the line with new details
                    String updatedLine = id + "/" + data[1].trim() + "/" + newUsername + "/" + newEmail + "/" + newContact;
                    bw.write(updatedLine);
                    bw.newLine();
                    System.out.println("User details updated successfully.");
                } 
                else 
                {
                    // Copy the existing line to the temporary file
                    bw.write(line);
                    bw.newLine();
                }
            }
        } 
        catch (IOException ex) 
        {
            System.out.println("Error updating user details: " + ex.getMessage());
        }
	
        // Replace the original file with the temporary file
        File originalFile = new File(filename);
        File tempFile = new File("temp.txt");
        if (tempFile.renameTo(originalFile)) 
        {
            System.out.println("File updated successfully.");
        } 
        else 
        {
            System.out.println("Error updating file.");
        }
    }

    // Admin - Update for Customers alone as it has an extra attributes: credit.
    public static void updateUserData_Customer(String filename, String id, String newUsername, String newEmail, int newContact) 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(filename));
        BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt", true))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] data = line.split("/");
                if (data[0].trim().equals(id)) 
                {
                    // Update the line with new details
                    String updatedLine = id + "/" + data[1].trim() + "/" + newUsername + "/" + newEmail + "/" + newContact + "/" + data[5].trim();
                    bw.write(updatedLine);
                    bw.newLine();
                    System.out.println("User details updated successfully.");
                } 
                else 
                {
                    // Copy the existing line to the temporary file
                    bw.write(line);
                    bw.newLine();
                }
            }
	} 
        catch (IOException ex) 
        {
            System.out.println("Error updating user details: " + ex.getMessage());
	}
	
	// Replace the original file with the temporary file
	File originalFile = new File(filename);
	File tempFile = new File("temp.txt");
	if (tempFile.renameTo(originalFile)) 
        {
             System.out.println("File updated successfully.");
	} 
        else 
        {
	    System.out.println("Error updating file.");
	}
    }

    // Admin - Delete User from User text files
    public static void deleteUser(String id) 
    {
        String idToDelete = id;
        try 
        {
            File inputFile = new File("Users.txt");
            File tempFile = new File("Temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

	    String line;
	    while ((line = reader.readLine()) != null) 
            {
                String[] wordsInLine = line.split("/");
                 if (!wordsInLine[0].equals(idToDelete)) 
                {
                    writer.println(line);
                }
            }
            writer.close();
            reader.close();
            // Rename the temporary file to the original file
            tempFile.renameTo(inputFile);
            System.out.println("User deleted");
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
	
    // Admin - Delete User from respective files
    public static void deleteFromUserTypesFiles(String id, String filename) 
    {
        String idToDelete = id;
        try 
        {
            File inputFile = new File(filename);
            File tempFile = new File("Temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] wordsInLine = line.split("/");
                if (!wordsInLine[0].equals(idToDelete)) 
                {
                    writer.println(line);
                }
            }

            writer.close();
            reader.close();

            // Rename the temporary file to the original file
            tempFile.renameTo(inputFile);

            System.out.println("User deleted");
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Admin - Show Credit Details before top-up
    public static void ShowCreditDetails(String id)
    {
        try (BufferedReader br = new BufferedReader(new FileReader("Customers.txt"))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] data = line.split("/");
	                
                for (String word : data) 
                {
                    if (word.equals(id)) 
                    {	                    	
                        if (data.length == 6) 
                        {
                            String userid = data[0].trim();	                        	
                            int credit = Integer.parseInt(data[5].trim());
                            System.out.println();
                            System.out.println("ID: " + userid + "\tCredit: " + credit);
                        }
                        else 
                        {
                            System.out.println("Text file with certain format is not found. Please check the latest txt file to check the latest format.");
                        }
                        System.out.println();
                        System.out.println("User Found!");
                        return;  
                    }
                }
            }

            System.out.println("User Not Found!");

        } 
        catch (IOException ex) 
        {
	    System.out.println("Error in reading customer file: " + ex.getMessage());
	}
    }

    // Admin - Top-up credit
    public static void TopUpCredit(String id, int amount) 
    {
	try (BufferedReader br = new BufferedReader(new FileReader("Customers.txt"));
	BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt", true))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
		String[] data = line.split("/");
		
		if (data[0].trim().equals(id)) 
                {
		    int initialcredit = Integer.parseInt(data[5].trim());
		    int topupcredit = initialcredit + amount;
		    String updatedLine = id + "/" + data[1].trim() + "/" + data[2].trim() + "/" + data[3].trim() + "/" + data[4].trim() + "/" + topupcredit;
		    bw.write(updatedLine);
		    bw.newLine();
		    System.out.println("User credit details updated successfully.");
		    System.out.println("Credit: " + initialcredit + " -> " + topupcredit);
		} 
                else 
                {
		    // Copy the existing line to the temporary file
		    bw.write(line);
		    bw.newLine();
		}
            }
	} 
        catch (IOException ex) 
        {
            System.out.println("Error updating user details: " + ex.getMessage());
	}
		
	// Replace the original file with the temporary file
	File originalFile = new File("Customers.txt");
	File tempFile = new File("temp.txt");
	if (tempFile.renameTo(originalFile)) 
        {
            System.out.println("File updated successfully.");
	} 
        else 
        {
             System.out.println("Error updating file.");
	}
    }
    
    // Admin - Generate Receipt after top-up credit and write into the Notifications text files
    public static void generateReceipt(String id, int amount) 
    {
        File fileinput = new File("Notifications.txt");
    	try 
        {    		
            FileWriter fw = new FileWriter(fileinput, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String line = id + "/" + "A0001" + "/" + "Receipt for Top-Up" + "/" + "Top-up Credit:\t\t\t\t\t" + amount;
            bw.newLine();
            pw.write(line);
            pw.close();
    	}
    	catch(IOException ex)
        {
            System.out.println("File does not exist: " + ex.getMessage());
    	}
    }
	
    // Admin - View the latest Receipt after top-up, allocate the last line
    public static void viewLatestReceipt(String id) 
    {
	String lastline = "";
		
	try (BufferedReader br = new BufferedReader(new FileReader("Notifications.txt"))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] notification = line.split("/");
                for (String word : notification) 
                {
                    if (word.equals(id)) 
                    {                    	           	                        	
                    	lastline = line;		                        
                    }
                }
            }
        } 
        catch (IOException ex) 
        {
            System.out.println("Error in reading customer file: " + ex.getMessage());
        }
	if(!lastline.equals(""))
        {
	    viewLatestReceipt(id, lastline);
	} 
        else 
        {
	    System.out.println("User Not Found! " + id);
	}
    }
	
    // Admin - Print the latest Receipt after top-up
    public static void viewLatestReceipt(String id, String lastline) 
    {
        String [] notification = lastline.split("/");          
        if(notification.length == 4) 
        {
            String receiverid = notification[0].trim();
            String senderid = notification[1].trim();
            String title = notification[2].trim();
            String content = notification[3].trim();
            System.out.println("****************************************************");
            System.out.println("To: " + receiverid + "\nFrom: " + senderid );
            System.out.println("****************************************************");
            System.out.println("\t\t" + title);
            System.out.println();
            System.out.println(content);
            System.out.println();    	
            System.out.println();
            System.out.println("----------------------------------------------------");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();         	    	

        }                 
        else 
        {
            System.out.println("Text file with certain format is not found. Please check the latest txt file to check the latest format.");
        }
    }
                           		
    // Admin + Customer - View Receipt History
    public static void viewReceipt(String id) 
    {
	boolean found = false;
	try (BufferedReader br = new BufferedReader(new FileReader("Notifications.txt"))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] notification = line.split("/");
                for (String word : notification) 
                {
                    if (word.equals(id)) 
                    {
                    	
                    	if(!found) 
                        {
                            System.out.println("Matching ID: " + id);
                            found = true;
                    	}
	                if(notification.length == 4) 
                        {
	                    String receiverid = notification[0].trim();
                            String senderid = notification[1].trim();
                            String title = notification[2].trim();
                            String content = notification[3].trim();
                            System.out.println("****************************************************");
                            System.out.println("To: " + receiverid + "\nFrom: " + senderid );
                            System.out.println("****************************************************");
                            System.out.println("\t\t" + title);
                            System.out.println();
                            System.out.println(content);
                            System.out.println();
                            System.out.println();
                            System.out.println("----------------------------------------------------");
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                        }                 
                        else 
                        {
                            System.out.println("Text file with certain format is not found. Please check the latest txt file to check the latest format.");
                        }
                    }
                }
            }
        } 
        catch (IOException ex) 
        {
            System.out.println("Error in reading customer file: " + ex.getMessage());
        }	
	if(!found) 
        {
	    System.out.println("User Not Found or User has not top-up yet. Hence, no receipt!"); 
	}
    }
	
    // For login purpose, check whether id matches with the password.
    public boolean searchDataLogin(String filename, String id, String pw) 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filename))))
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] userData = line.split("/");

                if (userData.length >= 3) 
                {
                    String storedID = userData[0];
                    String storedPW = userData[1];

                    if (id.equals(storedID) && pw.equals(storedPW)) 
                    {
                        return true; // Match found
                    }
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return false; // No match found
    }

    // For login purpose, after matching the user, know the user type and navigate to correct main page based on types.
    public String getUserType(String filename, String id, String pw) 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filename))))
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] userData = line.split("/");

                if (userData.length >= 3) 
                {
                    String storedID = userData[0];
                    String storedPW = userData[1];
                    String userType = userData[2];

                    if (id.equals(storedID) && pw.equals(storedPW)) 
                    {
                        return userType; // Match found, return the user type
                    }
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return null; // No match found
    }
    
    // Delivery Runner - Method to initialize RunnerID
    public static boolean isValidRunnerId(String runnerId) {
    try (BufferedReader br = new BufferedReader(new FileReader("Runners.txt"))) {
        System.out.println("");
        System.out.println("");
        System.out.printf("%-10s %-25s %-30s %-15s%n", "RunnerID", "Name", "Email", "Contact Number");
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("/"); // Assuming the format is ID/Name/Email/...
            if (parts.length > 0 && parts[0].equals(runnerId)) {
                System.out.printf("%-10s %-25s %-30s %-15s%n", parts[0], parts[1], parts[2], parts[3]);
                return true;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
}

    // Delivery Runner - This method reads and returns a list of tasks that are available for a specific runner. It checks for tasks where the runnerId matches and both acceptance and operational status are empty.
    public static List<String> readAvailableTasksForRunner(String runnerId) {
    List<String> availableTasks = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("Tasks.txt"))) {
        System.out.println("");
        System.out.println("");
        System.out.printf("%-10s %-15s %-20s %-20s %-20s %-25s %-25s %-15s%n", 
                  "TaskID", "OrderID", "AssignedRunnerID", "AcceptanceStatus", "OperationalStatus", "PickupAddress", "DeliveryAddress", "Date");
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("TaskID")) {
                continue; // Skip the header line
            }
            String[] parts = line.split("/");
            if (parts.length > 7 && parts[2].equals(runnerId) && parts[3].isEmpty() && parts[4].isEmpty()) {
                availableTasks.add(line);
                System.out.printf("%-10s %-15s %-20s %-20s %-20s %-25s %-25s %-15s%n", 
                                  parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
     // Check if no available tasks were found
    if (availableTasks.isEmpty()) {
        availableTasks.add("None");
    }
        return availableTasks;
    }
    
     // Delivery Runner - This method updates the acceptance status (Accepted/Declined) of a task for a given taskId and runnerId.
    public static void updateTaskAcceptanceStatus(String taskId, String runnerId, String newAcceptanceStatus) {
        updateTaskStatus(taskId, runnerId, newAcceptanceStatus, 3); // 3 is the index for AcceptanceStatus
    }
    
    // Delivery Runner - This method retrieves the orderId associated with a given taskId from the "Tasks.txt" file.
    private static String getOrderIdForTask(String taskId) {
    try (BufferedReader br = new BufferedReader(new FileReader("Tasks.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("/");
            if (parts.length > 1 && parts[0].equals(taskId)) {
                return parts[1]; // OrderID
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}

    // Delivery Runner - This method updates the status (e.g., 'Delivered') of an order in the "Orders.txt" file based on the given orderId.
    public static void updateOrderStatus(String orderId, String newStatus) {
    File tempFile = new File("Orders_temp.txt");
    File originalFile = new File("Orders.txt");

    try (BufferedReader br = new BufferedReader(new FileReader(originalFile));
         BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("/");
            if (parts.length > 8 && parts[1].equals(orderId)) {
                parts[9] = newStatus; // Update OrderStatus
                line = String.join("/", parts);
            }
            bw.write(line);
            bw.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        // Rename temp file to original file
        if (originalFile.delete()) {
            tempFile.renameTo(originalFile);
        }
    }
}
    // Delivery Runner - This method is to display the "Orders.txt" after get updated
    public static void displayOrders() {
    File ordersFile = new File("Orders.txt");
    try (BufferedReader br = new BufferedReader(new FileReader(ordersFile))) {
        System.out.println("");
        System.out.println("");
        System.out.printf("%-10s %-10s %-20s %-10s %-10s %-20s %-10s %-30s %-10s %-15s %-15s %-20s%n", "CustomerID", "OrderID", "Item Name", "Price", "Quantity", "Delivery Address", "Status", "Date", "Order Type", "Order Status", "Order Amount", "Pick Up Address");
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("/");
            if (!parts[0].equals("CustomerID")) { // Skip the header
                System.out.printf("%-10s %-10s %-20s %-10s %-10s %-20s %-10s %-30s %-10s %-15s %-15s %-20s%n", parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8], parts[9], parts[10], parts[11]);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
    // Delivery Runner - This method updates the operational status (e.g., 'Completed') of a task. If the new status is 'Completed', it also updates the corresponding order status in "Orders.txt" to 'Delivered'.
    public static void updateTaskOperationalStatus(String taskId, String newOperationalStatus) {
    // Update the task status in Tasks.txt
    updateTaskStatus(taskId, "", newOperationalStatus, 4); // 4 is the index for OperationalStatus
    
    // Retrieve the associated order ID for the task
    String orderId = getOrderIdForTask(taskId);
    if (orderId != null) {
        // Check if the status is "Completed" or "On Delivery"
        if ("Completed".equals(newOperationalStatus)) {
            // If the task is completed, mark the order as "Delivered" in Orders.txt
            updateOrderStatus(orderId, "Delivered");
        } else if ("On Delivery".equals(newOperationalStatus)) {
            // If the task is now on delivery, mark the order as "Out for Delivery" in Orders.txt
            updateOrderStatus(orderId, "Out for Delivery");
        }
    }
}

    // Delivery Runner - This is a generic method used internally to update a specific status field of a task in the "Tasks.txt" file. It is used by both updateTaskAcceptanceStatus and updateTaskOperationalStatus methods.
    private static void updateTaskStatus(String taskId, String runnerId, String newStatus, int statusIndex) {
        File tempFile = new File("Tasks_temp.txt");
        File originalFile = new File("Tasks.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(originalFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("/");
                if (parts.length > statusIndex && parts[0].equals(taskId)) {
                    if (!runnerId.isEmpty() && statusIndex == 3) {
                        parts[2] = runnerId; // Update Runner ID for acceptance status
                    }
                    parts[statusIndex] = newStatus; // Update specified status
                    line = String.join("/", parts);
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Attempt to delete the original file and rename the temp file
            if (originalFile.delete()) {
                if (!tempFile.renameTo(originalFile)) {
                    System.out.println("Could not rename the temporary file to the original file name.");
                }
            } else {
                System.out.println("Could not delete the original tasks file.");
            }
        }
    }
    
    // Delivery Runner - This method is to display the "Tasks.txt" after get updated
    public static void displayTasks(String runnerId) {
    try (BufferedReader br = new BufferedReader(new FileReader("Tasks.txt"))) {
        // Print the table header
        System.out.println("");
        System.out.println("");
        System.out.printf("%-10s %-10s %-20s %-15s %-15s %-20s %-20s %-15s%n", 
                          "TaskID", "OrderID", "AssignedRunnerID", "AcceptanceStatus", 
                          "OperationalStatus", "PickupAddress", "DeliveryAddress", "Date");
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("/");
            if (parts.length >= 8 && parts[2].equals(runnerId)) {
                System.out.printf("%-10s %-10s %-20s %-15s %-15s %-20s %-20s %-15s%n", 
                                  parts[0], parts[1], parts[2], parts[3], parts[4], 
                                  parts[5], parts[6], parts[7]);
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading Tasks.txt: " + e.getMessage());
    }
}

    // Delivery Runner - This method prints the task history for a specific runner, focusing on tasks that have been completed.
    public static void readRunnerTaskHistory(String runnerId) {
    boolean completedTasksFound = false;

    try (BufferedReader br = new BufferedReader(new FileReader("Tasks.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains(runnerId) && line.contains("Completed")) {
                // Assuming the task format includes the status 'Completed'
                System.out.println(line);
                completedTasksFound = true;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    if (!completedTasksFound) {
        System.out.println("None"); // Display "None" if no completed tasks are found
    }
}

    // Delivery Runner - This method calculates and displays the total earnings of a runner for a specified period (daily, monthly, or yearly). The earnings are based on completed tasks.
    public static void calculateRunnerEarnings(String runnerId, String period) {
        int totalCompletedTasks = 0;
        LocalDate now = LocalDate.now();

        try (BufferedReader br = new BufferedReader(new FileReader("Tasks.txt"))) {
            System.out.println("");
            System.out.println("");
            System.out.printf("%-10s %-15s %-20s %-20s %-20s %-25s %-25s %-15s%n", 
                  "TaskID", "OrderID", "RunnerID", "AcceptanceStatus", "OperationalStatus", "PickupAddress", "DeliveryAddress", "Date");
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("/");
                if (parts.length > 7 && parts[2].equals(runnerId) && "Completed".equals(parts[4])) {
                    LocalDate taskDate = LocalDate.parse(parts[7], DateTimeFormatter.ISO_LOCAL_DATE);

                    if ((period.equals("daily") && taskDate.equals(now)) ||
                        (period.equals("monthly") && taskDate.getMonth() == now.getMonth() && taskDate.getYear() == now.getYear()) ||
                        (period.equals("yearly") && taskDate.getYear() == now.getYear())) {
                        totalCompletedTasks++;
                    }
                    System.out.printf("%-10s %-15s %-20s %-20s %-20s %-25s %-25s %-15s%n", 
                        parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int earnings = totalCompletedTasks * 4; // 4 ringgit per delivery
        System.out.println("Total Earnings for " + period + ": " + earnings + " Ringgit");
    }
    
    // Delivery Runner - This method resets tasks to their original state and updates order statuses to "Pending".
    public static void resetTasksToOriginal() {
    List<String> updatedTasks = new ArrayList<>();
    Set<String> orderIdsToUpdate = new HashSet<>();

    // Read tasks and reset statuses
    try (BufferedReader br = new BufferedReader(new FileReader("Tasks.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("/");
            if (parts.length > 4) {
                // Collect order IDs to update their statuses later
                if (!parts[3].isEmpty() || !parts[4].isEmpty()) {
                    orderIdsToUpdate.add(parts[1]);
                }
                // Reset AcceptanceStatus and OperationalStatus
                parts[3] = "";
                parts[4] = "";
                line = String.join("/", parts);
            }
            updatedTasks.add(line);
        }
    } catch (IOException e) {
        System.out.println("Error reading tasks: " + e.getMessage());
    }

    // Write updated tasks back to file
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Tasks.txt"))) {
        for (String task : updatedTasks) {
            bw.write(task);
            bw.newLine();
        }
    } catch (IOException e) {
        System.out.println("Error writing updated tasks: " + e.getMessage());
    }

    // Update order statuses to "Pending"
    if (!orderIdsToUpdate.isEmpty()) {
        updateOrdersToPending(orderIdsToUpdate);
    }
}

    // Delivery Runner - Helper method to update order statuses in Orders.txt to "Pending"
    private static void updateOrdersToPending(Set<String> orderIdsToUpdate) {
        File tempFile = new File("Orders_temp.txt");
        File originalFile = new File("Orders.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(originalFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("/");
                if (parts.length > 8 && orderIdsToUpdate.contains(parts[1])) {
                    // Update OrderStatus to "Pending"
                    parts[9] = "Pending";
                    line = String.join("/", parts);
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Rename temp file to original file
            if (originalFile.delete()) {
                if (!tempFile.renameTo(originalFile)) {
                    System.out.println("Could not rename the temporary file to the original file name.");
                }
            } else {
                System.out.println("Could not delete the original Orders file.");
            }
        }
    }
    
    // Delivery Runner - This method reads and returns a list of customer reviews from the "CustomerReview.txt" file.
    public static List<String> readCustomerReviews() {
        List<String> reviews = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Review.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                reviews.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviews;
    }
    
    // Delivery Runner - This method is to display the "Review.txt" after get updated
    public static void displayReviews() {
    try (BufferedReader br = new BufferedReader(new FileReader("Review.txt"))) {
        // Print the table header
        System.out.println("");
        System.out.printf("%-15s %-10s %-30s%n", "CustomerID", "OrderID", "Reviews");
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("/");
            if (parts.length >= 3) {
                System.out.printf("%-15s %-10s %-30s%n", parts[0], parts[1], parts[2]);
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading Review.txt: " + e.getMessage());
    }
}
    
    // Delivery Runner - This method extracts delivery tasks from the "Orders.txt" file and appends them to the "Tasks.txt" file. It checks for new delivery orders that are not already in the tasks file and assigns a new or existing TaskID to them.
    private static String lastAssignedRunnerId = "R0002"; // Start with R0002 so that R0001 is the first to be assigned
    public static void extractDeliveryTasks() {
    String ordersFilePath = "Orders.txt";
    String tasksFilePath = "Tasks.txt";
    Map<String, String> orderIdToTaskIdMap = new HashMap<>();
    Set<String> existingOrderIds = new HashSet<>();

    // Read existing tasks to find already processed order IDs and their associated TaskID
    try (BufferedReader tasksReader = new BufferedReader(new FileReader(tasksFilePath))) {
        String taskLine;
        while ((taskLine = tasksReader.readLine()) != null) {
            if (taskLine.startsWith("TaskID")) {
                continue; // Skip the header line
            }
            String[] taskParts = taskLine.split("/");
            if (taskParts.length > 1) {
                existingOrderIds.add(taskParts[1]); // Assuming order ID is the second element
                orderIdToTaskIdMap.put(taskParts[1], taskParts[0]); // Map OrderID to TaskID
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    LocalDate currentDate = LocalDate.now();
    String formattedDate = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);

    // Process orders.txt and append new tasks to tasks.txt
    try (BufferedReader ordersReader = new BufferedReader(new FileReader(ordersFilePath));
         BufferedWriter tasksWriter = new BufferedWriter(new FileWriter(tasksFilePath, true))) {

        String orderLine;
        boolean newTasksAdded = false;
        int nextTaskId = getNextTaskId(tasksFilePath);

        while ((orderLine = ordersReader.readLine()) != null) {
            if (orderLine.startsWith("CustomerID")) {
                continue; // Skip the header line
            }
            String[] orderParts = orderLine.split("/");
            if (orderParts.length >= 12 && "Delivery".equals(orderParts[8]) && !existingOrderIds.contains(orderParts[1])) {
                String taskId;
                if (orderIdToTaskIdMap.containsKey(orderParts[1])) {
                    // Use existing TaskID for this OrderID
                    taskId = orderIdToTaskIdMap.get(orderParts[1]);
                } else {
                    // Create new TaskID for this OrderID
                    taskId = "T" + String.format("%03d", nextTaskId++);
                    orderIdToTaskIdMap.put(orderParts[1], taskId);
                    newTasksAdded = true;
                }
                
                String assignedRunnerId = lastAssignedRunnerId.equals("R0001") ? "R0002" : "R0001";
                lastAssignedRunnerId = assignedRunnerId; // Update the last assigned Runner ID
                String newTask = taskId + "/" + orderParts[1] + "/" + assignedRunnerId + "///" + orderParts[11] + "/" + orderParts[5] + "/" + formattedDate;
                tasksWriter.write(newTask);
                tasksWriter.newLine();
            }
        }

        if (newTasksAdded) {
            System.out.println("Tasks updated from orders.");
        } else {
            System.out.println("No new tasks available.");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    // Delivery Runner - This method finds the highest TaskID number in the "Tasks.txt" file and returns the next available TaskID.
    private static int getNextTaskId(String tasksFilePath) throws IOException {
    int highestId = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(tasksFilePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty() || !line.matches("T\\d{3}/.*")) {
                // Skip blank lines and lines that do not start with 'T' followed by exactly three digits
                continue;
            }
            try {
                int taskId = Integer.parseInt(line.split("/")[0].substring(1));
                highestId = Math.max(highestId, taskId);
            } catch (NumberFormatException e) {
                System.out.println("Skipping line with invalid TaskID format: " + line);
            }
        }
    }
    return highestId + 1;
}
    
    // Delivery Runner - This method reads and returns a list of all tasks (including both completed and non-completed) associated with a specific runner, identified by their runnerId.
    public static List<String> readTasksForRunner(String runnerId) {
        List<String> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Tasks.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(runnerId) && !line.contains("Completed")) { 
                    tasks.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
    
    // Delivery Runner - This method reads the profile of a runner from the Runners.txt file.
    public static String[] readRunnerProfile(String runnerId) {
        try (BufferedReader br = new BufferedReader(new FileReader("Runners.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("/");
                if (parts.length > 3 && parts[0].equals(runnerId)) {
                    return parts; // Return the parts of the profile
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Runners.txt: " + e.getMessage());
        }
        return null; // Return null if profile not found
    }
    
    // Customer - Write food review
    public static void writeReview(String filename, String name, String review) {
    	File fileinput = new File(filename);
    	try {
    		FileWriter fw = new FileWriter(fileinput, true);
    		BufferedWriter bw = new BufferedWriter(fw);
                try (PrintWriter pw = new PrintWriter(bw)) {
                    String line = name + "/" + review;
                    bw.newLine();
                    pw.write(line);
                }
    	}
    	catch(IOException ex){
    		System.out.println("File does not exist: " + ex.getMessage());
    	}
    }
    
    // Admin - Read Customer Data
    public static void Read_Data_From_Vendor_Menu(String filename) {
    	UserList.clear();
    	try {
    		FileReader fr = new FileReader(filename);
                try (BufferedReader br = new BufferedReader(fr)) {
                    String line;
                    while ((line = br.readLine())!= null)
                    {
                        String[] column = line.split("/");
                        if(column.length == 6) {
                            String restaurant_name = column[0].trim();
                            String description = column[1].trim();
                            double Price = Double.parseDouble(column[2].trim());
                            int Quantity = Integer.parseInt(column[3].trim());
                            String Address = column[4].trim();
                            System.out.println("Restaurant Name: " + restaurant_name + " Food Details: " + description + " Pricing: " +
                                    Price + " Quantity: " + Quantity + " Address: " + Address);
                            System.out.println("");
                        }
                    }
                }
    	}
    	catch(IOException ex) {
    		System.out.println("Error in reading item file: " + ex.getMessage());
    	}
    }
    
        // Customer place order
    public static void Customer_place_order(String filename, String id) {
        
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;

                while ((line = br.readLine()) != null) {
                    String[] data = line.split("/");

                    for (String word : data) {
                        if (word.equals(id)) {
                            String orderid = data[0].trim();
                            String itemName = data[1].trim();
                            double itemPrice = Double.parseDouble(data[2].trim());
                            int itemQuantity = Integer.parseInt(data[3].trim());
                            String Address = data[4].trim();

                            // Display Order Details
                            System.out.println();
                            System.out.println("Order Details:");
                            System.out.println("Order ID: " + orderid + " Restaurant Name: " + itemName + " Pricing: " +
                                    itemPrice + " Quantity: " + itemQuantity + " Address: " + Address);                        
                            break;
                            }
                        }
                    }
                }

               
            catch (IOException ex) {
                System.out.println("Error in reading/updating user file: " + ex.getMessage());}
            }
    
     // Customers - Read Customers Data
    public static List<Customer> Read_Data_From_File_Customer1(String filename, String customerid)
    {
        String Id = customerid;
        List<Customer> Customers = new ArrayList<>();
        try 
        {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] column = line.split("/");
                if (column.length == 6 && column[0].trim().equals(Id)) 
                {
                    String id = column[0].trim();
                    String password = column[1].trim();
                    String username = column[2].trim();
                    String email = column[3].trim();
                    String contactNum = column[4].trim();
                    String balance = column[5].trim();

                    Customer customer = new Customer(id, password, email, username, contactNum, balance);
                    Customers.add(customer);
                }
            }
            br.close();
        } 
        catch (IOException ex) 
        {
            System.out.println("Error in reading customer file: " + ex.getMessage());
        }
        return Customers;
    }
}