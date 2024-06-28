/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASSIGNMENT;

import java.util.List;

/**
 *
 * @author Athelene
 */
public class Runner { //extends User 
    
    // Runner-specific attributes
    private String id;
    
    // Constructor
    public Runner(String id) {
        this.id = id;
    }

    /*
    // Constructor - if use extends to User abstract class
    public Runner(String id) {
        super(id); // This will set the id in the User class
        // Initialize runner-specific attributes
    }
    
    public Runner(String id) {
        this.id = id;
    }
    
    // Implement the abstract method from the User class
    @Override
    public void viewProfile() {
        // Fetch the runner's ID using getId() from the User class
        String runnerId = this.getId();
        // Now use runnerId to get the rest of the profile information
        String[] profile = File_Operation.readRunnerProfile(this.getId());
        if (profile != null) {
            System.out.println("Runner Profile:");
            System.out.println("ID: " + profile[0]);
            System.out.println("Name: " + profile[1]);
            System.out.println("Email: " + profile[2]);
            System.out.println("Contact Number: " + profile[3]);
        } else {
            System.out.println("Profile not found for Runner ID: " + this.getId());
        }
    }
    */

    // Runner-specific methods
    // This method displays the profile of the runner, including their ID, name, email, and contact number.
    public void displayProfile() {
        String[] profile = File_Operation.readRunnerProfile(this.id);
        if (profile != null) {
            System.out.println("Runner Profile:");
            System.out.println("ID: " + profile[0]);
            System.out.println("Name: " + profile[1]);
            System.out.println("Email: " + profile[2]);
            System.out.println("Contact Number: " + profile[3]);
        } else {
            System.out.println("Profile not found for Runner ID: " + this.id);
        }
    }  
    
    // This method retrieves and displays a list of tasks that are available for the runner. It checks for tasks assigned to the runner that are neither accepted nor completed.
    public void viewAvailableTasks() {
    List<String> tasks = File_Operation.readAvailableTasksForRunner(this.id);
    if (tasks.isEmpty()) {
        System.out.println("No available tasks for Runner ID: " + this.id);
    } else {
        System.out.println("Available tasks for Runner ID: " + this.id);
        for (String task : tasks) {
            System.out.println(task);
        }
    }
}

    // This method updates the acceptance status (Accepted/Declined) of a specific task for the runner. The updated status is displayed to the console.
    public void updateTaskAcceptanceStatus(String taskId, String newStatus) {
        File_Operation.updateTaskAcceptanceStatus(taskId, this.id, newStatus);
        System.out.println("Task " + taskId + " status updated to " + newStatus);
    }

    // This method updates the operational status (e.g., 'On Delivery', 'Completed') of a task. It also updates the corresponding order status in the "Orders.txt" file if the task is marked as 'Completed'.
     public void updateTaskOperationalStatus(String taskId, String newStatus) {
        File_Operation.updateTaskOperationalStatus(taskId, newStatus);
        System.out.println("Task " + taskId + " status updated to " + newStatus);
    }
    
    // This method displays a list of all tasks associated with the runner, including their IDs. It shows tasks that are in progress or not yet completed.
    public void displayTasksWithIDs() {
        List<String> tasks = File_Operation.readTasksForRunner(this.id);
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Available Tasks:");
            for (String task : tasks) {
                System.out.println(task);
            }
        }
    } 
     
     // This method displays the task history for the runner, focusing on tasks that have been completed.
    public void checkTaskHistory() {
        System.out.println("Task History for Runner " + this.id + ":");
        File_Operation.readRunnerTaskHistory(this.id);
    }

    // This method calculates and displays the runner's earnings for a specified period (daily, monthly, or yearly). Earnings are calculated based on the number of completed deliveries.
    public void viewEarnings(String period) {
        File_Operation.calculateRunnerEarnings(this.id, period);
    }
    
    // This method retrieves and displays customer reviews. It shows all reviews available in the "CustomerReview.txt" file.
    public void readCustomerReviews() {
        List<String> reviews = File_Operation.readCustomerReviews();
        if (reviews.isEmpty()) {
            System.out.println("No customer reviews available.");
        } else {
            System.out.println("Customer Reviews:");
            for (String review : reviews) {
                System.out.println(review);
            }
        }
    }
    
    /*@Override
    public void logActivity(String activity) { //If want to use the log activity interface that already have been created in the User abstract class (Implements)
        // Specific implementation for Runner
        System.out.println("Runner " + this.getId() + " performed: " + activity);
    }*/

    // This getter method returns the ID of the runner.
    public String getId() {
        return id;
    } 
}
