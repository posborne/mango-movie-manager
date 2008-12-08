package com.themangoproject.ui.model;

import java.util.List;

import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;

public class ListEditableMovieTableModel extends EditableMovieTableModel {

	private static final long serialVersionUID = -2313799858998027018L;
	private List<Movie> movies;
	
	public ListEditableMovieTableModel(String label) {
		movies = MangoController.getInstance().getListWithLabel(label);
	}
	
	@Override
	public List<Movie> getMovies() {
		return movies;
	}

}
