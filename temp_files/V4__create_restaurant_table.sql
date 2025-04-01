
CREATE TABLE restaurants (
     id BIGSERIAL PRIMARY KEY,
     name VARCHAR(100) NOT NULL,
     address VARCHAR(200) NOT NULL,
     cuisine_type VARCHAR(50) NOT NULL,
     operating_hours VARCHAR(100) NOT NULL,
     owner_id BIGINT NOT NULL,
     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     last_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

     CONSTRAINT fk_restaurant_owner
        FOREIGN KEY (owner_id)
        REFERENCES users(id)
);


CREATE INDEX idx_restaurants_owner_id ON restaurants(owner_id);
CREATE INDEX idx_restaurants_name ON restaurants(name);