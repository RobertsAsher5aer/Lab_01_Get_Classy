import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the reading and displaying of Product data from a file.
 */
public class ProductReader {

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Product Data File");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files", "txt"));

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            List<Product> products = readProductsFromFile(file);
            displayProducts(products);
        } else {
            System.out.println("No file selected.");
        }
    }

    private static List<Product> readProductsFromFile(File file) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String description = parts[2].trim();
                    double cost = Double.parseDouble(parts[3].trim());
                    products.add(new Product(id, name, description, cost));
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return products;
    }

    private static void displayProducts(List<Product> products) {
        int idWidth = 10;
        int nameWidth = 20;
        int descriptionWidth = 30;
        int costWidth = 10;

        System.out.printf("%-" + idWidth + "s %-"+ nameWidth + "s %-"+ descriptionWidth + "s %-"+ costWidth + "s%n", "ID", "Name", "Description", "Cost");
        System.out.println("=".repeat(idWidth + nameWidth + descriptionWidth + costWidth + 8)); // +8 for spaces between columns

        for (Product product : products) {
            System.out.printf("%-" + idWidth + "s %-"+ nameWidth + "s %-"+ descriptionWidth + "s %10.2f%n",
                    product.getId(), product.getName(), product.getDescription(), product.getCost());
        }
    }
}
