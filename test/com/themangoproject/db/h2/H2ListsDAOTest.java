package com.themangoproject.db.h2;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import com.themangoproject.model.Movie;

public class H2ListsDAOTest {

	@Before
	public void setUp() throws Exception {
		// Get everything primed and ready to roll
		H2Util.getInstance().setDatabaseLocation("~/mangotesting.db");
		H2Util.getInstance().initializeSchemaOnDb();
		TestUtility.executeInserts();
	}

	@Test
	public void testGetAllLists() {
		TestUtility.executeInserts();
		
		List<String> lists = H2ListsDAO.getInstance().getAllLists();
		assertTrue(lists.size() == 1);
		assertTrue(lists.contains("Action Marathon"));
	}

	@Test
	public void testGetMoviesInList() {
		TestUtility.executeInserts();
		
		List<Movie> marathonMovies = H2ListsDAO.getInstance().getMoviesInList("Action Marathon");
		assertEquals("Die Hard", marathonMovies.get(0).getTitle());
		assertEquals("Die Hard: With a Vengeance", marathonMovies.get(1).getTitle());
	}

	@Test
	public void testRemoveList() {
		TestUtility.executeInserts();

		H2ListsDAO.getInstance().removeList("Action Marathon");
		assertTrue(H2ListsDAO.getInstance().getAllLists().size() == 0);
	}

	@Test
	public void testRemoveMovieFromList() {
		TestUtility.executeInserts();
		
		DBMovie dieHard = new DBMovie();
		dieHard.setId(1);
	}

	@Test
	public void testReorderMoviesInList() {
		TestUtility.executeInserts();
		
		// get the movies and reorder them
		List<Movie> movies = H2ListsDAO.getInstance().getMoviesInList("Action Marathon");
		ArrayList<Movie> newMoviesList = new ArrayList<Movie>();
		newMoviesList.add(movies.get(1));
		newMoviesList.add(movies.get(0));
		
		H2ListsDAO.getInstance().reorderMoviesInList("Action Marathon", newMoviesList);
		
		List<Movie> moviesAgain = H2ListsDAO.getInstance().getMoviesInList("Action Marathon");
		assertEquals("Die Hard: With a Vengeance", moviesAgain.get(0).getTitle());
		assertEquals("Die Hard", moviesAgain.get(1).getTitle());
	}

	@Test
	public void testAddMovieToList() {
		TestUtility.executeInserts();
		H2ListsDAO.getInstance().addMovieToList("Action Marathon", 
			H2ListsDAO.getInstance().getMoviesInList("Action Marathon").get(0));
		
		List<Movie> movies = H2ListsDAO.getInstance().getMoviesInList("Action Marathon");
		
		assertEquals(3, movies.size());
		assertEquals("Die Hard", movies.get(0).getTitle());
		assertEquals("Die Hard: With a Vengeance", movies.get(1).getTitle());
		assertEquals("Die Hard", movies.get(2).getTitle());
	}

}
