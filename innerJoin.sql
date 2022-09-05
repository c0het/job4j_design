CREATE TABLE book(
	id serial PRIMARY KEY,
	name varchar(255),
	year int
);


CREATE TABLE author (
 	id serial PRIMARY KEY,
	name VARCHAR(255),
	book_id int references book(id)
);

INSERT into book(name, year) VALUES ('War and Peace', 1867);
INSERT into book(name, year) VALUES ('Harry Potter', 1997);
INSERT into book(name, year) VALUES ('To Kill a Mockingbird', 1960);

INSERT INTO author(name, book_id) VALUES ('Lev Tolstoi', 1);
INSERT INTO author(name, book_id) VALUES ('Harper Lee', 3);
INSERT INTO author(name, book_id) VALUES ('Joanne Rowling', 2);

INSERT INTO author(name) VALUES ('Pushkin');

SELECT b.name AS "Книга", a.name AS "Автор" , b.year AS "Первая публикация"
FROM book b JOIN author a ON a.book_id = b.id;

SELECT b.name, a.name INNER
FROM book AS b JOIN author AS a ON a.book_id = b.id;

SELECT b.name, a.name 
FROM book b JOIN author a ON a.book_id = b.id;