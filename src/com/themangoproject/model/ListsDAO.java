package com.themangoproject.model;

import java.util.List;

public interface ListsDAO {
	public List<String> getAllLists();
	public List<Movie> getMoviesInSet(String label);
	public void removeList(String label);
	public void removeMovieFromList(String label, int inOrderPosition);
	public void reorderMoviesInList(String label, List<Movie> moviesInOrder);
}
