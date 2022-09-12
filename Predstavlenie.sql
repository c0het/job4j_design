CREATE TABLE shops(
	id serial PRIMARY KEY,
	name varchar(100)
);

CREATE TABLE products(
	id serial PRIMARY KEY,
	shop_id int REFERENCES shops(id),
	name varchar(100),
	weight float8
);  

CREATE TABLE factories(
	id serial PRIMARY KEY,
	product_id int REFERENCES products(id),
	name varchar(255)
);

CREATE TABLE countries(
	id serial PRIMARY KEY,
	name varchar(100)
);

CREATE TABLE clients(
	id serial PRIMARY KEY,
	country_id int REFERENCES countries(id),
	name varchar
);

CREATE TABLE orders(
	id serial PRIMARY KEY,
	number int,
	product_id int REFERENCES products(id),
	client_id int REFERENCES clients(id)
);

INSERT INTO shops(name) VALUES ('Shop1'),('Shop2'),('Shop3'),('Shop4'),('Shop5');
INSERT INTO countries(name) VALUES ('country1'),('country2'),('country3'),('country4');
INSERT INTO clients(name, country_id) VALUES ('John', 1), ('Nick', 2) ,('Alon', 1), ('Sara',3);
INSERT INTO products(name, weight, shop_id) VALUES ('product1', 10.0, 1),('product2', 100.0 , 2),
('product3', 200.0, 5),('product4', 50.0, 3);
INSERT INTO factories(name,product_id) VALUES ('factory1', 1),('factory2', 3),('factory3', 2),('factory4', 4);
INSERT INTO orders (number, product_id, client_id) VALUES (1,1,1),(2,2,4),(3,2,1),(4,4,3),(5,3,2);

CREATE VIEW show_weight_more_50 AS
	SELECT  o.number, p.name AS "Product name", p.weight, s.name AS "Shop name", f.name
	FROM orders o
	JOIN products p ON o.product_id = p.id
	JOIN shops s ON p.shop_id = s.id
	JOIN factories f ON f.product_id = p.id
	JOIN clients cl ON o.client_id = cl.id
	JOIN countries cntr ON cl.country_id = cntr.id
	WHERE p.weight > 50.0
	ORDER BY p.weight;
	
SELECT * FROM show_weight_more_50






