package com.themangoproject.db.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeListener;

import com.themangoproject.model.ListsDAO;
import com.themangoproject.model.Movie;

/**
 * @author Paul Osborne
 */
public class H2ListsDAO implements ListsDAO {

	private static H2ListsDAO instance;
	private Connection conn;

	private PreparedStatement allListsPS;
	private static final String allListsSQL = "SELECT label FROM lists";

	private PreparedStatement getMoviesInListPS;
	private static final String getMoviesInListSQL = "SELECT movie_id, order_id "
			+ "	FROM lists, list_contents "
			+ "	WHERE label=? AND list_id=id" + 
			"	ORDER BY order_id ASC";

	private PreparedStatement removeMovieFromListPS;
	private static final String removeMovieFromListSQL = "DELETE FROM list_contents"
			+ "	WHERE order_id=? AND list_id IN (SELECT id FROM lists WHERE label=?)";

	private PreparedStatement renameListPS;
	private static final String renameListSQL = "UPDATE lists SET label=? WHERE label=?";

	private PreparedStatement removeListFromListsPS;
	private static final String removeListFromListsSQL = "DELETE FROM lists WHERE label=?";

	private PreparedStatement removeListFromListContentsPS;
	private static final String removeListFromListContentsSQL = "DELETE FROM list_contents"
			+ "	WHERE list_id IN (SELECT id FROM lists WHERE label=?)";

	private PreparedStatement setItemOrderPS;
	private static final String setItemOrderSQL = 
		"UPDATE list_contents " +
		"	SET order_id=? " +
		"	WHERE list_id " +
		"		IN (SELECT id FROM lists WHERE label=?) " +
		"		AND order_id=?";

	private PreparedStatement addMovieToListPS;
	private static final String addMovieToListSQL = 
		"INSERT INTO list_contents (list_id, movie_id, order_id) "
			+ "		VALUES(?, ?, ?)";

	private PreparedStatement listIdForLabelPS;
	private static final String listIdForLabelSQL =
		"SELECT id FROM lists WHERE label=?";
	
	private PreparedStatement highestOrderInListPS;
	private static final String hightestOrderInListSQL = 
		"SELECT MAX(order_id) FROM list_contents " +
		"	WHERE list_id in (SELECT id FROM lists WHERE label=?)";

	private PreparedStatement addListPS;
	private static final String addListSQL = "INSERT INTO lists(label) VALUES (?)";

