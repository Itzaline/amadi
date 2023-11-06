import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! Welcome to Amadi!");
        System.out.println("Choose what do you want to do.");
        System.out.println("1. Create account");
        System.out.println("2. Delete account");
        System.out.println("3. Exit");
        int chooseStart = scanner.nextInt();
        switch (chooseStart) {
            case 1:
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();
                User user = new User(username, password);
                user.addPermission("read");
                user.addPermission("write");
                System.out.println("Account created successfully.");
                break;
            case 2:
                System.out.println("com soon");
                break;
            case 3:
                System.out.println("coming soon");
                break;
            default:
                System.out.print("Wrong input");
                break;
        }
    }
}
