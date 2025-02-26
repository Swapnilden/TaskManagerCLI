import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskManager {
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Task> tasks = TaskStorage.loadTasks();
    private static Stack<Task> deletedTasks = new Stack<>(); // Stack for Undo feature

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1 -> addTask();
                case 2 -> listTasks();
                case 3 -> markTaskAsDone();
                case 4 -> deleteTask();
                case 5 -> undoDelete();
                case 6 -> exitProgram();
                default -> System.out.println("Invalid option, try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n==================");
        System.out.println(" Task Manager ");
        System.out.println("==================");
        System.out.println("1. Add Task");
        System.out.println("2. List Tasks");
        System.out.println("3. Mark Task as Done");
        System.out.println("4. Delete Task");
        System.out.println("5. Undo Last Deletion");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void addTask() {
        System.out.print("Enter task description: ");
        scanner.nextLine();
        String description = scanner.nextLine();

        System.out.print("Enter due date (YYYY-MM-DD): ");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter priority (LOW, MEDIUM, HIGH): ");
        Task.Priority priority = Task.Priority.valueOf(scanner.nextLine().toUpperCase());

        tasks.add(new Task(description, dueDate, priority));
        tasks.sort(Comparator.naturalOrder());
        TaskStorage.saveTasks(tasks);
        System.out.println("Task added successfully.");
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        tasks.sort(Comparator.naturalOrder()); // Sort before displaying
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void markTaskAsDone() {
        listTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to mark as done: ");
        int index = getValidTaskIndex();
        if (index == -1) return;

        tasks.get(index).markAsDone();
        TaskStorage.saveTasks(tasks);
        System.out.println("Task marked as done.");
    }

    private static void deleteTask() {
        listTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to delete: ");
        int index = getValidTaskIndex();
        if (index == -1) return;

        System.out.print("Are you sure you want to delete this task? (yes/no): ");
        scanner.nextLine();
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            Task deletedTask = tasks.remove(index);
            deletedTasks.push(deletedTask); // Store in Undo Stack
            TaskStorage.saveTasks(tasks);
            System.out.println("Task deleted. You can undo this action.");
        } else {
            System.out.println("Task deletion cancelled.");
        }
    }

    private static void undoDelete() {
        if (!deletedTasks.isEmpty()) {
            Task restoredTask = deletedTasks.pop();
            tasks.add(restoredTask);
            tasks.sort(Comparator.naturalOrder());
            TaskStorage.saveTasks(tasks);
            System.out.println("Task restored successfully!");
        } else {
            System.out.println("No deleted tasks to restore.");
        }
    }

    private static int getValidTaskIndex() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Enter a valid task number.");
            scanner.next();
        }
        int index = scanner.nextInt() - 1;
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task number.");
            return -1;
        }
        return index;
    }

    private static void exitProgram() {
        System.out.println("Exiting... Goodbye!");
        System.exit(0);
    }
}
