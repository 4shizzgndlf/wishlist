package dk.ek.wishlist.services;

import dk.ek.wishlist.models.Wishlist;
import dk.ek.wishlist.models.WishlistItem;
import dk.ek.wishlist.repositories.ReservationRepository;
import dk.ek.wishlist.repositories.WishlistItemRepository;
import dk.ek.wishlist.repositories.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepo;
    private final WishlistItemRepository itemRepo;
    private final ReservationRepository reservationRepo;

    public WishlistService(WishlistRepository wishlistRepo,
                           WishlistItemRepository itemRepo,
                           ReservationRepository reservationRepo) {
        this.wishlistRepo = wishlistRepo;
        this.itemRepo = itemRepo;
        this.reservationRepo = reservationRepo;
    }

    public void createWishlist(long userId, String name, String description) {
        wishlistRepo.create(userId, name, description);
    }

    public List<Wishlist> getAllWishlists() {
        return wishlistRepo.findAllPublic();
    }

    public void addProduct(long wishlistId, long productId) {
        itemRepo.addProduct(wishlistId, productId);
    }

    public List<WishlistItem> getItems(long wishlistId) {
        List<WishlistItem> items = itemRepo.findByWishlist(wishlistId);

        for (WishlistItem item : items) {
            boolean reserved = reservationRepo.isReserved(item.getId());
            item.setReserved(reserved);
        }

        return items;
    }

    public void reserve(long itemId, long userId) {
        reservationRepo.reserve(itemId, userId);
    }

    // --- New Helper Class to combine wishlist + items ---
    public List<WishlistWithItems> getAllWishlistsWithItems() {
        List<Wishlist> wishlists = wishlistRepo.findAllPublic();
        return wishlists.stream().map(w -> new WishlistWithItems(
                w.getId(),
                w.getUserId(),
                w.getName(),
                w.getDescription(),
                getItems(w.getId())
        )).toList();
    }

    public static class WishlistWithItems extends Wishlist {
        private final List<WishlistItem> items;

        public WishlistWithItems(long id, long userId, String name, String description, List<WishlistItem> items) {
            super(id, userId, name, description);
            this.items = items;
        }

        public List<WishlistItem> getItems() {
            return items;
        }
    }
}