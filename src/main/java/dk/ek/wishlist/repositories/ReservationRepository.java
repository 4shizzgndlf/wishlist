package dk.ek.wishlist.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ReservationRepository {

    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public void reserve(long wishlistItemId, long userId) {
        String sql = "INSERT INTO reservations (wishlist_item_id, reserved_by_user_id) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, wishlistItemId);
            stmt.setLong(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public boolean isReserved(long wishlistItemId) {
        String sql = "SELECT COUNT(*) FROM reservations WHERE wishlist_item_id = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, wishlistItemId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}