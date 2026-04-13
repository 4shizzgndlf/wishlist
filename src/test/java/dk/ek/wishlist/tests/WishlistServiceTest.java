package dk.ek.wishlist.tests;

import dk.ek.wishlist.repositories.*;
import dk.ek.wishlist.services.WishlistService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WishlistServiceTest {

    static class FakeWishlistRepo extends WishlistRepository {}
    static class FakeItemRepo extends WishlistItemRepository {}
    static class FakeReservationRepo extends ReservationRepository {}

    @Test
    void testServiceCreatesObject() {
        WishlistService service = new WishlistService(
                new FakeWishlistRepo(),
                new FakeItemRepo(),
                new FakeReservationRepo()
        );

        assertNotNull(service);
    }

    @Test
    void testAddProductDoesNotCrash() {
        WishlistService service = new WishlistService(
                new FakeWishlistRepo(),
                new FakeItemRepo(),
                new FakeReservationRepo()
        );

        // Just test that method runs without error
        service.addProduct(1, 1);

        assertTrue(true); // if no crash = success
    }

    @Test
    void testReserveDoesNotCrash() {
        WishlistService service = new WishlistService(
                new FakeWishlistRepo(),
                new FakeItemRepo(),
                new FakeReservationRepo()
        );

        service.reserve(1, 1);

        assertTrue(true);
    }
}