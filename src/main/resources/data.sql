-- SQL Script for Initializing Test Data in the Database
-- This script populates the `brand` and `product` tables with initial test data.

-- Insert test brands
INSERT INTO brand (id_brand, brand_name)
VALUES (1, 'Brand A'),
       (2, 'Brand B'),
       (3, 'Brand C');

-- Insert test products
-- MEN category
INSERT INTO product (id_product, product_name, price, description, brand_id, category)
VALUES (1, 'T-shirt Homme Black', 19.99, 'A classic black long-sleeve T-shirt for men, versatile and stylish.', 1, 'MEN'),
       (2, 'T-shirt Homme Burnt Brick', 24.99, 'Long-sleeve T-shirt in a warm burnt brick color, ideal for a casual look.', 1, 'MEN'),
       (3, 'T-shirt Homme Dark Grey', 29.99, 'Stylish dark grey long-sleeve T-shirt, perfect for a modern wardrobe.', 2, 'MEN'),
       (4, 'T-shirt Homme Deep Blue', 34.99, 'A deep blue long-sleeve T-shirt, great for both work and leisure.', 3, 'MEN'),
       (5, 'T-shirt Homme Surf', 39.99, 'Surf-inspired long-sleeve T-shirt with a light, fresh color.', 2, 'MEN');

-- WOMEN category
INSERT INTO product (id_product, product_name, price, description, brand_id, category)
VALUES (6, 'T-shirt Femme Black', 21.99, 'Elegant black long-sleeve T-shirt for women, perfect for all occasions.', 1, 'WOMEN'),
       (7, 'T-shirt Femme Light Blue', 26.99, 'Light blue long-sleeve T-shirt with a soft and refreshing look.', 2, 'WOMEN'),
       (8, 'T-shirt Femme Pink', 31.99, 'Stylish pink long-sleeve T-shirt for a touch of femininity.', 3, 'WOMEN'),
       (9, 'T-shirt Femme White', 36.99, 'Classic white long-sleeve T-shirt, easy to pair with any outfit.', 1, 'WOMEN'),
       (10, 'T-shirt Femme Yellow', 41.99, 'Bright yellow long-sleeve T-shirt that adds a pop of color.', 2, 'WOMEN');

-- GIRL category
INSERT INTO product (id_product, product_name, price, description, brand_id, category)
VALUES (11, 'T-shirt Fille Black', 17.99, 'A comfy black short-sleeve T-shirt for girls, perfect for daily wear.', 3, 'GIRL'),
       (12, 'T-shirt Fille Green', 22.99, 'Short-sleeve T-shirt in green, adding a touch of nature-inspired freshness.', 1, 'GIRL'),
       (13, 'T-shirt Fille Pulpe', 27.99, 'Pulpy-colored short-sleeve T-shirt, vibrant and fun for girls.', 2, 'GIRL'),
       (14, 'T-shirt Fille Red', 32.99, 'Bright red short-sleeve T-shirt for a bold look.', 3, 'GIRL'),
       (15, 'T-shirt Fille White', 37.99, 'Classic white short-sleeve T-shirt, ideal for school and play.', 1, 'GIRL');

-- Insert images for products in the MEN category
INSERT INTO product_image (id_prod_image, image_url, color, product_id)
VALUES (1, '/images/Men/Black_O_long-sleeve.jpg', 'BLACK', 1),
       (2, '/images/Men/BurntBrick_O_long-sleeve.jpg', 'ORANGE', 2),
       (3, '/images/Men/DarkGrey_O_long-sleeve.jpg', 'GREY', 3),
       (4, '/images/Men/DeepBlue_O_long-sleeve.jpg', 'BLUE', 4),
       (5, '/images/Men/Surf_O_long-sleeve.jpg', 'MULTICOLOR', 5);

-- Insert images for products in the WOMEN category
INSERT INTO product_image (id_prod_image, image_url, color, product_id)
VALUES (6, '/images/Women/Black-long-sleeve.jpg', 'BLACK', 6),
       (7, '/images/Women/lightblue-long-sleeve.jpg', 'BLUE', 7),
       (8, '/images/Women/Pink-long-sleeve.jpg', 'PINK', 8),
       (9, '/images/Women/white-longsleeve.jpg', 'WHITE', 9),
       (10, '/images/Women/yellow-long-sleeve.jpg', 'YELLOW', 10);

-- Insert images for products in the GIRL category
INSERT INTO product_image (id_prod_image, image_url, color, product_id)
VALUES (11, '/images/Girl/black-short-sleeve.jpg', 'BLACK', 11),
       (12, '/images/Girl/green-short-sleeve.jpg', 'GREEN', 12),
       (13, '/images/Girl/pulpe-short-sleeve.jpg', 'UNDEFINED', 13),
       (14, '/images/Girl/red-short-sleeve.jpg', 'RED', 14),
       (15, '/images/Girl/white-short-sleeve.jpg', 'WHITE', 15);
