# Task Manager CLI

A **feature-rich command-line task manager** built in Java.  
This application allows users to:  
✅ **Add tasks** with **due dates and priority levels**  
✅ **List tasks** (sorted by priority and due date)  
✅ **Mark tasks as done**  
✅ **Delete tasks** (with an undo feature)  
✅ **Persist tasks** in a `tasks.txt` file  

## How to Run

1. Compile the program:
   ```sh
   javac TaskManager.java TaskStorage.java Task.java
   ```
2. Run the program:
   ```sh
   java TaskManager
   ```

## Features
✅ Persistent task storage – Saves tasks to tasks.txt
✅ Task prioritization – Sort tasks by priority (LOW, MEDIUM, HIGH)
✅ Due dates support – Add and display due dates for tasks
✅ Undo deletion – Recover the last deleted task
✅ User-friendly CLI – Simple and interactive

## 📝 Usage Guide

| **Action**         | **Steps** |
|--------------------|----------|
| **Add a Task**     | Enter description, due date (`YYYY-MM-DD`), and priority (`LOW, MEDIUM, HIGH`) |
| **List Tasks**     | Displays tasks sorted by priority & due date |
| **Mark Task as Done** | Select a task to mark as complete |
| **Delete Task**    | Select a task to delete (with confirmation) |
| **Undo Deletion**  | Restore the last deleted task |

## Contributing
Feel free to fork and improve this project!
