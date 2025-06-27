INSERT INTO tn_user_cred VALUES (
	'A100001', 
	'DUMMY', 
	STR_TO_DATE('01/20/2025 00:00:00', '%m/%d/%Y %k:%i:%s'), 
	STR_TO_DATE('12/31/9999 23:59:59', '%m/%d/%Y %k:%i:%s'), 
	STR_TO_DATE('01/20/2025 00:00:00', '%m/%d/%Y %k:%i:%s'), 
	0, 
	'N', 
	NULL,
	'N', 
	NULL, 
	'SYSTEM', 
	STR_TO_DATE('01/20/2025 00:00:00', '%m/%d/%Y %k:%i:%s'),
	NULL, 
	NULL
);
INSERT INTO tn_user_cred VALUES (
	'A100002', 
	'DUMMY', 
	STR_TO_DATE('01/20/2025 00:00:00', '%m/%d/%Y %k:%i:%s'), 
	STR_TO_DATE('12/31/9999 23:59:59', '%m/%d/%Y %k:%i:%s'), 
	STR_TO_DATE('01/20/2025 00:00:00', '%m/%d/%Y %k:%i:%s'), 
	0, 
	'N', 
	NULL,
	'N', 
	NULL, 
	'SYSTEM', 
	STR_TO_DATE('01/20/2025 00:00:00', '%m/%d/%Y %k:%i:%s'),
	NULL, 
	NULL
);

COMMIT;

SELECT * FROM tn_user_cred LIMIT 10;