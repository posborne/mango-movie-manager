package com.themangoproject.model;

import java.util.List;

import javax.swing.event.ChangeListener;

public interface SetsDAO {
	public List<String> getAllSets();
	public List<Movie> getMoviesInSet(String label);
	public void removeSet(String label);
	public void removeMovieFromSet(String label, Movie m);
	public void addMovieToSet(String label, Movie m);
	public void addSet(String label);
	public void addSetsChangeListener(ChangeListener changeListener);
	public void removeSetsChangeListener(ChangeListener changeListener);
}
