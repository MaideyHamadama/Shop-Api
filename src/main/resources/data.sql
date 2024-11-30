-- SQL Script for Initializing Test Data in the Database
-- This script populates the `brand` and `product` tables with initial test data.
-- Supprimer les relations dans les tables de liaison
DELETE FROM product_sizes;

-- Supprimer les données des tables dépendantes
DELETE FROM product_image;

-- Supprimer les tailles, produits et marques
DELETE FROM sizes;
DELETE FROM product;
DELETE FROM brand;

-- Insert test brands
INSERT INTO brand (id_brand, brand_name)
VALUES (1, 'Brand A'),
       (2, 'Brand B'),
       (3, 'Brand C');

-- Insert test products
-- MEN category
INSERT INTO product (id_product, product_name, price, description, brand_id, category)
VALUES
    (1, 'T-shirt Homme Black', 19.99, 'Classic black T-shirt for men.', 1, 'MEN'),
    (2, 'T-shirt Homme Burnt Brick', 24.99, 'Stylish burnt brick T-shirt for men.', 1, 'MEN'),
    (3, 'T-shirt Homme Dark Grey', 29.99, 'Comfortable dark grey T-shirt for men.', 2, 'MEN'),
    (4, 'T-shirt Homme Deep Blue', 34.99, 'Elegant deep blue T-shirt for men.', 3, 'MEN'),
    (5, 'T-shirt Homme Surf', 39.99, 'Fresh surf-inspired T-shirt for men.', 2, 'MEN');

-- WOMEN category
INSERT INTO product (id_product, product_name, price, description, brand_id, category)
VALUES
    (6, 'T-shirt Femme Black', 21.99, 'Elegant black T-shirt for women.', 1, 'WOMEN'),
    (7, 'T-shirt Femme Light Blue', 26.99, 'Soft light blue T-shirt for women.', 2, 'WOMEN'),
    (8, 'T-shirt Femme Pink', 31.99, 'Feminine pink T-shirt for women.', 3, 'WOMEN'),
    (9, 'T-shirt Femme White', 36.99, 'Classic white T-shirt for women.', 1, 'WOMEN'),
    (10, 'T-shirt Femme Yellow', 41.99, 'Bright yellow T-shirt for women.', 2, 'WOMEN');

-- GIRL category
INSERT INTO product (id_product, product_name, price, description, brand_id, category)
VALUES
    (11, 'T-shirt Fille Black', 17.99, 'Comfy black T-shirt for girls.', 3, 'GIRL'),
    (12, 'T-shirt Fille Green', 22.99, 'Fresh green T-shirt for girls.', 1, 'GIRL'),
    (13, 'T-shirt Fille Pulpe', 27.99, 'Vibrant pulpy-colored T-shirt for girls.', 2, 'GIRL'),
    (14, 'T-shirt Fille Red', 32.99, 'Bright red T-shirt for girls.', 3, 'GIRL'),
    (15, 'T-shirt Fille White', 37.99, 'Classic white T-shirt for girls.', 1, 'GIRL');

INSERT INTO product_image (id_prod_image, image_url, color, product_id)
VALUES (1, '/images/Men/Black_O_long-sleeve.jpg', 'BLACK', 1),
       (2, '/images/Men/BurntBrick_O_long-sleeve.jpg', 'ORANGE', 2),
       (3, '', 'GREY', 3),
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

-- Tailles adultes
INSERT INTO sizes (type, adult_size, child_size)
VALUES
    ('ADULT', 'XS', NULL),
    ('ADULT', 'S', NULL),
    ('ADULT', 'M', NULL),
    ('ADULT', 'L', NULL),
    ('ADULT', 'XL', NULL);


-- Tailles enfants
INSERT INTO sizes (type, adult_size, child_size)
VALUES
    ('CHILD', NULL, 'SIZE_2'),
    ('CHILD', NULL, 'SIZE_4'),
    ('CHILD', NULL, 'SIZE_6'),
    ('CHILD', NULL, 'SIZE_8'),
    ('CHILD', NULL, 'SIZE_10');

-- Associer tailles adultes aux produits MEN
INSERT INTO product_sizes (product_id, size_id)
VALUES
    (1, 1), -- XS
    (1, 2), -- S
    (2, 3), -- M
    (3, 4), -- L
    (4, 5); -- XL

-- Associer tailles adultes aux produits WOMEN
INSERT INTO product_sizes (product_id, size_id)
VALUES
    (6, 1), -- XS
    (7, 2), -- S
    (8, 3), -- M
    (9, 4), -- L
    (10, 5); -- XL

-- Associer tailles enfants aux produits GIRL
INSERT INTO product_sizes (product_id, size_id)
VALUES
    (11, 6), -- AGE_2
    (12, 7), -- AGE_4
    (13, 8), -- AGE_6
    (14, 9), -- AGE_8
    (15, 10); -- AGE_10
