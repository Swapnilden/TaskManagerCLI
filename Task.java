import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task implements Serializable, Comparable<Task> {
    private String description;
    private boolean isDone;
    private LocalDate dueDate;
    private Priority priority;

    public enum Priority { LOW, MEDIUM, HIGH }

    public Task(String description, LocalDate dueDate, Priority priority) {
        this.description = description;
        this.isDone = false;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getDescription() { return description; }
    public boolean isDone() { return isDone; }
    public LocalDate getDueDate() { return dueDate; }
    public Priority getPriority() { return priority; }

    public void markAsDone() { this.isDone = true; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return (isDone ? "[done] " : "[ ] ") + description +
               " | Due: " + dueDate.format(formatter) + 
               " | Priority: " + priority;
    }

    @Override
    public int compareTo(Task other) {
        if (this.priority != other.priority) {
            return other.priority.ordinal() - this.priority.ordinal();
        }
        return this.dueDate.compareTo(other.dueDate);
    }
}
