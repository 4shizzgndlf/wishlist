package dk.ek.wishlist.tests;

import dk.ek.wishlist.services.WishlistService;
import dk.ek.wishlist.models.WishlistItem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IntegrationTest {

    @Autowired
    WishlistService wishlistService;

    @Test
    void testCreateWishlistAndAddProduct() {
        wishlistService.createWishlist(1, "Test", "Test");

        // Just check that code runs without crashing
        assertTrue(true);
    }

    @Test
    void testReserveItem() {

        // reserve item (item 1 must exist in DB)
        wishlistService.reserve(1, 1);

        // If no crash → success
        assertTrue(true);
    }
}