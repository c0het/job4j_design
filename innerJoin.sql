CREATE TABLE book(
	id serial PRIMARY KEY,
	name varchar(255)
);


CREATE TABLE author (
 	id serial PRIMARY KEY,
	name VARCHAR(255),
	book_id int references book(id)
);

INSERT into book(name) VALUES ('War and Peace');
INSERT into book(name) VALUES ('Harry Potter');
INSERT into book(name) VALUES ('To Kill a Mockingbird');

INSERT INTO author(name, book_id) VALUES ('Lev Tolstoi', 1);
INSERT INTO author(name, book_id) VALUES ('Harper Lee', 3);
INSERT INTO author(name, book_id) VALUES ('Joanne Rowling', 2);

INSERT INTO author(name) VALUES ('Pushkin');

SELECT b.name AS "Книга", a.name AS "Автор"
FROM book b JOIN author a ON a.book_id = b.id;