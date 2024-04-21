import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Task Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. Delete Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. Create a report for tasks due today");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    add(manager, scanner);
                    break;
                case 2:
                    markCompleted(manager, scanner);
                    break;
                case 3:
                    delete(manager, scanner);
                    break;
                case 4:
                    view(manager);
                    break;
                case 5:
                    generateReportToday(manager);
                    break;
                case 6:
                    System.out.println("Exiting Task Manager...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    private static void add(TaskManager manager, Scanner scanner) {
        System.out.print("Enter Task Description: ");
        scanner.nextLine(); 
        String desc = scanner.nextLine();
        System.out.print("Enter Due Date: ");
        LocalDate dueDate = LocalDate.parse(scanner.next());
        System.out.print("Is this a high priority task?: ");
        String priorityInput = scanner.next().toLowerCase();
        boolean highPriority = priorityInput.equals("yes") || priorityInput.equals("y"); 
        manager.addTask(desc, dueDate, highPriority);
        System.out.println("Task added successfully! Task ID is " + manager.getTasks().size());
    }

    private static void markCompleted(TaskManager manager, Scanner scanner) {
        System.out.print("Enter Task ID to Mark as Completed: ");
        int id = scanner.nextInt();
        boolean found = false;
        for (Task task : manager.getTasks()) {
            if (task.getTaskId() == id) {
                manager.markTaskAsCompleted(id);
                System.out.println("Task marked as completed successfully!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    private static void delete(TaskManager manager, Scanner scanner) {
        System.out.print("Enter Task ID to Delete: ");
        int id = scanner.nextInt();
        boolean found = false;
        for (Task task : manager.getTasks()) {
            if (task.getTaskId() == id) {
                manager.deleteTask(id);
                System.out.println("Task deleted successfully!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    private static void view(TaskManager manager) {
        System.out.println("Displaying all tasks:");
        manager.viewAllTasks();
    }

    private static void generateReportToday(TaskManager manager) {
        System.out.println("Generating report:");
        manager.generateReportTasksDueToday(LocalDate.now()).forEach(task ->
                System.out.println("Task " + task.getTaskId() + ": " + task.getDescription() +
                        ", Due " + task.getDueDate()));
    }
}
