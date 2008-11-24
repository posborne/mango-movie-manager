/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.themangoproject.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author osbpau
 */
public class H2PersonDAOTest {

    public H2PersonDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addPerson method, of class H2PersonDAO.
     */
    @Test
    public void testAddPerson() {
        Person person = new DBPerson();
        H2PersonDAO instance = new H2PersonDAO();
        instance.addPerson("bob", "3001 Hello Lane, St. Paul, MN 55112", Long.valueOf("9528187258"), "paul@posborne.net");
    }

    /**
     * Test of updatePerson method, of class H2PersonDAO.
     */
    @Test
    public void testUpdatePerson() {
        System.out.println("updatePerson");
        Person person = null;
        H2PersonDAO instance = new H2PersonDAO();
        instance.updatePerson(person);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Unnamed method, of class H2PersonDAO.
     */
    @Test
    public void testUnnamed() {
        System.out.println("Unnamed");
        H2PersonDAO instance = new H2PersonDAO();
        instance.Unnamed();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}