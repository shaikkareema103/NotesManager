import java.io.*;
import java.util.Scanner;

public class NotesManager {
    private static final String FILE_NAME = "notes.txt";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("\n===== NOTES MANAGER =====");
            System.out.println("1. Add a Note");
            System.out.println("2. View All Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1 -> addNote(scanner);
                case 2 -> viewNotes();
                case 3 -> {
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again!");
            }
        }
    }
    private static void addNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) { // 'true' enables append mode
            fw.write(note + System.lineSeparator());
            System.out.println(" Note saved successfully!");
        } catch (IOException e) {
            System.out.println(" Error writing to file: " + e.getMessage());
        }
    }
    private static void viewNotes() {
        System.out.println("\n===== SAVED NOTES =====");
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean empty = true;
            while ((line = br.readLine()) != null) {
                System.out.println("- " + line);
                empty = false;
            }
            if (empty) {
                System.out.println("(No notes found)");
            }
        } catch (FileNotFoundException e) {
            System.out.println("(No notes file found yet)");
        } catch (IOException e) {
            System.out.println(" Error reading from file: " + e.getMessage());
        }
    }
}
