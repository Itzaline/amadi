import java.io.*;
import java.util.Scanner;

import java.io.*;
import java.util.Scanner;

public class User {
    private String password;
    private String accountsFile;

    public User(String accountsFile) {
        this.accountsFile = accountsFile;
    }

    public User(String username, String password) {
        this.password = password;
    }

    public void createAccount(File accounts) {
        File file = new File(accounts + ".txt");

        // Write the password to the file for future login
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(password);
            writer.newLine();
            System.out.println("Account has been created.");
        } catch (IOException e) {
            System.out.println("Error creating the account file.");
            e.printStackTrace();
        }
    }

    public void login(File accounts) {
        Scanner scanner = new Scanner(System.in);
        File login = new File(accounts.toURI());

        int access = 0;
        while (access < 1) {
            if (login.exists() && login.isFile()) {
                System.out.print("Enter the password: ");
                String inputPassword = scanner.nextLine();
                try (BufferedReader reader = new BufferedReader(new FileReader(login))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.equals(inputPassword)) {
                            System.out.println("Access granted.");
                            access++;
                        } else {
                            System.out.println("Access denied. Try again.");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error reading the password file.");
                }
            }
        }
    }

    public void deleteAccount() {
        File file = new File(accountsFile + ".txt");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Account has been deleted");
            } else {
                System.out.println("Error deleting the account.");
            }
        } else {
            System.out.println("Account not found");
        }
    }
}

