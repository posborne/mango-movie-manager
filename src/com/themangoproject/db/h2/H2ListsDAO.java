package com.themangoproject.db.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.themangoproject.model.ListsDAO;
import com.themangoproject.model.Movie;

/**
 * @author Paul Osborne
 */
public class H2ListsDAO implements ListsDAO {

	private static H2ListsDAO instance;
	private Connection conn;
	
	private PreparedStatement allListsPS;
	private static final String allListsSQL =
		"SELECT DISTINCT label FROM lists ORDER BY label";
	
	private PreparedStatement getMoviesInListPS;
	private static final String getMoviesInListSQL =
		"SELECT movie_id, order_id " +
		"FROM lists " +
		"WHERE label=? " +
		"ORDER BY order_id";
	
	private PreparedStatement removeMovieFromListPS;
	private static final String removeMovieFromListSQL = 
		"DELETE FROM lists WHERE label=? AND movie_id=?";
	
	private PreparedStatement renameListPS;
	private static final String renameListSQL =
		"UPDATE lists SET label=? WHERE label=?";
	
	private PreparedStatement removeListPS;
	private static final String removeListSQL =
		"DELETE FROM lists WHERE label=?";
	
	private PreparedStatement setItemOrderPS;
	private static final String setItemOrderSQL =
		"UPDATE lists SET order_id=? WHERE label=? AND order_id=?";
	
	private PreparedStatement addMovieToListPS;
	private static final String addMovieToListSQL = 
		"INSERT INTO lists (label, movie_id, order_id) " +
			"VALUES(?, ?, ?)";
	
	private PreparedStatement highestOrderInListPS;
	private static final String hightestOrderInListSQL =
		"SELECT MAX(order_id) FROM lists WHERE label=?";
	
	private H2ListsDAO() {
		conn = H2Util.getInstance().getConnection();
		try {
			allListsPS = conn.prepareStatement(allListsSQL);
			getMoviesInListPS = conn.prepareStatement(getMoviesInListSQL);
			removeMovieFromListPS = conn.prepareStatement(removeMovieFromListSQL);
			renameListPS = conn.prepareStatement(renameListSQL);
			removeListPS = conn.prepareStatement(removeListSQL);
			setItemOrderPS = conn.prepareStatement(setItemOrderSQL);
			addMovieToListPS = conn.prepareStatement(addMovieToListSQL);
			highestOrderInListPS = conn.prepareStatement(hightestOrderInListSQL);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static H2ListsDAO getInstance() {
		if (instance == null) {
			instance = new H2ListsDAO();
		}
		return instance;
	}

	/**
	 * 
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

	@Override
	public List<Movie> getMoviesInList(String label) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			getMoviesInListPS.setString(1, label);
			ResultSet rs = getMoviesInListPS.executeQuery();
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
	public void removeList(String label) {
		try {
			removeListPS.setString(1, label);
			removeListPS.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void removeMovieFromList(String label, int inOrderPosition) {
		try {
			removeMovieFromListPS.setString(1, label);
			removeMovieFromListPS.setInt(2, inOrderPosition);
			removeMovieFromListPS.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void reorderMoviesInList(String label, List<Movie> moviesInOrder) {
		try {
			// all these need to be done as a single transaction
			conn.setAutoCommit(false);
			for (int i = 0; i < moviesInOrder.size(); i++) {
				DBMovie m = (DBMovie) moviesInOrder.get(i);
				setItemOrderPS.setInt(1, i + 1);
				setItemOrderPS.setString(2, label);
				// TODO: how do we get the old order?
				// what is here is only temporary...
				setItemOrderPS.setInt(3, i + 1); 
				setItemOrderPS.executeUpdate();
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void addMovieToList(String label, Movie m) {
		if (!(m instanceof DBMovie)) {
			throw new ClassCastException();
		}
		DBMovie movie = (DBMovie) m;
		
		try {
			addMovieToListPS.setString(1, label);
			addMovieToListPS.setInt(2, movie.getId());
			addMovieToListPS.setInt(3, getHighestOrderInList(label) + 1);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
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
	
}
