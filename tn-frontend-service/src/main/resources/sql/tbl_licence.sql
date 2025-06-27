INSERT INTO tn_licence VALUES ('LIC00001', 'Licence 01', 'COMP00001', STR_TO_DATE('01/20/2025', '%m/%d/%Y %k:%i:%s'), STR_TO_DATE('12/31/9999', '%m/%d/%Y %k:%i:%s'));
INSERT INTO tn_licence VALUES ('LIC00002', 'Licence 02', 'COMP00002', STR_TO_DATE('01/20/2025', '%m/%d/%Y %k:%i:%s'), STR_TO_DATE('12/31/9999', '%m/%d/%Y %k:%i:%s'));

COMMIT;

SELECT * FROM tn_licence LIMIT 10;
SELECT * FROM tn_company LIMIT 10;
