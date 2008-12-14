package com.themangoproject.db.h2;

import java.sql.*;
import java.util.Scanner;

import com.themangoproject.db.DBSchema;

/**
 * Set of utility operations on the H2 database. The H2Util class is a singleton
 * and provides a single instance through the getInstance() method. The utility
 * class guarantees that only a single connection to the database is created for
 * all users of the H2Util class.
 * 
 * @author Paul Osborne
 */
public class H2Util {

	/** Singleton instance of the H2Util class */
	private static H2Util instance;

	/** Store the location of the database */
	private String databaseLocation = "~/mango.db";

	/** Connection to H2 database */
	private Connection conn;

	/**
	 * Private constructor for the singleton. Initializes the state of the
	 * utility class.
	 */
	private H2Util() {
		try {
			Class.forName("org.h2.Driver"); // load H2 driver into memory
		} catch (ClassNotFoundException ex) {
			System.err.println("Could not load H2 driver into memory");
		}
		this.conn = null;
	}

	/**
	 * @return An instance of the H2Utility class for use.
	 */
	public static H2Util getInstance() {
		if (instance == null) {
			instance = new H2Util();
		}
		return instance;
	}

	/**
	 * Set the location of the database.
	 * 
	 * @param dbLocation
	 *            The location of the database that should be used. This can
	 *            either be an absolute path (C:\mango.db) or a relative path
	 *            (~/testing.db OR ./mangotesting.db).
	 */
	public void setDatabaseLocation(String dbLocation) {
		this.databaseLocation = dbLocation;
	}

	/**
	 * @param dbName
	 *            The name of the database to connect to.
	 * @return A connection to the H2 database.
	 * @throws SQLException
	 *             in the case that there is an error creating a connection to
	 *             the database.
	 */
	private Connection connectToDb() throws SQLException {
		String url = "jdbc:h2:" + databaseLocation;
		return DriverManager.getConnection(url);
	}

	/**
	 * Return a connection to the database. The class is set up such that only
	 * one connection to the database will be established.
	 * 
	 * @return A connection to the database.
	 */
	public Connection getConnection() {
		try {
			return connectToDb(); // this is no longer a singleton
		} catch (SQLException ex) {
			return null;
		}
	}

	/**
	 * 
	 * 
	 * @param dbName
	 *            The name of the database that should be initialized.
	 */
	public void initializeSchemaOnDb() {
		conn = getConnection();
		try {
			Statement stat = conn.createStatement();
			// Drop everything if exists (in right order)
			stat.executeUpdate(DBSchema.dropSetContentsTable);
			stat.executeUpdate(DBSchema.dropSetsTable);
			stat.executeUpdate(DBSchema.dropGenreTable);
			stat.executeUpdate(DBSchema.dropSavedSearchesTable);
			stat.executeUpdate(DBSchema.dropActingRolesTable);
			stat.executeUpdate(DBSchema.dropSetContentsTable);
			stat.executeUpdate(DBSchema.dropListContentsTable);
			stat.executeUpdate(DBSchema.dropListsTable);
			stat.executeUpdate(DBSchema.dropActorTable);
			stat.executeUpdate(DBSchema.dropMovieTable);
			stat.executeUpdate(DBSchema.dropPersonTable);
			stat.executeUpdate(DBSchema.dropPhoneNumberDomain);
			stat.executeUpdate(DBSchema.dropMovieMediumDomain);
			stat.executeUpdate(DBSchema.dropMovieRatingDomain);

			// Create everything (in right order)
			stat.executeUpdate(DBSchema.createMovieMediumDomain);
			stat.executeUpdate(DBSchema.createMovieRatingDomain);
			stat.executeUpdate(DBSchema.createPhoneNumberDomain);
			stat.executeUpdate(DBSchema.createActorTable);
			stat.executeUpdate(DBSchema.createPersonTable);
			stat.executeUpdate(DBSchema.createMovieTable);
			stat.executeUpdate(DBSchema.createGenreTable);
			stat.executeUpdate(DBSchema.createActingRolesTable);
			stat.executeUpdate(DBSchema.createListsTable);
			stat.executeUpdate(DBSchema.createListContentsTable);
			stat.executeUpdate(DBSchema.createSetsTable);
			stat.executeUpdate(DBSchema.createSetContentsTable);
			stat.executeUpdate(DBSchema.createSavedSearchesTable);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @param values
	 *            The values that should be quoted and comma separated.
	 * @return A set of comma-separated quoted values from the inputs.
	 */
	public static String singleQuotedCSV(String... values) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < values.length; i++) {
			sb.append("'" + values[i] + "'");
			if (i != values.length - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	/**
	 * Mini application for resetting the schema on a database to the newest
	 * state. This will remove all data in the database.
	 * 
	 * @param args
	 *            Command line arguments (not used).
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("This utility will clear and reset schema on DB");
		System.out.print("DB Location: ");
		String dbName = scan.nextLine();
		H2Util.getInstance().setDatabaseLocation(dbName);
		H2Util.getInstance().initializeSchemaOnDb();
		System.out.println("Complete! Cowabunga Dude!");
	}
}
