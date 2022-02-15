CREATE TABLE IF NOT EXISTS products(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    price INT NOT NULL,
    description VARCHAR(100) NOT NULL,
    quantity INT);


INSERT INTO products (id, title,price,description,quantity)
VALUES
       (1,'Мололко', 112, 'Домик в деревне', 3),
       (2,'Кефир', 80, 'Избенка', 4),
       (3,'Творог', 56, 'Фермерский', 10),
       (4,'Сахар', 12, 'Кусковой', 3),
       (5,'Cыр', 112, 'Российский', 3),
       (6,'Картошка', 80, 'Мытая', 4),
       (7,'Курица', 159, 'Филе бедра', 10),
       (8,'Мясо', 210, 'Свинина', 3),
       (9,'Хлеб', 56, 'Черный', 3),
       (10,'Хлеб', 45, 'Белый', 3),
       (11,'Масло', 87, 'Сливочное', 3),
       (12,'Кофе', 250, 'Растворимый', 3),
       (13,'Йогурт', 97, 'Детский', 3),
       (14,'Сырок', 34, 'Твороженный', 3),
       (15,'Шоколад', 98, 'Аленка', 3),
       (16,'Перец', 18, 'Красный', 3),
       (17,'Помидор', 12, 'Турецкий', 3),
       (18,'Огурец', 10, 'Длинный', 3),
       (19,'Лук', 17, 'Репчатый', 3),
       (20,'Хрен', 8, 'Садовый', 3);


CREATE TABLE IF NOT EXISTS customers (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL);

INSERT INTO customers (id, name)
VALUES
       (1,'Bob'),
       (2,'John'),
       (3,'Jason');

CREATE TABLE IF NOT EXISTS customers_products(
    customer_id BIGINT,
    product_id BIGINT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (id),
    FOREIGN KEY (product_id) REFERENCES products(id));

INSERT INTO customers_products (customer_id, product_id)
VALUES (1,1),(1,4),(2,2),(2,3),(2,4),(3,1);


CREATE TABLE IF NOT EXISTS orders(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    paid boolean DEFAULT false NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (id));

CREATE TABLE IF NOT EXISTS order_lines(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    price INT NOT NULL,
    amount INT DEFAULT 1,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (order_id) REFERENCES orders(id));

