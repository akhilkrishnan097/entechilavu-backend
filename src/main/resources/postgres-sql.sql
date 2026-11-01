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