package com.themangoproject.model;

import java.util.List;

import javax.swing.event.ChangeListener;

public interface SetsDAO {
	public class MovieExistsInSetException extends Exception {
		private static final long serialVersionUID = 3695763528603583L;
	}

	public List<String> getAllSets();
	public List<Movie> getMoviesInSet(String label);
	public void removeSet(String label);
	public void removeMovieFromSet(String label, Movie m);
	public void addMovieToSet(String label, Movie m) throws MovieExistsInSetException;
	public void addSet(String label);
	public void addSetsChangeListener(ChangeListener changeListener);
	public void removeSetsChangeListener(ChangeListener changeListener);
	public void renameSet(String oldLabel, String newLabel);
}
