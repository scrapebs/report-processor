CREATE TABLE IF NOT EXISTS products
(
    id BIGINT NOT NULL,
    product_name CHARACTER VARYING(100),
    price DOUBLE PRECISION,
    creation_date TIMESTAMP without time zone NOT NULL,
    CONSTRAINT pk_products_id PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS seq_products_id;