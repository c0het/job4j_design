create table productss (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

INSERT INTO productss(name, producer, count, price) VALUES ('product1', 'producer1', 5, 10);
INSERT INTO productss(name, producer, count, price) VALUES ('product2', 'producer2', 4, 20);



CREATE TRIGGER tax_plus
	AFTER INSERT ON productss
	REFERENCING NEW TABLE AS inserted 
	FOR EACH STATEMENT
	EXECUTE PROCEDURE tax();
	
CREATE OR REPLACE FUNCTION tax()
	RETURNS TRIGGER AS
$$
	BEGIN
		UPDATE productss
		SET price = price + price * 0.18;
		RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';



CREATE TRIGGER tax_plus
	BEFORE INSERT
	ON productss
	FOR EACH ROW
	EXECUTE PROCEDURE tax();
	
CREATE OR REPLACE FUNCTION tax()
	RETURNS TRIGGER AS
$$
	BEGIN
		UPDATE productss
		SET price = price + price * 0.18;
		RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';



create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

CREATE TRIGGER add_in_history
	BEFORE INSERT
	ON productss
	FOR EACH ROW
	EXECUTE PROCEDURE add_in_history();


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

SELECT *
FROM history_of_price



