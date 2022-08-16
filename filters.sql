SELECT * FROM fauna WHERE NAME LIKE  'fish%';
SELECT * FROM fauna WHERE avg_age >10000 AND avg_age<21000;
SELECT * FROM fauna WHERE discovery_date IS NULL;
SELECT * FROM fauna WHERE discovery_date < '01.01.1950';