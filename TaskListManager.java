import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskListManager implements RecordManager {

    private static final String TASK_LISTS_DIRECTORY = "task_lists";
    private static final String COMPLETED_TASKS_FILE = "completed_tasks.txt";
    private String username;

    public TaskListManager(String username) {
        this.username = username;
    }

    @Override
    public void create() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the name of the task list:");
            String name = scanner.nextLine();

            // Create a directory for the user if it doesn't exist
            File userDirectory = new File(TASK_LISTS_DIRECTORY, username);
            if (!userDirectory.exists()) {
                if (!userDirectory.mkdirs()) {
                    throw new IOException("Failed to create user directory.");
                }
            }

            // Create a file for the task list within the user's directory
            File file = new File(userDirectory, name + ".txt");
            if (file.createNewFile()) {
                System.out.println("Task list file created: " + file.getAbsolutePath());
            } else {
                System.out.println("Task list file already exists.");
            }

            // Open the file in Notepad
            openFileInNotepad(file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error creating the task list: " + e.getMessage());
        }
    }

    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the task list:");
        String name = scanner.nextLine();

        // Open the file in Notepad for updating
        openFileInNotepad(getTaskListFilePath(name));
    }

    @Override
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the task list: ");
        String name = scanner.nextLine();
        File file = new File(getTaskListFilePath(name));

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Task list has been deleted");
            } else {
                System.out.println("Error deleting the task list.");
            }
        } else {
            System.out.println("Task list not found");
        }
    }

    @Override
    public void showAll() {
        File userDirectory = new File(TASK_LISTS_DIRECTORY, username);
        File[] files = userDirectory.listFiles();

        if (files != null) {
            System.out.println("All task lists:");
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    System.out.println(file.getName());
                }
            }
        }
    }

    public void handleTaskLists() {
        int workWithTaskList = 0;
        while (workWithTaskList < 1) {
            System.out.println("Select an option:");
            System.out.println("1. Create task list");
            System.out.println("2. Update task list");
            System.out.println("3. Delete task list");
            System.out.println("4. Show all task lists");
            System.out.println("5. View completed tasks history");
            System.out.println("6. Mark a task as completed");
            System.out.println("7. Exit");

            Scanner scanner = new Scanner(System.in);
            int selTask = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (selTask) {
                    case 1:
                        create();
                        break;
                    case 2:
                        update();
                        break;
                    case 3:
                        delete();
                        break;
                    case 4:
                        showAll();
                        break;
                    case 5:
                        viewCompletedTasksHistory();
                        break;
                    case 6:
                        setTaskStatus();
                        break;
                    case 7:
                        System.out.println("Exiting the task list manager.");
                        workWithTaskList++;
                        break;
                    default:
                        System.out.println("Invalid value. Try again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error handling task lists: " + e.getMessage());
            }
        }
    }

    private String getTaskListFilePath(String name) {
        return new File(TASK_LISTS_DIRECTORY, username + File.separator + name + ".txt").getAbsolutePath();
    }

    private void openFileInNotepad(String filename) {
        try {
            Runtime.getRuntime().exec("notepad.exe " + filename);
        } catch (IOException e) {
            System.out.println("Error trying to open the file in Notepad.");
            e.printStackTrace();
        }
    }

    private void setTaskStatus() throws IOException {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the name of the task list to update status: ");
            String name = scanner.nextLine();
            File file = new File(getTaskListFilePath(name));

            if (file.exists()) {
                System.out.print("Is the task list completed? (yes/no): ");
                String statusInput = scanner.nextLine().toLowerCase();
                boolean isCompleted = statusInput.equals("yes");

                if (isCompleted) {
                    System.out.println("Task list marked as completed");
                    writeCompletedTaskToFile(name);
                } else {
                    System.out.println("Task list marked as not completed");
                }
            } else {
                System.out.println("Task list not found");
            }
        } catch (IOException e) {
            System.out.println("Error setting task status: " + e.getMessage());
            throw e;
        }
    }

    private void writeCompletedTaskToFile(String taskName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COMPLETED_TASKS_FILE, true))) {
            writer.write(username + ": " + taskName);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing completed task to file.");
            throw e;
        }
    }

    public void viewCompletedTasksHistory() throws IOException {
        try {
            File completedTasksFile = new File(COMPLETED_TASKS_FILE);
            if (completedTasksFile.exists()) {
                List<String> completedTasks = readCompletedTasksFromFile(completedTasksFile);
                if (!completedTasks.isEmpty()) {
                    System.out.println("Completed Tasks History:");
                    for (String task : completedTasks) {
                        System.out.println(task);
                    }
                } else {
                    System.out.println("No completed tasks found in history.");
                }
            } else {
                System.out.println("Completed tasks history file not found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading completed tasks history.");
            throw e;
        }
    }

    private List<String> readCompletedTasksFromFile(File file) throws IOException {
        List<String> completedTasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                completedTasks.add(line);
            }
        }
        return completedTasks;
    }
}
