
CREATE TABLE menu_items (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    available_only_in_restaurant BOOLEAN NOT NULL DEFAULT FALSE,
    photo_path VARCHAR(255),
    restaurant_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_menu_item_restaurant
        FOREIGN KEY (restaurant_id)
        REFERENCES restaurants(id)
);


CREATE INDEX idx_menu_items_restaurant_id ON menu_items(restaurant_id);
CREATE INDEX idx_menu_items_name ON menu_items(name);