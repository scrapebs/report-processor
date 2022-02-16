CREATE TABLE IF NOT EXISTS order_positions
(
    id BIGINT NOT NULL,
    order_id CHARACTER VARYING(100) NOT NULL,
    product_id BIGINT NOT NULL,
    product_quantity INTEGER NOT NULL,
    CONSTRAINT pk_order_position_id PRIMARY KEY (id),
    CONSTRAINT order_positions_fk_orders FOREIGN KEY (order_id)
    REFERENCES orders (id)
    ON DELETE CASCADE,
    CONSTRAINT order_positions_fk_products FOREIGN KEY (product_id)
    REFERENCES products (id)
    );

CREATE SEQUENCE IF NOT EXISTS seq_order_positions_id;

CREATE INDEX IF NOT EXISTS order_positions_order_id_idx
    ON order_positions USING btree (order_id);