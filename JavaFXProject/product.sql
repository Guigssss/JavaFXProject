DROP DATABASE IF EXISTS product; 
CREATE DATABASE IF NOT EXISTS product; 
USE product;

DROP TABLE IF EXISTS clothestable;
CREATE TABLE IF NOT EXISTS clothestable(
id INTEGER NOT NULL auto_increment ,
name VARCHAR(40),
price DOUBLE,
nbItems INTEGER,	
size INTEGER,
reduc DOUBLE DEFAULT 30,
indic INTEGER DEFAULT 1,
PRIMARY KEY (id)
);

DROP TABLE IF EXISTS shoestable;
CREATE TABLE IF NOT EXISTS shoestable(
id INTEGER NOT NULL auto_increment ,
name VARCHAR(40),
price DOUBLE,
nbItems INTEGER,
shoeSize INTEGER,
reduc DOUBLE DEFAULT 20,
indic INTEGER DEFAULT 2,
PRIMARY KEY (id)
);

DROP TABLE IF EXISTS accessoriestable;
CREATE TABLE IF NOT EXISTS accessoriestable(
id INTEGER NOT NULL auto_increment ,
name VARCHAR(40),
price DOUBLE,
nbItems INTEGER,
reduc DOUBLE DEFAULT 50,
indic INTEGER DEFAULT 3,
PRIMARY KEY (id)
);

SELECT * FROM clothestable;