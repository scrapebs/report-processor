CREATE TABLE IF NOT EXISTS orders
(
    id CHARACTER VARYING(100) NOT NULL,
    creation_date TIMESTAMP without time zone NOT NULL,
    customer_id CHARACTER VARYING(100),
    sum INTEGER NOT NULL,
    address CHARACTER VARYING(200),
    CONSTRAINT pk_orders_id PRIMARY KEY (id)
);