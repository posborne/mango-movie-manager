package com.themangoproject.model;

import com.themangoproject.db.h2.H2Util;
import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test the H2Util class methods.
 * @author Paul Osborne
 */
public class H2UtilTest {

    public H2UtilTest() {
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
     * Test of getConnection method, of class H2Util.
     */
    @Test
    public void testGetConnection() {
        H2Util instance = H2Util.getInstance();
        Connection result = instance.getConnection();
        assertNotNull(result);
    }

}