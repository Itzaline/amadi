import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NoteManager implements RecordManager {
    @Override
    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of note:");
        String name = scanner.nextLine();
        //создание файла для записи
        try {
            File file = new File(name + ".txt");
            if (file.exists()) {
                scanner = new Scanner(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //открытие файла в блокноте
            Runtime.getRuntime().exec("notepad.exe " + name).waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error trying to open a file in notepad.");
            e.printStackTrace();
        }

    }

    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of note:");
        String name = scanner.nextLine();
        try {
            //открытие файла в блокноте
            Runtime.getRuntime().exec("notepad.exe " + name).waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error trying to open a file in notepad.");
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the note: ");
        String name = scanner.nextLine();
        File file = new File(name);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File has been deleted");
            } else {
                System.out.println("Error");
            }
        } else {
            System.out.println("File has not been founded");
        }
    }

    @Override
    public void showAll() {

    }
}
