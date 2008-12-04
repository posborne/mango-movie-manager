/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.themangoproject.db.h2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import com.themangoproject.db.h2.DBPerson;
import com.themangoproject.model.Movie;
import com.themangoproject.model.Person;
import com.themangoproject.model.PersonExistsException;
import com.themangoproject.model.PersonNotFoundException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author Paul Osborne
 */
public class H2PersonDAOTest extends TestCase {

	public H2PersonDAOTest() {
	}

	@Before
	public void setUp() {
		// Get everything primed and ready to roll
		H2Util.getInstance().setDatabaseLocation("~/mangotesting.db");
		H2Util.getInstance().initializeSchemaOnDb();
		TestingSetupUtility.executeInserts();
	}

	/**
	 * Test of addPerson method, of class H2PersonDAO.
	 */
	@Test
	public void testAddPerson() {
		TestingSetupUtility.executeInserts();

		DBPerson p = new DBPerson();
		p.setName("Bob Billy McTest");
		p.setEmail("bob@junit.org");
		p.setAddress("123 the lane");
		p.setPhoneNumber("952.883.3918");

		try {
			H2PersonDAO.getInstance().addPerson(p);
		} catch (PersonExistsException pee) {
			fail("Why was this thrown?");
		}

		List<Person> people = H2PersonDAO.getInstance().getAllPersons();
		ArrayList<String> names = new ArrayList<String>();
		for (Person person : people) {
			names.add(person.getName());
		}

		assertTrue(names.contains("Bob Billy McTest"));
	}

	/**
	 * Test of updatePerson method, of class H2PersonDAO.
	 */
	@Test
	public void testUpdatePerson() {
		TestingSetupUtility.executeInserts();

		DBPerson p = new DBPerson();
		p.setId(1); // Paul is person 1
		p.setName("Some name that is not Paul");
		p.setEmail("testemail@email.com");
		p.setAddress("3899 drive");
		p.setPhoneNumber("38828810083");
		H2PersonDAO.getInstance().updatePerson(p);

		List<Person> people = H2PersonDAO.getInstance().getAllPersons();
		ArrayList<String> names = new ArrayList<String>();
		for (Person person : people) {
			names.add(person.getName());
		}

		assertTrue(names.contains("Some name that is not Paul"));
	}

	@Test
	public void testBorrowMovie() {
		TestingSetupUtility.executeInserts();

		// setup people and movie
		DBPerson kyle = new DBPerson();
		kyle.setId(3);
		DBMovie dieHard = new DBMovie();
		dieHard.setId(1);

		// borrow die hard to kyle
		H2PersonDAO.getInstance().borrowMovie(kyle, dieHard);
		H2MovieDAO.getInstance().updateMovie(dieHard);

		assertTrue(((DBPerson) dieHard.getBorrower()).getId() == kyle.getId());
	}

	@Test
	public void testGetAllPersons() {
		TestingSetupUtility.executeInserts();

		List<Person> people = H2PersonDAO.getInstance().getAllPersons();
		String[] names = { "Paul Osborne", "Zachary Varberg", "Kyle Ronning" };
		List<String> namesList = Arrays.asList(names);
		for (Person p : people) {
			assertTrue(namesList.contains(p.getName()));
		}
	}

	@Test
	public void testGetBorrowedMovies() {
		TestingSetupUtility.executeInserts();

		DBPerson zach = new DBPerson();
		zach.setId(2);
		List<Movie> movies = H2PersonDAO.getInstance().getBorrowedMovies(zach);
		assertTrue(movies.size() == 1);
		assertTrue(movies.get(0).getTitle()
				.equals("Die Hard: With a Vengeance"));
	}

	@Test
	public void testGetOwnedMovies() {
		TestingSetupUtility.executeInserts();

		DBPerson paul = new DBPerson();
		paul.setId(1);
		try {
			H2PersonDAO.getInstance().populatePerson(paul);
		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Movie> paulsMovies = H2PersonDAO.getInstance()
				.getOwnedMovies(paul);
		ArrayList<String> movieStrings = new ArrayList<String>();
		for (Movie m : paulsMovies) {
			movieStrings.add(m.getTitle());
		}

		assertTrue(movieStrings.contains("Die Hard"));
		assertTrue(movieStrings.contains("Die Hard: With a Vengeance"));
	}

	@Test
	public void testPopulatePerson() {
		TestingSetupUtility.executeInserts();

		// Paul is id 1
		DBPerson paul = new DBPerson();
		paul.setId(1);

		try {
			H2PersonDAO.getInstance().populatePerson(paul);
		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(paul.getName().equals("Paul Osborne"));
		assertTrue(paul.getAddress().equals("3001 Hampshire Ave N"));
		assertTrue(paul.getEmail().equals("osbpau@bethel.edu"));
		assertTrue(paul.getPhoneNumber().equals("7637970688"));
	}

	@Test
	public void testReturnMovie() {
		// TODO: things still need to be sorted out with how borrower
		// ids are handles here and there.
		TestingSetupUtility.executeInserts();

		DBMovie dieHard = new DBMovie();
		dieHard.setId(1);
		dieHard.getTitle();
		H2PersonDAO.getInstance().returnMovie(dieHard);
		H2MovieDAO.getInstance().getMovieInfo(dieHard);
		assertTrue(dieHard.getBorrower() == null);
	}

}