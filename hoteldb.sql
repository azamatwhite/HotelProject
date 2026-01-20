CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50),
                       surname VARCHAR(50),
                       phone VARCHAR(20)
);

CREATE TABLE rooms (
                       id SERIAL PRIMARY KEY,
                       room_number INT,
                       type VARCHAR(20),
                       price DECIMAL
);

INSERT INTO rooms (room_number, type, price) VALUES (101, 'Standard', 15000);
INSERT INTO rooms (room_number, type, price) VALUES (102, 'Standard', 15000);
INSERT INTO rooms (room_number, type, price) VALUES (103, 'Lux', 30000);
INSERT INTO rooms (room_number, type, price) VALUES (201, 'Standard', 15000);
INSERT INTO rooms (room_number, type, price) VALUES (202, 'Lux', 30000);
INSERT INTO rooms (room_number, type, price) VALUES (203, 'Lux', 30000);