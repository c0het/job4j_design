CREATE TABLE car_bodies(
	id serial PRIMARY KEY,
	name varchar(255)
);

CREATE TABLE car_engines(
	id serial PRIMARY KEY,
	name varchar(255)
);

CREATE TABLE car_transmissions(
	id serial PRIMARY KEY,
	name varchar(255)
);

CREATE TABLE cars(
	id serial PRIMARY KEY,
	name varchar(255),
	body_id int REFERENCES car_bodies(id),
	engine_id int REFERENCES car_engines(id),
	transmission_id int REFERENCES car_transmissions(id)
);

INSERT INTO car_bodies(name) VALUES ('Седан'),('Кросовер'),('Хэтчбек'),('Пикап'),('Минивен'),('Универсал');
INSERT INTO car_engines(name) VALUES ('Бензиновый'),('Дизельный'),('Хибрид'),('Электричество'),('Водородный');
INSERT INTO car_transmissions(name) VALUES ('Механическая'),('Роботизированная'),('Автомат'),('Вариатор'),
('Вариатор + автомат');
INSERT INTO cars(name,body_id,engine_id,transmission_id) VALUES ('Solaris',1,1,3),('Golf',3,1,2),('Tiguan',2,2,2),
('Prius',3,3,3),('Cybertruck', 4, 4, null);

SELECT c.name, cd.name, ce.name, ct.name
FROM cars c
LEFT JOIN car_bodies cd ON cd.id = c.body_id
LEFT JOIN car_engines ce ON ce.id = c.engine_id
LEFT JOIN car_transmissions ct ON ct.id = c.transmission_id;

SELECT cb.name
FROM car_bodies cb
LEFT JOIN cars c ON c.body_id = cb.id
WHERE c.body_id IS null;

SELECT ce.name
FROM car_engines ce
LEFT JOIN cars c ON c.engine_id = ce.id
WHERE c.body_id IS null;

SELECT ct.name
FROM car_transmissions ct
LEFT JOIN cars c ON c.transmission_id = ct.id
WHERE c.body_id IS null;
