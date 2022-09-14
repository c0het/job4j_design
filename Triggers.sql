create table productss (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

INSERT INTO productss(name, producer, count, price) VALUES ('product1', 'producer1', 5, 10);
INSERT INTO productss(name, producer, count, price) VALUES ('product2', 'producer2', 4, 20);

CREATE FUNCTION tax_insert()
	RETURNS TRIGGER AS
$$
	BEGIN
		UPDATE productss
		SET price = price + price * 0.18
		WHERE id = (SELECT ID FROM inserted );
		RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE TRIGGER tax_for_insert
	AFTER INSERT ON productss
	REFERENCING NEW TABLE AS inserted 
	FOR EACH STATEMENT
	EXECUTE PROCEDURE tax_insert();
	

CREATE OR REPLACE FUNCTION tax_last()
	RETURNS TRIGGER AS
$$
	BEGIN
		NEW.price = NEW.price + NEW.price * 0.18;
		RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';


CREATE TRIGGER tax_plus_last
	BEFORE INSERT
	ON productss
	FOR EACH ROW
	EXECUTE PROCEDURE tax_last();
	



create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

CREATE FUNCTION add_in_history()
	RETURNS TRIGGER AS
$$
	BEGIN
		INSERT INTO history_of_price(name, price, date)
		VALUES (NEW.name, NEW.price, CURRENT_DATE);
		RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql'

CREATE TRIGGER add_in_history
	BEFORE INSERT
	ON productss
	FOR EACH ROW
	EXECUTE PROCEDURE add_in_history();




SELECT *
FROM productss

SELECT *
FROM history_of_price



