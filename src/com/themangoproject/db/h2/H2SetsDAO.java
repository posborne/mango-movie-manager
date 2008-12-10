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
 * @author Paul Osborne
 */
public class H2SetsDAO implements SetsDAO {

	private static H2SetsDAO instance;
	private Connection conn;
	
	private PreparedStatement allSetsPS;
	private static final String allSetsSQL =
		"SELECT label FROM sets";
	
	private PreparedStatement moviesInSetPS;
	private static final String moviesInSetSQL =
		"SELECT movie_id FROM set_contents WHERE " +
		"    set_id IN (SELECT id FROM sets WHERE label=?)";
	
	private PreparedStatement removeSetFromSetContentsPS;
	private static final String removeSetSQL =
		"DELETE FROM set_contents WHERE set_id IN (SELECT id FROM sets WHERE label=?)";
	
	private PreparedStatement removeSetFromSetsPS;
	private static final String removeSetFromSetsSQL =
		"DELETE FROM sets WHERE label=?";
	
	private PreparedStatement removeMovieFromSetPS;
	private static final String removeMovieFromSetSQL =
		"DELETE FROM set_contents WHERE set_id IN (SELECT id FROM sets WHERE label=?) AND movie_id=?";
	
	private PreparedStatement addMovieToSetPS;
	private static final String addMovieToSetSQL =
		"INSERT INTO set_contents (set_id, movie_id) " +
		"	VALUES(?, ?)";
	
	private PreparedStatement setIdForLabelPS;
	private static final String setIdForLabelSQL =
		"SELECT id FROM sets WHERE label=?";
	
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
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static H2SetsDAO getInstance() {
		if (instance == null) {
			instance = new H2SetsDAO();
		}
		return instance;
	}

	private PreparedStatement addSetPS;
	private static final String addSetSQL =
		"INSERT INTO sets(label) VALUES(?)";
	
	public void addSet(String label) {
		try {
			addSetPS.setString(1, label);
			addSetPS.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		fireSetsChangedEvent();
	}
	
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

	@Override
	public void removeMovieFromSet(String label, Movie m) {
		try {
			removeMovieFromSetPS.setString(1, label);
			removeMovieFromSetPS.setInt(2,((DBMovie)m).getId());
			removeMovieFromSetPS.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void addMovieToSet(String label, Movie m) {
		try {
			setIdForLabelPS.setString(1, label);
			ResultSet rs = setIdForLabelPS.executeQuery();
			rs.next();
			int setId = rs.getInt("id");

			addMovieToSetPS.setInt(1, setId);
			addMovieToSetPS.setInt(2, ((DBMovie)m).getId());
			addMovieToSetPS.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private ArrayList<ChangeListener> setsChangeListeners;
	
	@Override
	public void addSetsChangeListener(ChangeListener changeListener) {
		setsChangeListeners.add(changeListener);		
	}

	@Override
	public void removeSetsChangeListener(ChangeListener changeListener) {
		setsChangeListeners.remove(changeListener);
	}
	
	private void fireSetsChangedEvent() {
		for (ChangeListener l : setsChangeListeners) {
			l.stateChanged(null);
		}
	}
	
}
