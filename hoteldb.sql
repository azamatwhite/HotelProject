DROP TABLE reservations;
DROP TABLE rooms;
DROP TABLE users;
DROP TABLE categories;

CREATE TABLE categories (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(50)
);

INSERT INTO categories (name) VALUES ('Standard'), ('Lux'), ('Presidential');


CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50),
                       surname VARCHAR(50),
                       phone VARCHAR(5) NOT NULL UNIQUE,
                       role VARCHAR(20) DEFAULT 'CLIENT'
);

CREATE TABLE rooms (
                       id SERIAL PRIMARY KEY,
                       room_number INT UNIQUE,
                       category_id INT REFERENCES categories(id),
                       price DECIMAL
);


INSERT INTO rooms (room_number, price, category_id) VALUES (101, 15000, 1);
INSERT INTO rooms (room_number, price, category_id) VALUES (102, 15000, 1);
INSERT INTO rooms (room_number, price, category_id) VALUES (201, 30000, 2);
INSERT INTO rooms (room_number, price, category_id) VALUES (202, 30000, 2);
INSERT INTO rooms (room_number, price, category_id) VALUES (301, 50000, 3);
INSERT INTO rooms (room_number, price, category_id) VALUES (302, 50000, 3);


CREATE TABLE reservations (
                              id SERIAL PRIMARY KEY,
                              user_id INT REFERENCES users(id),
                              room_id INT REFERENCES rooms(id),
                              check_in DATE,
                              check_out DATE
);


INSERT INTO users (name, surname, phone, role)
VALUES ('Azamat', 'Zhiyembetov', '11111', 'ADMIN'),
       ('Ruslan', 'Absamat', '77777', 'ADMIN'),
       ('Meiirzhan', 'Yernazar', '44444', 'ADMIN');