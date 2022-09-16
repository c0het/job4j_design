CREATE TABLE balnce(
	id serial PRIMARY KEY,
	name varchar(100),
	balance int,
	account int
);

INSERT INTO balnce (name, balance, account) VALUES ('client1', 1000, 1),('client2', 1000, 2);

BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;

BEGIN;
 	UPDATE balnce SET balance = balance - 100 WHERE name = 'client2';
	SELECT * FROM balnce;
COMMIT;




