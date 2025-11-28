use eshop;

ALTER TABLE user_info
    ADD COLUMN username VARCHAR(50)  NOT NULL UNIQUE FIRST,
    ADD COLUMN password VARCHAR(255) NOT NULL AFTER username,
    ADD COLUMN isBan    TINYINT(1)   NOT NULL DEFAULT 0 AFTER password;

ALTER TABLE user_info
    MODIFY COLUMN user_id INT(11) NOT NULL AUTO_INCREMENT FIRST;

select *
from user_info;

CREATE TABLE role
(
    role_id   INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO role (role_name)
VALUES ('ADMIN'),
       ('USER');

CREATE TABLE user_role
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,

    PRIMARY KEY (user_id, role_id),

    CONSTRAINT fk_userrole_user
        FOREIGN KEY (user_id) REFERENCES user_info (user_id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,

    CONSTRAINT fk_userrole_role
        FOREIGN KEY (role_id) REFERENCES role (role_id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

INSERT INTO user_info (username, password, isBan, name, email, phone_number, image, created_at)
VALUES ('admin', '$2a$10$H4oYwNq0N6IYf4Y0e6X8peA3Gt0a1bwJzPf/x5R3M7tWRgF9QTuEu', 0, 'Administrator', 'admin@gmail.com',
        '0900000000', NULL, NOW()),
       ('john01', '$2a$10$4qUZpFgm6glve4O8hZb5G.6KmyxD4CPf/3fMqeYoqVfYyKqYb5rZm', 0, 'John Wick', 'john01@gmail.com',
        '0901110001', NULL, NOW()),
       ('anna', '$2a$10$VtgfnbJmOpOoiE5q1XFa9OsNW2M.pYhsY4y14p3EyTNNmafpXyYYG', 0, 'Anna Nguyễn', 'anna@gmail.com',
        '0902220002', NULL, NOW()),
       ('vincent', '$2a$10$KaoU9qF3cB1tFN2X0kcMXuVUpW2X0G10Mo2rkQCYwJwBrsJnsMnPG', 0, 'Vincent Long',
        'vincent@gmail.com', '0903330003', NULL, NOW()),
       ('lily', '$2a$10$f3IMTzf.0Y7VD5LWtJ2eJ.xYg./ajIESG/0X8z0xgL/hHyG7f5m5e', 0, 'Lily Trần', 'lily@gmail.com',
        '0904440004', NULL, NOW()),
       ('tiger', '$2a$10$7uHbJ7d5sSL6C6E5E6XnTuC7Q2uX8BdL3PmtCPY3QqKJ/xkGmM9dm', 0, 'Tiger Võ', 'tiger@gmail.com',
        '0905550005', NULL, NOW()),
       ('maria', '$2a$10$o2BBj4SyKDTuJoYHMLzA9u8G3M8v8GCFYN6ydz.Ppw3xLLlDRqYZK', 0, 'Maria Hồ', 'maria@gmail.com',
        '0906660006', NULL, NOW()),
       ('robin', '$2a$10$Tzj6o8uEJGutPx2XyC2DBe1T0NRe2iPEi2QRA9vNFzf0YfRfO5zD.', 0, 'Robin Hood', 'robin@gmail.com',
        '0907770007', NULL, NOW()),
       ('sean', '$2a$10$18PoxNnyJvwZ6RdWyIYIxe9gcW0IFf43K3QNWAEH/VD7Jh2tG0kOS', 0, 'Sean Lê', 'sean@gmail.com',
        '0908880008', NULL, NOW()),
       ('flora', '$2a$10$5E4W9kHDiwI9S4ecnC31/u.8rV4hVvXrO.sx5dgO/WmcgZ6Z8LRay', 0, 'Flora Đinh', 'flora@gmail.com',
        '0909990009', NULL, NOW());

select *
from user_role;

-- admin (user_id = 1) có 2 quyền
INSERT INTO user_role (user_id, role_id)
VALUES (1, 1), -- ROLE_ADMIN
       (1, 2);

-- 9 user còn lại (user_id 2 → 10) đều là ROLE_USER
INSERT INTO user_role (user_id, role_id)
VALUES (2, 2),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 2),
       (7, 2),
       (8, 2),
       (9, 2),
       (10, 2);
