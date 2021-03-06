package com.themangoproject.ui.model;

import java.sql.SQLException;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;

/**
 * UnsavedSearchEditabledTableModel is a table model for saved
 * searches that have not been saved yet. It helps provide the user
 * with an idea of what the search picked up.
 * 
 * @author Paul Osborne
 */
public class UnsavedSearchEditabledTableModel extends
        SearchEditableTableModel {
	
    private static final long serialVersionUID = -2108996136639230644L;
    private String searchQuery;
	private ChangeListener moviesChangeListener;

    /**
     * Constructs an unsaved saved search from a query
     * <code>query</code>
     * 
     * @param query
     *            The query to test.
     */
    public UnsavedSearchEditabledTableModel(String query) {
        this.searchQuery = query;
        moviesChangeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                retrieveMovies();
                fireTableStructureChanged();
            }
        };
        MangoController.getInstance().addMoviesChangeListener(moviesChangeListener);
        retrieveMovies();
    }

    /**
     * Executes the unsaved saved search.
     * 
     * @return A list of
     * @throws SQLException
     *             If the search is bad
     */
    @Override
    public List<Movie> executeSearch() throws SQLException {
        return MangoController.getInstance().executeSearch(
                searchQuery);
    }
}
