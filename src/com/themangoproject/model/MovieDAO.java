package com.themangoproject.model;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public interface MovieDAO {
    public List<Movie> getAllMovies ();
    public void updateMovie (Movie movie);
    public void addMovie (String title, String director, String rating, int runtime, int year, String asin, Date purchaseDate, String customDescription, String condition, String type, int mangoRating);
    public void deleteMovie (Movie movie) throws MovieDeleteConflict;
    public void forceDeleteMovie (Movie movie);
    public List<String> getGenresForMovie (Movie movie);
    public List<Actor> getActorsForMovie (Movie movie);
    public void getMovieInfo(Movie movie);
    public void removeGenreFromMovie(Movie movie, String genre);
    public void addGenreToMovie(Movie movie, String genre);
    public void setImageForMovie(InputStream is, Movie m);
}

