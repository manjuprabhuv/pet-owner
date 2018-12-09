DROP SCHEMA IF EXISTS pet_owner;

CREATE SCHEMA IF NOT EXISTS demo;
USE demo;

/* Table: owner  */
CREATE TABLE IF NOT EXISTS OWNER (
    owner_id     INT auto_increment,
    first_name  NVARCHAR(50) ,
    last_name   NVARCHAR(50) ,
    city  	    NVARCHAR(50) ,
    PRIMARY KEY(owner_id)
);

/* Table: pets */
CREATE TABLE IF NOT EXISTS PET (
  pet_id     INT auto_increment,
  name       VARCHAR(50) ,
  birth_date      DATE ,
  owner_id	INT NOT NULL,
  PRIMARY KEY (pet_id)
);


/* Foreign Key: pets */
ALTER TABLE PET ADD CONSTRAINT IF NOT EXISTS fk_pet_owner FOREIGN KEY (owner_id) REFERENCES OWNER(owner_id);
