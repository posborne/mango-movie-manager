package com.themangoproject.ui.model;

import java.util.List;
import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;

public class AllMoviesEditableTableModel extends EditableMovieTableModel {
	private static final long serialVersionUID = 2646510614507126276L;
	private final List<Movie> movies;
	
	public AllMoviesEditableTableModel() {
		movies = MangoController.getInstance().getAllMovies();
	}

	@Override
	public List<Movie> getMovies() {
		return movies;
	}
	
}
