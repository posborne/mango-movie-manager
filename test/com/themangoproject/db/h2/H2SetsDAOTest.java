package com.themangoproject.db.h2;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.themangoproject.model.Movie;
import com.themangoproject.model.SetListAlreadyExistsException;

public class H2SetsDAOTest {

    @Before
    public void setUp() throws Exception {
        // Get everything primed and ready to roll
        H2Util.getInstance().setDatabaseLocation("~/mangotesting.db");
        H2Util.getInstance().initializeSchemaOnDb();
        TestUtility.executeInserts();
    }

    @Test
    public void testGetAllSets() {
        TestUtility.executeInserts();

        List<String> labels = H2SetsDAO.getInstance().getAllSets();
        assertTrue(labels.contains("Die Hard Series"));
        assertEquals(1, labels.size());
    }

    @Test
    public void testGetMoviesInSet() {
        TestUtility.executeInserts();

        List<Movie> movies = H2SetsDAO.getInstance().getMoviesInSet(
                "Die Hard Series");
        ArrayList<String> titles = new ArrayList<String>();
        for (Movie m : movies) {
            titles.add(m.getTitle());
        }
        assertEquals(2, movies.size());
        assertTrue(titles.contains("Die Hard"));
        assertTrue(titles.contains("Die Hard: With a Vengeance"));
    }

    @Test
    public void testRemoveSet() {
        TestUtility.executeInserts();

        H2SetsDAO.getInstance().removeSet("Die Hard Series");
        assertEquals(0, H2SetsDAO.getInstance().getAllSets().size());
    }

    @Test
    public void testRemoveMovieFromSet() {
        TestUtility.executeInserts();

        DBMovie dieHard = new DBMovie();
        dieHard.setId(1);
        H2SetsDAO.getInstance().removeMovieFromSet("Die Hard Series",
                dieHard);

        List<Movie> movies = H2SetsDAO.getInstance().getMoviesInSet(
                "Die Hard Series");
        assertEquals(1, movies.size());
        assertEquals("Die Hard: With a Vengeance", movies.get(0)
                .getTitle());
    }

    @Test
    public void testAddMovieToSet() {
        TestUtility.executeInserts();

        H2MovieDAO.getInstance()
                .addMovie("Gladiator", "Ridley Scott", "R", 155,
                        2000, null, new java.sql.Date(2002, 20, 12),
                        null, null, "DVD", 5);

        DBMovie glad = new DBMovie();
        glad.setId(3);
        DBMovie dh1 = new DBMovie();
        dh1.setId(1);
        DBMovie dh2 = new DBMovie();
        dh2.setId(2);
        try {
			H2SetsDAO.getInstance().addSet("Great Action Flicks");
		} catch (SetListAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
            H2SetsDAO.getInstance().addMovieToSet(
                    "Great Action Flicks", glad);
            H2SetsDAO.getInstance().addMovieToSet(
                    "Great Action Flicks", dh1);
            H2SetsDAO.getInstance().addMovieToSet(
                    "Great Action Flicks", dh2);
        } catch (Exception ex) {

        }

        List<Movie> movies = H2SetsDAO.getInstance().getMoviesInSet(
                "Great Action Flicks");
        ArrayList<String> titles = new ArrayList<String>();
        for (Movie m : movies) {
            titles.add(m.getTitle());
        }
        assertEquals(3, movies.size());
        assertTrue(titles.contains("Die Hard"));
        assertTrue(titles.contains("Die Hard: With a Vengeance"));
        assertTrue(titles.contains("Gladiator"));
    }

}
