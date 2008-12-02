/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.themangoproject.db.h2;

import java.util.ArrayList;
import java.util.List;

import com.themangoproject.db.h2.DBPerson;
import com.themangoproject.model.Movie;
import com.themangoproject.model.Person;
import com.themangoproject.model.PersonExistsException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Paul Osborne
 */
public class H2PersonDAOTest {

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
    	p.setAddress("123 Screw you lane");
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
        H2PersonDAO.getInstance().populatePerson(p);
        p.setName("Some name that is not Paul");
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
    	fail("Not Implemented");
    }

    @Test  
    public void testGetAllPersons() {
    	fail("Not Implemented");
    }

    @Test
    public void testGetBorrowedMovies() {
    	fail("Not Implemented");
    }

    @Test
    public void testGetOwnedMovies() {
    	TestingSetupUtility.executeInserts();
    	
    	DBPerson paul = new DBPerson();
    	paul.setId(1);
    	H2PersonDAO.getInstance().populatePerson(paul);
    	List<Movie> paulsMovies = H2PersonDAO.getInstance().getOwnedMovies(paul);
    	ArrayList<String>  movieStrings = new ArrayList<String>();
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
    	
    	H2PersonDAO.getInstance().populatePerson(paul);
    	assertTrue(paul.getName().equals("Paul Osborne"));
    	assertTrue(paul.getAddress().equals("3001 Hampshire Ave N"));
    	assertTrue(paul.getEmail().equals("osbpau@bethel.edu"));
    	assertTrue(paul.getPhoneNumber().equals("7637970688"));
    }

    @Test
    public void testReturnMovie() {
    	fail("Not Implemented");
    }

}