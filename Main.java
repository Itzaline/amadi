import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Main {
    public static void main(String[] args){
        String sessionLog = "launch_count.txt";
        String accounts = "List_of_acc.txt";
        int launchCount = 0;

        try {
            File file = new File(sessionLog);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                if (scanner.hasNextInt()) {
                    launchCount = scanner.nextInt();
                }
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        launchCount++;

        try {
            FileWriter writer = new FileWriter(sessionLog);
            writer.write(Integer.toString(launchCount));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File file = new File(sessionLog);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                if (scanner.hasNextInt()) {
                    launchCount = scanner.nextInt();
                }
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (launchCount == 1){
            try {
                File file = new File(accounts);
                if (file.createNewFile()) {
                    System.out.println("Empty file created successfully.");
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Scanner scanner = new Scanner(System.in);
        AccountManager accountManager = new AccountManager();

        System.out.println("Hello! Welcome to Amadi!");
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Create an account");
            System.out.println("2. Log in");
            System.out.println("3. Exit");
            System.out.println("4. Clear cache");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter a username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter a password: ");
                    String password = scanner.nextLine();
                    User user = new User(username, password);
                    accountManager.createAccount(user);
                    System.out.println("Account created successfully!");

                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(accounts));
                        writer.write(username);
                        writer.write(password);
                        writer.close();
                        System.out.println("Data has been saved.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.print("Enter your username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String loginPassword = scanner.nextLine();

                    if (accountManager.login(loginUsername, loginPassword)) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Login failed. Invalid username or password.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting the application.");
                    scanner.close();
                    System.exit(0);
                    break;
                case 4:
                    try {
                        File file = new File(sessionLog);
                        if (file.exists()) {
                            FileWriter writer = new FileWriter(file);
                            writer.write("");
                            writer.close();
                            System.out.println("Data in the file has been deleted.");
                        } else {
                            System.out.println("File not found. Nothing to delete.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
