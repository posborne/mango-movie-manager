package com.themangoproject.model;

/**
 * This is an exception that happens when you try to delete a movie
 * that is being used in other relationships.
 * 
 * @author Zachary Varberg, Paul Osborne
 */
public class MovieDeleteConflict extends Exception {
    private static final long serialVersionUID = 7846240539373360962L;
}
