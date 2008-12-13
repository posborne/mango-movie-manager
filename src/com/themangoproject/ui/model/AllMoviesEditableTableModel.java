package com.themangoproject.ui.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;

/**
 * Editable table model for representing all movies in the database.
 * 
 * @author Paul Osborne
 */
public class AllMoviesEditableTableModel extends EditableMovieTableModel {
	/** Generated serial UID */
	private static final long serialVersionUID = 2646510614507126276L;
	/** List of all movies */
	private List<Movie> movies;
	/** Listener for changes to the set of all movies */
	ChangeListener moviesListener;
	/** Listener for changes to a particular movie */
	ChangeListener movieChangeListener;
	
	/**
	 * Constructor for the table.  Initialize the state of the model and add
	 * listeners for changes to the model underlying this model.
	 */
	public AllMoviesEditableTableModel() {
		movies = new ArrayList<Movie>();
		moviesListener = new AllMoviesChangeListener();
		movieChangeListener = new SingleMovieChangeListener();
		retrieveMovies();
		MangoController.getInstance().addMoviesChangeListener(moviesListener);
	}
	
	private void retrieveMovies() {
		// Remove movie listener from all movie instances
		for (Movie m : movies) {
			m.removeChangeListener(movieChangeListener);
		}
		
		// Retrieve movies and add change listener
		movies = MangoController.getInstance().getAllMovies();
		for (Movie m : movies) {
			m.addChangeListener(movieChangeListener);
		}
	}

	/**
	 * Return the list of all movies
	 */
	public List<Movie> getMovies() {
		return movies;
	}

	/**
	 * Cleanup before removing any reference to this table model.
	 */
	public void cleanup() {
		MangoController.getInstance().removeMoviesChangeListener(moviesListener);
		for (Movie m : movies) {
			m.removeChangeListener(movieChangeListener);
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Action Listeners
	///////////////////////////////////////////////////////////////////////////
	
	public class SingleMovieChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			AllMoviesEditableTableModel.this.fireTableDataChanged();
		}
	}
	
	public class AllMoviesChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			AllMoviesEditableTableModel.this.retrieveMovies();
			AllMoviesEditableTableModel.this.fireTableStructureChanged();
		}
	}
}
