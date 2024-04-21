import java.time.LocalDate;

public class Task {
    private int taskId;
    private String description;
    private LocalDate dueDate;
    private boolean completed;
    private boolean highPriority;

    // Constructor to initialize task attributes
    public Task(int taskId, String description, LocalDate dueDate, boolean highPriority) {
        this.taskId = taskId;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = false;
        this.highPriority = highPriority;
    }

    // Getters and setters for task attributes
    public int getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public boolean isHighPriority() {
        return highPriority;
    }
}

