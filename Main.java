import java.io.BufferedWriter;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args){
        String sessionLog = "launch_count.txt";
        String accounts = "List_of_acc.txt";
        int launchCount = 0;
        //создание счётчика запусков
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

        //запись количества входов
        try {
            FileWriter writer = new FileWriter(sessionLog);
            writer.write(Integer.toString(launchCount));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //считывание количества сессий
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

        //если это первая сессия создаём файл для хранения логина и пароля (в идиале)
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
                    String username = scanner.nextLine();
                    System.out.print("Enter the password: ");
                    String password = scanner.nextLine();
                    User user = new User(username, password);
                    System.out.println("The account successfully created!");
                    user.createAccount(new File(accounts));
                    successStart++; //чтобы после создания акка или входа не возвращаться в начало
                    continue;
                case 2:
                    user = new User(accounts);
                    if (user.login()) {
                        System.out.println("Доступ разрешен.");
                        successStart++;
                    } else {
                        System.out.println("Доступ запрещен. Попробуйте снова.");
                    }
                    successStart++;
                    continue;
                case 3:
                    System.out.println("Exiting the application.");
                    scanner.close();
                    System.exit(0);
                    break;
                case 4:
                    try {
                        File file = new File(accounts);
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
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }


    }
}
