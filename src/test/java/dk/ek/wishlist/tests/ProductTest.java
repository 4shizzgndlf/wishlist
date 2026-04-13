package dk.ek.wishlist.tests;

import dk.ek.wishlist.models.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testGettersAndSetters() {
        Product p = new Product(1, "Phone", "Nice", 100, "url", "img", "now");

        assertEquals("Phone", p.getName());

        p.setName("New Phone");

        assertEquals("New Phone", p.getName());
    }
}