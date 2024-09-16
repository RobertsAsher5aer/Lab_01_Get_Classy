public class ObjInputTest {

    public static void main(String[] args) {
        SafeInputObj safeInput = new SafeInputObj();

        // Test prettyHeader
        safeInput.prettyHeader(" Welcome to the Safe Input Test ");

        // Test getNonZeroLenString
        String name = safeInput.getNonZeroLenString("Enter your name ");
        System.out.println("You entered: " + name);

        // Test getInt
        int age = safeInput.getInt("Enter your age ");
        System.out.println("You entered: " + age);

        // Test getDouble
        double height = safeInput.getDouble("Enter your height in inches ");
        System.out.println("You entered: " + height);

        // Test getRangedInt
        int score = safeInput.getRangedInt("Enter your score ", 0, 100);
        System.out.println("You entered: " + score);

        // Test getRangedDouble
        double weight = safeInput.getRangedDouble("Enter your weight ", 30.0, 300.0);
        System.out.println("You entered: " + weight);

        // Test getYNConfirm
        boolean confirm = safeInput.getYNConfirm("Do you want to continue? ");
        System.out.println("You chose: " + (confirm ? "Yes" : "No"));

        // Test getRegExString
        String email = safeInput.getRegExString("Enter your email ", "[^@]+@[^\\.]+\\..+");
        System.out.println("You entered: " + email);


    }
}

