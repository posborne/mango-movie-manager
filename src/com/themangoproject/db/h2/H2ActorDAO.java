package com.themangoproject.db.h2;

import com.themangoproject.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeListener;

/**
 * The <code>H2ActorDAO</code> is an <code>ActorDAO</code> that
 * provides methods for communicating with the H2 database system,
 * specifically with regards to queries and updates that affect the
 * <code>Actor</code> business object.
 * 
 * The DAO is a singleton and obtains and manages its own database
 * connection.
 * 
 * @author Paul Osborne
 * @version November 28, 2008
 */
public class H2ActorDAO implements ActorDAO {

    /** Singleton instance of the H2ActorDAO */
    private static H2ActorDAO instance;

    /** List of change listeners */
    private ArrayList<ChangeListener> changeListeners;

    /** Database connection for the DAO */
    private Connection conn;

    /** Prepared statement for retrieving ids of all actors */
    private PreparedStatement allActorsPS;
    /** Retrieve ids and names of all actors */
    private static final String allActorsSQL = "SELECT id, first_name, last_name"
            + " FROM actor";

    /** Retrieve all roles for an actor */
    private PreparedStatement rolesForActorPS;
    private static final String rolesForActorSQL = "SELECT movie_id, role, character"
            + " FROM movie, acting_roles" + " WHERE actor_id=?";

    /** Add an actor to the database (no roles) */
    private PreparedStatement addActorPS;
    private static final String addActorSQL = "INSERT INTO actor (first_name, last_name)"
            + " VALUES(?, ?)";

    /** Update an actor in the database */
    private PreparedStatement updateActorPS;
    private static final String updateActorSQL = "UPDATE actor"
            + " SET first_name=?, last_name=?" + " WHERE id=?";

    /** Retrieve an actors information */
    private PreparedStatement populateActorPS;
    private static final String populateActorSQL = "SELECT first_name, last_name"
            + " FROM actor" + " WHERE id=?";

    /** Delete an actor from the database */
    private PreparedStatement deleteActorPS;
    private static final String deleteActorSQL = "DELETE FROM actor WHERE id=?";

    /** Delete acting roles with a give actor */
    private PreparedStatement deleteActingRolesWithActorPS;
    private static final String deleteActingRolesWithActorSQL = "DELETE FROM acting_roles"
            + " WHERE actor_id=?";

    /** SQL for the movies an actor has been in */
    private PreparedStatement moviesForActorPS;
    private static final String moviesForActorSQL = "SELECT movie_id FROM acting_roles WHERE actor_id=?";

    /**
     * Populate a role from the database based on the actor and movie
     * ids
     */
    private PreparedStatement populateRolePS;
    private static final String populateRoleSQL = "SELECT character, role FROM acting_roles WHERE movie_id=? AND actor_id=?";

