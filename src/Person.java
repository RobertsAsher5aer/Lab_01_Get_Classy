import java.io.Serializable;

/**
 * Represents a person with an ID, first name, last name, title, and year of birth.
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String firstName;
    private String lastName;
    private String title;
    private int yearOfBirth;

    /**
     * Constructs a Person object with the specified attributes.
     *
     * @param id the ID of the person
     * @param firstName the first name of the person
     * @param lastName the last name of the person
     * @param title the title of the person
     * @param yearOfBirth the year of birth of the person
     */
    public Person(String id, String firstName, String lastName, String title, int yearOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * Returns the ID of the person.
     *
     * @return the ID of the person
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the first name of the person.
     *
     * @return the first name of the person
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the person.
     *
     * @return the last name of the person
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the title of the person.
     *
     * @return the title of the person
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the year of birth of the person.
     *
     * @return the year of birth of the person
     */
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * Returns the full name of the person in the format: "FirstName LastName".
     *
     * @return the full name of the person
     */
    public String fullName() {
        return firstName + " " + lastName;
    }

    /**
     * Returns the formal name of the person in the format: "Title FullName".
     *
     * @return the formal name of the person
     */
    public String formalName() {
        return title + " " + fullName();
    }

    /**
     * Returns the age of the person assuming the current year.
     *
     * @return the age of the person
     */
    public String getAge() {
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        return String.valueOf(currentYear - yearOfBirth);
    }

    /**
     * Returns the age of the person for a specified year.
     *
     * @param year the year to calculate the age for
     * @return the age of the person in the specified year
     */
    public String getAge(int year) {
        return String.valueOf(year - yearOfBirth);
    }

    /**
     * Returns a CSV representation of the Person object.
     *
     * @return a CSV string representing the Person object
     */
    public String toCSV() {
        return String.join(",", id, firstName, lastName, title, String.valueOf(yearOfBirth));
    }

    /**
     * Returns a JSON representation of the Person object.
     *
     * @return a JSON string representing the Person object
     */
    public String toJSON() {
        return String.format(
                "{\"id\":\"%s\",\"firstName\":\"%s\",\"lastName\":\"%s\",\"title\":\"%s\",\"yearOfBirth\":%d}",
                id, firstName, lastName, title, yearOfBirth
        );
    }

    /**
     * Returns an XML representation of the Person object.
     *
     * @return an XML string representing the Person object
     */
    public String toXML() {
        return String.format(
                "<Person><Id>%s</Id><FirstName>%s</FirstName><LastName>%s</LastName><Title>%s</Title><YearOfBirth>%d</YearOfBirth></Person>",
                id, firstName, lastName, title, yearOfBirth
        );
    }
}