	/**
	 * Private constructor for the singleton instance.
	 */
	private H2ListsDAO() {
		conn = H2Util.getInstance().getConnection();
		listsChangeListeners = new ArrayList<ChangeListener>();
		try {
			allListsPS = conn.prepareStatement(allListsSQL);
			getMoviesInListPS = conn
					.prepareStatement(getMoviesInListSQL);
			removeMovieFromListPS = conn
					.prepareStatement(removeMovieFromListSQL);
			renameListPS = conn.prepareStatement(renameListSQL);
			removeListFromListsPS = conn
					.prepareStatement(removeListFromListsSQL);
			removeListFromListContentsPS = conn
					.prepareStatement(removeListFromListContentsSQL);
			setItemOrderPS = conn.prepareStatement(setItemOrderSQL);
			addMovieToListPS = conn.prepareStatement(addMovieToListSQL);
			highestOrderInListPS = conn
					.prepareStatement(hightestOrderInListSQL);
			addListPS = conn.prepareStatement(addListSQL);
			listIdForLabelPS = conn.prepareStatement(listIdForLabelSQL);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Get the static singleton instance.
	 * 
	 * @return the singleton H2ListsDAO.
	 */
	public static H2ListsDAO getInstance() {
		if (instance == null) {
			instance = new H2ListsDAO();
		}
		return instance;
	}

	/**
	 * Retrieve a list of all the lists in the database.
	 * 
	 * @return a list of all the lists in the database.
	 */
	@Override
	public List<String> getAllLists() {
		ArrayList<String> labels = new ArrayList<String>();
		try {
			ResultSet rs = allListsPS.executeQuery();
			while (rs.next()) {
				labels.add(rs.getString("label"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return labels;
	}

	/**
	 * Retrieve the list of movies in the list with the specified label.
	 * 
	 * @param label
	 *            The label of the list we are interested in.
	 * @return the list of all movies in the list with the specified label.
	 */
	@Override
	public List<Movie> getMoviesInList(String label) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			getMoviesInListPS.setString(1, label);
			ResultSet rs = getMoviesInListPS.executeQuery();
			while (rs.next()) {
				DBListMovie m = new DBListMovie();
				m.setId(rs.getInt("movie_id"));
				m.setOrderId(rs.getInt("order_id"));
				movies.add(m);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return movies;
	}

	/**
	 * Remove the movie from the list with the specified label.
	 * 
	 * @param label
	 *            Remove the list with the specified label from the database.
	 */
	@Override
	public void removeList(String label) {
		try {
			// Do this as a transaction
			conn.setAutoCommit(false);
			removeListFromListsPS.setString(1, label);
			removeListFromListContentsPS.setString(1, label);
			removeListFromListContentsPS.executeUpdate();
			removeListFromListsPS.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		notifyListsChanged();
	}

	/**
	 * Remove the movie from the specified list that
	 * 
	 * @param label
	 *            The label of the movie that should be removed.
	 * @param m
	 *            The movie that should be removed.
	 */
	@Override
	public void removeMovieFromList(String label, Movie m) {
		try {
			removeMovieFromListPS.setString(1, label);
			removeMovieFromListPS.setInt(2, ((DBListMovie) m)
					.getOrderId());
			removeMovieFromListPS.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Reorder the movies in the list in the database to match the order of the
	 * movies in the list passed in.
	 * 
	 * @param label
	 *            The label of the list that should be reordered.
	 * @param moviesInOrder
	 *            The new order for the movies.
	 */
	@Override
	public void reorderMoviesInList(String label,
			List<Movie> moviesInOrder) {
		try {
			// all these need to be done as a single transaction
			conn.setAutoCommit(false);
			int highest = getHighestOrderInList(label);
			for (int i = 0; i < moviesInOrder.size(); i++) {
				DBListMovie m = (DBListMovie) moviesInOrder.get(i);
				setItemOrderPS.setInt(1, highest + i + 1);
				setItemOrderPS.setString(2, label);
				setItemOrderPS.setInt(3, m.getOrderId());
				setItemOrderPS.executeUpdate();
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Add a new list with the specified label.
	 * 
	 * @param label
	 *            The label for the new list.
	 */
	public void addList(String label) {
		try {
			addListPS.setString(1, label);
			addListPS.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		notifyListsChanged();
	}

	/**
	 * Add the specified movie to the list with the specified label.
	 * 
	 * @param label
	 *            The label of the list the movie should be added to.
	 * @param m
	 *            The movie that should be added to the list.
	 */
	public void addMovieToList(String label, Movie m) {
		if (!(m instanceof DBMovie)) {
			throw new ClassCastException();
		}
		DBMovie movie = (DBMovie) m;
		boolean newList = (getHighestOrderInList(label) == 0);
		try {
			// TODO: some of this is moderately ugly
			listIdForLabelPS.setString(1, label);
			ResultSet rs = listIdForLabelPS.executeQuery();
			rs.first();
			int listId = rs.getInt("id");
			
			addMovieToListPS.setInt(1, listId); 
			addMovieToListPS.setInt(2, movie.getId());
			addMovieToListPS
					.setInt(3, getHighestOrderInList(label) + 1);
			addMovieToListPS.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		if (newList) {
			notifyListsChanged();
		}
	}

	/**
	 * Get the highest order element in the list.
	 * 
	 * @param label
	 *            The label of the list we are interested in.
	 * @return the highest order id for the list.
	 */
	private int getHighestOrderInList(String label) {
		int highest = 0;
		try {
			highestOrderInListPS.setString(1, label);
			ResultSet rs = highestOrderInListPS.executeQuery();
			if (rs.first()) {
				highest = rs.getInt(1);
			} else {
				highest = 0;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return highest;
	}

	/** List of change listeners to notify */
	private ArrayList<ChangeListener> listsChangeListeners;

	/**
	 * Add the specified change listener to the list that will be notified when
	 * a change to this list of sets occurs.
	 * 
	 * @param l
	 *            The change listener to add.
	 */
	@Override
	public void addListsChangeListener(ChangeListener l) {
		listsChangeListeners.add(l);
	}

	/**
	 * Remove the specified listener from the list of listeners.
	 * 
	 * @param l
	 *            The listener to remove
	 */
	@Override
	public void removeListsChangeListener(ChangeListener l) {
		listsChangeListeners.remove(l);
	}

	/**
	 * Notify all the list listeners that the set of lists have changed.
	 */
	private void notifyListsChanged() {
		for (int i = 0; i < listsChangeListeners.size(); i++) {
			ChangeListener l = listsChangeListeners.get(i);
			l.stateChanged(null); // what object should be passed?
		}
	}

}
