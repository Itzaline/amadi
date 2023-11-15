import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String accounts = null;
        String username, password;

        Scanner scanner = new Scanner(System.in);

        // Start program
        int successStart = 0;
        System.out.println("Hello! Welcome to Amadi!");
        while (successStart < 1) {
            System.out.println("Choose an option:");
            System.out.println("1. Create an account");
            System.out.println("2. Log in");
            System.out.println("3. Exit");
            System.out.println("4. Delete account");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
                    successStart++;
                    break;
                case 2:
                    System.out.print("Enter the username: ");
                    username = scanner.nextLine();
                    accounts = username + ".txt";
                    user = new User(accounts);
                    user.login(new File(accounts));
                    successStart++;
                    break;
                case 3:
                    System.out.println("Exiting the application.");
                    successStart++;
                    break;
                case 4:
                    user = new User(accounts);
                    user.deleteAccount();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            if (successStart >= 1) {
                // Continue with the main program
                int session = 0;
                while (session < 1) {
                    System.out.println("Select an option:");
                    System.out.println("1. Work with notes");
                    System.out.println("2. Work with task lists");
                    System.out.println("3. Exit");

                    int select = scanner.nextInt();
                    scanner.nextLine();

                    switch (select) {
                        case 1:
                            NoteManager noteManager = new NoteManager(accounts);
                            noteManager.handleNotes();
                            break;
                        case 2:
                            TaskListManager taskListManager = new TaskListManager(accounts);
                            taskListManager.handleTaskLists();
                            break;
                        case 3:
                            System.out.println("Exiting the application.");
                            session++;
                            break;
                        default:
                            System.out.println("Invalid value. Try again.");
                            break;
                    }
                }
            }
        }
    }
}
