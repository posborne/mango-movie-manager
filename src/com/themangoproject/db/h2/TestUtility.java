package com.themangoproject.db.h2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestUtility {

    private static final String clearMovie = "DELETE FROM movie";

    private static final String clearPerson = "DELETE FROM person";

    private static final String clearActor = "DELETE FROM actor";

    private static final String clearGenre = "DELETE FROM genre";

    private static final String clearActingRoles = "DELETE FROM acting_roles";

    private static final String clearSavedSearches = "DELETE FROM saved_searches";

    private static final String clearLists = "DELETE FROM lists";

    private static final String clearListContents = "DELETE FROM list_contents";

    private static final String clearSets = "DELETE FROM sets";

    private static final String clearSetContents = "DELETE FROM set_contents";

    private static final String personInserts = "INSERT INTO person (id, name, address, phone_num, email)"
            + " VALUES (1, 'Paul Osborne', '3001 Hampshire Ave N', '7637970688', 'osbpau@bethel.edu'),"
            + "(2, 'Zachary Varberg', 'Turnwall Apartments 21', '651.635.2272', '651.635.2272'),"
            + "(3, 'Kyle Ronning', 'Heritage Hall 313C', '651.635.2193', 'kyle-ronning@bethel.edu')";

    private static final String movieInserts = "INSERT INTO movie (id, director, title, rating, runtime, year, asin, purchase_date, custom_description, condition, type, mango_rating, owner_id, borrower_id)"
            + " VALUES (1, 'John McTiernan', 'Die Hard', 'R', 131, 1988, 'B000O77SRC', '2003-12-23', 'Yippe Ki Yay', 'Good', 'DVD', 4, 1, NULL),"
            + "(2, 'John McTiernan', 'Die Hard: With a Vengeance', 'R', 131, 1995, 'B000O78KW4', '2005-10-18', 'This one is pretty fun to watch', 'Poor', 'DVD', 5, 1, 2)";

    private static final String actorInserts = "INSERT INTO actor (id, first_name, last_name)"
            + " VALUES (1, 'Bruce', 'Willis'),"
            + "(2, 'Bonnie', 'Bedalia'),"
            + "(3, 'Paul', 'Gleason'),"
            + "(4, 'Jeremy', 'Irons'),"
            + "(5, 'Samuel L.', 'Jackson'),"
            + "(6, 'Graham', 'Greene')";

    private static final String actingRoleInserts = "INSERT INTO acting_roles (movie_id, actor_id, role, character) "
            + "VALUES (1, 1, 'Lead Character', 'Officer John McClane'),"
            + "(1, 2, 'Lead Character', 'Holly Gennaro McClane'),"
            + "(1, 3, 'Supporting Character', 'Deputy Polic Chief Dwayne T. Robinson'),"
            + "(2, 1, 'Lead Character', 'John McClane'),"
            + "(2, 4, 'Lead Character', 'Simon Gruber'),"
            + "(2, 5, 'Lead Character', 'Zeus Carver'),"
            + "(2, 6, 'Support Character', 'Joe Lambert')";

    private static final String genreInserts = "INSERT INTO genre (movie_id, name) "
            + "VALUES (1, 'Action'),"
            + "(1, 'Thriller'),"
            + "(2, 'Action')," + "(2, 'Thriller')";

    private static final String listContentsInserts = "INSERT INTO list_contents (list_id, movie_id, order_id)"
            + "VALUES (1, 1, 1)," + "(1, 2, 2)";

    private static final String listInserts = "INSERT INTO lists (id, label)"
            + "	VALUES(1, 'Action Marathon')";

    private static final String setContentsInserts = "INSERT INTO set_contents (set_id, movie_id) "
            + "VALUES (1, 1)," + "(1, 2)";

    private static final String setInserts = "INSERT INTO sets (id, label)"
            + "	VALUES (1, 'Die Hard Series')";

    public static void executeInserts() {
        Connection conn = H2Util.getInstance().getConnection();
        Statement stat;
        try {
            stat = conn.createStatement();

            // Clear Everything
            stat.executeUpdate(clearListContents);
            stat.executeUpdate(clearLists);
            stat.executeUpdate(clearSavedSearches);
            stat.executeUpdate(clearSetContents);
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
            stat.executeUpdate(setInserts);
            stat.executeUpdate(setContentsInserts);
            stat.executeUpdate(listInserts);
            stat.executeUpdate(listContentsInserts);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("DB Location: ");
        String dbName = scan.nextLine();
        H2Util.getInstance().setDatabaseLocation(dbName);
        executeInserts();
    }
}
