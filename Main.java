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
            System.out.println("4. Clear cache");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter a username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter a password: ");
                    String password = scanner.nextLine();
                    User user = new User(username, password);
                    System.out.println("Account created successfully!");

                    //запись пароля в файл для дальнейшего входа
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(accounts));
                        writer.write(password);
                        writer.close();
                        System.out.println("Password has been saved.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    successStart++; //чтобы после создания акка или входа не возвращаться в начало
                    continue;
                case 2:
                    int access = 0;
                    while (access < 1) {
                        System.out.print("Enter a password: ");
                        String usersPassword = scanner.nextLine();

                        //чтение пароля из файла
                        try {
                            File pass = new File("List_of_acc.txt");
                            Scanner checker = new Scanner(pass);
                            while (checker.hasNextLine()) {
                                String data = checker.nextLine();
                                if (Objects.equals(data, usersPassword)) { //сравнение пароля из файла с введённым паролем
                                    System.out.println("Access cleared");
                                    access++;
                                } else {
                                    System.out.println("Access denied. Try again.");
                                }
                            }
                            checker.close();
                        } catch (FileNotFoundException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
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
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }


    }
}
