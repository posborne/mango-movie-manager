-- Mango Database Schema (H2 Compatible)
DROP DOMAIN IF EXISTS MOVIE_MEDIUM;
CREATE DOMAIN MOVIE_MEDIUM AS CHAR(10)
	CHECK (VALUE IN ('DVD', 'VHS', 'BRAY', 'HD-DVD', 'DIGITAL', 'VCD') );

-- We allow for TV ratings as well
DROP DOMAIN IF EXISTS MOVIE_RATING;
CREATE DOMAIN MOVIE_RATING AS CHAR(5)
	CHECK (VALUE IN ('G', 'PG', 'PG-13', 'NC-17', 'R', 'NR', 'RP', 'TVPG', 
			 'TVY7', 'TVY7FV', 'TVG', 'TV14', 'TVMA') );

-- 10 Digit numbers
DROP DOMAIN IF EXISTS PHONE_NUMBER;
CREATE DOMAIN PHONE_NUMBER AS VARCHAR(25);

DROP TABLE IF EXISTS person;
CREATE TABLE person (
  id      INT NOT NULL AUTO_INCREMENT,
  name    CHAR(50) NOT NULL,
  address   CHAR(256),
  phone_num PHONE_NUMBER,
  email   CHAR(70),
  PRIMARY KEY(id)
);
-- FDs:
--   id -> {all}
-- (In BCNF.)

DROP TABLE IF EXISTS actor;
CREATE TABLE actor (
  id        INT NOT NULL AUTO_INCREMENT,
  first_name    CHAR(50) NOT NULL,
  last_name   CHAR(50) NOT NULL,
  PRIMARY KEY(id),
  UNIQUE(first_name, last_name)
);
-- FDs:
--   id -> {all}
-- (In BCNF.)

DROP TABLE IF EXISTS movie;
CREATE TABLE movie (
	id					INT NOT NULL AUTO_INCREMENT,
	director			CHAR(50) NOT NULL,
	title				CHAR(256) NOT NULL,
	rating				MOVIE_RATING NOT NULL DEFAULT 'NR',
	runtime				INT,
	year				YEAR(4),
	asin				CHAR(50) DEFAULT NULL,
	purchase_date		DATE,
	custom_description	TEXT,
	condition			CHAR(100),
	type				MOVIE_MEDIUM, -- DVD, VHS, ...
	mango_rating		INT,
	owner_id			INT,
	borrower_id			INT,
	FOREIGN KEY (owner_id) REFERENCES person(id),
	FOREIGN KEY (borrower_id) REFERENCES person(id),
	PRIMARY KEY(id)
);

-- FDs:
--   id -> {all}
--
-- (In BCNF.  Note that {title, director} and {title, year} are not specified
--   as FDs in order to allow for NULL values in cases where that information
--   does not apply.  One example would be for home videos.)

DROP TABLE IF EXISTS acting_roles;
CREATE TABLE acting_roles (
	movie_id		INT NOT NULL,
	actor_id		INT NOT NULL,
	role			CHAR(50),
	character		CHAR(50),
	FOREIGN KEY(movie_id) REFERENCES movie(id),
	FOREIGN KEY(actor_id) REFERENCES actor(id),
	PRIMARY KEY(movie_id, actor_id)
);
-- FDs:
--   {movie_id, actor_id} -> {role, character}
-- (In BCNF.)

DROP TABLE IF EXISTS genre;
CREATE TABLE genre (
	name			CHAR(50) NOT NULL,
	movie_id		INT NOT NULL,
	FOREIGN KEY(movie_id) REFERENCES movie(id),
	PRIMARY KEY(name, movie_id)
);
-- FDs: (none)
-- (In BCNF.)

DROP TABLE IF EXISTS saved_searches;
CREATE TABLE saved_searches (
	label		CHAR(30) NOT NULL,
 	query		TEXT NOT NULL,
 	PRIMARY KEY (label)
);
-- FDs:
--   {label} -> {query}
-- (In BCNF.)

DROP TABLE IF EXISTS lists;
CREATE TABLE lists (
	label		CHAR(30) NOT NULL,
	movie_id	INT NOT NULL,
	order_id	INT NOT NULL,
	FOREIGN KEY (movie_id) REFERENCES movie(id),
	PRIMARY KEY (label, movie_id, order_id)
);
-- FDs: (none)

DROP TABLE IF EXISTS sets;
CREATE TABLE sets (
	label 		CHAR(30) NOT NULL,
	movie_id 	INT NOT NULL,
	PRIMARY KEY (label, movie_id)
);
-- FDs: (none)
