INSERT INTO tn_hs VALUES ('11010001', STR_TO_DATE('01/20/2025', '%m/%d/%Y %k:%i:%s'), STR_TO_DATE('12/31/9999 23:59:59', '%m/%d/%Y %k:%i:%s'), 'Audi Q3 car');
INSERT INTO tn_hs VALUES ('11010002', STR_TO_DATE('01/20/2025', '%m/%d/%Y %k:%i:%s'), STR_TO_DATE('12/31/9999 23:59:59', '%m/%d/%Y %k:%i:%s'), 'BMW X1 car');

COMMIT;

SELECT * FROM tn_hs LIMIT 10;