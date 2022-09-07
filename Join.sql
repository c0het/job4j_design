CREATE TABLE departments(
	id serial PRIMARY KEY,
	name varchar(255)
);

CREATE TABLE employees(
	id serial PRIMARY KEY,
	name varchar(255),
	id_departments int references departments(id)
);

INSERT INTO departments(name) VALUES ('Бугалтерия'),('Гараж'),('Оснащение'),('Договорной');
INSERT INTO employees(name, id_departments ) VALUES ('Сотрудник 1',1),('Сотрудник 2',2),('Сотрудник 3',3),
	('Сотрудник 4',4),('Сотрудник 5',null),('Сотрудник 6',4);
	
SELECT *
FROM departments d
LEFT JOIN employees e ON e.id_departments = d.id;

SELECT *
FROM departments d
FULL JOIN employees e ON e.id_departments = d.id;

SELECT *
FROM departments d
CROSS JOIN employees e;

SELECT *
FROM employees e
LEFT JOIN departments d ON e.id_departments = d.id
WHERE e.id_departments IS null;

SELECT d.id, d.name, e.id, e.name
FROM departments d
LEFT JOIN employees e ON e.id_departments = d.id
GROUP BY d.id, d.name, e.id , e.name;

SELECT d.id, d.name, e.id, e.name
FROM employees e
RIGHT JOIN departments d ON e.id_departments = d.id
GROUP BY d.id, d.name, e.id , e.name;

CREATE TABLE teens(
	id serial PRIMARY KEY,
	name varchar(255),
	gender varchar(255)
);

INSERT INTO teens(name, gender) VALUES ('Имя1', 'M'),('Имя2', 'W'),('Имя3', 'M'),('Имя4', 'W'),('Имя5', 'W');

SELECT t1.name,t1.gender,t2.name,t2.gender
FROM teens t1
CROSS JOIN teens t2;




	
