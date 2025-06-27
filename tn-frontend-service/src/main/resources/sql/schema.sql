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

CREATE TABLE tn_user_cred
(
	user_id VARCHAR(20) NOT NULL, 
    usr_pwd VARCHAR(1000) NOT NULL, 
    eff_dtm DATETIME NOT NULL, 
    exp_dtm DATETIME NOT NULL, 
    last_pwd_change_dtm DATETIME NOT NULL, 
    no_of_attempts NUMERIC(1) NULL, 
    is_suspended VARCHAR(1) NULL, 
    suspend_reason VARCHAR(1000) NULL, 
    is_terminated VARCHAR(1) NULL, 
    terminate_reason VARCHAR(1000) NULL, 
    created_by VARCHAR(20) NULL, 
    created_on DATETIME NULL, 
    modified_by VARCHAR(20) NULL, 
    modified_on DATETIME NULL, 
    PRIMARY KEY (user_id) 
);

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

CREATE TABLE tn_company
(
	company_id VARCHAR(40) NOT NULL,
    name VARCHAR(400) NOT NULL, 
    PRIMARY KEY (company_id)
);

CREATE TABLE tn_licence
(
	code VARCHAR(20) NOT NULL,
    description VARCHAR(1000) NOT NULL, 
    company_id VARCHAR(40) NOT NULL, 
    eff_dtm DATETIME NOT NULL,
    exp_dtm DATETIME NOT NULL,
    PRIMARY KEY (code, company_id)
);
