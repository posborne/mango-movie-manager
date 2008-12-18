package com.themangoproject.webservice;

public class AmazonMovieUtility {
    
    /** Kyle's Associates ID */
    private String associatesID = "1SWY1ER59KJ70KP59TR2";

    /** Singleton instance for utility class */
    private static AmazonMovieUtility instance;

    /**
     * Instantiate the singleton instance of the movie utility.
     */
    private AmazonMovieUtility() {
    }

    /**
     * Get a reference to the singleton instance of the movie utility.
     * 
     * @return the AmazonMovieUtility
     */
    public static AmazonMovieUtility getInstance() {
        if (instance == null) {
            instance = new AmazonMovieUtility();
        }
        return instance;
    }

    /**
     * Get the associates id for the application. This is here so we do not need
     * to store it all over the place.
     * 
     * @return the associate id to use.
     */
    protected String getAssociatesID() {
        return associatesID;
    }
}
