-- Mango Database Schema
CREATE TABLE movie (
	id				INT,
	asin			INT,
	director		CHAR(50),
	title			CHAR(256),
	rating			CHAR(10),
	runtime			TIME,
	year			YEAR(4),
	purchase_date	DATE,
	custom_description	TEXT,
	condition		CHAR(100),
	type			CHAR(20), -- DVD, VHS, ...
	mango_rating	INT,
	owner_id		INT,
	borrower_id		INT,
	FOREIGN KEY (owner_id) REFERENCES person(id),
	FOREIGN KEY (borrower_id) REFERENCES person(id),
	PRIMARY KEY(id)
);

CREATE TABLE person (
	id			INT,
	name		CHAR(50),
	address		CHAR(256),
	phone_num	CHAR(20),
	email		CHAR(70),
	PRIMARY KEY(id)
);

CREATE TABLE actor (
	id				INT,
	first_name		CHAR(50),
	last_name		CHAR(50),
	PRIMARY KEY(id)
);

CREATE TABLE acting_roles (
	movie_id		INT,
	actor_id		INT,
	role			CHAR(50),
	FOREIGN KEY(movie_id) REFERENCES movie(id),
	FOREIGN KEY(actor_id) REFERENCES actor(id),
	PRIMARY KEY(movie_id, actor_id)
);

CREATE TABLE genre (
	name			CHAR(50),
	movie_id		INT,
	FOREIGN KEY(movie_id) REFERENCES movie(id),
	PRIMARY KEY(name, movie_id)
);

CREATE TABLE saved_searches (
	label		CHAR(30),
 	query		TEXT,
 	PRIMARY KEY (label)
);
