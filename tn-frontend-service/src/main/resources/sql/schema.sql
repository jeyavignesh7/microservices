CREATE SCHEMA tradenet;

USE tradenet;

CREATE USER 'tnfeuser'@'%' IDENTIFIED BY 'P@ssw0rd1234';

GRANT SELECT ON tradenet.* TO 'tnfeuser'@'%';
GRANT INSERT ON tradenet.* TO 'tnfeuser'@'%';
GRANT UPDATE ON tradenet.* TO 'tnfeuser'@'%';
GRANT DELETE ON tradenet.* TO 'tnfeuser'@'%';
GRANT EXECUTE ON tradenet.* TO 'tnfeuser'@'%';
GRANT SHOW VIEW ON tradenet.* TO 'tnfeuser'@'%';
GRANT LOCK TABLES ON tradenet.* TO 'tnfeuser'@'%';

USE tradenet;

CREATE TABLE tn_exchange_rate
(
	curr_code VARCHAR(3) NOT NULL,
    eff_dtm DATETIME NOT NULL,
    exp_dtm DATETIME NOT NULL,
    curr_unit NUMERIC(15,2) NULL,
    exc_rate NUMERIC(15,2) NULL,
    PRIMARY KEY (curr_code, eff_dtm, exp_dtm)
);

CREATE TABLE tn_hs
(
	hs_code VARCHAR(40) NOT NULL,
    eff_dtm DATETIME NOT NULL,
    exp_dtm DATETIME NOT NULL,
    description VARCHAR(1000) NOT NULL, 
    PRIMARY KEY (hs_code, eff_dtm, exp_dtm)
);

CREATE TABLE tn_country
(
	code VARCHAR(3) NOT NULL,
    name VARCHAR(100) NOT NULL, 
    PRIMARY KEY (code)
);

