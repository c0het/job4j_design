create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

INSERT INTO devices(name, price) VALUES ('Poco', 4999), ('Apple', 80000), ('Samsung', 30000);
INSERT INTO people(name) VALUES('Nikolai'),('Dmitrii'),('Sergey');
INSERT INTO devices_people(device_id,people_id) VALUES (1,2),(1,3),(2,1),(3,2);

SELECT AVG(d.price) FROM devices AS d;

SELECT dp.people_id, AVG(d.price) AS "Средняя цена", p.name
FROM devices_people AS dp
JOIN devices AS d ON dp.device_id = d.id
JOIN people AS p ON p.id = dp.people_id
GROUP BY dp.people_id, p.name;

SELECT dp.people_id, AVG(d.price) AS "Средняя цена", p.name
FROM devices_people AS dp
JOIN devices AS d ON dp.device_id = d.id
JOIN people AS p ON p.id = dp.people_id
GROUP BY dp.people_id, p.name
HAVING  AVG(d.price) > 5000;


