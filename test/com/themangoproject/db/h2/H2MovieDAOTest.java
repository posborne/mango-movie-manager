package com.themangoproject.db.h2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.themangoproject.model.Actor;
import com.themangoproject.model.Movie;
import com.themangoproject.model.MovieDeleteConflict;

public class H2MovieDAOTest extends TestCase {

	@Before
	public void setUp() throws Exception {
		H2Util.getInstance().setDatabaseLocation("~/mangotesting.db");
	}

	@Test
	public void testGetAllMovies() {
		TestUtility.executeInserts();
		List<Movie> movies = H2MovieDAO.getInstance().getAllMovies();
		ArrayList<String> titles = new ArrayList<String>();
		for (Movie m : movies) {
			H2MovieDAO.getInstance().getMovieInfo(m);
			titles.add(m.getTitle());
		}

		assertTrue(titles.contains("Die Hard"));
		assertTrue(titles.contains("Die Hard: With a Vengeance"));
	}

	@Test
	public void testUpdateMovie() {
		TestUtility.executeInserts();
		DBMovie dieHard = new DBMovie();
		dieHard.setId(1);
		dieHard.getTitle();
		dieHard.setTitle("Dead");
		H2MovieDAO.getInstance().updateMovie(dieHard);
		
		DBMovie dead = new DBMovie();
		dead.setId(1);
		H2MovieDAO.getInstance().getMovieInfo(dead);
		assertEquals(dead.getTitle(), "Dead");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testAddMovie() {
		TestUtility.executeInserts();
		
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
		
		List<Movie> movies = H2MovieDAO.getInstance().getAllMovies();
		ArrayList<String> titles = new ArrayList<String>();
		for (Movie m : movies) {
			titles.add(m.getTitle());
		}
		
		assertTrue(titles.contains("Gladiator"));
	}

	@Test
	public void testDeleteMovie() {
		TestUtility.executeInserts();
		
		DBMovie dieHard = new DBMovie();
		dieHard.setId(1);
		
		boolean exCaught = false;
		try {
			H2MovieDAO.getInstance().deleteMovie(dieHard);
		} catch (MovieDeleteConflict e) {
			exCaught = true;
		}
		assertTrue(exCaught);
		
		H2MovieDAO.getInstance().forceDeleteMovie(dieHard);
		
		List<Movie> movies = H2MovieDAO.getInstance().getAllMovies();
		assertTrue(movies.size() == 1);
		assertTrue(movies.get(0).getTitle().equals("Die Hard: With a Vengeance"));
	}

	@Test
	public void testGetGenresForMovie() {
		TestUtility.executeInserts();
		
		DBMovie dieHard = new DBMovie();
		dieHard.setId(1);
		
		List<String> genres = H2MovieDAO.getInstance().getGenresForMovie(dieHard);
		assertTrue(genres.contains("Action"));
		assertTrue(genres.contains("Thriller"));
		assertTrue(genres.size() == 2);
	}

	@Test
	public void testGetActorsForMovie() {
		TestUtility.executeInserts();
		
		DBMovie dieHard = new DBMovie();
		dieHard.setId(1);
		
		List<Actor> actors = H2MovieDAO.getInstance().getActorsForMovie(dieHard);
		String dieHardActors[] = {
				"Willis",
				"Bedalia",
				"Gleason"
		};
		
		ArrayList<String> lastNames = new ArrayList<String>();
		for (Actor a : actors) {
			lastNames.add(a.getLastName());
		}
		
		for (int i = 0; i < dieHardActors.length; i++) {
			assertTrue(lastNames.contains(dieHardActors[i]));
		}
	}

	@Test
	public void testGetMovieInfo() {
		TestUtility.executeInserts();
		
		DBMovie dieHard = new DBMovie();
		dieHard.setId(1);
		
		H2MovieDAO.getInstance().getMovieInfo(dieHard);
		
		assertEquals("John McTiernan", dieHard.getDirector());
		assertEquals("Die Hard", dieHard.getTitle());
		assertEquals("R", dieHard.getRating());
		assertEquals(131, dieHard.getRuntime());
		assertEquals(1988, dieHard.getYear());
		assertEquals("B000O77SRC", dieHard.getASIN());
		assertEquals(new GregorianCalendar(2003,11,23).getTime(),dieHard.getPurchaseDate());
		assertEquals("Yippe Ki Yay", dieHard.getCustomDescription());
		assertEquals("Good", dieHard.getCondition());
		assertEquals("DVD", dieHard.getType());
		assertEquals(4, dieHard.getMangoRating());
		assertTrue(dieHard.getOwner().getEmail().equals("osbpau@bethel.edu"));
		assertTrue(dieHard.getBorrower() == null);
	}

	@Test
	public void testAddGenreToMovie() {
		TestUtility.executeInserts();
		
		DBMovie dieHard = new DBMovie();
		dieHard.setId(1);
		
		H2MovieDAO.getInstance().addGenreToMovie(dieHard, "New Genre");
		List<String> genres = H2MovieDAO.getInstance().getGenresForMovie(dieHard);
		assertTrue(genres.contains("New Genre"));
	}

	@Test
	public void testRemoveGenreFromMovie() {
		TestUtility.executeInserts();
		
		DBMovie dieHard = new DBMovie();
		dieHard.setId(1);
		dieHard.getTitle();
		
		H2MovieDAO.getInstance().removeGenreFromMovie(dieHard, "Action");
		List<String> genres = H2MovieDAO.getInstance().getGenresForMovie(dieHard);
		
		assertTrue(!genres.contains("Action"));
	}
//
//	@Test
//	public void testGetMovieFromId() {
//		fail("Not yet implemented"); // TODO
//	}

}
