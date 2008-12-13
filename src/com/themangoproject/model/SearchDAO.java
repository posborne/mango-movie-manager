package com.themangoproject.model;

import java.sql.SQLException;
import java.util.List;

import javax.swing.event.ChangeListener;

/**
 * DAO for doing search operations and dealing with saved searches.
 * 
 * @author Paul Osborne
 */
public interface SearchDAO {
	// /////////////////////////////////////////////////////
	// Movie retrieval methods
	// /////////////////////////////////////////////////////

	/**
	 * @return a list of all the movies that match the search query
	 * @throws SQLException
	 *             in the case that the search query is invalid.
	 */
	public List<Movie> executeSearch(String query) throws SQLException;

	/**
	 * Execute the saved search with the specified label.
	 * 
	 * @param searchLabel
	 *            The label of the saved search we want to execute
	 * @return a list of movies that match the search query.
	 * @throws SQLException
	 *             if there is a problem with the saved search execution
	 */
	public List<Movie> executeSavedSearch(String searchLabel)
			throws SQLException;

	// /////////////////////////////////////////////////////
	// Saved search maintenance
	// /////////////////////////////////////////////////////

	/**
	 * @return a list of all the labels of the saved searches in the database.
	 */
	public List<String> getAllSavedSearches();

	/**
	 * Save a search query associated with the given label.
	 * 
	 * @param searchLabel
	 *            The label of the saved search.
	 * @param query
	 *            The query to associate.
	 */
	public void saveSearch(String searchLabel, String query);

	/**
	 * Remove a saved search with the specified label.
	 * 
	 * @param searchLabel
	 *            The label of the saved search that should be removed.
	 */
	public void removeSavedSearch(String searchLabel);

	/**
	 * Rename the saved search with oldLabel to newLabel.
	 * 
	 * @param oldLabel
	 *            The old saved search label.
	 * @param newLabel
	 *            The new saved search label.
	 */
	public void renameSavedSearch(String oldLabel, String newLabel);

	/**
	 * Add the specified listener as a change listener for when saved searches
	 * are added or removed.
	 * 
	 * @param l
	 *            The change listener to add.
	 */
	public void addSavedSearchChangeListener(ChangeListener l);

	/**
	 * Remove the specified change listener from the DAO.
	 * 
	 * @param l
	 *            The change listener to remove.
	 */
	public void removeSavedSearchChangeListener(ChangeListener l);
}
