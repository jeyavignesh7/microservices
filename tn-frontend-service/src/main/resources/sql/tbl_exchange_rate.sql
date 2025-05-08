INSERT INTO tn_exchange_rate VALUES ('SGD', STR_TO_DATE('01/20/2025', '%m/%d/%Y %k:%i:%s'), STR_TO_DATE('12/31/9999', '%m/%d/%Y %k:%i:%s'), 1, 0.75);
INSERT INTO tn_exchange_rate VALUES ('INR', STR_TO_DATE('01/20/2025', '%m/%d/%Y %k:%i:%s'), STR_TO_DATE('12/31/9999', '%m/%d/%Y %k:%i:%s'), 100, 1.17);

COMMIT;

SELECT * FROM tn_exchange_rate LIMIT 10;