package com.themangoproject.model;

import com.themangoproject.db.h2.ActorExistsInOtherRelationsException;
import com.themangoproject.db.h2.DBPerson;
import com.themangoproject.db.h2.H2ListsDAO;
import com.themangoproject.db.h2.H2MovieDAO;
import com.themangoproject.db.h2.H2ActorDAO;
import com.themangoproject.db.h2.H2PersonDAO;
import com.themangoproject.db.h2.H2SearchDAO;
import com.themangoproject.db.h2.H2SetsDAO;
import com.themangoproject.model.PersonDAO.PersonHasMoviesException;
import com.themangoproject.model.SetsDAO.MovieExistsInSetException;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.swing.event.ChangeListener;

/**
 * This is the controller. A class that has a number of methods useful
 * for all sorts of classes to have access too.
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
     * @param name
     *            The name of the list.
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
    public Movie addMovie(String title, String director,
            String rating, int runtime, int year, String asin,
            Date purchaseDate, String customDescription,
            String condition, String type, int mangoRating) {
        return movieDAO.addMovie(title, director, rating, runtime,
                year, asin, purchaseDate, customDescription,
                condition, type, mangoRating);
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
     * @param movie
     *            The movie the actor should be added to.
     * @param actor
     *            The actor to add to the movie.
     * @param role
     *            The role the actor plays in the movie.
     * @param character
     *            The character the actor plays in the movie.
     */
    public void addActorToMovie(Movie movie, Actor actor,
            String role, String character) {
        movieDAO.addActorToMovie(movie, actor, role, character);
    }

    //TODO: I need this method.
    public void deleteRolesForMovie(Movie m) {
        this.movieDAO.removeAllRolesForMovie(m);
    }

    public Object getPersonFromId(int ownerId) {
        return this.personDAO.getPersonFromId(ownerId);
    }

    /**
     * Removes an actor from a movie <code>movie</code>.
     * 
     * @param movie
     *            The movie to remove the actor from.
     * @param actor
     *            The actor to remove from the movie.
     */
    public void removeActorFromMovie(Movie movie, Actor actor) {
        movieDAO.removeActorFromMovie(movie, actor);
    }

    /**
     * Gets all the actors for a given movie <code>m</code>.
     * 
     * @param m
     *            The movie to get actors for.
     * @return A list of actors for a given movie.
     */
    public List<Actor> getActorsForMovie(Movie m) {
        return movieDAO.getActorsForMovie(m);
    }

    /**
     * This will set the image for this movie based on the inputstream
     * passed
     * 
     * @param m
     *            the movie with the new image
     * @param is
     *            and InputStream representing the movie to be added.
     */
    public void setImageForMovie(Movie m, InputStream is) {
        this.movieDAO.setImageForMovie(is, m);
    }

    /**
     * This will add a new person.
     * 
     * @param name
     *            The name of the person.
     * @param phone
     *            The phone number of the person.
     * @param email
     *            The email of the person.
     * @param address
     *            The address of the person.
     * 
     * @throws PersonExistsException
     *             if this person already exists in the DB
     */
    public boolean addPerson(String name, String phone, String email,
            String address) throws PersonExistsException {
        Person person = new DBPerson();
        person.setName(name);
        person.setPhoneNumber(phone);
        person.setEmail(email);
        person.setAddress(address);
        return personDAO.addPerson(person);
    }

    /**
     * This will take the person passed and update their information
     * in the DB
     * 
     * @param p
     *            the person to be updated
     */
    public boolean updatePerson(Person p) {
        return this.personDAO.updatePerson(p);
    }

    /**
     * This will add a new set with name <code>name</code>.
     * 
     * @param name
     *            The name of the list.
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
     * This will returnl a list of all the people
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
     * Return a list containing the labels of all sets in the
     * database.
     */
    public List<String> getAllSets() {
        return setsDAO.getAllSets();
    }

    /**
     * Return a list containing the labels of all lists in the
     * databse.
     */
    public List<String> getAllLists() {
        return listsDAO.getAllLists();
    }

    /**
     * Add a ChangeListener that should be notified when certain
     * things occur.
     * 
     * @param l
     *            The change listener that should be added.
     */
    public void addListsChangeListener(ChangeListener l) {
        listsDAO.addListsChangeListener(l);
    }

    /**
     * l Remove the specified lists change listener.
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
     * @return a list of all the movies in the list with the specified
     *         label.
     */
    public List<Movie> getListWithLabel(String label) {
        return listsDAO.getMoviesInList(label);
    }

    /**
     * Retrieve a list of the movies in the set with the specified
     * label.
     * 
     * @param label
     *            The label of the set we are interested in.
     * @return a list of movies in the set.
     */
    public List<Movie> getSetWithLabel(String label) {
        return setsDAO.getMoviesInSet(label);
    }

    /**
     * This will add a SetsChangeListener where necessary, to receive
     * needed events.
     * 
     * @param changeListener
     *            the SetsChangeListener to be added
     */
    public void addSetsChangeListener(ChangeListener changeListener) {
        setsDAO.addSetsChangeListener(changeListener);
    }

    /**
     * This will remove a SetsChangeListener where necessary
     * 
     * @param changeListener
     *            the SetsChangeListener to be removed
     */
    public void removeSetsChangeListener(ChangeListener changeListener) {
        setsDAO.removeSetsChangeListener(changeListener);
    }

    /**
     * This will remove a MoviesChangeListener where necessary.
     * 
     * @param l
     *            the MoviesChangeListener to be removed.
     */
    public void removeMoviesChangeListener(ChangeListener l) {
        movieDAO.removeMoviesChangeListener(l);
    }

    /**
     * This will remove all MoviesChangeListener where necessary.
     * 
     */
    public void clearAllMoviesChangeListener() {
        movieDAO.removeAllMoviesChangeListeners();
    }

    /**
     * This will add a MoviesChangeListener where necessary, to
     * receive needed events.
     * 
     * @param l
     *            the MoviesChangeListener to be added
     */
    public void addMoviesChangeListener(ChangeListener l) {
        movieDAO.addMoviesChangeListener(l);
    }

    /**
     * This will delete a movie from the model, if it only exists as a
     * movie and is a part of no other relations.
     * 
     * @param m
     *            the movie to be deleted
     * @throws com.themangoproject.model.MovieDeleteConflict
     *             if this movie is involved in other relationships.
     */
    public void deleteMovie(Movie m) throws MovieDeleteConflict {
        movieDAO.deleteMovie(m);
    }

    /**
     * This will delete a movie from the model, including any
     * relations it is a part of.
     * 
     * @param m
     *            the movie to be deleted.
     */
    public void forceDeleteMovie(Movie m) {
        movieDAO.forceDeleteMovie(m);
    }

    /**
     * This will add a movie to a set.
     * 
     * @param label
     *            the name of the set to add the movie to.
     * @param m
     *            the movie to be added to set label.
     * @throws com.themangoproject.model.SetsDAO.MovieExistsInSetException
     *             if movie m already exists in set label (sets do not
     *             allow duplicate movies).
     */
    public void addMovieToSet(String label, Movie m)
            throws MovieExistsInSetException {
        setsDAO.addMovieToSet(label, m);
    }

    /**
     * This will add a movie to a list.
     * 
     * @param label
     *            the name of the list to add the movie to.
     * @param m
     *            the movie to be added to list label.
     */
    public void addMovieToList(String label, Movie m) {
        listsDAO.addMovieToList(label, m);
    }

    /**
     * This will delete a set from the model.
     * 
     * @param setLabel
     *            the name of the set to be deleted.
     */
    public void deleteSet(String setLabel) {
        setsDAO.removeSet(setLabel);
    }

    /**
     * This wil delete a list from the model
     * 
     * @param listLabel
     *            The label of the list to remove.
     */
    public void deleteList(String listLabel) {
        listsDAO.removeList(listLabel);
    }

    /**
     * This will rename a set.
     * 
     * @param setLabel
     *            the old name of the set.
     * @param newName
     *            the new name of the set.
     */
    public void renameSet(String oldLabel, String newLabel) {
        setsDAO.renameSet(oldLabel, newLabel);
    }

    /**
     * This will rename a list.
     * 
     * @param listLabel
     *            the old name of the list
     * @param newName
     *            the new name of the list
     */
    public void renameList(String oldLabel, String newLabel) {
        listsDAO.renameList(oldLabel, newLabel);
    }

    /**
     * this will execute a search on the database.
     * 
     * @param query
     *            a String representing a query for the database.
     * @return a list of Movies matching the query
     * @throws java.sql.SQLException
     *             if there was if there was an error executing the
     *             SQL in query
     */
    public List<Movie> executeSearch(String query)
            throws SQLException {
        return searchDAO.executeSearch(query);
    }

    /**
     * This will execute a search that has previously been saved.
     * 
     * @param searchLabel
     *            the name of the search to execute
     * @return a list of Movies mathcing the query
     * @throws java.sql.SQLException
     *             if there was an error executing the SQL in query
     */
    public List<Movie> executeSavedSearch(String searchLabel)
            throws SQLException {
        return searchDAO.executeSavedSearch(searchLabel);
    }

    /**
     * This will save a search.
     * 
     * @param searchLabel
     *            the name to save the search under.
     * @param query
     *            the SQL query to execute for this search.
     */
    public void saveSearch(String searchLabel, String query) {
        searchDAO.saveSearch(searchLabel, query);
    }

    /**
     * This will delete a saved search from the model.
     * 
     * @param searchLabel
     *            the name of the search to delete
     */
    public void removeSavedSearch(String searchLabel) {
        searchDAO.removeSavedSearch(searchLabel);
    }

    /**
     * This will add a SaveSearchListener where necessary, to receive
     * needed events.
     * 
     * @param l
     *            the SavedSearchListener to be added
     */
    public void addSaveSearchListener(ChangeListener l) {
        searchDAO.addSavedSearchChangeListener(l);
    }

    /**
     * This will remove a SavedSearchListener where necessary.
     * 
     * @param l
     *            the SavedSearchListener to be removed
     */
    public void removeSavedSearchListener(ChangeListener l) {
        searchDAO.removeSavedSearchChangeListener(l);
    }

    /**
     * This will return a list of all the saved searches.
     * 
     * @return a List of Strings tha are all the names of the saved
     *         searches
     */
    public List<String> getAllSavedSearches() {
        return searchDAO.getAllSavedSearches();
    }

    /**
     * This will add a genre to a movie.
     * 
     * @param m
     *            the movie to add the genre to.
     * @param s
     *            a string that is the genre to be added.
     */
    public void addGenreToMovie(Movie m, String s) {
        m.addGenre(s);
    }

    /**
     * This will remove a genre from a movie.
     * 
     * @param m
     *            the movie to remove a genre from.
     * @param s
     *            the string genre to be removed.
     */
    public void removeGenreFromMovie(Movie m, String s) {
        m.removeGenre(s);
    }

    /**
     * This will remove a movie from a set.
     * 
     * @param label
     *            the name of the set to remove the movie from.
     * @param m
     *            the movie to remove from set label.
     */
    public void removeMovieFromSet(String label, Movie m) {
        setsDAO.removeMovieFromSet(label, m);
    }

    /**
     * This will set the owner of a movie.
     * 
     * @param m
     *            the movie to set the owner on.
     * @param o
     *            the Person who owns movie m.
     */
    public void setOwnerToMovie(Movie m, Person o) {
        m.setOwner(o);
    }

    /**
     * This will set the borrower of a movie.
     * 
     * @param m
     *            the movie to set the borrower on.
     * @param b
     *            the Person who is borrowing movie m.
     */
    public void setBorrowerToMovie(Movie m, Person b) {
        m.setBorrower(b);
    }

    /**
     * This will remove a movie from a list.
     * 
     * @param label
     *            the name of the list the movie is being removed
     *            from.
     * @param m
     *            the movie to be removed from list label.
     */
    public void removeMovieFromList(String label, Movie m) {
        listsDAO.removeMovieFromList(label, m);
    }

    /**
     * This will delete an actor if the actor is not involved in any
     * other relations.
     * 
     * @param a
     *            the actor to be deleted.
     * @throws com.themangoproject.db.h2.ActorExistsInOtherRelationsException
     *             if the actor is involved in other relations.
     */
    public void deleteActor(Actor a)
            throws ActorExistsInOtherRelationsException {
        actorDAO.deleteActor(a);
    }

    /**
     * This will delete an actor, from any and all relations it is a
     * part of.
     * 
     * @param a
     *            the actor to be forcably deleted
     */
    public void forceDeleteActor(Actor a) {
        actorDAO.forceDeleteActor(a);
    }

    /**
     * This will add a PersonsChangeListener where necessary, to
     * receive needed events.
     * 
     * @param l
     *            the PersonsChangeListener to be added
     */
    public void addPersonsChangeListener(ChangeListener l) {
        personDAO.addPersonChangeListener(l);
    }

    /**
     * This will remove a PersonsChangeListener where necessary.
     * 
     * @param l
     *            the PerosnsChangeListener to be removed.
     */
    public void removePersonsChangeListener(ChangeListener l) {
        personDAO.removePersonChangeListener(l);
    }

    /**
     * This will add an ActorsChangeListener where necessary, to
     * receive needed events.
     * 
     * @param l
     *            the ActorsChangeListener to be added
     */
    public void addActorsChangeListener(ChangeListener l) {
        actorDAO.addActorsChangeListener(l);
    }

    /**
     * This will remove an ActorsChangeListener where necessary.
     * 
     * @param l
     *            the ActorsChangeListener to be removed.
     */
    public void removeActorsChangeListener(ChangeListener l) {
        actorDAO.removeActorsChangeListener(l);
    }

    /**
     * This will update the order of movies in a list.
     * 
     * @param label
     *            the name of the list which is being reordered.
     * @param movies
     *            a List of movies in the new order for list label.
     */
    public void updateListOrder(String label, List<Movie> movies) {
        listsDAO.reorderMoviesInList(label, movies);
    }

    /**
     * This will delete a person if the person is not involved in any
     * relations.
     * 
     * @param p
     *            the person to be deleted
     * @throws com.themangoproject.model.PersonDAO.PersonHasMoviesException
     *             if the person exists in other relations.
     */
    public void deletePerson(Person p)
            throws PersonHasMoviesException {
        personDAO.deletePerson(p);
    }

    /**
     * This will delete a person, from any and all relations it is a
     * part of.
     * 
     * @param a
     *            the person to be forcably deleted
     */
    public void forceDeletePerson(Person p) {
        personDAO.forceDeletePerson(p);
    }

}
