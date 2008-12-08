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
		TestingSetupUtility.executeInserts();
	}

	@Test
	public void testGetAllLists() {
		TestingSetupUtility.executeInserts();
		
		List<String> lists = H2ListsDAO.getInstance().getAllLists();
		assertTrue(lists.size() == 1);
		assertTrue(lists.contains("Action Marathon"));
	}

	@Test
	public void testGetMoviesInList() {
		TestingSetupUtility.executeInserts();
		
		List<Movie> marathonMovies = H2ListsDAO.getInstance().getMoviesInList("Action Marathon");
		ArrayList<String> movieTitles = new ArrayList<String>();
		for (Movie m : marathonMovies) {
			movieTitles.add(m.getTitle());
		}
		assertTrue(movieTitles.contains("Die Hard"));
		assertTrue(movieTitles.contains("Die Hard: With a Vengeance"));
	}

	@Test
	public void testRemoveList() {
		TestingSetupUtility.executeInserts();

		H2ListsDAO.getInstance().removeList("Action Marathon");
		assertTrue(H2ListsDAO.getInstance().getAllLists().size() == 0);
	}

	@Test
	public void testRemoveMovieFromList() {
		TestingSetupUtility.executeInserts();
		
		DBMovie dieHard = new DBMovie();
		dieHard.setId(1);
	}

	@Test
	public void testReorderMoviesInList() {
		TestingSetupUtility.executeInserts();
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddMovieToList() {
		TestingSetupUtility.executeInserts();
		fail("Not yet implemented"); // TODO
	}

}
