BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;
BEGIN;
	UPDATE balnce SET balance = 5000  WHERE name = 'client1';
	SELECT * FROM balnce;
COMMIT;
