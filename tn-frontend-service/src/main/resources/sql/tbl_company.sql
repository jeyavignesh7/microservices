INSERT INTO tn_company VALUES ('COMP00001', 'Company 01');
INSERT INTO tn_company VALUES ('COMP00002', 'Company 02');

COMMIT;

SELECT * FROM tn_company LIMIT 10;
