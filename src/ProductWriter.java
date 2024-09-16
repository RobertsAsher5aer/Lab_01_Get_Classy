import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles the creation and storage of Product objects.
 */
public class ProductWriter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();

        System.out.println("Enter product information below.");
        while (true) {
            String id = SafeInput.getRegExString(scanner, "Enter Product ID: ", "\\d+");
            String name = getValidName(scanner);
            String description = getValidDescription(scanner);
            double cost = SafeInput.getDouble(scanner, "Enter Product Cost: ");

            products.add(new Product(id, name, description, cost));

            boolean done = SafeInput.getYNConfirm(scanner, "Add another product? ");
            if (!done) {
                break;
            }
        }

        String fileName = SafeInput.getNonZeroLenString(scanner, "Enter file name to save data: ");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : products) {
                writer.write(product.toCSV());
                writer.newLine();
            }
            System.out.println("Data successfully saved to: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static String getValidName(Scanner scanner) {
        String name;
        while (true) {
            name = SafeInput.getNonZeroLenString(scanner, "Enter Product Name: ");
            if (isValidText(name)) {
                break;
            } else {
                System.out.println("Invalid input. Product name cannot contain numbers.");
            }
        }
        return name;
    }

    private static String getValidDescription(Scanner scanner) {
        String description;
        while (true) {
            description = SafeInput.getNonZeroLenString(scanner, "Enter Product Description: ");
            if (isValidText(description)) {
                break;
            } else {
                System.out.println("Invalid input. Product description cannot contain numbers.");
            }
        }
        return description;
    }

    private static boolean isValidText(String input) {
        return !input.matches(".*\\d.*");
    }
}
