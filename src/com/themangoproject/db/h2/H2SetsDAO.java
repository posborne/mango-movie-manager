package com.themangoproject.db.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		"SELECT DISTINCT label FROM sets";
	
	private PreparedStatement moviesInSetPS;
	private static final String moviesInSetSQL =
		"SELECT movie_id FROM sets WHERE label=?";
	
	private PreparedStatement removeSetPS;
	private static final String removeSetSQL =
		"DELETE FROM sets WHERE label=?";
	
	private PreparedStatement removeMovieFromSetPS;
	private static final String removeMovieFromSetSQL =
		"DELETE FROM sets WHERE label=? AND movie_id=?";
	
	private PreparedStatement addMovieToSetPS;
	private static final String addMovieToSetSQL =
		"INSERT INTO sets (label, movie_id) " +
			"VALUES (?, ?)";
	
	private H2SetsDAO() {
		conn = H2Util.getInstance().getConnection();
		try {
			allSetsPS = conn.prepareStatement(allSetsSQL);
			moviesInSetPS = conn.prepareStatement(moviesInSetSQL);
			removeSetPS = conn.prepareStatement(removeSetSQL);
			removeMovieFromSetPS = conn.prepareStatement(removeMovieFromSetSQL);
			addMovieToSetPS = conn.prepareStatement(addMovieToSetSQL);
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
			removeSetPS.setString(1, label);
			removeSetPS.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
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
			addMovieToSetPS.setString(1, label);
			addMovieToSetPS.setInt(2, ((DBMovie)m).getId());
			addMovieToSetPS.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
}
