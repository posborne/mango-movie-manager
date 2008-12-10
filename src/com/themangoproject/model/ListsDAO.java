package com.themangoproject.model;

import java.util.List;

import javax.swing.event.ChangeListener;

public interface ListsDAO {
	public List<String> getAllLists();
	public List<Movie> getMoviesInList(String label);
	public void removeList(String label);
	public void removeMovieFromList(String label, Movie m);
	public void reorderMoviesInList(String label, List<Movie> moviesInOrder);
	public void addListsChangeListener(ChangeListener l);
	public void removeListsChangeListener(ChangeListener l);
	public void addList(String label);
}
