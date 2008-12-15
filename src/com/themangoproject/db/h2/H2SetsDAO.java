package com.themangoproject.db.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeListener;

import com.themangoproject.model.Movie;
import com.themangoproject.model.SetsDAO;

/**
 * The H2SetsDAO is an implementation of the SetsDAO specific to the H2
 * database. These methods are responsible for doing different database layer
 * interactions involving sets.
 * 
 * @author Paul Osborne
 */
public class H2SetsDAO implements SetsDAO {

	/** Singleton instance */
	private static H2SetsDAO instance;
	/** Database connection that will be used */
	private Connection conn;

	/** Retrieve labels of all sets */
	private PreparedStatement allSetsPS;
	private static final String allSetsSQL = "SELECT label FROM sets";

	/** Retrieve ids of all movies in a set */
	private PreparedStatement moviesInSetPS;
	private static final String moviesInSetSQL = "SELECT movie_id FROM set_contents WHERE "
			+ "    set_id IN (SELECT id FROM sets WHERE label=?)";

	/** Remove a set from the set_contents table */
	private PreparedStatement removeSetFromSetContentsPS;
	private static final String removeSetSQL = "DELETE FROM set_contents WHERE set_id IN (SELECT id FROM sets WHERE label=?)";

	/** Remove a set from the sets table */
	private PreparedStatement removeSetFromSetsPS;
	private static final String removeSetFromSetsSQL = "DELETE FROM sets WHERE label=?";

	/** Remove a specific movie from a list */
	private PreparedStatement removeMovieFromSetPS;
	private static final String removeMovieFromSetSQL = "DELETE FROM set_contents WHERE set_id IN (SELECT id FROM sets WHERE label=?) AND movie_id=?";

	/** Add a movie to a set */
	private PreparedStatement addMovieToSetPS;
	private static final String addMovieToSetSQL = "INSERT INTO set_contents (set_id, movie_id) "
			+ "	VALUES(?, ?)";

	/** Retrieve the set_id for a label */
	private PreparedStatement setIdForLabelPS;
	private static final String setIdForLabelSQL = "SELECT id FROM sets WHERE label=?";

	/** Add a new set to the list of sets */
	private PreparedStatement addSetPS;
	private static final String addSetSQL = "INSERT INTO sets(label) VALUES(?)";

	/** Rename a set */
	private PreparedStatement renameSetPS;
	private static final String renameSetSQL = "UPDATE sets SET label=? WHERE label=?";

	/**
	 * Private singleton constructor initializes different Prepared Statements
	 * and other state for the singleton.
	 */
	private H2SetsDAO() {
		conn = H2Util.getInstance().getConnection();
		setsChangeListeners = new ArrayList<ChangeListener>();
		try {
			allSetsPS = conn.prepareStatement(allSetsSQL);
			moviesInSetPS = conn.prepareStatement(moviesInSetSQL);
			removeSetFromSetContentsPS = conn.prepareStatement(removeSetSQL);
			removeSetFromSetsPS = conn.prepareStatement(removeSetFromSetsSQL);
			removeMovieFromSetPS = conn.prepareStatement(removeMovieFromSetSQL);
			addMovieToSetPS = conn.prepareStatement(addMovieToSetSQL);
			setIdForLabelPS = conn.prepareStatement(setIdForLabelSQL);
			addSetPS = conn.prepareStatement(addSetSQL);
			renameSetPS = conn.prepareStatement(renameSetSQL);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Get the singleton instance of the DAO.
	 * 
	 * @return the singleton instance of the H2SetsDAO.
	 */
	public static H2SetsDAO getInstance() {
		if (instance == null) {
			instance = new H2SetsDAO();
		}
		return instance;
	}

	/**
	 * Add a new set with the specified label to the database
	 * 
	 * @param label
	 *            The label for the new set.
	 */
	public void addSet(String label) {
		try {
			addSetPS.setString(1, label);
			addSetPS.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		fireSetsChangedEvent();
	}

	/**
	 * Get a list of all set labels in the database.
	 * 
	 * @return a list of all sets in the database.
	 */
	@Override
	public List<String> getAllSets() {
		ArrayList<String> sets = new ArrayList<String>();
		try {
			ResultSet rs = allSetsPS.executeQuery();
			while (rs.next()) {
				sets.add(rs.getString("label"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return sets;
	}

	/**
	 * Retrieve a list of all movies in the specified set.
	 * 
	 * @param label
	 *            The label of the set we are interested in.
	 * @return a list of all the movies in a set.
	 */
	@Override
	public List<Movie> getMoviesInSet(String label) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			moviesInSetPS.setString(1, label);
			ResultSet rs = moviesInSetPS.executeQuery();
			while (rs.next()) {
				DBMovie m = new DBMovie();
				m.setId(rs.getInt("movie_id"));
				movies.add(m);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return movies;
	}

	/**
	 * Remove the set with the specified label from the database.
	 * 
	 * @param label
	 *            The label of the set that should be removed.
	 */
	@Override
	public void removeSet(String label) {
		try {
			conn.setAutoCommit(false);
			removeSetFromSetContentsPS.setString(1, label);
			removeSetFromSetsPS.setString(1, label);
			removeSetFromSetContentsPS.executeUpdate();
			removeSetFromSetsPS.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		fireSetsChangedEvent();
	}

	/**
	 * Remove a specific movie from a set
	 * 
	 * @param m
	 *            The movie that should be removed.
	 * @param label
	 *            The set from which the movie should be removed.
	 */
	@Override
	public void removeMovieFromSet(String label, Movie m) {
		try {
			removeMovieFromSetPS.setString(1, label);
			removeMovieFromSetPS.setInt(2, ((DBMovie) m).getId());
			removeMovieFromSetPS.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Add the specified movie to the set with the specified label.
	 * 
	 * @param label
	 *            The label of the set we should add to.
	 * @param m
	 *            The movie that should be added.
	 */
	@Override
	public void addMovieToSet(String label, Movie m) throws MovieExistsInSetException {
		try {
			setIdForLabelPS.setString(1, label);
			ResultSet rs = setIdForLabelPS.executeQuery();
			rs.next();
			int setId = rs.getInt("id");

			addMovieToSetPS.setInt(1, setId);
			addMovieToSetPS.setInt(2, ((DBMovie) m).getId());
			addMovieToSetPS.executeUpdate();
		} catch (SQLException ex) {
			throw new MovieExistsInSetException();
		}
	}

	/** List of change listeners intersted in changes to list of sets */
	private ArrayList<ChangeListener> setsChangeListeners;

	/**
	 * Add a sets change listener
	 * 
	 * @param changeListener
	 *            The listener that should be added.
	 */
	@Override
	public void addSetsChangeListener(ChangeListener changeListener) {
		setsChangeListeners.add(changeListener);
	}

	/**
	 * Remove the specified changeListener from the list of listeners.
	 */
	@Override
	public void removeSetsChangeListener(ChangeListener changeListener) {
		setsChangeListeners.remove(changeListener);
	}

	/**
	 * Notify all listeners that there has been some change to the list of sets.
	 */
	private void fireSetsChangedEvent() {
		for (ChangeListener l : setsChangeListeners) {
			l.stateChanged(null);
		}
	}

	/**
	 * Rename a the set with oldLabel to have the label newLable
	 * 
	 * @param oldLabel
	 *            the old set label
	 * @param newLable
	 *            Thew new set label.
	 */
	public void renameSet(String oldLabel, String newLabel) {
		try {
			renameSetPS.setString(1, newLabel);
			renameSetPS.setString(2, oldLabel);
			renameSetPS.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
