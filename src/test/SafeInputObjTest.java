import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SafeInputObjTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ByteArrayInputStream inputStream = new ByteArrayInputStream("John Doe\n25\n3.14\n50\n100\nY\nhello@example.com\n".getBytes());
    private PrintStream originalOut;
    private SafeInputObj safeInput;

    @Before
    public void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        System.setIn(inputStream);
        safeInput = new SafeInputObj(new Scanner(System.in));
    }

    @Test
    public void testGetNonZeroLenString() {
        String expected = "John Doe";
        String actual = safeInput.getNonZeroLenString("Enter your name");
        assertEquals("Expected and actual strings do not match.", expected, actual);
    }

    @Test
    public void testGetInt() {
        int expected = 25;
        int actual = safeInput.getInt("Enter your age");
        assertEquals("Expected and actual integers do not match.", expected, actual);
    }

    @Test
    public void testGetDouble() {
        double expected = 25.0;
        double actual = safeInput.getDouble("Enter your height");
        assertEquals("Expected and actual doubles do not match.", expected, actual, 0.0);
    }

    @Test
    public void testGetRangedInt() {
        int expected = 25;
        int actual = safeInput.getRangedInt("Enter your score", 0, 100);
        assertEquals("Expected and actual integers do not match.", expected, actual);
    }

    @Test
    public void testGetRangedDouble() {
        double expected = 50.0;
        double actual = safeInput.getRangedDouble("Enter your weight", 30.0, 300.0);
        assertEquals("Expected and actual doubles do not match.", expected, actual, 0.0);
    }

    @Test
    public void testGetYNConfirm() {
        boolean expected = true;
        boolean actual = safeInput.getYNConfirm("Do you want to continue?");
        assertEquals("Expected and actual boolean values do not match.", expected, actual);
    }


    @Test
    public void testPrettyHeader() {
        safeInput.prettyHeader("Test Header");

        // Get the output as a string
        String output = outputStream.toString();

        // Split the output into lines
        String[] lines = output.split("\n");

        // The expected border should be a line of 60 asterisks
        String expectedBorder = "*".repeat(60);

        // Verify the top border
        assertEquals("Top border is incorrect", expectedBorder, lines[0].trim());

        // Check the content line
        String contentLine = lines[1].trim();
        String expectedContent = "***                  Test Header                  ***";
        int expectedContentLength = 60;

        // Ensure the content line is correct
        assertTrue("Header content line should start with '***'", contentLine.startsWith("***"));
        assertTrue("Header content line should end with '***'", contentLine.endsWith("***"));
        assertEquals("Header content line length is incorrect", expectedContentLength, contentLine.length());
        assertTrue("Header content line should contain the message", contentLine.contains("Test Header"));

        // Verify the bottom border
        assertEquals("Bottom border is incorrect", expectedBorder, lines[2].trim());
    }

}


