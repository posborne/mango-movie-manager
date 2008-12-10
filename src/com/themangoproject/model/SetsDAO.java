package com.themangoproject.model;

import java.util.List;

public interface SetsDAO {
	public List<String> getAllSets();
	public List<Movie> getMoviesInSet(String label);
	public void removeSet(String label);
	public void removeMovieFromSet(String label, Movie m);
	public void addMovieToSet(String label, Movie m);
	public void createSet(String label);
	public void addSet(String label);
}
