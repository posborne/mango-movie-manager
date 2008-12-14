package com.themangoproject.model;

import com.themangoproject.db.h2.DBPerson;
import com.themangoproject.db.h2.H2ListsDAO;
import com.themangoproject.db.h2.H2MovieDAO;
import com.themangoproject.db.h2.H2ActorDAO;
import com.themangoproject.db.h2.H2PersonDAO;
import com.themangoproject.db.h2.H2SearchDAO;
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
	/** Search DAO that will be used */
	private SearchDAO searchDAO;

	/**
	 * Instantiate the singleton instance of the Mango controller.
	 */
	private MangoController() {
		personDAO = H2PersonDAO.getInstance();
		movieDAO = H2MovieDAO.getInstance();
		actorDAO = H2ActorDAO.getInstance();
		listsDAO = H2ListsDAO.getInstance();
		setsDAO = H2SetsDAO.getInstance();
		searchDAO = H2SearchDAO.getInstance();
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
	public Movie addMovie(String title, String director, String rating,
			int runtime, int year, String asin, Date purchaseDate,
			String customDescription, String condition, String type,
			int mangoRating) {
		return movieDAO.addMovie(title, director, rating, runtime, year, asin,
				purchaseDate, customDescription, condition, type, mangoRating);
	}

	/**
	 * This will add an actor and save it.
	 * 
	 * @param actor
	 *            the actor to be added
	 */
	public boolean addActor(String firstName, String lastName) {
		return actorDAO.addActor(firstName, lastName);
	}
        
                
        /**
         * Adds an actor to a movie <code>movie</code> with the role and
         * character that actor played.
         * 
         * @param movie The movie the actor should be added to.
         * @param actor The actor to add to the movie.
         * @param role The role the actor plays in the movie.
         * @param character The character the actor plays in the movie.
         */
        public void addActorToMovie(Movie movie, Actor actor, String role, String character) {
            movieDAO.addActorToMovie(movie, actor, role, character);
        }
        
        /**
         * Removes an actor from a movie <code>movie</code>.
         * 
         * @param movie The movie to remove the actor from.
         * @param actor The actor to remove from the movie.
         */
        public void removeActorFromMovie(Movie movie, Actor actor) {
            movieDAO.removeActorFromMovie(movie, actor);
        }
        /**
         * Gets all the actors for a given movie <code>m</code>.
         * 
         * @param m The movie to get actors for.
         * @return A list of actors for a given movie.
         */
        public List<Actor> getActorsForMovie(Movie m) {
            return movieDAO.getActorsForMovie(m);
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

	public void addSetsChangeListener(ChangeListener changeListener) {
		setsDAO.addSetsChangeListener(changeListener);
	}
	
	public void removeSetsChangeListener(ChangeListener changeListener) {
		setsDAO.removeSetsChangeListener(changeListener);
	}

	public void removeMoviesChangeListener(ChangeListener l) {
		movieDAO.removeMoviesChangeListener(l);
	}
	
	public void clearAllMoviesChangeListener() {
		movieDAO.removeAllMoviesChangeListeners();
	}
	
	public void addMoviesChangeListener(ChangeListener l) {
		movieDAO.addMoviesChangeListener(l);
	}

	public void deleteMovie(Movie m) throws MovieDeleteConflict {
		movieDAO.deleteMovie(m);
	}
	
	public void forceDeleteMovie(Movie m) {
		movieDAO.forceDeleteMovie(m);
	}

	public void addMovieToSet(String label, Movie m) {
		setsDAO.addMovieToSet(label, m);
	}

	public void addMovieToList(String label, Movie m) {
		listsDAO.addMovieToList(label, m);
	}

	public void deleteSet(String setLabel) {
		setsDAO.removeSet(setLabel);
	}

	/**
	 * @param listLabel The label of the list to remove.
	 */
	public void deleteList(String listLabel) {
		listsDAO.removeList(listLabel);
	}

	/**
	 * @param setLabel
	 * @param newName
	 */
	public void renameSet(String oldLabel, String newLabel) {
		setsDAO.renameSet(oldLabel, newLabel);
	}

	/**
	 * @param listLabel
	 * @param newName
	 */
	public void renameList(String oldLabel, String newLabel) {
		listsDAO.renameList(oldLabel, newLabel);
	}
	
	public List<Movie> executeSearch(String query) throws SQLException {
		return searchDAO.executeSearch(query);
	}
	
	public List<Movie> executeSavedSearch(String searchLabel) 
		throws SQLException {
		return searchDAO.executeSavedSearch(searchLabel);
	}
	
	public void saveSearch(String searchLabel, String query) {
		searchDAO.saveSearch(searchLabel, query);
	}
	
	public void removeSavedSearch(String searchLabel) {
		searchDAO.removeSavedSearch(searchLabel);
	}
	
	public void addSaveSearchListener(ChangeListener l) {
		searchDAO.addSavedSearchChangeListener(l);
	}
	
	public void removeSavedSearchListener(ChangeListener l) {
		searchDAO.removeSavedSearchChangeListener(l);
	}
	
	public List<String> getAllSavedSearches() {
		return searchDAO.getAllSavedSearches();
	}
        
    public void addGenreToMovie(Movie m, String s) {
        m.addGenre(s);
    }
        
    public void removeGenreFromMovie(Movie m, String s) {
        m.removeGenre(s);
    }
    
    public void removeMovieFromSet(String label, Movie m) {
    	setsDAO.removeMovieFromSet(label, m);
    }
        
        
        public void setOwnerToMovie(Movie m, Person o) {
            m.setOwner(o);
        }
        
        public void setBorrowerToMovie(Movie m, Person b) {
            m.setBorrower(b);
        }

		public void removeMovieFromList(String label, Movie m) {
			listsDAO.removeMovieFromList(label, m);
		}
}
