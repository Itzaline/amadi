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
            System.out.print("Введите пароль: ");
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


}
