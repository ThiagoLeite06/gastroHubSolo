INSERT INTO users (name, email, login, password, address) VALUES
    ('João Silva', 'joao@email.com', 'joaosilva', '$2a$10$YourHashedPasswordHere', 'Rua das Flores, 123, São Paulo - SP'),
    ('Maria Santos', 'maria@email.com', 'mariasantos', '$2a$10$YourHashedPasswordHere', 'Av. Principal, 456, Rio de Janeiro - RJ'),
    ('Pedro Oliveira', 'pedro@email.com', 'pedrooliveira', '$2a$10$YourHashedPasswordHere', 'Rua do Comércio, 789, Belo Horizonte - MG');

-- Atualizando last_updated_at para ter valores diferentes
UPDATE users SET last_updated_at = CURRENT_TIMESTAMP - INTERVAL '2 days' WHERE email = 'joao@email.com';
UPDATE users SET last_updated_at = CURRENT_TIMESTAMP - INTERVAL '1 day' WHERE email = 'maria@email.com';
UPDATE users SET last_updated_at = CURRENT_TIMESTAMP WHERE email = 'pedro@email.com';