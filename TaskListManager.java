import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TaskListManager implements RecordManager {

    @Override
    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of task list:");
        String name = scanner.nextLine();
        //создание файла для записи списка задач
        try {
            File file = new File(name + ".txt");
            if (file.exists()) {
                scanner = new Scanner(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //открытие файла в блокноте
            Runtime.getRuntime().exec("notepad.exe " + name).waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error trying to open a file in notepad.");
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of note:");
        String name = scanner.nextLine();
        try {
            //открытие файла в блокноте
            Runtime.getRuntime().exec("notepad.exe " + name).waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error trying to open a file in notepad.");
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the task list: ");
        String name = scanner.nextLine();
        File file = new File(name);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File has been deleted");
            } else {
                System.out.println("Error");
            }
        } else {
            System.out.println("File has not been founded");
        }
    }

    @Override
    public void showAll() {
        File currentDirectory = new File(".");
        File[] files = currentDirectory.listFiles();

        if (files != null) {
            System.out.println("All task lists:");
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    System.out.println(file.getName());
                }
            }
        }
    }

    public void isCompleted(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the completed task list: ");
        String name = scanner.nextLine();
        File file = new File(name);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Task list has been deleted");
            } else {
                System.out.println("Error");
            }
        } else {
            System.out.println("Task list has not been founded");
        }
    }
}
