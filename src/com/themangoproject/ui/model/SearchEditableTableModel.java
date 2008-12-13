package com.themangoproject.ui.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;

public abstract class SearchEditableTableModel extends EditableMovieTableModel implements
		MangoTableModelIF {

	/** Serial UID */
	private static final long serialVersionUID = -3378374611042190618L;
	private List<Movie> movies;
	private ChangeListener moviesChangeListener;
	private ChangeListener singleMovieChangeListener;
	
	public SearchEditableTableModel() {
		movies = new ArrayList<Movie>();
		moviesChangeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				retrieveMovies();
				fireTableStructureChanged();
			}
		};
		singleMovieChangeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				fireTableDataChanged();
			}
		};
		MangoController.getInstance().addMoviesChangeListener(moviesChangeListener);
		retrieveMovies();
	}
	
	private void retrieveMovies() {
		// remove change listeners from old movies
		for (Movie m : movies) {
			m.removeChangeListener(singleMovieChangeListener);
		}
		// add change listeners to new movies
		try {
			movies = executeSearch();
			for (Movie m : movies) {
				m.addChangeListener(singleMovieChangeListener);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public abstract List<Movie> executeSearch() throws SQLException;

	@Override
	public List<Movie> getMovies() {
		return movies;
	}

	@Override
	public void cleanup() {
		MangoController.getInstance().removeSetsChangeListener(moviesChangeListener);
		for (Movie m : movies) {
			m.removeChangeListener(singleMovieChangeListener);
		}
	}

}
