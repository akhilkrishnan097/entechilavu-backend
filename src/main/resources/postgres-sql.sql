CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(100),
                       email VARCHAR(150) UNIQUE,
                       password VARCHAR(255),
                       role VARCHAR(10),
                       created DATE
);

INSERT INTO users (name, email, password, role)
VALUES (
           'Akhil',
           'akhil@example.com',
           '$2a$10$exampleBCryptHash',
           'user'
       );

CREATE TABLE categories (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            icon VARCHAR(50),
                            color VARCHAR(20),
                            created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE payment_modes (
                               id BIGSERIAL PRIMARY KEY,
                               name VARCHAR(100) NOT NULL,
                               created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE transactions (
                              id BIGSERIAL PRIMARY KEY,
                              user_id BIGINT NOT NULL REFERENCES users(id),
                              category_id BIGINT NOT NULL,
                              payment_mode_id BIGINT NOT NULL,
                              amount NUMERIC(12,2) NOT NULL,
                              description VARCHAR(255),
                              transaction_date DATE NOT NULL,
                              notes TEXT,
                              created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              created_by VARCHAR(255),
                              updated_by VARCHAR(255)
);
