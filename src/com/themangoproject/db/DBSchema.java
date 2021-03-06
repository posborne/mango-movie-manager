package com.themangoproject.db;

/**
 * @author Paul Osborne
 *
 */
public class DBSchema {
	public static final String dropMovieMediumDomain = 
		"DROP DOMAIN IF EXISTS MOVIE_MEDIUM";

	public static final String createMovieMediumDomain = 
		"CREATE DOMAIN MOVIE_MEDIUM AS CHAR(10) " + 
		"CHECK (VALUE IN ('DVD', 'VHS', 'Blu-ray', 'HD-DVD', 'Digital', 'VCD') )";

	public static final String dropMovieRatingDomain = 
		"DROP DOMAIN IF EXISTS MOVIE_RATING";

	public static final String createMovieRatingDomain = 
		"CREATE DOMAIN MOVIE_RATING AS CHAR(8) " + 
		"CHECK (VALUE IN ('G', 'PG', 'PG-13', 'NC-17', 'R', 'NR', 'RP', 'TVPG', " + 
		"'TVY7', 'TVY7FV', 'TVG', 'TV14', 'TVMA', 'Unrated') )";

	public static final String dropPhoneNumberDomain = 
		"DROP DOMAIN IF EXISTS PHONE_NUMBER";

	public static final String createPhoneNumberDomain = 
		"CREATE DOMAIN PHONE_NUMBER AS VARCHAR(25)";

	public static final String createPersonTable = 
		"CREATE TABLE person ("
			+ "id      INT NOT NULL AUTO_INCREMENT,"
			+ "name    CHAR(50) NOT NULL," + "address   CHAR(256),"
			+ "phone_num PHONE_NUMBER," + "email   CHAR(70),"
			+ "UNIQUE(name),"
			+ "PRIMARY KEY(id)," 
			+ ")";

	public static final String createActorTable =
		"CREATE TABLE actor " +
		"(id INT NOT NULL AUTO_INCREMENT, " +
		"first_name CHAR(50) NOT NULL," +
		"last_name CHAR(50) NOT NULL," +
		"PRIMARY KEY(id)," +
		"UNIQUE(first_name, last_name) )";

	public static final String createGenreTable =
		"CREATE TABLE genre (" +
		"	name			CHAR(50) NOT NULL," +
		"	movie_id		INT NOT NULL," +
		"	FOREIGN KEY(movie_id) REFERENCES movie(id)," +
		"	PRIMARY KEY(name, movie_id) )";
	
	public static final String createMovieTable =
		"CREATE TABLE movie (" + 
		"	id					INT NOT NULL AUTO_INCREMENT," +
		"	director			CHAR(50) NOT NULL," +
		"	title				CHAR(256) NOT NULL," +
		"	rating				MOVIE_RATING DEFAULT 'NR'," +
		"	runtime				INT," +
		"	year				YEAR(4)," +
		"	asin				CHAR(50) DEFAULT NULL," +
		"	purchase_date		DATE," +
		"	custom_description	TEXT," +
		"	condition			CHAR(100)," +
		"	type				MOVIE_MEDIUM," +
		"	mango_rating		INT," +
		"	owner_id			INT," +
		"	borrower_id			INT," +
		"   cover_art			BLOB," +
		"	FOREIGN KEY (owner_id) REFERENCES person(id)," +
		"	FOREIGN KEY (borrower_id) REFERENCES person(id)," +
		"	PRIMARY KEY(id) )";
	
	public static final String createActingRolesTable =
		"CREATE TABLE acting_roles (" +
		"	movie_id		INT NOT NULL," +
		"	actor_id		INT NOT NULL," +
		"	role			CHAR(50)," +
		"	character		CHAR(50)," +
		"	FOREIGN KEY(movie_id) REFERENCES movie(id)," +
		"	FOREIGN KEY(actor_id) REFERENCES actor(id)," +
		"	PRIMARY KEY(movie_id, actor_id) )";
	
	public static final String createSavedSearchesTable =
		"CREATE TABLE saved_searches (" +
		"	label		CHAR(30) NOT NULL," +
		" 	query		TEXT NOT NULL," +
		" 	PRIMARY KEY (label) )";
	
	public static final String createListsTable =
		"CREATE TABLE lists (" +
		"	id		INT NOT NULL AUTO_INCREMENT," +
		"	label	CHAR(30) NOT NULL," +
		"	UNIQUE(label)," +
		"	PRIMARY KEY(id) )";
	
	public static final String createListContentsTable =
		"CREATE TABLE list_contents (" +
		"	list_id		INT NOT NULL," +
		"	movie_id	INT NOT NULL," +
		"	order_id	INT NOT NULL," +
		"	FOREIGN KEY (list_id) REFERENCES lists(id)," +
		"	FOREIGN KEY (movie_id) REFERENCES movie(id)," +
		"	PRIMARY KEY (list_id, order_id) )";
	
	public static final String createSetsTable =
		"CREATE TABLE sets (" +
		"	id			INT NOT NULL AUTO_INCREMENT," +
		"	label		CHAR(30) NOT NULL," +
		"	UNIQUE(label)," +
		"	PRIMARY KEY (id) )";
	
	public static final String createSetContentsTable =
		"CREATE TABLE set_contents (" +
		"	set_id 		INT NOT NULL," +
		"	movie_id 	INT NOT NULL," +
		"	FOREIGN KEY (set_id) REFERENCES sets(id)," +
		"	PRIMARY KEY (set_id, movie_id) )";
	
	public static final String dropSetsTable =
		"DROP TABLE IF EXISTS sets";
	
	public static final String dropSetContentsTable =
		"DROP TABLE IF EXISTS set_contents";
	
	public static final String dropSavedSearchesTable =
		"DROP TABLE IF EXISTS saved_searches";
	
	public static final String dropListsTable =
		"DROP TABLE IF EXISTS lists";
	
	public static final String dropListContentsTable =
		"DROP TABLE IF EXISTS list_contents";
	
	public static final String dropGenreTable =
		"DROP TABLE IF EXISTS genre";
	
	public static final String dropActorTable =
		"DROP TABLE IF EXISTS actor";
	
	public static final String dropMovieTable =
		"DROP TABLE IF EXISTS movie";
	
	public static final String dropPersonTable =
		"DROP TABLE IF EXISTS person";
	
	public static final String dropActingRolesTable =
		"DROP TABLE IF EXISTS acting_roles";
}
