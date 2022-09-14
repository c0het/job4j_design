create table productss (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

INSERT INTO productss(name, producer, count, price) VALUES ('product1', 'producer1', 5, 10),
('product2', 'producer2', 5, 10),('product3', 'producer3', 5, 10);

CREATE OR REPLACE PROCEDURE delet_if_count_is_0(u_count integer, u_id integer)
LANGUAGE 'plpgsql'
AS $$
	BEGIN
		IF u_count = 0 THEN
			DELETE FROM productss WHERE id = u_id;
		END IF;
		IF u_count > 0 THEN
			UPDATE productss SET count = count + u_count;
		END IF;
	END;
$$;

DROP PROCEDURE delet_if_count_is_0(u_count integer, u_id integer);

CALL delet_if_count_is_0(0, 4);

SELECT *
FROM productss