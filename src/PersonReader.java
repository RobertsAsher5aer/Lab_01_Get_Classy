import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Reads Person objects from a CSV file and displays them.
 */
public class PersonReader {

    public static void main(String[] args) {
        // JFileChooser Instance
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Person Data File");

        // Set file filter to only show text files
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files", "txt"));

        // Show the file chooser dialog
        int userSelection = fileChooser.showOpenDialog(null);

        // Checkpoint
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Read and process the file
            ArrayList<Person> persons = readPersonsFromFile(file);
            displayPersons(persons);
        } else {
            System.out.println("No file selected.");
        }
    }

    /**
     * Reads Person objects from a file and returns an ArrayList of Person objects.
     *
     * @param file the file to read from
     * @return an ArrayList of Person objects
     */
    private static ArrayList<Person> readPersonsFromFile(File file) {
        ArrayList<Person> persons = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                if (fields.length == 5) {
                    // Create a Person object from the CSV fields
                    String id = fields[0].trim();
                    String firstName = fields[1].trim();
                    String lastName = fields[2].trim();
                    String title = fields[3].trim();
                    int yearOfBirth;
                    try {
                        yearOfBirth = Integer.parseInt(fields[4].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid year of birth format: " + fields[4]);
                        continue; // Skip this line and move to the next
                    }

                    Person person = new Person(id, firstName, lastName, title, yearOfBirth);
                    persons.add(person);
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return persons;
    }

    /**
     * Displays the list of Person objects in a formatted table.
     *
     * @param persons the ArrayList of Person objects to display
     */
    private static void displayPersons(ArrayList<Person> persons) {
        // Define column widths
        int idWidth = 10;
        int firstNameWidth = 15;
        int lastNameWidth = 15;
        int titleWidth = 10;
        int yobWidth = 5;

        // Print the header
        System.out.printf("%-" + idWidth + "s %-"+ firstNameWidth + "s %-"+ lastNameWidth + "s %-"+ titleWidth + "s %-"+ yobWidth + "s%n",
                "ID#", "Firstname", "Lastname", "Title", "YOB");
        System.out.println("=".repeat(idWidth + firstNameWidth + lastNameWidth + titleWidth + yobWidth + 4)); // +4 for the spaces between columns

        // Print each Person object
        for (Person person : persons) {
            System.out.printf("%-" + idWidth + "s %-"+ firstNameWidth + "s %-"+ lastNameWidth + "s %-"+ titleWidth + "s %-"+ yobWidth + "d%n",
                    person.getId(),
                    person.getFirstName(),
                    person.getLastName(),
                    person.getTitle(),
                    person.getYearOfBirth());
        }
    }
}
