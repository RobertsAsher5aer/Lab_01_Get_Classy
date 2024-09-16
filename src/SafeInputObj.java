import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Provides methods to safely input various types of data from a Scanner instance.
 */
public class SafeInputObj {
    private Scanner pipe;

    /**
     * Default constructor that uses System.in as the input source.
     */
    public SafeInputObj() {
        this.pipe = new Scanner(System.in);
    }

    /**
     * Constructor that allows specifying a custom Scanner.
     * @param scanner The Scanner to be used for input.
     */
    public SafeInputObj(Scanner scanner) {
        this.pipe = scanner;
    }

    /**
     * Gets a non-empty string from the user.
     * @param prompt The prompt to display to the user.
     * @return A non-empty string entered by the user.
     */
    public String getNonZeroLenString(String prompt) {
        String retString;
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.isEmpty());
        return retString;
    }

    /**
     * Gets an integer value from the user.
     * @param prompt The prompt to display to the user.
     * @return An integer entered by the user.
     */
    public int getInt(String prompt) {
        int value = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(prompt);
            if (pipe.hasNextInt()) {
                value = pipe.nextInt();
                validInput = true;
            } else {
                String trash = pipe.next();
                System.out.println("Invalid input: " + trash + ". Please enter a valid integer.");
            }
            pipe.nextLine();
        }
        return value;
    }

    /**
     * Gets a double value from the user.
     * @param prompt The prompt to display to the user.
     * @return A double entered by the user.
     */
    public double getDouble(String prompt) {
        double value = 0.0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("\n" + prompt);
            if (pipe.hasNextDouble()) {
                value = pipe.nextDouble();
                validInput = true;
            } else {
                String trash = pipe.next();
                System.out.println("Invalid input: " + trash + ". Please enter a valid double.");
            }
            pipe.nextLine();
        }
        return value;
    }

    /**
     * Gets an integer value within a specified range from the user.
     * @param prompt The prompt to display to the user.
     * @param low The minimum acceptable value.
     * @param high The maximum acceptable value.
     * @return An integer within the specified range.
     */
    public int getRangedInt(String prompt, int low, int high) {
        int value = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                value = pipe.nextInt();
                if (value >= low && value <= high) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input: " + value + ". Please enter a value between " + low + " and " + high + ".");
                }
            } else {
                String trash = pipe.next();
                System.out.println("Invalid input: " + trash + ". Please enter a valid integer.");
            }
            pipe.nextLine();
        }
        return value;
    }

    /**
     * Gets a double value within a specified range from the user.
     * @param prompt The prompt to display to the user.
     * @param low The minimum acceptable value.
     * @param high The maximum acceptable value.
     * @return A double within the specified range.
     */
    public double getRangedDouble(String prompt, double low, double high) {
        double value = 0.0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                value = pipe.nextDouble();
                if (value >= low && value <= high) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input: " + value + ". Please enter a value between " + low + " and " + high + ".");
                }
            } else {
                String trash = pipe.next();
                System.out.println("Invalid input: " + trash + ". Please enter a valid double.");
            }
            pipe.nextLine();
        }
        return value;
    }

    /**
     * Gets a yes or no confirmation from the user.
     * @param prompt The prompt to display to the user.
     * @return true if the user inputs 'Y', false otherwise.
     */
    public boolean getYNConfirm(String prompt) {
        boolean validInput = false;
        boolean response = false;

        while (!validInput) {
            System.out.print(prompt + " [Y/N]: ");
            String input = pipe.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("n")) {
                validInput = true;
                response = input.equals("y");
            } else {
                System.out.println("Invalid input: " + input + ". Please enter 'Y' or 'N'.");
            }
        }
        return response;
    }

    /**
     * Gets a string that matches a specified regular expression.
     * @param prompt The prompt to display to the user.
     * @param regEx The regular expression pattern to match.
     * @return A string that matches the pattern.
     */
    public String getRegExString(String prompt, String regEx) {
        boolean validInput = false;
        String input = "";

        while (!validInput) {
            System.out.print(prompt + " ");
            input = pipe.nextLine().trim();
            if (input.matches(regEx)) {
                validInput = true;
            } else {
                System.out.println("Invalid input: " + input + ". Please enter a string that matches the pattern: " + regEx);
            }
        }
        return input;
    }

    /**
     * Prints a pretty header with a message.
     * @param msg The message to display in the header.
     */
    public void prettyHeader(String msg) {
        int totalWidth = 60;
        int starsWidth = 3;
        int maxMessageWidth = totalWidth - starsWidth * 2 - 2; // 2 for the spaces on each side of the message

        // Print the top border
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();

        // Print the message lines
        while (msg.length() > 0) {
            String line;
            if (msg.length() > maxMessageWidth) {
                line = msg.substring(0, maxMessageWidth);
                msg = msg.substring(maxMessageWidth);
            } else {
                line = msg;
                msg = "";
            }

            int paddingWidth = (totalWidth - starsWidth * 2 - line.length()) / 2;

            System.out.print("***");
            for (int i = 0; i < paddingWidth; i++) {
                System.out.print(" ");
            }
            System.out.print(line);
            for (int i = 0; i < paddingWidth; i++) {
                System.out.print(" ");
            }
            // Add an extra space if the total width is odd
            if ((line.length() + starsWidth * 2) % 2 != 0) {
                System.out.print(" ");
            }
            System.out.println("***");
        }

        // Print the bottom border
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

}
