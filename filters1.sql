CREATE TABLE type(
	id serial PRIMARY KEY,
	name varchar(255)
);

CREATE TABLE product (
	id serial PRIMARY KEY,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price FLOAT
);

INSERT INTO type(name) VALUES ('Сыр'),('Мясо'),('Хлеб'),('Бытовая химия');
INSERT INTO product(name,type_id,expired_date,price) VALUES ('Масдам',1,'2022-10-01',400),
('Филе',2,'2022-09-25',500),('Тосты',3,'2022-08-20',200),('Средство для мытья полов',4,'2023-01-01',100),
('Чеддер',1,'2022-05-10',600),('Мороженое мясо',2,'2022-09-06',800),('Белый хлеб',3,'2022-12-10',150),
('Средство для мытья посуды',4,'2024-10-05',300), ('Пармезан',1,'2022-11-11',650);

SELECT t.name, p.name
FROM type t
JOIN product p ON p.type_id = t.id
WHERE t.name = 'Сыр';

SELECT p.name
FROM product p
WHERE p.name ~ 'Средство';

SELECT *
FROM product p
WHERE p.expired_date < CURRENT_DATE;

SELECT *
FROM product p
WHERE p.price = (
	SELECT MAX(p.price)
	FROM product p);
	
SELECT DISTINCT t.name,COUNT(p.name)
FROM type t 
JOIN product p ON p.type_id = t.id
GROUP BY t.name;

SELECT t.name, p.name
FROM type t
JOIN product p ON p.type_id = t.id
WHERE t.name = 'Сыр' OR t.name = 'Мясо';

SELECT t.name,COUNT(p.name)
FROM type t 
JOIN product p ON p.type_id = t.id
GROUP BY t.name
HAVING COUNT(p.name) > 2;

SELECT *
FROM product p
JOIN type t ON p.type_id = t.id;


