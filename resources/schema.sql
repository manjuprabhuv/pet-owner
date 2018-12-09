DROP SCHEMA IF EXISTS pet_owner;

CREATE SCHEMA demo;
USE demo;

/* Table: owner  */
CREATE TABLE OWNER (
    owner_id     INT auto_increment,
    first_name  NVARCHAR(50) ,
    last_name   NVARCHAR(50) ,
    city  	    NVARCHAR(50) ,
    PRIMARY KEY(owner_id)
);

/* Table: pets */
CREATE TABLE PET (
  pet_id     INT auto_increment,
  name       VARCHAR(50) ,
  birth_date      DATE ,
  owner_id	INT NOT NULL,
  PRIMARY KEY (pet_id)
);

create sequence owner_seq;

create sequence pet_seq;

/* Foreign Key: pets */
ALTER TABLE PET ADD CONSTRAINT fk_pet_owner FOREIGN KEY (owner_id) REFERENCES OWNER(owner_id);
