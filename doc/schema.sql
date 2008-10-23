-- Mango Database Schema

CREATE DOMAIN MOVIE_MEDIUM CHAR(10)
	CHECK (VALUE IN ('DVD', 'VHS', 'BRAY', 'HD-DVD', 'DIGITAL', 'VCD') );

-- We allow for TV ratings as well
CREATE DOMAIN MOVIE_RATING CHAR(5)
	CHECK (VALUE IN ('G', 'PG', 'PG-13', 'NC-17', 'R', 'NR', 'RP', 'TVPG', 
					 'TVY7', 'TVY7FV', 'TVG', 'TV14', 'TVMA') );

-- 10 Digit numbers
CREATE DOMAIN PHONE_NUMBER INT(10)
	CHECK (VALUE > 999999999 AND VALUE < 10000000000);

CREATE TABLE movie (
	id					INT NOT NULL,
	director			CHAR(50) NOT NULL,
	title				CHAR(256) NOT NULL,
	rating				MOVIE_RATING NOT NULL DEFAULT 'NR',
	runtime				TIME,
	year				YEAR(4),
	asin				INT DEFAULT NULL,
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

CREATE TABLE person (
	id			INT NOT NULL,
	name		CHAR(50) NOT NULL,
	address		CHAR(256),
	phone_num	PHONE_NUMBER,
	email		CHAR(70),
	PRIMARY KEY(id)
);
-- FDs:
--   id -> {all}
-- (In BCNF.)

CREATE TABLE actor (
	id				INT NOT NULL,
	first_name		CHAR(50) NOT NULL,
	last_name		CHAR(50) NOT NULL,
	PRIMARY KEY(id)
);
-- FDs:
--   id -> {all}
-- (In BCNF.)

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

CREATE TABLE genre (
	name			CHAR(50) NOT NULL,
	movie_id		INT NOT NULL,
	FOREIGN KEY(movie_id) REFERENCES movie(id),
	PRIMARY KEY(name, movie_id)
);
-- FDs: (none)
-- (In BCNF.)

CREATE TABLE saved_searches (
	label		CHAR(30) NOT NULL,
 	query		TEXT NOT NULL,
 	PRIMARY KEY (label)
);
-- FDs:
--   {label} -> {query}
-- (In BCNF.)
