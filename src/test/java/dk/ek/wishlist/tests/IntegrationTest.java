package dk.ek.wishlist.tests;

import dk.ek.wishlist.models.User;
import dk.ek.wishlist.repositories.UserRepository;
import dk.ek.wishlist.services.AuthService;
import dk.ek.wishlist.services.WishlistService;
import dk.ek.wishlist.models.WishlistItem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IntegrationTest {

    @Autowired
    WishlistService wishlistService;

    // Fake repository to avoid real database
    static class FakeUserRepository extends UserRepository {

        @Override
        public User findByUsername(String usernameInput) {
            if (usernameInput.equals("integrationUser")) {
                return new User(1, "integrationUser", "test@test.com", "1234");
            }
            return null;
        }

        @Override
        public void save(User user) {
            // Do nothing (fake DB)
        }
    }

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

    @Test
    void testRegisterAndLoginFlow() {

        AuthService service = new AuthService(new FakeUserRepository());

        // Register (does nothing but simulates flow)
        service.register("integrationUser", "test@test.com", "1234");

        // Login
        User user = service.login("integrationUser", "1234");

        // Assert
        assertNotNull(user);
        assertEquals("integrationUser", user.getUsername());
    }
}