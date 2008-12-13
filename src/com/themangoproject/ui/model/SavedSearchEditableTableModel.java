package com.themangoproject.ui.model;

import java.sql.SQLException;
import java.util.List;

import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;

public class SavedSearchEditableTableModel extends SearchEditableTableModel {
	private static final long serialVersionUID = 3197501279105670669L;
	private String searchLabel;
	public SavedSearchEditableTableModel(String searchLabel) {
		this.searchLabel = searchLabel;
	}
	
	@Override
	public List<Movie> executeSearch() throws SQLException {
		return MangoController.getInstance().executeSavedSearch(searchLabel);
	}

}
