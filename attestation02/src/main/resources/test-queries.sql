-- Выводим все товары
SELECT * FROM product;

-- Выводим заказы с именами покупателей и описанием товара
SELECT c.full_name, p.description, o.quantity, o.order_date
  FROM "order" o
  JOIN customer c ON o.customer_id = c.id
  JOIN product p  ON o.product_id  = p.id;

-- Увеличиваем цену наушников 'Sony WH-1000XM5 Wireless Headphones' на 20%
UPDATE product
  SET price = price * 1.2
  WHERE description = 'Sony WH-1000XM5 Wireless Headphones';

-- Обновляем имя покупателя
UPDATE customer
  SET full_name = 'Иван Иванов'
  WHERE id = 1;

-- Удаляем заказ на флеш-карту 'SanDisk Extreme Pro 64GB USB 3.2'
DELETE FROM "order"
  WHERE product_id = (SELECT id FROM product 
    WHERE description = 'SanDisk Extreme Pro 64GB USB 3.2');

-- Выводим остатки товаров
SELECT description, quantity FROM product WHERE quantity < 20;

-- Удаляем покупателя без заказов (если такие есть)
DELETE FROM customer
  WHERE id NOT IN (SELECT DISTINCT customer_id FROM "order");

-- Выводим общую стоимость каждого заказа
SELECT c.full_name, p.description, o.quantity, p.price, 
    (o.quantity * p.price) AS total
  FROM "order" o
  JOIN customer c ON o.customer_id = c.id
  JOIN product p  ON o.product_id  = p.id;
