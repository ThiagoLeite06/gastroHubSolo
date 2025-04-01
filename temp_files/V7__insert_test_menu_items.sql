-- src/main/resources/db/migration/V7__insert_test_menu_items.sql
-- Itens para Cantina Italiana (id 1)
INSERT INTO menu_items (name, description, price, available_only_in_restaurant, photo_path, restaurant_id, created_at, last_updated_at) VALUES
    ('Spaghetti alla Carbonara', 'Massa italiana com molho à base de ovos, queijo pecorino, pimenta preta e pancetta.', 42.90, false, '/images/pratos/carbonara.jpg', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Lasagna alla Bolognese', 'Camadas de massa intercaladas com molho bolonhesa, bechamel e queijo parmesão.', 48.90, false, '/images/pratos/lasagna.jpg', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Risotto ai Funghi', 'Arroz arbóreo cremoso preparado com mix de cogumelos frescos, manteiga e parmesão.', 54.90, true, '/images/pratos/risotto.jpg', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Itens para Sabor Mineiro (id 2)
INSERT INTO menu_items (name, description, price, available_only_in_restaurant, photo_path, restaurant_id, created_at, last_updated_at) VALUES
    ('Feijoada Completa', 'Tradicional prato com feijão preto, diversas carnes de porco, acompanha arroz, couve, farofa e laranja.', 49.90, false, '/images/pratos/feijoada.jpg', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Frango com Quiabo', 'Frango caipira cozido com quiabo e temperos mineiros, acompanha angu e arroz branco.', 38.90, false, '/images/pratos/frango_quiabo.jpg', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Tutu à Mineira', 'Feijão batido com farinha, servido com linguiça, torresmo, costelinha de porco, ovo frito e couve.', 42.90, true, '/images/pratos/tutu.jpg', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Itens para Sushi Express (id 3)
INSERT INTO menu_items (name, description, price, available_only_in_restaurant, photo_path, restaurant_id, created_at, last_updated_at) VALUES
    ('Combo Sushi Especial', '30 peças variadas incluindo sushis, sashimis, uramakis e hossomakis.', 89.90, false, '/images/pratos/combo_sushi.jpg', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Temaki Salmão', 'Cone de alga recheado com arroz, salmão fresco e cream cheese.', 28.90, false, '/images/pratos/temaki.jpg', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Hot Philadelphia', 'Rolinho empanado e frito recheado com salmão, cream cheese e cebolinha.', 32.90, true, '/images/pratos/hot_philadelphia.jpg', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Itens para Churrascaria Gaúcha (id 4)
INSERT INTO menu_items (name, description, price, available_only_in_restaurant, photo_path, restaurant_id, created_at, last_updated_at) VALUES
    ('Rodízio Completo', 'Experiência completa de churrasco gaúcho com mais de 15 cortes diferentes de carnes, buffet de saladas e acompanhamentos.', 99.90, true, '/images/pratos/rodizio.jpg', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Picanha Especial', 'Picanha premium grelhada no ponto, acompanha arroz, feijão, farofa e vinagrete.', 68.90, false, '/images/pratos/picanha.jpg', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Costela no Bafo', 'Costela bovina cozida lentamente, extremamente macia, acompanha mandioca cozida e arroz branco.', 72.90, true, '/images/pratos/costela.jpg', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Atualizando last_updated_at para simular diferentes datas de atualização
UPDATE menu_items SET last_updated_at = CURRENT_TIMESTAMP - INTERVAL '5 days' WHERE name = 'Spaghetti alla Carbonara';
UPDATE menu_items SET last_updated_at = CURRENT_TIMESTAMP - INTERVAL '4 days' WHERE name = 'Feijoada Completa';
UPDATE menu_items SET last_updated_at = CURRENT_TIMESTAMP - INTERVAL '3 days' WHERE name = 'Combo Sushi Especial';
UPDATE menu_items SET last_updated_at = CURRENT_TIMESTAMP - INTERVAL '2 days' WHERE name = 'Rodízio Completo';