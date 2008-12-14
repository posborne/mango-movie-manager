package com.themangoproject.ui.model;

import java.sql.SQLException;
import java.util.List;

import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;

public class UnsavedSearchEditabledTableModel extends SearchEditableTableModel {
	private static final long serialVersionUID = -2108996136639230644L;
	private String searchQuery;
	public UnsavedSearchEditabledTableModel(String query) {
		this.searchQuery = query;
		retrieveMovies();
	}
	@Override
	public List<Movie> executeSearch() throws SQLException {
		return MangoController.getInstance().executeSearch(searchQuery);
	}
}
