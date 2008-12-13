package com.themangoproject.ui.model;

import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;

public class ListEditableMovieTableModel extends EditableMovieTableModel {

	private static final long serialVersionUID = -2313799858998027018L;
	private List<Movie> movies;
	private ChangeListener moviesChangeListener;
	
	public ListEditableMovieTableModel(final String label) {
		movies = MangoController.getInstance().getListWithLabel(label);
		moviesChangeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				movies = MangoController.getInstance().getListWithLabel(label);
				fireTableStructureChanged();
			}
		};
		MangoController.getInstance().addMoviesChangeListener(moviesChangeListener);
	}
	
	@Override
	public List<Movie> getMovies() {
		return movies;
	}

	@Override
	public void cleanup() {
		MangoController.getInstance().removeListsChangeListener(moviesChangeListener);
	}

}
