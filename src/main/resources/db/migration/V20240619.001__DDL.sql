DROP TABLE IF EXISTS account;

CREATE TABLE account (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  account_number int  NOT NULL,
  balance decimal NOT NULL,
  currency VARCHAR(5) NOT NULL,
  created_time timestamp NOT NULL,
  updated_time timestamp NOT NULL
);