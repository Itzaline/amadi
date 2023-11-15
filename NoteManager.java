import java.io.*;
import java.util.Scanner;

public class NoteManager implements RecordManager {

    private static final String NOTES_DIRECTORY = "notes";
    private String username;

    public NoteManager(String username) {
        this.username = username;
    }

    @Override
    public void create() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the name of the note:");
            String name = scanner.nextLine();

            // Create a directory for the user if it doesn't exist
            File userDirectory = new File(NOTES_DIRECTORY, username);
            if (!userDirectory.exists()) {
                if (!userDirectory.mkdirs()) {
                    throw new IOException("Failed to create user directory.");
                }
            }

            // Create a file for the note within the user's directory
            File file = new File(userDirectory, name + ".txt");
            if (file.createNewFile()) {
                System.out.println("Note file created: " + file.getAbsolutePath());
            } else {
                System.out.println("Note file already exists.");
            }

            // Open the file in Notepad
            openFileInNotepad(file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error creating the note: " + e.getMessage());
        }
    }

    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the note:");
        String name = scanner.nextLine();

        // Open the file in Notepad for updating
        openFileInNotepad(getNoteFilePath(name));
    }

    @Override
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the note: ");
        String name = scanner.nextLine();
        File file = new File(getNoteFilePath(name));

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Note has been deleted");
            } else {
                System.out.println("Error deleting the note.");
            }
        } else {
            System.out.println("Note not found");
        }
    }

    @Override
    public void showAll() {
        File userDirectory = new File(NOTES_DIRECTORY, username);
        File[] files = userDirectory.listFiles();

        if (files != null) {
            System.out.println("All notes:");
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    System.out.println(file.getName());
                }
            }
        }
    }

    public void handleNotes() {
        int workWithNotes = 0;
        while (workWithNotes < 1) {
            System.out.println("Select an option:");
            System.out.println("1. Create note");
            System.out.println("2. Update note");
            System.out.println("3. Delete note");
            System.out.println("4. Show all notes");
            System.out.println("5. Exit");

            Scanner scanner = new Scanner(System.in);
            int selNote = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (selNote) {
                    case 1:
                        create();
                        break;
                    case 2:
                        update();
                        break;
                    case 3:
                        delete();
                        break;
                    case 4:
                        showAll();
                        break;
                    case 5:
                        System.out.println("Exiting the note manager.");
                        workWithNotes++;
                        break;

                        default:
                        System.out.println("Invalid value. Try again.");
                        break;
            }
        }
    }

    private String getNoteFilePath(String name) {
        return new File(NOTES_DIRECTORY, username + File.separator + name + ".txt").getAbsolutePath();
    }

    private void openFileInNotepad(String filename) {
        try {
            Runtime.getRuntime().exec("notepad.exe " + filename);
        } catch (IOException e) {
            System.out.println("Error trying to open the file in Notepad.");
            e.printStackTrace();
        }
    }
}
