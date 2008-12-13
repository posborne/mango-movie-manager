package com.themangoproject.model;

import java.awt.Image;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.swing.event.ChangeListener;

public interface MovieDAO {
	// getters
	public List<Movie> getAllMovies ();
    public Image getImageForMovie(Movie m);
    public List<String> getGenresForMovie (Movie movie);
    public List<Actor> getActorsForMovie (Movie movie);
    public void getMovieInfo(Movie movie);
        
    // actions
    public void updateMovie (Movie movie);
    public Movie addMovie (String title, String director, String rating, int runtime, int year, String asin, Date purchaseDate, String customDescription, String condition, String type, int mangoRating);
    public void deleteMovie (Movie movie) throws MovieDeleteConflict;
    public void forceDeleteMovie (Movie movie);
    public void removeGenreFromMovie(Movie movie, String genre);
    public void addActorToMovie(Movie movie, Actor actor, String role, String character);
    public void addGenreToMovie(Movie movie, String genre);
    public void setImageForMovie(InputStream is, Movie m);
    
    // listeners
    public void addMoviesChangeListener(ChangeListener l);
    public void removeMoviesChangeListener(ChangeListener l);
    public void removeAllMoviesChangeListeners();
}

