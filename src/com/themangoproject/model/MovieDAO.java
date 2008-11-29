package com.themangoproject.model;

import java.util.List;

import com.themangoproject.db.h2.DBMovie;


public interface MovieDAO {

    public List<Movie> getAllMovies ();
    public void updateMovie (Movie movie);
    public void addMovie (Movie movie);
    public void deleteMovie (Movie movie);
    public List<String> getGenresForMovie (Movie movie);
    public List<Actor> getActorsForMovie (Movie movie);
    
    // Paul, I need this method.  It will take a Movie get the
    // most recent info from the DB and then update all the fields
    // in the movie passed
    public void getMovieInfo(Movie movie);
    // I also need this one.  It's pretty self explanatory
    public void removeGenreFromMovie(Movie movie, String genre);
    // This one as well.
    public void addGenreToMovie(DBMovie movie, String genre);

}

