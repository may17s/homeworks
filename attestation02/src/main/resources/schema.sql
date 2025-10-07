-- Товары
CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    price NUMERIC(10, 2) NOT NULL CHECK (price >= 0),
    quantity INTEGER NOT NULL CHECK (quantity >= 0)
);
COMMENT ON TABLE product IS 'Товары';

-- Покупатели
CREATE TABLE IF NOT EXISTS customer (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL
);
COMMENT ON TABLE customer IS 'Покупатели';

-- Заказы
CREATE TABLE IF NOT EXISTS "order" (
    product_id INTEGER NOT NULL REFERENCES product(id) ON DELETE CASCADE,
    customer_id INTEGER NOT NULL REFERENCES customer(id) ON DELETE CASCADE,
    order_date DATE NOT NULL DEFAULT CURRENT_DATE,
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    PRIMARY KEY (product_id, customer_id, order_date)
);
COMMENT ON TABLE "order" IS 'Заказы';

-- Очистка при повторном запуске
TRUNCATE TABLE "order", customer, product RESTART IDENTITY CASCADE;

-- Вставка в таблицу "Товары"
INSERT INTO product (description, price, quantity) VALUES
('Apple MacBook Air M2 13" 8GB/256GB', 89990.00, 12),
('Samsung Galaxy S24 Ultra 512GB', 129990.00, 8),
('Logitech MX Master 3S Wireless Mouse', 8490.00, 25),
('Keychron K8 Pro Mechanical Keyboard', 11990.00, 18),
('Dell UltraSharp U2723QE 27" 4K Monitor', 45990.00, 6),
('Sony WH-1000XM5 Wireless Headphones', 29990.00, 15),
('SanDisk Extreme Pro 64GB USB 3.2', 1290.00, 42),
('Razer Kiyo Pro Streaming Webcam', 14990.00, 10),
('Anker PowerPort III 65W Charger', 3490.00, 30),
('Tomtoc 16" Laptop Shoulder Bag', 5990.00, 22)
ON CONFLICT DO NOTHING;

-- Вставка в таблицу "Покупатели"
INSERT INTO customer (full_name) VALUES
('Алексей Соколов'),
('Екатерина Макарова'),
('Дмитрий Федоров'),
('Анна Голубева'),
('Игорь Белов'),
('Мария Воробьёва'),
('Сергей Калинин'),
('Полина Никитина'),
('Роман Орлов'),
('Ольга Зайцева')
ON CONFLICT DO NOTHING;

-- Вставка в таблицу "Заказы"
INSERT INTO "order" (product_id, customer_id, order_date, quantity) VALUES
(1, 1, '2025-06-01', 1),
(2, 2, '2025-06-02', 2),
(3, 3, '2025-06-03', 1),
(4, 4, '2025-06-04', 1),
(5, 5, '2025-06-05', 3),
(6, 6, '2025-06-06', 5),
(7, 7, '2025-06-07', 1),
(8, 8, '2025-06-08', 2),
(9, 9, '2025-06-09', 1),
(10, 10, '2025-06-10', 1)
ON CONFLICT DO NOTHING;
