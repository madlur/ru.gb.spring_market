
CREATE TABLE IF NOT EXISTS  category(
                                        id bigserial primary key,
                                        name VARCHAR(100) NOT NULL
);

create table products
(
    id         bigserial primary key,
    title      varchar(255),
    price      int,
    category_id int references category (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table orders
(
    id          bigserial primary key,
    username    varchar(255) not null,
    total_price int    not null,
    address     varchar(255),
    phone       varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null references products (id),
    order_id          bigint not null references orders (id),
    quantity          int    not null,
    price_per_product int    not null,
    price             int    not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);


INSERT INTO category (name) VALUES
                                             ('Продукты'),
                                             ('Бакалея'),
                                             ('Молочные продукты');


insert into products (title, price, category_id)
values ('Молоко', 78, 3),
       ('Хлеб', 40, 1),
       ('Сыр Российский', 90, 3),
       ('Шпроты', 300, 2),
       ('Картошка', 90, 1),
       ('Чипсы', 112, 2),
       ('Макароны', 90, 1);


insert into orders (username, total_price, address, phone)
values ('bob', 200, 'address', '12345');

insert into order_items (product_id, order_id, quantity, price_per_product, price)
values (1, 1, 2, 100, 200);









