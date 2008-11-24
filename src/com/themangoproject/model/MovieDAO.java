package com.themangoproject.model;

import java.util.List;


public interface MovieDAO {

    public List<Movie> getAllMovies ();

    public Movie updateMovie (Movie movie);

    public Movie addMovie (Movie movie);

    public void deleteMovie (Movie movie);

    public List<String> getGenresForMovie (Movie movie);

    public List<Actor> getActorsForMovie (Movie movie);

}

