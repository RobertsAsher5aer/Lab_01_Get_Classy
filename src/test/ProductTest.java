import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the Product class.
 */
public class ProductTest {

    private Product product;

    @Before
    public void setUp() {
        // Initialize a Product object for testing
        product = new Product("1", "Laptop", "High performance laptop", 1200.00);
    }

    @Test
    public void testToCSV() {
        assertEquals("1,Laptop,High performance laptop,1200.00", product.toCSV());
    }

    @Test
    public void testEquals() {
        Product anotherProduct = new Product("1", "Laptop", "High performance laptop", 1200.00);
        assertTrue(product.equals(anotherProduct));
    }

    @Test
    public void testNotEquals() {
        Product differentProduct = new Product("2", "Phone", "Smartphone", 800.00);
        assertFalse(product.equals(differentProduct));
    }

    @Test
    public void testHashCode() {
        Product anotherProduct = new Product("1", "Laptop", "High performance laptop", 1200.00);
        assertEquals(product.hashCode(), anotherProduct.hashCode());
    }
}
