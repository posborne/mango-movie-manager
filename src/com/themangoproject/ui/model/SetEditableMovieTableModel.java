package com.themangoproject.ui.model;

import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;

public class SetEditableMovieTableModel extends EditableMovieTableModel {
	private static final long serialVersionUID = 8384297114625361568L;
	private List<Movie> movies;
	private ChangeListener moviesChangeListener;
	
	public SetEditableMovieTableModel(final String label) {
		movies = MangoController.getInstance().getSetWithLabel(label);
		moviesChangeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				movies = MangoController.getInstance().getSetWithLabel(label);
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
	public ChangeListener getMoviesChangeListener() {
		return moviesChangeListener;
	}

	@Override
	public void clenup() {
		MangoController.getInstance().removeSetsChangeListener(moviesChangeListener);
	}

}
