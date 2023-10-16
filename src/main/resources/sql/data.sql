insert into role (role_id, role_name) VALUES(1, 'ADMIN');
insert into role (role_id, role_name) VALUES(2, 'USER');

insert into users (username, password, enabled, last_logged_in) VALUES('admin', 'password', true, CURRENT_TIMESTAMP);

insert into user_role (user_id, role_id) VALUES(1, 1);

select * from users ;


-- Insert producers
INSERT INTO producer (producer_id, name) VALUES (1, 'Le Grappin');
INSERT INTO producer (producer_id, name) VALUES (2, 'Domaine Pattes Loup');
INSERT INTO producer (producer_id, name) VALUES (3, 'Domaine du Pélican');
INSERT INTO producer (producer_id, name) VALUES (4, 'Claude Buchot');
INSERT INTO producer (producer_id, name) VALUES (5, 'Timo Mayer');
INSERT INTO producer (producer_id, name) VALUES (6, 'Château Le Puy');
INSERT INTO producer (producer_id, name) VALUES (7, 'George Descombes');


-- Add more producer inserts as needed

-- Insert regions
INSERT INTO region (region_id, name, country) VALUES (1, 'Bourgogne', 'France');
INSERT INTO region (region_id, name, country) VALUES (2, 'Chablis', 'France');
INSERT INTO region (region_id, name, country) VALUES (3, 'Jura', 'France');
INSERT INTO region (region_id, name, country) VALUES (4, 'Loire', 'France');
INSERT INTO region (region_id, name, country) VALUES (5, 'Yarra Valley', 'Australia');
INSERT INTO region (region_id, name, country) VALUES (6, 'Bordeaux', 'France');
INSERT INTO region (region_id, name, country) VALUES (7, 'Morgon', 'France');
INSERT INTO region (region_id, name, country) VALUES (8, 'Santorini', 'Greece');
-- Add more region inserts as needed

