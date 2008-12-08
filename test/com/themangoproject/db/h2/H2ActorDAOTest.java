package com.themangoproject.db.h2;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.themangoproject.model.Actor;
import com.themangoproject.model.ActorDAO;
import com.themangoproject.model.Movie;
import com.themangoproject.model.Role;

public class H2ActorDAOTest extends TestCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		H2Util.getInstance().setDatabaseLocation("~/mangotesting.db");
		H2Util.getInstance().initializeSchemaOnDb();
	}

	@Test
	public void testGetInstance() {
	}

	private String getActorString(Actor a) {
		return a.getLastName() + ", " + a.getFirstName();
	}
	
	private List<String> getActorListString(List<Actor> list) {
		ArrayList<String> strings = new ArrayList<String>();
		for (Actor a : list) {
			strings.add(getActorString(a));
		}
		return strings;
	}
	
	@Test
	public void testGetAllActors() {
		TestUtility.executeInserts();
		ActorDAO dao = H2ActorDAO.getInstance();
		String correctActors[] = { "Willis, Bruce", "Bedalia, Bonnie",
				"Gleason, Paul", "Irons, Jeremy", "Jackson, Samuel L.",
				"Greene, Graham" };
		List<Actor> actors = dao.getAllActors();
		List<String> formattedNames = getActorListString(actors);
		for (int i = 0; i < correctActors.length; i++) {
			assertTrue(formattedNames.contains(correctActors[i]));
		}
	}

	@Test
	public void testGetRolesForActor() {
		TestUtility.executeInserts();
		DBActor bruce = new DBActor();
		bruce.setId(1);
		
		// Get roles and format string
		List<Role> bruceRoles = H2ActorDAO.getInstance().getRolesForActor(bruce);
		ArrayList<String> resultantRolesText = new ArrayList<String>();
		for (int i = 0; i < bruceRoles.size(); i++) {
			DBRole r = (DBRole) bruceRoles.get(i);
			resultantRolesText.add("movie: " + r.getMovieId() +
					", role: " + r.getRole() + ", character: " + r.getCharacter());
		}
		
		// build values to test against
		String[] rolesTextList = {
				"movie: 1, role: Lead Character, character: Officer John McClane",
				"movie: 2, role: Lead Character, character: John McClane",
		};
		System.out.println(resultantRolesText);
		for (int i = 0; i < rolesTextList.length; i++) {
			assertTrue(resultantRolesText.contains(rolesTextList[i]));
		}
		
		
	}

	@Test
	public void testAddActor() {
		// clear things out
		TestUtility.executeInserts();
		
		ActorDAO dao = H2ActorDAO.getInstance();
		dao.addActor("Phil", "McTest");
		
		// Is it in there?
		List<String> actorNames = getActorListString(dao.getAllActors());
		assertTrue(actorNames.contains("McTest, Phil"));
	}

	@Test
	public void testPopulateActor() {
		// Clear things out
		TestUtility.executeInserts();
		
		// Bruce is known to have id 1
		DBActor bruce = new DBActor();
		bruce.setId(1);
		
		// Populate bruce
		H2ActorDAO.getInstance().populateActor(bruce);
		assertEquals(bruce.getFirstName(), "Bruce");
		assertEquals(bruce.getLastName(), "Willis");
	}

	@Test
	public void testUpdateActor() {
		// clear things out
		TestUtility.executeInserts();
		ActorDAO dao = H2ActorDAO.getInstance();
		
		// We know that Bruce Willis is id 1
		DBActor bruce = new DBActor();
		bruce.setId(1);
		bruce.getFirstName();
		bruce.setFirstName("Not Bruce");
		bruce.setLastName("Not Willis");
		
		// Do the update
		dao.updateActor(bruce);
		
		List<String> actorNames = getActorListString(dao.getAllActors());
		assertTrue(actorNames.contains("Not Willis, Not Bruce"));
	}

	@Test
	public void testDeleteActor() {
		// clear things out
		TestUtility.executeInserts();
		ActorDAO dao = H2ActorDAO.getInstance();
		
		// We know Bruce Willis is id 1
		DBActor bruce = new DBActor();
		bruce.setId(1);
		bruce.setFirstName("Bruce");
		bruce.setLastName("Willis");
		
		// Attempt to remove bruce without forcing
		boolean exceptionCaught = false;
		try {
			dao.deleteActor(bruce);
		} catch (ActorExistsInOtherRelationsException e) {
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
		
		// Force removal of bruce (sorry man, you've got to go)
		dao.forceDeleteActor(bruce);
		
		// Make sure bruce is gone for good.
		List<String> actorNames = getActorListString(dao.getAllActors());
		assertFalse(actorNames.contains("Willis, Bruce"));
	}

	@Test
	public void testGetMoviesForActor() {
		TestUtility.executeInserts();
		ActorDAO dao = H2ActorDAO.getInstance();
		
		// Bruce is id 1
		DBActor bruce = new DBActor();
		bruce.setId(1);
		
		// Get movies for Bruce
		List<Movie> movies = dao.getMoviesForActor(bruce);
		ArrayList<String> movieStrings = new ArrayList<String>();
		for (Movie m : movies) {
			movieStrings.add(m.getTitle());
		}
		
		assertTrue(movieStrings.contains("Die Hard"));
		assertTrue(movieStrings.contains("Die Hard: With a Vengeance"));
	}
	
}
