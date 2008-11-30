package com.themangoproject.model;

import java.util.List;

public interface MovieDAO {

    public List<Movie> getAllMovies ();
    public void updateMovie (Movie movie);
    public void addMovie (Movie movie);
    public void deleteMovie (Movie movie);
    public List<String> getGenresForMovie (Movie movie);
    public List<Actor> getActorsForMovie (Movie movie);
    public void getMovieInfo(Movie movie);
    public void removeGenreFromMovie(Movie movie, String genre);
    public void addGenreToMovie(Movie movie, String genre);

}

