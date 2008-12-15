package com.themangoproject.model;

import java.util.List;
import javax.swing.event.ChangeListener;

/**
 * SetsDAO is an interface which is a data access object for sets.
 * Sets are lists that do not contain duplicates. The SetsDAO provides
 * methods to access information from a DBMS. A concrete
 * implementation of this class will will implement these methods for
 * their specific database.
 * 
 * @author Zach Varberg, Kyle Ronning
 */
public interface SetsDAO {

    /**
     * MovieExistsInSetException is an exception that is thrown when a
     * set already contains the movie which is trying to be added.
     * 
     * @author Paul Osborne
     */
    public class MovieExistsInSetException extends Exception {
        private static final long serialVersionUID = 3695763528603583L;
    }

    /**
     * Gets all the sets in the database.
     * 
     * @return All the sets in the database.
     */
    public List<String> getAllSets();

    /**
     * Gets a list of movies that belong to a set with lable
     * <code>label</code>.
     * 
     * @param label
     *            The label of the set.
     * @return A list of movies.
     */
    public List<Movie> getMoviesInSet(String label);

    /**
     * Removes a set from the database that has the label
     * <code>label</code>.
     * 
     * @param label
     *            The label of the set to remove.
     */
    public void removeSet(String label);

    /**
     * Removes a movie <code>m</code> from a set with label
     * <code>label</code>.
     * 
     * @param label
     *            The label of the set.
     * @param m
     *            The movie to remove from the set <code>label</code>.
     */
    public void removeMovieFromSet(String label, Movie m);

    /**
     * Adds a movie <code>m</code> to a set <code>label</code>
     * 
     * @param label
     *            The label of the set.
     * @param m
     *            The movie to add to the set.
     * @throws MovieExistsInSetException
     *             If the set already contains the movie
     *             <code>m</code>.
     */
    public void addMovieToSet(String label, Movie m)
            throws MovieExistsInSetException;

    /**
     * Adds a new set to the database.
     * 
     * @param label
     *            The name of the set.
     */
    public void addSet(String label);

    /**
     * Adds a <code>ChangeListener</code>.
     * 
     * @param changeListener
     *            The listener.
     */
    public void addSetsChangeListener(ChangeListener changeListener);

    /**
     * Removes a <code>ChangeListener</code>.
     * 
     * @param changeListener
     *            The listener to remove.
     */
    public void removeSetsChangeListener(ChangeListener changeListener);

    /**
     * Rename a set with label <code>oldLabel</code> to be named
     * <code>newLabel
     * </code>
     * 
     * @param oldLabel
     *            The name of the old label
     * @param newLabel
     *            the name of the new label
     */
    public void renameSet(String oldLabel, String newLabel);
}
