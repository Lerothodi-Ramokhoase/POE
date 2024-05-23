/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poe;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class POE {
    public static String[][] users = new String[10][4]; // Storing user data: [username, password, first name, last name]
    public static int userCount = 0;
    public static Task[] tasks; // Array to store tasks
    public static int taskCount = 0;
    public static int taskNumber = 0; // Auto-incremented task number

    
    public static void main(String[] args) {
        boolean loggedIn = false;
        while (!loggedIn) {
            String choice = JOptionPane.showInputDialog("Choose an option:\n1. Register\n2. Login");
            switch (choice) {
                case "1":
                    register();
                    break;
                case "2":
                    loggedIn = login();
                    break;    
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
            }
        }
        
        
        JOptionPane.showMessageDialog(null, "Login successful!");
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");
        
        boolean running = true;
        while (running) {
            String choice = JOptionPane.showInputDialog("Choose an option:\n1. Add tasks\n2. Show report\n3. Quit");
            switch (choice) {
                case "1":
                    addTasks();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Coming Soon");
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
            }
        }
    }
    public static void register() {
        String username = JOptionPane.showInputDialog("Enter username (username must contain an underscore):");

        // Validate username
        if (username == null || username.length() > 5 || !username.contains("_")) {
            JOptionPane.showMessageDialog(null, "Invalid username! Username must contain an underscore and not more than 5 characters.");
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Username successfully captured");
        }
        // Validate password
        String password = JOptionPane.showInputDialog("Enter password:");
        if (password == null || password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*") || !password.matches(".*[!@#$%^&*()].*")) {
            JOptionPane.showMessageDialog(null, "Invalid password! Password must be at least 8 characters and contain at least one uppercase letter, one number, and one special character.");
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Password successfully captured");
        }
        String firstName = JOptionPane.showInputDialog("Enter first name:");
        String lastName = JOptionPane.showInputDialog("Enter last name:");
        // Check if username already exists
        for (int i = 0; i < userCount; i++) {
            if (username.equals(users[i][0])) {
                JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different one.");
                return;
            }
        }
        // Add user to the array
        users[userCount][0] = username;
        users[userCount][1] = password;
        users[userCount][2] = firstName;
        users[userCount][3] = lastName;
        userCount++;

        JOptionPane.showMessageDialog(null, "Registration successful!");
    }

    public static boolean login() {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");
        // Check if username and password match
        for (int i = 0; i < userCount; i++) {
            if (username.equals(users[i][0]) && password.equals(users[i][1])) {
                JOptionPane.showMessageDialog(null, "Welcome " + users[i][2] + " " + users[i][3] + ", It is great to see you again.");
                return true; // Login successful
            }
        }

        JOptionPane.showMessageDialog(null, "Username or password incorrect, Please try again.");
        return false; // Login failed
    }

    public static void addTasks() {
        String taskCountStr = JOptionPane.showInputDialog("How many tasks would you like to add?");
        int taskCount = Integer.parseInt(taskCountStr);

        tasks = new Task[taskCount]; // Initialize the tasks array

        for (int i = 0; i < taskCount; i++) {
            String taskName = JOptionPane.showInputDialog("Enter task name:");
            String taskDescription = JOptionPane.showInputDialog("Enter task description (max 50 characters):");

            // Validate task description length
            if (taskDescription.length() > 50) {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
                i--; // Repeat this task entry
                continue;
            } else {
                JOptionPane.showMessageDialog(null, "Task successfully captured");
            }
            String developerDetails = JOptionPane.showInputDialog("Enter developer's first and last name:");
            String taskDurationStr = JOptionPane.showInputDialog("Enter task duration in hours:");
            int taskDuration = Integer.parseInt(taskDurationStr);

            String taskStatus = JOptionPane.showInputDialog("Select task status:\n1. To Do\n2. Done\n3. Doing");
            String taskStatusStr = "";
            switch (taskStatus) {
                case "1":
                    taskStatusStr = "To Do";
                    break;
                case "2":
                    taskStatusStr = "Done";
                    break;
                case "3":
                    taskStatusStr = "Doing";
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Defaulting to 'To Do'.");
                    taskStatusStr = "To Do";
            }
            // Create the task and add to the list
            Task task = new Task(taskName, taskNumber, taskDescription, developerDetails, taskDuration, taskStatusStr);
            tasks[i] = task;
            taskNumber++;

            // Display task details
            JOptionPane.showMessageDialog(null, task.printTaskDetails());
        }
        // Display total hours after all tasks are entered
        int totalHours = returnTotalHours();
        JOptionPane.showMessageDialog(null, "Total hours for all tasks: " + totalHours);
    }

    public static int returnTotalHours() {
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.getTaskDuration();
        }
        return totalHours;
    }
}

// Task class with the specified methods
class Task {
    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskStatus;
    private String taskID;

    public Task(String taskName, int taskNumber, String taskDescription, String developerDetails, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID();
    }

    public boolean checkTaskDescription() {
        return this.taskDescription.length() <= 50;
    }

    public String createTaskID() {
        String taskID = (taskName.length() > 2 ? taskName.substring(0, 2) : taskName) + ":" + taskNumber + ":" + (developerDetails.length() > 3 ? developerDetails.substring(developerDetails.length() - 3) : developerDetails);
        return taskID.toUpperCase();
    }

    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n" +
                "Developer Details: " + developerDetails + "\n" +
                "Task Number: " + taskNumber + "\n" +
                "Task Name: " + taskName + "\n" +
                "Task Description: " + taskDescription + "\n" +
                "Task ID: " + taskID + "\n" +
                "Task Duration: " + taskDuration + " hours";
    }

    public int getTaskDuration() {
        return this.taskDuration;
    }
}
