USE e_shop;

-- INSERT INTO category (title, image_link) VALUES
-- ('Điện thoại', 'images/cat_phone.jpg'),
-- ('Laptop', 'images/cat_laptop.jpg'),
-- ('Phụ kiện', 'images/cat_accessories.jpg');
-- 
-- 
-- INSERT INTO user_info (name, email, phone_number, image, created_at) VALUES
-- ('Nguyen Van A', 'a@example.com', '0901000001', 'images/user1.jpg', NOW()),
-- ('Tran Thi B', 'b@example.com', '0901000002', 'images/user2.jpg', NOW()),
-- ('Le Van C', 'c@example.com', '0901000003', 'images/user3.jpg', NOW());
-- 
-- 
-- INSERT INTO customer_profile (name, email, house_no, road_area, city, district, user_id) VALUES
-- ('Nguyen Van A', 'a@example.com', '12A', 'Nguyen Trai', 'Hanoi', 'Thanh Xuan', 1),
-- ('Tran Thi B', 'b@example.com', '22B', 'Le Loi', 'Hanoi', 'Cau Giay', 2),
-- ('Le Van C', 'c@example.com', '101', 'Tran Hung Dao', 'Hanoi', 'Hoan Kiem', 3);
-- 
-- 
-- INSERT INTO product (title, category_brand_id, short_description, product_features, selling_price, image1, image2)
-- VALUES
-- ('iPhone 14 Pro', 1, 'Điện thoại cao cấp', 'Chip A16, 6GB RAM, 128GB', 26990000, 'img/iphone14_1.jpg', 'img/iphone14_2.jpg'),
-- ('MacBook Air M2', 2, 'Laptop mỏng nhẹ', 'Chip M2, 8GB RAM, 256GB SSD', 28990000, 'img/macbook_1.jpg', 'img/macbook_2.jpg'),
-- ('Tai nghe Bluetooth', 3, 'Tai nghe không dây', 'Pin 24h, Bluetooth 5.2', 590000, 'img/earphone_1.jpg', 'img/earphone_2.jpg');
-- 
-- 
-- INSERT INTO orders (order_id, order_date, expected_delivery_date, canceled_date, shipped_date, status, sort_description)
-- VALUES
-- ('ORD001', NOW(), '2024-05-20', '0000-00-00 00:00:00', NOW(), 'SHIPPED', 'Giao nhanh'),
-- ('ORD002', NOW(), '2024-05-22', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'PENDING', 'Chưa thanh toán'),
-- ('ORD003', NOW(), '2024-05-23', NOW(), '0000-00-00 00:00:00', 'CANCELED', 'Khách yêu cầu hủy');
-- 
-- 
-- INSERT INTO order_details (order_id, product_id, qty, price_each) VALUES
-- ('ORD001', 1, 1, 26990000),
-- ('ORD001', 3, 2, 590000),
-- ('ORD002', 2, 1, 28990000),
-- ('ORD003', 1, 1, 26990000);
-- 
-- 
-- INSERT INTO payments (payment_id, order_id, customer_id, amount, paymentDate, mode) VALUES
-- ('PAY001', 'ORD001', 1, 28170000, NOW(), 'CREDIT_CARD'),
-- ('PAY002', 'ORD002', 2, 28990000, NOW(), 'COD');
-- 
-- 
-- INSERT INTO refund (order_id, product_id, refund_amount, status, updated_at) VALUES
-- ('ORD003', '1', 26990000, 'COMPLETED', NOW()),
-- ('ORD002', '2', 28990000, 'PENDING', NOW());



CREATE TABLE cart (
  cart_id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  created_at TIMESTAMP DEFAULT CURTIME(),
  FOREIGN KEY (user_id) REFERENCES user_info(user_id)
);

CREATE TABLE cart_item (
	cart_item_id INT AUTO_INCREMENT PRIMARY KEY,
	cart_id INT NOT NULL,
	product_id INT NOT NULL,
	quantity INT NOT NULL,
	FOREIGN KEY (cart_id) REFERENCES cart(cart_id),
	FOREIGN KEY (product_id) REFERENCES product(product_id)
)


INSERT INTO cart (cart_id, user_id, created_at)
VALUES
(1, 1, NOW()),
(2, 2, NOW());


INSERT INTO cart_item (cart_item_id, cart_id, product_id, quantity)
VALUES
(1, 1, 1, 2),
(2, 1, 3, 1),
(3, 2, 2, 1);

