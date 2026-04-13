package dk.ek.wishlist.tests;

import dk.ek.wishlist.models.User;
import dk.ek.wishlist.repositories.UserRepository;
import dk.ek.wishlist.services.AuthService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    // Simple fake repository (no Mockito)
    static class FakeUserRepository extends UserRepository {
        @Override
        public User findByUsername(String usernameInput) {
            if (usernameInput.equals("testuser")) {
                return new User(1, "testuser", "test@test.com", "1234");
            }
            return null;
        }
    }

    @Test
    void testLoginSuccess() {
        AuthService service = new AuthService(new FakeUserRepository());

        User user = service.login("testuser", "1234");

        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
    }

    @Test
    void testLoginFailsWrongPassword() {
        AuthService service = new AuthService(new FakeUserRepository());

        User user = service.login("testuser", "wrong");

        assertNull(user);
    }

    @Test
    void testLoginFailsUserNotFound() {
        AuthService service = new AuthService(new FakeUserRepository());

        User user = service.login("unknown", "1234");

        assertNull(user);
    }
}