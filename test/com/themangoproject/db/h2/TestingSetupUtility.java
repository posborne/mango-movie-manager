package com.themangoproject.db.h2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestingSetupUtility {

	private static final String clearMovie =
		"DELETE FROM movie";
	
	private static final String clearPerson =
		"DELETE FROM person";
	
	private static final String clearActor =
		"DELETE FROM actor";
	
	private static final String clearGenre =
		"DELETE FROM genre";
	
	private static final String clearActingRoles =
		"DELETE FROM acting_roles";
	
	private static final String clearSavedSearches =
		"DELETE FROM saved_searches";
	
	private static final String clearLists=
		"DELETE FROM lists";
	
	private static final String clearSets =
		"DELETE FROM sets";
	
	private static final String personInserts =
		"INSERT INTO person (id, name, address, phone_num, email)" +
		" VALUES (1, 'Paul Osborne', '3001 Hampshire Ave N', '7637970688', 'osbpau@bethel.edu')," +
		"(2, 'Zachary Varberg', 'Turnwall Apartments 21', '651.635.2272', '651.635.2272')," +
		"(3, 'Kyle Ronning', 'Heritage Hall 313C', '651.635.2193', 'kyle-ronning@bethel.edu')";
	
	private static final String movieInserts = 
		"INSERT INTO movie (id, director, title, rating, runtime, year, asin, purchase_date, custom_description, condition, type, mango_rating, owner_id, borrower_id)" +
		" VALUES (1, 'John McTiernan', 'Die Hard', 'R', 131, 1988, 'B000O77SRC', '2003-12-23', 'Yippe Ki Yay', 'Good', 'DVD', 4, 1, 3)," + 
		"(2, 'John McTiernan', 'Die Hard: With a Vengeance', 'R', 131, 1995, 'B000O78KW4', '2005-10-18', 'This one is pretty fun to watch', 'Poor', 'DVD', 5, 1, 2)";
	
	private static final String actorInserts =
		"INSERT INTO actor (id, first_name, last_name)" +
		" VALUES (1, 'Bruce', 'Willis')," +
		"(2, 'Bonnie', 'Bedalia')," +
		"(3, 'Paul', 'Gleason')," +
		"(4, 'Jeremy', 'Irons')," +
		"(5, 'Samuel L.', 'Jackson')," +
		"(6, 'Graham', 'Greene')";
	
	private static final String actingRoleInserts = 
		"INSERT INTO acting_roles (movie_id, actor_id, role, character) " +
		"VALUES (1, 1, 'Lead Character', 'Officer John McClane')," +
		"(1, 2, 'Lead Character', 'Holly Gennaro McClane')," +
		"(1, 3, 'Supporting Character', 'Deputy Polic Chief Dwayne T. Robinson')," +
		"(2, 1, 'Lead Character', 'John McClane')," +
		"(2, 4, 'Lead Character', 'Simon Gruber')," +
		"(2, 5, 'Lead Character', 'Zeus Carver')," +
		"(2, 6, 'Support Character', 'Joe Lambert')";
	
	private static final String genreInserts =
		"INSERT INTO genre (movie_id, name) " +
		"VALUES (1, 'Action')," +
		"(1, 'Thriller')," +
		"(2, 'Action')," +
		"(2, 'Thriller')";
	
	private static final String listsInserts = 
		"INSERT INTO lists (label, movie_id, order_id)" +
		"VALUES ('Action Marathon', 1, 1)," +
		"('Action Marathon', 2, 2)";
	
	private static final String setsInserts = 
		"INSERT INTO sets (label, movie_id) " +
		"VALUES ('Die Hard Series', 1)," +
		"('Die Hard Series', 2)";
	
	public static void executeInserts(String dbName) {
		Connection conn = H2Util.getInstance().getConnection(dbName);
		Statement stat;
		try {
			System.out.println(conn.getMetaData().getURL());
			stat = conn.createStatement();
			
			// Clear Everything
			stat.executeUpdate(clearLists);
			stat.executeUpdate(clearSavedSearches);
			stat.executeUpdate(clearSets);
			stat.executeUpdate(clearActingRoles);
			stat.executeUpdate(clearGenre);
			stat.executeUpdate(clearMovie);
			stat.executeUpdate(clearPerson);
			stat.executeUpdate(clearActor);
			
			// Do inserts
			stat.executeUpdate(personInserts);
			stat.executeUpdate(movieInserts);
			stat.executeUpdate(actorInserts);
			stat.executeUpdate(genreInserts);
			stat.executeUpdate(actingRoleInserts);
			stat.executeUpdate(setsInserts);
			stat.executeUpdate(listsInserts);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("DB: ");
		String dbName = scan.nextLine();
		executeInserts(dbName);
	}
}
