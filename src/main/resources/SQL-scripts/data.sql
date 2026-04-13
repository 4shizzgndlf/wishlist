INSERT INTO users (id, username, email, password_hash) VALUES
                                                           (1, 'alice', 'alice@example.com', 'hashed_pw_1'),
                                                           (2, 'bob', 'bob@example.com', 'hashed_pw_2'),
                                                           (3, 'charlie', 'charlie@example.com', 'hashed_pw_3'),
                                                           (4, 'diana', 'diana@example.com', 'hashed_pw_4');

INSERT INTO wishlists (id, user_id, name, description, is_public) VALUES
                                                                      (1, 1, 'Alice Birthday Wishlist', 'Things I want for my birthday 🎂', TRUE),
                                                                      (2, 1, 'Christmas Wishlist', 'Christmas gift ideas', TRUE),
                                                                      (3, 2, 'Bob Tech Wishlist', 'Cool gadgets I want', TRUE),
                                                                      (4, 3, 'Charlie Travel Wishlist', 'Stuff for my next trip ✈️', TRUE);

INSERT INTO products (id, name, description, price, url, image_url) VALUES
                                                                        (1, 'iPhone 15', 'Latest Apple smartphone', 999.99, 'https://example.com/iphone15', 'https://example.com/img/iphone15.jpg'),
                                                                        (2, 'Sony WH-1000XM5', 'Noise cancelling headphones', 349.99, 'https://example.com/sony-headphones', 'https://example.com/img/sony.jpg'),
                                                                        (3, 'LEGO Star Wars Set', 'Fun LEGO set', 129.99, 'https://example.com/lego', 'https://example.com/img/lego.jpg'),
                                                                        (4, 'Samsonite Suitcase', 'Durable travel suitcase', 199.99, 'https://example.com/suitcase', 'https://example.com/img/suitcase.jpg'),
                                                                        (5, 'Kindle Paperwhite', 'E-reader device', 149.99, 'https://example.com/kindle', 'https://example.com/img/kindle.jpg'),
                                                                        (6, 'Gaming Mouse', 'High precision gaming mouse', 79.99, 'https://example.com/mouse', 'https://example.com/img/mouse.jpg');

INSERT INTO wishlist_items (id, wishlist_id, product_id, quantity, note) VALUES
-- Alice birthday wishlist
(1, 1, 1, 1, 'Would love this in black'),
(2, 1, 2, 1, NULL),
(3, 1, 3, 2, 'For fun with friends'),

-- Alice Christmas wishlist
(4, 2, 5, 1, NULL),
(5, 2, 3, 1, NULL),

-- Bob wishlist
(6, 3, 6, 1, 'For gaming setup'),
(7, 3, 2, 1, NULL),

-- Charlie wishlist
(8, 4, 4, 1, 'Need this for travel'),
(9, 4, 5, 1, NULL);

INSERT INTO reservations (id, wishlist_item_id, reserved_by_user_id, quantity_reserved) VALUES
-- Bob reserves items from Alice
(1, 1, 2, 1), -- Bob reserves Alice's iPhone
(2, 3, 2, 1), -- Bob reserves 1 LEGO

-- Charlie reserves from Alice
(3, 3, 3, 1), -- Charlie reserves another LEGO

-- Diana reserves from Bob
(4, 6, 4, 1),

-- Alice reserves from Charlie
(5, 8, 1, 1);

SELECT * FROM products;