
CREATE TABLE user_types (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Inserir tipos iniciais de usuários
INSERT INTO user_types (name, created_at, last_updated_at) VALUES
('Cliente', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Dono de Restaurante', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Adicionar coluna user_type_id na tabela users
ALTER TABLE users ADD COLUMN user_type_id BIGINT;

-- Adicionar constraint de chave estrangeira
ALTER TABLE users
    ADD CONSTRAINT fk_user_type
        FOREIGN KEY (user_type_id)
            REFERENCES user_types(id);

-- Definir o tipo padrão como 'Cliente' para os usuários existentes
UPDATE users SET user_type_id = (SELECT id FROM user_types WHERE name = 'Cliente');

-- Criar índice para melhorar performance de consultas por tipo de usuário
CREATE INDEX idx_users_user_type_id ON users(user_type_id);