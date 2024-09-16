import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the Person class.
 */
public class PersonTest {

    private Person person;

    @Before
    public void setUp() {
        // Initialize a Person object for testing
        person = new Person("1", "John", "Doe", "Mr.", 1985);
    }

    @Test
    public void testFullName() {
        assertEquals("John Doe", person.fullName());
    }

    @Test
    public void testFormalName() {
        assertEquals("Mr. John Doe", person.formalName());
    }

    @Test
    public void testGetAgeCurrentYear() {
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        assertEquals(String.valueOf(currentYear - 1985), person.getAge());
    }

    @Test
    public void testGetAgeSpecificYear() {
        assertEquals("35", person.getAge(2020)); // 2020 - 1985 = 35
    }

    @Test
    public void testToCSV() {
        assertEquals("1,John,Doe,Mr.,1985", person.toCSV());
    }

    @Test
    public void testToJSON() {
        String expectedJSON = "{\"id\":\"1\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"title\":\"Mr.\",\"yearOfBirth\":1985}";
        assertEquals(expectedJSON, person.toJSON());
    }

    @Test
    public void testToXML() {
        String expectedXML = "<Person><Id>1</Id><FirstName>John</FirstName><LastName>Doe</LastName><Title>Mr.</Title><YearOfBirth>1985</YearOfBirth></Person>";
        assertEquals(expectedXML, person.toXML());
    }
}
