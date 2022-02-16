CREATE TABLE IF NOT EXISTS refunds
(
    id BIGINT NOT NULL,
    creation_date TIMESTAMP without time zone NOT NULL,
    order_id CHARACTER VARYING(100),
    reason CHARACTER VARYING(100),
    CONSTRAINT pk_refunds_id PRIMARY KEY (id),
    CONSTRAINT refunds_fk_orders FOREIGN KEY (order_id)
        REFERENCES orders (id)
        ON DELETE CASCADE 
);

CREATE SEQUENCE IF NOT EXISTS seq_refunds_id;