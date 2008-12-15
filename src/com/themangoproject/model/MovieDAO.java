package com.themangoproject.model;

import java.awt.Image;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.swing.event.ChangeListener;

/**
 * This is an interface for a movie data access object (DAO). This is
 * an interface that provides methods to manipulate movies however
 * they are stored. A concrete implementation of this class would have
 * methods that are particular to the way the information is stored
 * (e.g. an H2 database, a derby databse, etc.)
 * 
 * @author Paul Osborne, Zachary Varberg
 */
public interface MovieDAO {
    // getters
    /**
     * @return a List of all the Movies stored.
     */
    public List<Movie> getAllMovies();

    /**
     * This will return the image for a given movie.
     * 
     * @param m
     *            the movie to retrieve an image for.
     * @return the thumbnail Image associated with movie m.
     */
    public Image getImageForMovie(Movie m);

    /**
     * This will retur a list of all the genres for a given movie
     * 
     * @param movie
     *            the Movie to get genres for.
     * @return a List of Strings of the genres for movie movie
     */
    public List<String> getGenresForMovie(Movie movie);

    /**
     * This will return a list of all the actors that played a part in
     * this movie.
     * 
     * @param movie
     *            the movie to get the actors for.
     * @return a List of Actors who played a role in movie movie.
     */
    public List<Actor> getActorsForMovie(Movie movie);

    /**
     * This will update all the information in the Movie object
     * passed, to make sure it is current.
     * 
     * @param movie
     *            the Movie to get current info for.
     */
    public void getMovieInfo(Movie movie);

    // actions
    /**
     * This will updae all of the information in the storage structure
     * to match the what is in movie
     * 
     * @param movie
     *            the new information to be saved to the storage
     *            structure.
     */
    public void updateMovie(Movie movie);

    /**
     * This adds a movie to the database with the parameters passed.
     * 
     * @param title
     * @param director
     * @param rating
     * @param runtime
     * @param year
     * @param asin
     * @param purchaseDate
     * @param customDescription
     * @param condition
     * @param type
     * @param mangoRating
     * @return a Movie object with all the information passed.
     */
    public Movie addMovie(String title, String director,
            String rating, int runtime, int year, String asin,
            Date purchaseDate, String customDescription,
            String condition, String type, int mangoRating);

    /**
     * This will delete a movie from the storage structure if it is
     * not involved in any relatonships.
     * 
     * @param movie
     *            the movie to be deleted.
     * @throws com.themangoproject.model.MovieDeleteConflict
     *             if the movie is involved in other relationships.
     */
    public void deleteMovie(Movie movie) throws MovieDeleteConflict;

    /**
     * This will forcably delete a movie from the storage structure
     * and any relationships it is a part of.
     * 
     * @param movie
     *            the movie to be deleted.
     */
    public void forceDeleteMovie(Movie movie);

    /**
     * This will remove a genre from a movie.
     * 
     * @param movie
     *            the Movie to remove a genre from
     * @param genre
     *            the genre to be removed from Movie movie.
     */
    public void removeGenreFromMovie(Movie movie, String genre);

    /**
     * This will add an actor to this movie
     * 
     * @param movie
     *            the movie to add an actor to.
     * @param actor
     *            the actor to add.
     * @param role
     *            the role the actor had in the movie.
     * @param character
     *            the character the actor played in this movie.
     */
    public void addActorToMovie(Movie movie, Actor actor,
            String role, String character);

    /**
     * This will remove an actor from a movie.
     * 
     * @param movie
     *            the movie to remove an actor from
     * @param actor
     *            the actor to be removed from Movie movie.
     */
    public void removeActorFromMovie(Movie movie, Actor actor);

    /**
     * This wil add a genre to a movie.
     * 
     * @param movie
     *            the movie to add a genre to.
     * @param genre
     *            the genre to add to Movie movie.
     */
    public void addGenreToMovie(Movie movie, String genre);

    /**
     * This will set the thumbnail image for a movie
     * 
     * @param is
     *            the InputStream of an image for this movie
     * @param m
     *            the movie to add an image for.
     */
    public void setImageForMovie(InputStream is, Movie m);

    // listeners
    /**
     * This will add a MoviesChangeListener to this DAO.
     * 
     * @param l
     *            the MoviesChangeListener to add to this DAO.
     */
    public void addMoviesChangeListener(ChangeListener l);

    /**
     * This will remove a MoviesChangeListener from this DAO.
     * 
     * @param l
     *            the MoviesChangeListener to be removed.
     */
    public void removeMoviesChangeListener(ChangeListener l);

    /**
     * This will clear all MoviesChangeListeners from this DAO.
     */
    public void removeAllMoviesChangeListeners();
}
