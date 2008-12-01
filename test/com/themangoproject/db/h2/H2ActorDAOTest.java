package com.themangoproject.db.h2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.extensions.TestSetup;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.themangoproject.model.Actor;
import com.themangoproject.model.ActorDAO;

public class H2ActorDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		H2Util.getInstance().initializeSchemaOnDb("mangotesting");
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
		TestingSetupUtility.executeInserts("mangotesting");
		ActorDAO dao = H2ActorDAO.getInstance();
		String correctActors[] = { "Willis, Bruce", "Bedalia, Bonnie",
				"Gleason, Paul", "Irons, Jeremy", "Jackson, Samuel L.",
				"Greene, Graham" };
		List<Actor> actors = dao.getAllActors();
		List<String> formattedNames = getActorListString(actors);
		System.out.println("Length: " + formattedNames.size() + " : " + formattedNames);
		for (int i = 0; i < correctActors.length; i++) {
			assertTrue(formattedNames.contains(correctActors[i]));
		}
	}

	@Test
	public void testGetRolesForActor() {

		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddActor() {
		// clear things out
		TestingSetupUtility.executeInserts("mangotesting");
		ActorDAO dao = H2ActorDAO.getInstance();
		
		// create test actor
		Actor testActor = new DBActor();
		testActor.setFirstName("Phil");
		testActor.setLastName("McTest");
		dao.addActor(testActor);
		
		// Is it in there?
		List<String> actorNames = getActorListString(dao.getAllActors());
		assertTrue(actorNames.contains("McTest, Phil"));
	}

	@Test
	public void testPopulateActor() {
		fail("Not implemented");
	}

	@Test
	public void testUpdateActor() {
		// clear things out
		TestingSetupUtility.executeInserts("mangotesting");
		ActorDAO dao = H2ActorDAO.getInstance();
		
		// We know that Bruce Willis is id 1
		DBActor bruce = new DBActor();
		bruce.setId(1);
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
		TestingSetupUtility.executeInserts("mangotesting");
		ActorDAO dao = H2ActorDAO.getInstance();
		
		// We know Bruce Willis is id 1
		DBActor bruce = new DBActor();
		bruce.setId(1);
		bruce.setFirstName("Bruce");
		bruce.setLastName("Willis");
		
		// Remove bruce
		dao.deleteActor(bruce);
		List<String> actorNames = getActorListString(dao.getAllActors());
		//System.out.println(actorNames);
		assertNull(actorNames.contains("Willis, Bruce"));
	}

}