    /**
     * The DAOs private constructor prepares the various, frequently
     * used queries as Prepared Statements and otherwise initializes
     * the state of the data access object.
     */
    private H2ActorDAO() {
        changeListeners = new ArrayList<ChangeListener>();
        conn = H2Util.getInstance().getConnection();
        try {
            allActorsPS = conn.prepareStatement(allActorsSQL);
            updateActorPS = conn.prepareStatement(updateActorSQL);
            rolesForActorPS = conn.prepareStatement(rolesForActorSQL);
            addActorPS = conn.prepareStatement(addActorSQL);
            updateActorPS = conn.prepareStatement(updateActorSQL);
            populateActorPS = conn.prepareStatement(populateActorSQL);
            deleteActorPS = conn.prepareStatement(deleteActorSQL);
            deleteActingRolesWithActorPS = conn
                    .prepareStatement(deleteActingRolesWithActorSQL);
            moviesForActorPS = conn
                    .prepareStatement(moviesForActorSQL);
            populateRolePS = conn.prepareStatement(populateRoleSQL);
        } catch (SQLException ex) {
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
     *            The actor for which the roles are desired (TODO:
     *            this sentence could be read the wrong way).
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
            rolesForActorPS.setInt(1, actor.getId());
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
     */
    public boolean addActor(String firstName, String lastName) {
        try {
            addActorPS.setString(1, firstName);
            addActorPS.setString(2, lastName);
            addActorPS.execute();
            fireActorsChangedEvent();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    /**
     * Update the Actor with the data in the database, populating the
     * object contents with the new database contents.
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
            populateActorPS.setInt(1, actor.getId());
            ResultSet results = populateActorPS.executeQuery();
            results.next(); // move to first
            actor.setFirstName(results.getString("first_name"));
            actor.setLastName(results.getString("last_name"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Update the specified database actor in the database with the
     * information contained in the object.
     * 
     * @param a
     *            The actor object containing the information that
     *            should be added to the database.
     * @throws ClassCastException
     *             if the Actor passed in is not a DBActor.
     */
    public void updateActor(Actor a) {
        if (!(a instanceof DBActor)) {
            throw new ClassCastException();
        }
        DBActor actor = (DBActor) a;

        try {
            updateActorPS.setString(1, actor.getFirstName());
            updateActorPS.setString(2, actor.getLastName());
            updateActorPS.setInt(3, actor.getId());
            updateActorPS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Delete the specified person from the database if there are no
     * relations in which the actor participates. If there are, throw
     * an exception noting that such is the case.
     * 
     * @param a
     *            The actor to delete from the database.
     * @throws ActorExistsInOtherRelationsException
     *             if the actor cannot be removed as a result of the
     *             actor existing in other relations (referential
     *             integrity constraint violation)
     * @throws ClassCastException
     *             if the actor is not a DBActor.
     */
    public void deleteActor(Actor a)
            throws ActorExistsInOtherRelationsException {
        if (!(a instanceof DBActor)) {
            throw new ClassCastException();
        }
        DBActor actor = (DBActor) a;

        try {
            deleteActorPS.setInt(1, actor.getId());
            deleteActorPS.executeUpdate();
            fireActorsChangedEvent();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 23003) {
                // Referential Integrity Constraint
                throw new ActorExistsInOtherRelationsException();
            }
            System.out.println("Error Code: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }

    /**
     * Delete the specified actor from the database in a forcible way.
     * Basically, remove any tuples from the database that have any
     * references to this particular actors. If you do not want this
     * behavior or at least want to be warned first with an exception
     * use deleteActor().
     * 
     * @param a
     *            The actor to remove from the database.
     */
    public void forceDeleteActor(Actor a) {
        if (!(a instanceof DBActor)) {
            throw new ClassCastException();
        }
        DBActor actor = (DBActor) a;

        try {
            // we need to use transactions here
            conn.setAutoCommit(false);
            deleteActingRolesWithActorPS.setInt(1, actor.getId());
            deleteActorPS.setInt(1, actor.getId());
            deleteActingRolesWithActorPS.executeUpdate();
            deleteActorPS.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception ex) {
            // TODO: handle this somehow
            ex.printStackTrace();
        }
    }

    /**
     * Retrieve a list of all the movies an actor has performed in.
     * 
     * @return A list of all the movies that the specified actor has
     *         acting roles in.
     */
    public List<Movie> getMoviesForActor(Actor a) {
        if (!(a instanceof DBActor)) {
            throw new ClassCastException();
        }
        DBActor actor = (DBActor) a;

        ArrayList<Movie> movies = new ArrayList<Movie>();
        try {
            moviesForActorPS.setInt(1, actor.getId());
            ResultSet rs = moviesForActorPS.executeQuery();
            while (rs.next()) {
                DBMovie m = new DBMovie();
                m.setId(rs.getInt("movie_id"));
                movies.add(m);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    /**
     * Populate the specified role. The role must be a DBRole and the
     * actor and movie ids must be set.
     * 
     * @param role
     *            The role which we would like to populate.
     * @throws RoleNotFoundException
     *             if the role actor and movie combination cannot be
     *             found in the database.
     * @throws ClassCastException
     *             if the the role is not a DBRole.
     */
    public void populateRole(Role role) throws RoleNotFoundException {
        if (!(role instanceof DBRole)) {
            throw new ClassCastException();
        }
        DBRole r = (DBRole) role;

        try {
            populateRolePS.setInt(1, r.getActorId());
            populateRolePS.setInt(2, r.getMovieId());
            ResultSet rs = populateRolePS.executeQuery();

            if (rs.first()) {
                r.setCharacter(rs.getString("character"));
                r.setRole(rs.getString("role"));
            } else {
                throw new RoleNotFoundException();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addActorsChangeListener(ChangeListener l) {
        changeListeners.add(l);
    }

    @Override
    public void removeActorsChangeListener(ChangeListener l) {
        changeListeners.add(l);
    }

    private void fireActorsChangedEvent() {
        for (ChangeListener l : changeListeners) {
            l.stateChanged(null);
        }
    }

}