-- Insert grapes
-- Grape Inserts
INSERT INTO grape (grape_id, name) VALUES (1, 'Albariño');
INSERT INTO grape (grape_id, name) VALUES (2, 'Arneis');
INSERT INTO grape (grape_id, name) VALUES (3, 'Assyrtiko');
INSERT INTO grape (grape_id, name) VALUES (4, 'Barbera');
INSERT INTO grape (grape_id, name) VALUES (5, 'Blaufränkisch');
INSERT INTO grape (grape_id, name) VALUES (6, 'Bobal');
INSERT INTO grape (grape_id, name) VALUES (7, 'Brunello');
INSERT INTO grape (grape_id, name) VALUES (8, 'Cabernet Franc');
INSERT INTO grape (grape_id, name) VALUES (9, 'Cabernet Sauvignon');
INSERT INTO grape (grape_id, name) VALUES (10, 'Carignan');
INSERT INTO grape (grape_id, name) VALUES (11, 'Chardonnay');
INSERT INTO grape (grape_id, name) VALUES (12, 'Chenin Blanc');
INSERT INTO grape (grape_id, name) VALUES (13, 'Cinsault');
INSERT INTO grape (grape_id, name) VALUES (14, 'Dolcetto');
INSERT INTO grape (grape_id, name) VALUES (15, 'Frappato');
INSERT INTO grape (grape_id, name) VALUES (16, 'Furmint');
INSERT INTO grape (grape_id, name) VALUES (17, 'Gamay');
INSERT INTO grape (grape_id, name) VALUES (18, 'Garganega');
INSERT INTO grape (grape_id, name) VALUES (19, 'Gewurztraminer');
INSERT INTO grape (grape_id, name) VALUES (20, 'Glera');
INSERT INTO grape (grape_id, name) VALUES (21, 'Godello');
INSERT INTO grape (grape_id, name) VALUES (22, 'Grenache');
INSERT INTO grape (grape_id, name) VALUES (23, 'Grüner Silvaner');
INSERT INTO grape (grape_id, name) VALUES (24, 'Grüner Veltliner');
INSERT INTO grape (grape_id, name) VALUES (25, 'Kékfrankos');
INSERT INTO grape (grape_id, name) VALUES (26, 'Macabeo');
INSERT INTO grape (grape_id, name) VALUES (27, 'Malbec');
INSERT INTO grape (grape_id, name) VALUES (28, 'Malvasia');
INSERT INTO grape (grape_id, name) VALUES (29, 'Marsanne');
INSERT INTO grape (grape_id, name) VALUES (30, 'Melon de Bourgogne');
INSERT INTO grape (grape_id, name) VALUES (31, 'Merlot');
INSERT INTO grape (grape_id, name) VALUES (32, 'Mencía');
INSERT INTO grape (grape_id, name) VALUES (33, 'Molinara');
INSERT INTO grape (grape_id, name) VALUES (34, 'Montepulciano');
INSERT INTO grape (grape_id, name) VALUES (35, 'Muscadelle');
INSERT INTO grape (grape_id, name) VALUES (36, 'Muscat');
INSERT INTO grape (grape_id, name) VALUES (37, 'Nerello Cappuccio');
INSERT INTO grape (grape_id, name) VALUES (38, 'Nerello Mascalese');
INSERT INTO grape (grape_id, name) VALUES (39, 'Nero d’Avola');
INSERT INTO grape (grape_id, name) VALUES (40, 'Pinot Blanc');
INSERT INTO grape (grape_id, name) VALUES (41, 'Petit Verdot');
INSERT INTO grape (grape_id, name) VALUES (42, 'Pinot Grigio');
INSERT INTO grape (grape_id, name) VALUES (43, 'Pinot Meunier');
INSERT INTO grape (grape_id, name) VALUES (44, 'Pinot Noir');
INSERT INTO grape (grape_id, name) VALUES (45, 'Poulsard');
INSERT INTO grape (grape_id, name) VALUES (46, 'Riesling');
INSERT INTO grape (grape_id, name) VALUES (47, 'Rondinella');
INSERT INTO grape (grape_id, name) VALUES (48, 'Rousanne');
INSERT INTO grape (grape_id, name) VALUES (49, 'Sangiovese');
INSERT INTO grape (grape_id, name) VALUES (50, 'Saperavi');
INSERT INTO grape (grape_id, name) VALUES (51, 'Sauvignon Blanc');
INSERT INTO grape (grape_id, name) VALUES (52, 'Savagnin');
INSERT INTO grape (grape_id, name) VALUES (53, 'Semillon');
INSERT INTO grape (grape_id, name) VALUES (54, 'Sylvaner');
INSERT INTO grape (grape_id, name) VALUES (55, 'Syrah');
INSERT INTO grape (grape_id, name) VALUES (56, 'Tempranillo');
INSERT INTO grape (grape_id, name) VALUES (57, 'Trousseau');
INSERT INTO grape (grape_id, name) VALUES (58, 'Vermentino');
INSERT INTO grape (grape_id, name) VALUES (59, 'Viognier');
INSERT INTO grape (grape_id, name) VALUES (60, 'Zinfandel');
INSERT INTO grape (grape_id, name) VALUES (61, 'Zweigelt');

-- Add more grape inserts as needed


-- Insert wines

-- White wines
INSERT INTO wine (wine_id, name, producer_id, vintage, region_id, category)
VALUES (1, 'Savigny-les-Beaune', 1, 2018, 1, 1);


INSERT INTO wine (wine_id, name, producer_id, vintage, region_id, category)
VALUES (2, 'Saint Aubin en l’Ebaupin', 1, 2018, 1, 1);


INSERT INTO wine (wine_id, name, producer_id, vintage, region_id, category)
VALUES (3, 'Beaune Premier Cru Les Grèves', 1, 2018, 1, 1);

INSERT INTO wine (wine_id, name, producer_id, vintage, region_id, category)
VALUES (4, 'Mis Tardive', 2, 2017, 2, 1);

INSERT INTO wine (wine_id, name, producer_id, vintage, region_id, category)
VALUES (5, 'Pélican', 3, 2018, 3, 1);

