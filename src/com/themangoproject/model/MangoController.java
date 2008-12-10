package com.themangoproject.model;

import com.themangoproject.db.h2.DBPerson;
import com.themangoproject.db.h2.H2ListsDAO;
import com.themangoproject.db.h2.H2MovieDAO;
import com.themangoproject.db.h2.H2ActorDAO;
import com.themangoproject.db.h2.H2PersonDAO;
import com.themangoproject.db.h2.H2SetsDAO;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.swing.event.ChangeListener;

/**
 * This is the controller. A class that has a number of methods useful for all
 * sorts of classes to have access too.
 * 
 * @author Paul Osborne, Zachary Varberg, Kyle Ronning
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
	/** ListsDAO that will be used */
	private ListsDAO listsDAO;
	/** SetsDAO that will be used */
	private SetsDAO setsDAO;

	/**
	 * Instantiate the singleton instance of the Mango controller.
	 */
	private MangoController() {
		personDAO = H2PersonDAO.getInstance();
		movieDAO = H2MovieDAO.getInstance();
		actorDAO = H2ActorDAO.getInstance();
		listsDAO = H2ListsDAO.getInstance();
		setsDAO = H2SetsDAO.getInstance();
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
         * This will add a new list with name <code>name</code>.
         * 
         * @param name The name of the list.
         */
    public void addList(String name) {
        listsDAO.addList(name);
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
         * This will set the image for this movie based on the inputstream
         * passed
         * 
         * @param m the movie with the new image
         * @param is and InputStream representing the movie to be added.
         */
        public void setImageForMovie(Movie m, InputStream is){
            this.movieDAO.setImageForMovie(is, m);
        }

        /**
         * This will add a new person.
         * 
         * @param name The name of the person.
         * @param phone The phone number of the person.
         * @param email The email of the person.
         * @param address The address of the person.
         * 
         * @throws PersonExistsException if this person already exists in the DB
         */
    public void addPerson(String name, String phone, String email, String address) throws PersonExistsException {
        Person person = new DBPerson();
        person.setName(name);
        person.setPhoneNumber(phone);
        person.setEmail(email);
        person.setAddress(address);
        personDAO.addPerson(person);
    }
    
    /**
     * This will take the person passed and update their information in the DB
     * 
     * @param p the person to be updated
     */
    public void updatePerson(Person p){
        this.personDAO.updatePerson(p);
    }

        /**
         * This will add a new set with name <code>name</code>.
         * @param name The name of the list.
         */
    public void addSet(String name) {
        setsDAO.addSet(name);
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
		return setsDAO.getAllSets();
	}

	/**
	 * Return a list containing the labels of all lists in the databse.
	 */
	public List<String> getAllLists() {
		return listsDAO.getAllLists();
	}

	/**
	 * Add a ChangeListener that should be notified when certain things occur.
	 * 
	 * @param l
	 *            The change listener that should be added.
	 */
	public void addListsChangeListener(ChangeListener l) {
		listsDAO.addListsChangeListener(l);
	}

	/**
	 * Remove the specified lists change listener.
	 * 
	 * @param l
	 */
	public void removeListsChangeListener(ChangeListener l) {
		listsDAO.removeListsChangeListener(l);
	}

	/**
	 * Retrieve a list of the list with the specified label.
	 * 
	 * @param label
	 *            The label of the list we are interested in.
	 * @return a list of all the movies in the list with the specified label.
	 */
	public List<Movie> getListWithLabel(String label) {
		return listsDAO.getMoviesInList(label);
	}

	/**
	 * Retrieve a list of the movies in the set with the specified label.
	 * 
	 * @param label
	 *            The label of the set we are interested in.
	 * @return a list of movies in the set.
	 */
	public List<Movie> getSetWithLabel(String label) {
		return setsDAO.getMoviesInSet(label);
	}

}
