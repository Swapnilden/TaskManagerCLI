import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private static final String FILE_NAME = "tasks.txt";

    public static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                boolean isDone = parts[0].startsWith("[done]");
                String description = parts[0].substring(isDone ? 7 : 4);
                LocalDate dueDate = LocalDate.parse(parts[1].split(": ")[1]);
                Task.Priority priority = Task.Priority.valueOf(parts[2].split(": ")[1]);
                Task task = new Task(description, dueDate, priority);
                if (isDone) task.markAsDone();
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("No existing tasks found.");
        }
        return tasks;
    }

    public static void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }
}

