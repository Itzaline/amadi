import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private String accountsFile;

    public User(String accountsFile) {
        this.accountsFile = accountsFile;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void createAccount(File accounts) {
        //запись пароля в файл для дальнейшего входа
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(accounts, true)); // Добавляем данные к файлу
            writer.write(username);
            writer.newLine(); // Добавляем новую строку для разделения записей
            writer.write(password);
            writer.newLine();
            writer.close();
            System.out.println("Data has been saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean login(){
        Scanner scanner = new Scanner(System.in);
        int access = 0;
        while (access < 1) {
            System.out.print("Enter the password: ");
            String usersPassword = scanner.nextLine();

            try {
                File pass = new File(accountsFile);
                Scanner checker = new Scanner(pass);
                boolean found = false;
                while (checker.hasNextLine()) {
                    String data = checker.nextLine();
                    if (Objects.equals(data, usersPassword)) {
                        found = true;
                        break;
                    }
                }
                checker.close();
                if (found) {
                    return true;
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error.");
                e.printStackTrace();
            }
            System.out.println("Access denied. Try again.");
        }
        return false;
    }

    public void deleteAccount(){
        try {
            File file = new File(accountsFile);
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
    }

}
