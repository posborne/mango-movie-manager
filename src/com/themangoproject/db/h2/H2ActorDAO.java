package com.themangoproject.db.h2;

import com.themangoproject.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The <code>H2ActorDAO</code> is an <code>ActorDAO</code> that provides methods
 * for communicating with the H2 database system, specifically with regards to
 * queries and updates that affect the <code>Actor</code> business object.
 * 
 * The DAO is a singleton and obtains and manages its own database connection.
 * 
 * @author Paul Osborne
 * @version November 28, 2008
 */
public class H2ActorDAO implements ActorDAO {

	/** Singleton instance of the H2ActorDAO */
	private static H2ActorDAO instance;

	/** Database connection for the DAO */
	private Connection conn;

	/** Prepared statement for retrieving ids of all actors */
	private PreparedStatement allActorsPS;
	/** Retrieve ids and names of all actors */
	private static final String allActorsQuery = "SELECT id, first_name, last_name"
			+ " FROM actor";

	/** Retrieve all roles for an actor */
	private PreparedStatement rolesForActorPS;
	private static final String rolesForActorQuery = "SELECT movie_id, role, character"
			+ " FROM movie, acting_roles" + " WHERE movie_id=? AND actor_id=?";

	/** Add an actor to the database (no roles) */
	private PreparedStatement addActorPS;
	private static final String addActorQuery = "INSERT INTO actor (first_name, last_name)"
			+ " VALUES(?, ?)";

	/** Update an actor in the database */
	private PreparedStatement updateActorPS;
	private static final String updateActorQuery = "UPDATE actor"
			+ " SET first_name=?, last_name=?" + " WHERE id=?";

	/** Retrieve an actors information */
	private PreparedStatement populatePersonPS;
	private static final String populatePersonQuery = "SELECT first_name, last_name"
			+ " FROM actor" + " WHERE id=?";

	/** Delete an actor from the database */
	private PreparedStatement deleteActorPS;
	private static final String deleteActorQuery = "DELETE FROM person WHERE id=?";

	/**
	 * The DAOs private constructor prepares the various, frequently used
	 * queries as Prepared Statements and otherwise initializes the state of the
	 * data access object.
	 */
	private H2ActorDAO() {
		conn = H2Util.getInstance().getConnection("mangotest");
		try {
			allActorsPS = conn.prepareStatement(allActorsQuery);
			updateActorPS = conn.prepareStatement(updateActorQuery);
			rolesForActorPS = conn.prepareStatement(rolesForActorQuery);
			addActorPS = conn.prepareStatement(addActorQuery);
			updateActorPS = conn.prepareStatement(updateActorQuery);
			populatePersonPS = conn.prepareStatement(populatePersonQuery);
			deleteActorPS = conn.prepareStatement(deleteActorQuery);
		} catch (SQLException ex) {
			// TODO; decide what to do here
			ex.printStackTrace();
		}

	}

	/**
	 * @return A singleton instance of the DAO.
	 */
	public static H2ActorDAO getInstance() {
		if (instance == null) {
			instance = new H2ActorDAO();
		}
		return instance;
	}

	/**
	 * @return A list of all the actors in the database.
	 */
	public List<Actor> getAllActors() {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		try {
			ResultSet results = allActorsPS.executeQuery();
			while (results.next()) {
				DBActor actor = new DBActor();
				actor.setId(results.getInt("id"));
				actor.setFirstName(results.getString("first_name"));
				actor.setLastName(results.getString("last_name"));
				actors.add(actor);
			}
		} catch (SQLException ex) {
			// TODO: decide what to do here
			ex.printStackTrace();
		}
		return actors;
	}

	/**
	 * Retrieve a list of all the roles for a specified actor.
	 * 
	 * @param a
	 *            The actor for which the roles are desired (TODO: this sentence
	 *            could be read the wrong way).
	 * @return a list of all the roles for the specified actor.
	 * @throws ClassCastException
	 *             if the Actor passed in is not a DBActor.
	 */
	public List<Role> getRolesForActor(Actor a) {
		if (!(a instanceof DBActor)) {
			throw new ClassCastException();
		}
		DBActor actor = (DBActor) a;

		ArrayList<Role> roles = new ArrayList<Role>();
		try {
			rolesForActorPS.setInt(0, actor.getId());
			ResultSet results = rolesForActorPS.executeQuery();
			while (results.next()) {
				DBRole role = new DBRole();
				role.setMovieId(results.getInt("movie_id"));
				role.setCharacter(results.getString("character"));
				role.setRole(results.getString("role"));
				roles.add(role);
			}
		} catch (SQLException ex) {
			// TODO: decide what to do here
			ex.printStackTrace();
		}
		return roles;
	}

	/**
	 * Add the specified actor to the database.
	 * 
	 * @param actor
	 *            The actor that should be added to the database.
	 */
	public void addActor(Actor actor) {
		try {
			addActorPS.setString(1, actor.getFirstName());
			addActorPS.setString(2, actor.getLastName());
			addActorPS.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Update the Actor with the data in the database, populating the object
	 * contents with the new database contents.
	 * 
	 * @param actor
	 *            The actor that should be updated from the db.
	 */
	public void populateActor(Actor a) {
		if (!(a instanceof DBActor)) {
			throw new ClassCastException();
		}
		DBActor actor = (DBActor) a;

		try {
			populatePersonPS.setInt(0, actor.getId());
			ResultSet results = populatePersonPS.executeQuery();
			results.next(); // move to first
			actor.setFirstName(results.getString("first_name"));
			actor.setLastName(results.getString("last_name"));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Update the specified database actor in the database with the information
	 * contained in the object.
	 * 
	 * @param a
	 *            The actor object containing the information that should be
	 *            added to the database.
	 * @throws ClassCastException
	 *             if the Actor passed in is not a DBActor.
	 */
	public void updateActor(Actor a) {
		if (!(a instanceof DBActor)) {
			throw new ClassCastException();
		}
		DBActor actor = (DBActor) a;

		try {
			updateActorPS.setString(0, actor.getFirstName());
			updateActorPS.setString(1, actor.getLastName());
			updateActorPS.setInt(3, actor.getId());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Delete the specified person from the database.
	 * 
	 * @param a
	 *            The actor to delete from the database.
	 * @throws ClassCastException
	 *             if the actor is not a DBActor.
	 */
	public void deleteActor(Actor a) {
		if (!(a instanceof DBActor)) {
			throw new ClassCastException();
		}
		DBActor actor = (DBActor) a;

		try {
			deleteActorPS.setInt(1, actor.getId());
			deleteActorPS.executeUpdate();
			deleteActorPS.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// TODO: Paul I need you to implement this method. It should
	// return all of the movies the actor passed was a part of.
	@Override
	public List<Movie> getMoviesForActor(Actor actor) {
		// TODO Auto-generated method stub
		return null;
	}

    // TODO: Paul I have some more work for you.  I don't know
    // if this is the best way to handle it, but it was the best
    // I could think of right now.
    public Actor getActorFromId(int actorID) {
        // TODO Auto-generated method stub
        return null;
    }

}
