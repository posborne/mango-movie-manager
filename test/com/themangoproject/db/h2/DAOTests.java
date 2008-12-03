package com.themangoproject.db.h2;

import junit.framework.Test;
import junit.framework.TestSuite;

public class DAOTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.themangoproject.db.h2");
		//$JUnit-BEGIN$
		suite.addTestSuite(H2MovieDAOTest.class);
		suite.addTestSuite(H2PersonDAOTest.class);
		suite.addTestSuite(H2ActorDAOTest.class);
		//$JUnit-END$
		return suite;
	}

}
