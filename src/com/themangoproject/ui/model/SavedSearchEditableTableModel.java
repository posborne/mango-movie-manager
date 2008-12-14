package com.themangoproject.ui.model;

import java.sql.SQLException;
import java.util.List;

import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;

public class SavedSearchEditableTableModel extends SearchEditableTableModel
	implements MangoTableModelIF {
	
	private static final long serialVersionUID = 3197501279105670669L;
	private String searchLabel;
	public SavedSearchEditableTableModel(String searchLabel) {
		this.searchLabel = searchLabel;
		retrieveMovies();
	}
	
	@Override
	public List<Movie> executeSearch() throws SQLException {
		System.out.println("Executing Search: " + searchLabel);
		return MangoController.getInstance().executeSavedSearch(searchLabel);
	}

}
