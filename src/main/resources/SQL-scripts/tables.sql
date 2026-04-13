CREATE DATABASE IF NOT EXISTS wishlistdb;

USE wishlistdb;

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE users ADD COLUMN password VARCHAR(255) NOT NULL;

ALTER TABLE users DROP COLUMN password_hash;

SELECT * FROM users;
SELECT username, password FROM users;

UPDATE users
SET users.password = 1234
WHERE id = 4;

CREATE TABLE wishlists (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           user_id BIGINT NOT NULL,
                           name VARCHAR(100) NOT NULL,
                           description TEXT,
                           is_public BOOLEAN DEFAULT TRUE,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                           FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE products (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          price DECIMAL(10,2),
                          url VARCHAR(500),
                          image_url VARCHAR(500),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE wishlist_items (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                wishlist_id BIGINT NOT NULL,
                                product_id BIGINT NOT NULL,
                                quantity INT DEFAULT 1,
                                note TEXT,

                                FOREIGN KEY (wishlist_id) REFERENCES wishlists(id),
                                FOREIGN KEY (product_id) REFERENCES products(id),

                                UNIQUE (wishlist_id, product_id)
);

CREATE TABLE reservations (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              wishlist_item_id BIGINT NOT NULL,
                              reserved_by_user_id BIGINT NOT NULL,
                              quantity_reserved INT DEFAULT 1,
                              reserved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                              FOREIGN KEY (wishlist_item_id) REFERENCES wishlist_items(id),
                              FOREIGN KEY (reserved_by_user_id) REFERENCES users(id)
);