INSERT INTO wine (wine_id, name, producer_id, vintage, region_id, category)
VALUES (6, 'Terroir du Bry Tradition', 4, 2015, 4, 1);

-- Add more white wine inserts as needed

-- Red wines
INSERT INTO wine (wine_id, name, producer_id, vintage, region_id, category)
VALUES (7, 'Dr.Mayer', 5, 2019, 5, 0);

INSERT INTO wine (wine_id, name, producer_id, vintage, region_id, category)
VALUES (8, 'Le Puy Emilien', 6, 2017, 6, 0);

INSERT INTO wine (wine_id, name, producer_id, vintage, region_id, category)
VALUES (9, 'Morgon', 7, 2017, 7, 0);

INSERT INTO wine (wine_id, name, producer_id, vintage, region_id, category)
VALUES (10, 'Savigny-les-Beaune', 1, 2018, 1, 0);

-- Add more red wine inserts as needed


-- Insert wine_grape associations

-- For the wine 'Savigny-les-Beaune' (wine_id 1) which contains 'Chardonnay' (grape_id 1)
INSERT INTO wine_grape (wine_id, grape_id) VALUES (1, (SELECT grape_id FROM grape WHERE name = 'Chardonnay'));


INSERT INTO wine_grape (wine_id, grape_id) VALUES (2, 11);
INSERT INTO wine_grape (wine_id, grape_id) VALUES (3, 11);

-- For the wine 'Mis Tardive' (wine_id 2) which contains 'Chardonnay' (grape_id 1)
INSERT INTO wine_grape (wine_id, grape_id) VALUES (4, 11);

INSERT INTO wine_grape (wine_id, grape_id) VALUES (5, 52);
INSERT INTO wine_grape (wine_id, grape_id) VALUES (6, 11);
INSERT INTO wine_grape (wine_id, grape_id) VALUES (7, 44);
INSERT INTO wine_grape (wine_id, grape_id) VALUES (8, 31);
INSERT INTO wine_grape (wine_id, grape_id) VALUES (9, 17);
INSERT INTO wine_grape (wine_id, grape_id) VALUES (10, 11);

INSERT INTO user_wine (user_id, wine_id, storing_location, information, quantity, created_at, is_finished) VALUES (1, 1, 'base', '', 1, CURRENT_TIMESTAMP, false);
INSERT INTO user_wine (user_id, wine_id, storing_location, information, quantity, created_at, is_finished) VALUES (1, 2, 'base', '', 1, CURRENT_TIMESTAMP, false);
INSERT INTO user_wine (user_id, wine_id, storing_location, information, quantity, created_at, is_finished) VALUES (1, 3, 'base', '', 1, CURRENT_TIMESTAMP, false);
INSERT INTO user_wine (user_id, wine_id, storing_location, information, quantity, created_at, is_finished) VALUES (1, 4, 'base', '', 1, CURRENT_TIMESTAMP, false);
INSERT INTO user_wine (user_id, wine_id, storing_location, information, quantity, created_at, is_finished) VALUES (1, 5, 'base', '', 1, CURRENT_TIMESTAMP, false);
INSERT INTO user_wine (user_id, wine_id, storing_location, information, quantity, created_at, is_finished) VALUES (1, 6, 'base', '', 1, CURRENT_TIMESTAMP, false);
INSERT INTO user_wine (user_id, wine_id, storing_location, information, quantity, created_at, is_finished) VALUES (1, 7, 'base', '', 1, CURRENT_TIMESTAMP, false);
INSERT INTO user_wine (user_id, wine_id, storing_location, information, quantity, created_at, is_finished) VALUES (1, 8, 'base', '', 1, CURRENT_TIMESTAMP, false);
INSERT INTO user_wine (user_id, wine_id, storing_location, information, quantity, created_at, is_finished) VALUES (1, 9, 'base', '', 1, CURRENT_TIMESTAMP, false);
INSERT INTO user_wine (user_id, wine_id, storing_location, information, quantity, created_at, is_finished) VALUES (1, 10, 'base', '', 1, CURRENT_TIMESTAMP, false);


-- category
-- , '', 'base', 1, CURRENT_TIMESTAMP, false