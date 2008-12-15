package com.themangoproject.ui.model;

import java.sql.SQLException;
import java.util.List;
import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;

/**
 * SavedSearchEditableTableModel is a table model for Saved Searches.
 * 
 * @author Paul Osborne
 */
public class SavedSearchEditableTableModel extends
        SearchEditableTableModel implements MangoTableModelIF {

    private static final long serialVersionUID = 3197501279105670669L;
    private String searchLabel;

    /**
     * Constructor
     * 
     * @param searchLabel
     *            The search label.
     */
    public SavedSearchEditableTableModel(String searchLabel) {
        this.searchLabel = searchLabel;
        retrieveMovies();
    }

    /**
     * Executes a saved search
     * 
     * @return Returns a list of movies
     * @throws SQLException
     *             If the search is bad
     */
    @Override
    public List<Movie> executeSearch() throws SQLException {
        System.out.println("Executing Search: " + searchLabel);
        return MangoController.getInstance().executeSavedSearch(
                searchLabel);
    }

}
