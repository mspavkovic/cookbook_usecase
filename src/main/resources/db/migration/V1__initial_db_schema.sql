CREATE TABLE recipes
(
    id                  BIGINT NOT NULL CONSTRAINT recipe_pkey PRIMARY KEY,
    name                VARCHAR(50) NOT NULL,
    number_of_servings  SMALLINT NOT NULL,
    is_vegetarian       BOOLEAN NOT NULL DEFAULT FALSE,
    instructions        TEXT NOT NULL,
    creation_time       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ingredients
(
    id        BIGINT       NOT NULL CONSTRAINT ingredient_pkey PRIMARY KEY,
    name      VARCHAR(50)  NOT NULL,
    quantity  VARCHAR(255) NOT NULL,
    recipe_id BIGINT       CONSTRAINT ingredient_recipe_id_fkey REFERENCES recipes (id)
);

CREATE TABLE users
(
    id          BIGINT NOT NULL CONSTRAINT user_pkey PRIMARY KEY,
    username    VARCHAR(50) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    creation_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE SEQUENCE hibernate_sequence
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE INDEX IF NOT EXISTS ingredients_recipe_id_idx ON ingredients(recipe_id);

-- admin/admin
INSERT INTO users VALUES (1, 'admin', '$2a$10$QjUNfmKJxO2pBhwoB8r5aOqV5OScs3g2UWV4VcuOLvMgt8N/JRyH2', now());
