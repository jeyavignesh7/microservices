INSERT INTO tn_user_cred VALUES (
	'A100001', 
	'DUMMY', 
    'a100001@domain.com', 
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
    'a100002@domain.com',
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
	'U100001', 
	'DUMMY', 
    'a100002@domain.com',
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
	'U100002', 
	'DUMMY', 
    'a100002@domain.com',
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

UPDATE tn_user_cred SET password = '{bcrypt}$2a$10$uageAGUrH1UUPWBf/dtcr.l6nXXDfjRPNVWRy6Dz7PNrjU4DxBaJm' WHERE user_id = 'A100001';
