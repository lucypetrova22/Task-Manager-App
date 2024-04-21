import java.time.LocalDate; // Import LocalDate class from java.time package
import java.util.ArrayList; // Import ArrayList class from java.util package
import java.util.List; // Import List interface from java.util package
import java.util.Scanner; // Import Scanner class from java.util package

public class Main {
    public static void main(String[] args) {
        Man taskManager = new Man(); // Create an instance of the Man class to manage tasks
        Scanner scanner = new Scanner(System.in); // Create a Scanner object to read user input

        // Main menu loop
        while (true) {
            // Displaying menu options
            System.out.println("Task Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. Delete Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. Create a Report for Tasks Due Today");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt(); // Read user's choice

            // Handling user's choice
            switch (choice) {
                case 1:
                    addTask(taskManager, scanner); // Call method to add a new task
                    break;
                case 2:
                    markAsCompleted(taskManager, scanner); // Call method to mark a task as completed
                    break;
                case 3:
                    deleteTask(taskManager, scanner); // Call method to delete a task
                    break;
                case 4:
                    viewAllTasks(taskManager); // Call method to view all tasks
                    break;
                case 5:
                    ReportTasksDueToday(taskManager); // Call method to generate a report for tasks due today
                    break;
                case 6:
                    System.out.println("Exiting Task Manager..."); // Print exit message
                    System.exit(0); // Exit the program
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6."); // Print invalid choice message
            }
        }
    }

    // Method to add a new task
    private static void addTask(Man taskManager, Scanner scanner) {
        System.out.print("Enter Task Description: "); // Prompt user to enter task description
        scanner.nextLine(); // Consume newline
        String description = scanner.nextLine(); // Read task description
        System.out.print("Enter Due Date (YYYY-MM-DD): "); // Prompt user to enter due date
        LocalDate dueDate = LocalDate.parse(scanner.next()); // Read due date
        System.out.print("Is this a high priority task? "); // Prompt user to specify priority
        String priorityInput = scanner.next().toLowerCase(); // Read priority input
        boolean highPriority = priorityInput.equals("yes") || priorityInput.equals("y"); // Set high priority flag based on user input
        taskManager.addTask(description, dueDate, highPriority); // Call method to add task
        System.out.println("Task added successfully! Task ID is " + taskManager.getTasks().size()); // Print success message
    }

    // Method to mark a task as completed
    private static void markAsCompleted(Man taskManager, Scanner scanner) {
        System.out.print("Enter Task ID to Mark as Completed: "); // Prompt user to enter task ID
        int taskId = scanner.nextInt(); // Read task ID
        boolean taskFound = false; // Initialize flag to indicate if task is found
        for (Task task : taskManager.getTasks()) { // Iterate over tasks
            if (task.getTaskId() == taskId) { // Check if task ID matches
                taskManager.markAsCompleted(taskId); // Call method to mark task as completed
                System.out.println("Task marked as completed successfully!"); // Print success message
                taskFound = true; // Set taskFound flag to true
                break; // Exit loop
            }
        }
        if (!taskFound) { // If task is not found
            System.out.println("Task with ID " + taskId + " not found."); // Print error message
        }
    }

    // Method to delete a task
    private static void deleteTask(Man taskManager, Scanner scanner) {
        System.out.print("Enter Task ID to Delete: "); // Prompt user to enter task ID
        int taskId = scanner.nextInt(); // Read task ID
        boolean taskFound = false; // Initialize flag to indicate if task is found
        for (Task task : taskManager.getTasks()) { // Iterate over tasks
            if (task.getTaskId() == taskId) { // Check if task ID matches
                taskManager.deleteTask(taskId); // Call method to delete task
                System.out.println("Task deleted successfully!"); // Print success message
                taskFound = true; // Set taskFound flag to true
                break; // Exit loop
            }
        }
        if (!taskFound) { // If task is not found
            System.out.println("Task with ID " + taskId + " not found."); // Print error message
        }
    }

    // Method to view all tasks
    private static void viewAllTasks(Man taskManager) {
        System.out.println("Displaying all tasks:"); // Print message
        taskManager.viewAllTasks(); // Call method to view all tasks
    }

    // Method to generate a report of tasks due today
    private static void ReportTasksDueToday(Man taskManager) {
        System.out.println("Generating report:"); // Print message
        taskManager.ReportTasksDueToday(LocalDate.now()).forEach(task -> // Call method to generate report for tasks due today
                System.out.println("Task " + task.getTaskId() + ": " + task.getDescription() +
                        ", Due " + task.getDueDate())); // Print task details
    }
}
