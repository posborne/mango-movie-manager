package com.themangoproject.db.h2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.themangoproject.model.Actor;
import com.themangoproject.model.ActorDAO;
import com.themangoproject.model.DAOFactory;

public class H2ActorDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetInstance() {
	}

	@Test
	public void testGetAllActors() {
		TestingSetupUtility.executeInserts();
		ActorDAO dao = H2ActorDAO.getInstance();

		String correctActors[] = { "Willis, Bruce", "Bonnie, Bedalia",
				"Gleason, Paul", "Irons, Jeremy", "Jackson, Samuel L.",
				"Green, Graham" };
		
		ArrayList<String> formattedNames = new ArrayList<String>();

		List<Actor> actors = dao.getAllActors();
		for (Actor a : actors) {
			formattedNames.add(a.getLastName() + ", " + a.getFirstName());
		}
		
		System.out.println("Length: " + formattedNames.size() + " : " + formattedNames);
		for (int i = 0; i < correctActors.length; i++) {
			System.out.println(correctActors[i]);
			assertTrue(formattedNames.contains(correctActors[i]));
		}
	}

	@Test
	public void testGetRolesForActor() {

		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddActor() {

		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testPopulateActor() {

		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUpdateActor() {

		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDeleteActor() {

		fail("Not yet implemented"); // TODO
	}

}
