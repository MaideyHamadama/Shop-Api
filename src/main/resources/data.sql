-- SQL Script for Initializing Test Data in the Database
-- This script populates the `brand` and `product` tables with initial test data.

-- Insert test brands
INSERT INTO brand (id_brand, brand_name)
VALUES (1, 'Brand A'),
       (2, 'Brand B'),
       (3, 'Brand C');

-- Insert test products
INSERT INTO product (id_product, product_name, price, description, brand_id, category)
VALUES (1, 'T-shirt Homme 1', 19.99, 'Un T-shirt basique pour homme, confortable et léger.', 1, 'MEN'),
       (2, 'T-shirt Homme 2', 24.99, 'Un T-shirt pour homme avec un design moderne et élégant.', 1, 'MEN'),
       (3, 'T-shirt Homme 3', 29.99, 'T-shirt pour homme en coton de haute qualité, parfait pour un look casual.', 2, 'MEN'),
       (4, 'T-shirt Homme 4', 34.99, 'T-shirt homme coupe ajustée, idéal pour le sport.', 3, 'MEN'),
       (5, 'T-shirt Homme 5', 39.99, 'Un T-shirt homme premium avec une finition douce.', 2, 'MEN'),

       (6, 'T-shirt Femme 1', 21.99, 'T-shirt basique pour femme, confortable et élégant.', 1, 'WOMEN'),
       (7, 'T-shirt Femme 2', 26.99, 'T-shirt pour femme en tissu léger et respirant.', 2, 'WOMEN'),
       (8, 'T-shirt Femme 3', 31.99, 'T-shirt pour femme avec une coupe moderne et stylée.', 3, 'WOMEN'),
       (9, 'T-shirt Femme 4', 36.99, 'Un T-shirt pour femme parfait pour les journées ensoleillées.', 1, 'WOMEN'),
       (10, 'T-shirt Femme 5', 41.99, 'T-shirt haut de gamme pour femme avec une finition impeccable.', 2, 'WOMEN'),

       (11, 'T-shirt Fille 1', 17.99, 'T-shirt pour fille, doux et confortable, idéal pour l’école.', 3, 'GIRL'),
       (12, 'T-shirt Fille 2', 22.99, 'Un T-shirt pour fille avec un design amusant.', 1, 'GIRL'),
       (13, 'T-shirt Fille 3', 27.99, 'T-shirt pour fille en coton bio, doux pour la peau.', 2, 'GIRL'),
       (14, 'T-shirt Fille 4', 32.99, 'T-shirt pour fille avec des couleurs vives et joyeuses.', 3, 'GIRL'),
       (15, 'T-shirt Fille 5', 37.99, 'T-shirt de qualité premium pour fille, idéal pour toutes les occasions.', 1, 'GIRL');