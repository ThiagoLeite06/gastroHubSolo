
INSERT INTO restaurants (name, address, cuisine_type, operating_hours, owner_id, created_at, last_updated_at) VALUES
      ('Cantina Italiana', 'Rua das Oliveiras, 123, São Paulo - SP', 'Italiana', 'Seg-Sex: 11h-23h, Sáb-Dom: 11h-00h', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      ('Sabor Mineiro', 'Avenida Brasil, 456, Belo Horizonte - MG', 'Mineira', 'Todos os dias: 10h-22h', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      ('Sushi Express', 'Rua Liberdade, 789, São Paulo - SP', 'Japonesa', 'Seg-Dom: 18h-23h', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      ('Churrascaria Gaúcha', 'Avenida Farrapos, 1020, Porto Alegre - RS', 'Churrasco', 'Ter-Dom: 11h30-15h e 18h30-23h', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


UPDATE restaurants SET last_updated_at = CURRENT_TIMESTAMP - INTERVAL '3 days' WHERE name = 'Cantina Italiana';
UPDATE restaurants SET last_updated_at = CURRENT_TIMESTAMP - INTERVAL '2 days' WHERE name = 'Sabor Mineiro';
UPDATE restaurants SET last_updated_at = CURRENT_TIMESTAMP - INTERVAL '1 day' WHERE name = 'Sushi Express';