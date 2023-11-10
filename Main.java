import java.io.BufferedWriter;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args){
        String accounts = null;
        String username, password;

        Scanner scanner = new Scanner(System.in);

        //старт программы
        int successStart = 0;
        System.out.println("Hello! Welcome to Amadi!");
        while (successStart < 1) { //цикл, чтобы в случае ошибки вернуться к выбору
            System.out.println("Choose an option:");
            System.out.println("1. Create an account");
            System.out.println("2. Log in");
            System.out.println("3. Exit");
            System.out.println("4. Delete account");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter the password: ");
                    password = scanner.nextLine();
                    User user = new User(username, password);
                    System.out.println("The account successfully created!");
                    accounts = username;
                    user.createAccount(new File(accounts));
                    successStart++; //чтобы после создания акка или входа не возвращаться в начало
                    continue;
                case 2:
                    System.out.print("Enter the username: ");
                    username = scanner.nextLine();
                    accounts = username;
                    user = new User(accounts);
                    user.login(new File(accounts));
                    successStart++;
                    continue;
                case 3:
                    System.out.println("Exiting the application.");
                    scanner.close();
                    System.exit(0);
                    break;
                case 4:
                    user = new User(accounts);
                    user.deleteAccount();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        //сама программа
        int session = 0;
        while (session < 1){
            System.out.println("Select a option:");
            System.out.println("1. Work with notes");
            System.out.println("2. Work with task list");
            System.out.println("3. Exit");
            int select = scanner.nextInt();
            switch (select){
                case 1:
                    int workWithNotes = 0;
                    while (workWithNotes < 1){
                        System.out.println("Select a option:");
                        System.out.println("1. Create note");
                        System.out.println("2. Update note");
                        System.out.println("3. Delete note");
                        System.out.println("4. Show all notes");
                        System.out.println("5. Exit");
                        int selNote = scanner.nextInt();
                        switch (selNote){
                            case 1:
                                NoteManager notes = new NoteManager();
                                notes.create();
                                continue;
                            case 2:
                                notes = new NoteManager();
                                notes.update();
                                continue;
                            case 3:
                                notes = new NoteManager();
                                notes.delete();
                            case 4:
                                notes = new NoteManager();
                                notes.showAll();
                                continue;
                            case 5:
                                System.out.println("Exiting the application.");
                                scanner.close();
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Invalid value. Try again.");
                                break;
                        }
                    }
                    continue;
                case 2:
                    int workWithTaskList = 0;
                    while (workWithTaskList < 0){
                        System.out.println("Select a option:");
                        System.out.println("1. Create task list");
                        System.out.println("2. Update task list");
                        System.out.println("3. Delete task list");
                        System.out.println("4. Show all task list");
                        System.out.println("5. The list of tasks has been cleared");
                        System.out.println("6. Exit");
                        int selTask = scanner.nextInt();
                        switch (selTask){
                            case 1:
                                TaskListManager tasks = new TaskListManager();
                                tasks.create();
                                continue;
                            case 2:
                                tasks = new TaskListManager();
                                tasks.update();
                                continue;
                            case 3:
                                tasks = new TaskListManager();
                                tasks.delete();
                                continue;
                            case 4:
                                tasks = new TaskListManager();
                                tasks.showAll();
                                continue;
                            case 5:
                                tasks = new TaskListManager();
                                tasks.isCopmleted();
                                continue;
                            case 6:
                                System.out.println("Exiting the application.");
                                scanner.close();
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Invalid value. Try again.");
                                break;
                        }
                    }
                    continue;
                case 3:
                    System.out.println("Exiting the application.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid value. Try again.");
                    break;
            }
        }
    }
}
