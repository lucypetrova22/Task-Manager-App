import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Man taskManager = new Man();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Task Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. Delete Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. Create a Report for Tasks Due Today");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addTask(taskManager, scanner);
                    break;
                case 2:
                    markAsCompleted(taskManager, scanner);
                    break;
                case 3:
                    deleteTask(taskManager, scanner);
                    break;
                case 4:
                    viewAllTasks(taskManager);
                    break;
                case 5:
                    ReportTasksDueToday(taskManager);
                    break;
                case 6:
                    System.out.println("Exiting Task Manager...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    private static void addTask(Man taskManager, Scanner scanner) {
        System.out.print("Enter Task Description: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        System.out.print("Enter Due Date (YYYY-MM-DD): ");
        LocalDate dueDate = LocalDate.parse(scanner.next());
        System.out.print("Is this a high priority task? ");
        String priorityInput = scanner.next().toLowerCase();
        boolean highPriority = priorityInput.equals("yes") || priorityInput.equals("y");
        taskManager.addTask(description, dueDate, highPriority);
        System.out.println("Task added successfully! Task ID is " + taskManager.getTasks().size());
    }

    private static void markAsCompleted(Man taskManager, Scanner scanner) {
        System.out.print("Enter Task ID to Mark as Completed: ");
        int taskId = scanner.nextInt();
        boolean taskFound = false;
        List<Task> tasks = taskManager.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getTaskId() == taskId) {
                taskManager.markAsCompleted(taskId);
                System.out.println("Task marked as completed successfully!");
                taskFound = true;
                break;
            }
        }
        if (!taskFound) {
            System.out.println("Task with ID " + taskId + " not found.");
        }
    }

    private static void deleteTask(Man taskManager, Scanner scanner) {
        System.out.print("Enter Task ID to Delete: ");
        int taskId = scanner.nextInt();
        boolean taskFound = false;
        List<Task> tasks = taskManager.getTasks();
        for (int i = 0; i < tasks.size(); i++) {                                        //Methods like add, markCompleted, 
                                                                                       //delete, view, and generateReportToday are responsible 
                                                                                      //for interacting with the TaskManager and performing tasks based on user choices.
            Task task = tasks.get(i);
            if (task.getTaskId() == taskId) {
                taskManager.deleteTask(taskId);
                System.out.println("Task deleted successfully!");
                taskFound = true;
                break;
            }
        }
        if (!taskFound) {
            System.out.println("Task with ID " + taskId + " not found.");
        }
    }

    private static void viewAllTasks(Man taskManager) {
        System.out.println("Displaying all tasks:");
        taskManager.viewAllTasks();
    }

    private static void ReportTasksDueToday(Man taskManager) {
        System.out.println("Generating report:");
        LocalDate today = LocalDate.now();
        List<Task> tasksDueToday = taskManager.ReportTasksDueToday(today);
        for (int i = 0; i < tasksDueToday.size(); i++) {
            Task task = tasksDueToday.get(i);
            System.out.println("Task " + task.getTaskId() + ": " + task.getDescription() +
                    ", Due " + task.getDueDate());
        }
    }
}
