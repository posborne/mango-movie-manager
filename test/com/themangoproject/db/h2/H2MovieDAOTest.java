package com.themangoproject.db.h2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.themangoproject.model.Movie;

public class H2MovieDAOTest extends TestCase {

	@Before
	public void setUp() throws Exception {
		H2Util.getInstance().setDatabaseLocation("~/mangotesting.db");
	}

	@Test
	public void testGetAllMovies() {
		TestingSetupUtility.executeInserts();
		List<Movie> movies = H2MovieDAO.getInstance().getAllMovies();
		ArrayList<String> titles = new ArrayList<String>();
		for (Movie m : movies) {
			H2MovieDAO.getInstance().getMovieInfo(m);
			titles.add(m.getTitle());
		}

		System.out.println(titles);
		assertTrue(titles.contains("Die Hard"));
		assertTrue(titles.contains("Die Hard: With a Vengeance"));
	}

	@Test
	public void testUpdateMovie() {
		TestingSetupUtility.executeInserts();
		DBMovie dieHard = new DBMovie();
		dieHard.setId(1);
		dieHard.getTitle();
		dieHard.setTitle("Dead");
		H2MovieDAO.getInstance().updateMovie(dieHard);
		
		DBMovie dead = new DBMovie();
		dead.setId(1);
		H2MovieDAO.getInstance().getMovieInfo(dead);
		System.out.println(dead.getTitle());
		assertEquals(dead.getTitle(), "Dead");
	}

	@Test
	public void testAddMovie() {
		TestingSetupUtility.executeInserts();
		
		DBMovie newMovie = new DBMovie();
		newMovie.setDirector("Ridley Scott");
		newMovie.setTitle("Gladiator");
		newMovie.setType("DVD");
		newMovie.setASIN("B00009ZYBY");
		newMovie.setCondition("Pretty Decent");
		newMovie.setRuntime(155);
		
		H2MovieDAO.getInstance().addMovie(
				"Gladiator", "Ridley Scott", "R", 155, 
				2000, null, new java.sql.Date(2002, 20, 12), null, 
				null, "DVD", 5);
		
	}

	@Test
	public void testDeleteMovie() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetGenresForMovie() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetActorsForMovie() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetMovieInfo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddGenreToMovie() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemoveGenreFromMovie() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetMovieFromId() {
		fail("Not yet implemented"); // TODO
	}

}
