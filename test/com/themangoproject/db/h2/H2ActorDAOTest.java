package com.themangoproject.db.h2;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.themangoproject.model.Actor;

public class H2ActorDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		H2ActorDAO dao = H2ActorDAO.getInstance();
		DBActor actor = new DBActor();
		actor.setFirstName("Bob");
		actor.setLastName("" + (Math.random() * 12000));
		dao.addActor(actor);
	}

	@Test
	public void testGetInstance() {
		H2ActorDAO dao = H2ActorDAO.getInstance();
		assertNotNull(dao);
	}

	@Test
	public void testGetAllActors() {
		H2ActorDAO dao = H2ActorDAO.getInstance();
		List<Actor> actors = dao.getAllActors();
		for (Actor actor : actors) {
			System.out.println(actor);
		}
		assertNotNull(actors);
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
