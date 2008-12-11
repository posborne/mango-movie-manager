package com.themangoproject.ui.model;

import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;

public class AllMoviesEditableTableModel extends EditableMovieTableModel {
	private static final long serialVersionUID = 2646510614507126276L;
	private List<Movie> movies;
	ChangeListener moviesListener;
	
	public AllMoviesEditableTableModel() {
		movies = MangoController.getInstance().getAllMovies();
		moviesListener = new AllMoviesChangeListener();
		MangoController.getInstance().addMoviesChangeListener(moviesListener);
	}

	@Override
	public List<Movie> getMovies() {
		return movies;
	}

	@Override
	public ChangeListener getMoviesChangeListener() {
		return moviesListener;
	}
	
	public class AllMoviesChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			AllMoviesEditableTableModel.this.movies = MangoController.getInstance().getAllMovies();
			AllMoviesEditableTableModel.this.fireTableStructureChanged();
		}
	}
}
