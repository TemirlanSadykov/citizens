CREATE TABLE users (
                       id SERIAL  NOT NULL,
                       name VARCHAR(128) NOT NULL,
                       inn INTEGER(32) NOT NULL,
                       birth DATE NOT NULL,
                       place VARCHAR(128) NOT NULL,
                       login VARCHAR(64) NOT NULL,
                       password VARCHAR(64) NOT NULL,
                       enabled BOOLEAN NOT NULL DEFAULT TRUE,
                       role_id BIGINT NOT NULL,
                       PRIMARY KEY (id)
);
CREATE TABLE roles (
                       id SERIAL NOT NULL,
                       name VARCHAR(64) NOT NULL,
                       PRIMARY KEY (id)
);
CREATE TABLE documents (
                       id SERIAL NOT NULL,
                       number VARCHAR(64) NOT NULL,
                       doctype VARCHAR(32) NOT NULL,
                       giver VARCHAR(128),
                       issue DATE NOT NULL,
                       expiration DATE NOT NULL,
                       PRIMARY KEY (id)
);
INSERT INTO roles
VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles
VALUES (2, 'ROLE_USER');