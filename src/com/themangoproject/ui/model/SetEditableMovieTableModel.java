package com.themangoproject.ui.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;

public class SetEditableMovieTableModel extends EditableMovieTableModel {
	private static final long serialVersionUID = 8384297114625361568L;
	private String label;
	private List<Movie> movies;
	private ChangeListener moviesChangeListener;
	private ChangeListener singleMovieChangeListener;
	
	public SetEditableMovieTableModel(final String label) {
		this.label = label;
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
		movies = MangoController.getInstance().getSetWithLabel(label);
		for (Movie m : movies) {
			m.addChangeListener(singleMovieChangeListener);
		}
	}

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
