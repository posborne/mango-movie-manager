package com.themangoproject.ui.model;

import java.util.List;
import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;

public class SetEditableMovieTableModel extends EditableMovieTableModel {
	private static final long serialVersionUID = 8384297114625361568L;
	private List<Movie> movies;
	
	public SetEditableMovieTableModel(String label) {
		movies = MangoController.getInstance().getSetWithLabel(label);
	}

	@Override
	public List<Movie> getMovies() {
		return movies;
	}

}
