import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Generates Person objects from user input and writes them to a CSV file.
 */
public class PersonGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Person> persons = new ArrayList<>();

        SafeInput.prettyHeader("Enter person(s) data below.");

        while (true) {
            // Gather person data
            String id = getValidId(scanner);
            String firstName = SafeInput.getNonZeroLenString(scanner, "Enter person's first name");
            String lastName = SafeInput.getNonZeroLenString(scanner, "Enter person's last name");
            String title = SafeInput.getNonZeroLenString(scanner, "Enter person's title (e.g., Mr., Mrs., Ms., Dr.)");
            int yearOfBirth = SafeInput.getInt(scanner, "Enter person's year of birth");

            // Create a Person object and add to the list
            Person person = new Person(id, firstName, lastName, title, yearOfBirth);
            persons.add(person);

            // Check if the user wants to continue
            boolean moreData = SafeInput.getYNConfirm(scanner, "Do you want to enter another person");
            if (!moreData) {
                break;
            }
        }

        // Save the data to a file
        String fileName = SafeInput.getNonZeroLenString(scanner, "Enter the file name to save data");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Person person : persons) {
                writer.write(person.toCSV());
                writer.newLine();
            }
            System.out.println("Data successfully saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }

        scanner.close();
    }

    private static String getValidId(Scanner scanner) {
        String id;
        while (true) {
            id = SafeInput.getNonZeroLenString(scanner, "Enter person's ID");
            if (isValidId(id)) {
                return id;
            } else {
                System.out.println("Invalid ID entered. Please enter a numeric ID.");
            }
        }
    }

    private static boolean isValidId(String id) {
        return id.matches("\\d+");
    }
}
