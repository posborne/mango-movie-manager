package com.themangoproject.model;

import java.util.List;

import javax.swing.event.ChangeListener;

/**
 * This is an interface for a lists data access object (DAO). This is
 * an interface that provides methods to manipulate lists however they
 * are stored. A concrete implementation of this class would have
 * methods that are particular to the way the information is stored
 * (e.g. an H2 database, a derby databse, etc.)
 * 
 * @author Paul Osborne, Zachary Varberg
 */
public interface ListsDAO {

    /**
     * This will return a list of all the Lists in the storage
     * structure
     * 
     * @return a list of all the Lists in the storage structure
     */
    public List<String> getAllLists();

    /**
     * This will return all of the movies in a given list
     * 
     * @param label
     *            the name of the list to retrieve movies for
     * 
     * @return a list of the movies in the correct order for the list
     *         label
     */
    public List<Movie> getMoviesInList(String label);

    /**
     * This will remove a list from the storage structure
     * 
     * @param label
     *            the name of the list to be removed
     */
    public void removeList(String label);

    /**
     * This will remove a movie from a list
     * 
     * @param label
     *            the name of the list the movie is to be removed
     *            from.
     * @param m
     *            the movie to remove from the list label.
     */
    public void removeMovieFromList(String label, Movie m);

    /**
     * This will reorder the movies in a list
     * 
     * @param label
     *            the name of the list to be reordered
     * @param moviesInOrder
     *            all of the movies to be put into the list, in the
     *            new order they are to be saved in.
     */
    public void reorderMoviesInList(String label,
            List<Movie> moviesInOrder);

    /**
     * This will add a ListsChangeListener to this DAO
     * 
     * @param l
     *            the ListsChangeListener to be added
     */
    public void addListsChangeListener(ChangeListener l);

    /**
     * This will remove a ListsChangeListener from this DAO
     * 
     * @param l
     *            the ListsChangeListener to be removed
     */
    public void removeListsChangeListener(ChangeListener l);

    /**
     * This will add a new list to the storage structure
     * 
     * @param label
     *            the name of the list to add.
     */
    public void addList(String label);

    /**
     * This will add a movie to a list
     * 
     * @param label
     *            the name of the list to add a movie to.
     * @param m
     *            the movie to add to list label
     */
    public void addMovieToList(String label, Movie m);

    /**
     * This will rename a list in the storage structure.
     * 
     * @param oldLabel
     *            the old name of the list.
     * @param newLabel
     *            the name the list will be changed to.
     */
    public void renameList(String oldLabel, String newLabel);
}
