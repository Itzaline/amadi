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
        //запись пароля в файл для дальнейшего входа
        try {
            // Добавляем данные к файлу
            BufferedWriter writer = new BufferedWriter(new FileWriter(accounts, true));
            writer.write(password);
            writer.close();
            System.out.println("Data has been saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(File accounts) {
        Scanner scanner = new Scanner(System.in);
        File login = new File(accounts.toURI());
        int access =0;
        while (access < 1) {
            if (login.exists() && login.isFile()) {
                System.out.print("Enter the password: ");
                String password = scanner.nextLine();
                try (BufferedReader reader = new BufferedReader(new FileReader(login))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.equals(password)) {
                            System.out.println("Access is allowed.");
                            access++;
                        }  else {
                            System.out.println("Access denied. Try again.");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error");
                }
            }
        }
    }

    public void deleteAccount() {
        File file = new File(accountsFile);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Account has been deleted");
            } else {
                System.out.println("Error");
            }
        } else {
            System.out.println("Account has not been founded");
        }
    }
}
