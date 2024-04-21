import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Man {
    private List<Task> tasks; // List to store tasks
    private int nextTaskId; // Counter for generating unique task IDs

    // Constructor to initialize task list and task ID counter
    public Man() {
        this.tasks = new ArrayList<>();
        this.nextTaskId = 1; // Initialize the counter
    }

    // Adds a new task to the list with a unique task ID
    public void addTask(String description, LocalDate dueDate, boolean highPriority) {
        Task task = new Task(nextTaskId, description, dueDate, highPriority);
        tasks.add(task);
        nextTaskId++; // Increment task ID counter
    }

    // Marks a task as completed based on its task ID
    public void markAsCompleted(int taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getTaskId() == taskId) {
                task.setCompleted(true);
                return;
            }
        }
        System.out.println("Task with ID " + taskId + " not found.");
    }

    // Deletes a task based on its task ID
    public void deleteTask(int taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getTaskId() == taskId) {
                tasks.remove(i);
                return;
            }
        }
        System.out.println("Task with ID " + taskId + " not found.");
    }

    // Displays details of all tasks in the list
    public void viewAllTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String priority = task.isHighPriority() ? "High Priority" : "Normal Priority"; // Display priority
            System.out.println("Task " + task.getTaskId() + ": " + task.getDescription() + " - Due: " + task.getDueDate() +
                    " - Completed: " + task.isCompleted() + " - Priority: " + priority);
        }
    }

    // Generates a report of tasks due today
    public List<Task> ReportTasksDueToday(LocalDate today) {
        List<Task> tasksDueToday = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDueDate().isEqual(today)) {
                tasksDueToday.add(task);
            }
        }
        return tasksDueToday;
    }

    // Method to get tasks
    public List<Task> getTasks() {
        return tasks;
    }
}
