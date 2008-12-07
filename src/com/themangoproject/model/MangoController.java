package com.themangoproject.model;

import com.themangoproject.db.h2.H2MovieDAO;
import com.themangoproject.db.h2.H2ActorDAO;
import com.themangoproject.db.h2.H2PersonDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is the controller. A class that has a number of methods useful for all
 * sorts of classes to have access too.
 * 
 * @author Paul Osborne, Zachary Varberg
 */
public class MangoController {

	/** Singleton controller instance */
	private static MangoController instance;
	/** PersonDAO that will be used */
	private PersonDAO personDAO;
	/** MovieDAO that will be used */
	private MovieDAO movieDAO;
	/** ActorDAO that will be used */
	private ActorDAO actorDAO;

	/**
	 * Instantiate the singleton instance of the Mango controller.
	 */
	private MangoController() {
		personDAO = H2PersonDAO.getInstance();
		movieDAO = H2MovieDAO.getInstance();
		actorDAO = H2ActorDAO.getInstance();
	}

	/**
	 * This will return the only instance of this controller.
	 * 
	 * @return the instance of this controller.
	 */
	public static final MangoController getInstance() {
		if (instance == null) {
			instance = new MangoController();
		}
		return instance;
	}

	/**
	 * This will add a movie and save it.
	 * 
	 * @param movie
	 *            the movie to be added
	 */
	public void addMovie(String title, String director, String rating,
			int runtime, int year, String asin, Date purchaseDate,
			String customDescription, String condition, String type,
			int mangoRating) {
		movieDAO.addMovie(title, director, rating, runtime, year, asin,
				purchaseDate, customDescription, condition, type, mangoRating);
	}

	/**
	 * This will add an actor and save it.
	 * 
	 * @param actor
	 *            the actor to be added
	 */
	public void addActor(String firstName, String lastName) {
		actorDAO.addActor(firstName, lastName);
	}

	/**
	 * This will save the changes made to this actor
	 * 
	 * @param actor
	 *            the actor that has changed and needs to be updated
	 */
	public void updateActor(Actor actor) {
		actorDAO.updateActor(actor);
	}

	/**
	 * This will save the changes made to this movie
	 * 
	 * @param movie
	 *            the movie that has changed and needs to be updated
	 */
	public void updateMovie(Movie movie) {
		this.movieDAO.updateMovie(movie);
	}

	/**
	 * This will return a list of all the people
	 * 
	 * @return a list of all the people
	 */
	public List<Person> getAllPersons() {
		return this.personDAO.getAllPersons();
	}

	/**
	 * This will return a list of all the actors
	 * 
	 * @return a list of all the actors
	 */
	public List<Actor> getAllActors() {
		return this.actorDAO.getAllActors();
	}

	/**
	 * This will return a list of all the movies
	 * 
	 * @return a list of all the movies
	 */
	public List<Movie> getAllMovies() {
		return this.movieDAO.getAllMovies();
	}

	/**
	 * Execute the specified that returns a movie result set.
	 * 
	 * @param query
	 *            The query that should be executed
	 * @return A list containing the movies matching the query
	 * @throws SQLException
	 *             if there is a problem with the query
	 */
	public List<Movie> executeMovieQuery(String query) throws SQLException {
		return null; // TODO: implement
	}
        
        /**
         * Return a list containing the labels of all sets in the database.
         */
        public List<String> getAllSets() {
           return new ArrayList<String>();
        }
        
        /**
         * Return a list containing the labels of all lists in the databse.
         */
        public List<String> getAllLists() {
            return new ArrayList<String>();
        }

}
