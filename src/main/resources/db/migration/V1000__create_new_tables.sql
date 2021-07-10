CREATE TABLE roles (
                       id SERIAL NOT NULL,
                       name VARCHAR(64) NOT NULL,
                       PRIMARY KEY (id)
);
CREATE TABLE documents (
                       id SERIAL NOT NULL,
                       number VARCHAR(64) NOT NULL,
                       doctype VARCHAR(32) NOT NULL,
                       giver VARCHAR(128) NOT NULL,
                       issue DATE NOT NULL,
                       expiration DATE NOT NULL,
                       PRIMARY KEY (id)
);
CREATE TABLE users (
                       id SERIAL NOT NULL,
                       name VARCHAR(128) NOT NULL,
                       inn VARCHAR(14) NOT NULL,
                       birth DATE NOT NULL,
                       place VARCHAR(128) NOT NULL,
                       login VARCHAR(64) NOT NULL,
                       password VARCHAR(64) NOT NULL,
                       enabled BOOLEAN NOT NULL DEFAULT TRUE,
                       role_id BIGINT NOT NULL,
                       document_id BIGINT,
                       PRIMARY KEY (id)
);
INSERT INTO roles
VALUES (1, 'ADMIN');
INSERT INTO roles
VALUES (2, 'USER');

INSERT INTO documents
VALUES (123456789, '111111', 'ID', 'MKK111111', '2000-01-01', '2100-01-01');

INSERT INTO users
VALUES (123456789, 'qwerty', '11111111111111', '2000-01-01', 'Bishek', 'qwerty', 'qwertyqwerty', true, 1, 123456789);