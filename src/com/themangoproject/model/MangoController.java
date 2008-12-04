package com.themangoproject.model;

import com.themangoproject.db.h2.DBMovie;
import com.themangoproject.db.h2.DBPerson;
import com.themangoproject.db.h2.H2MovieDAO;
import com.themangoproject.db.h2.H2ActorDAO;
import com.themangoproject.db.h2.H2PersonDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the controller.  A class that has a number of methods useful
 * for all sorts of classes to have access too.
 * 
 * @author Paul Osborne, Zachary Varberg
 */
public class MangoController {

    private static MangoController instance;
    private PersonDAO personDAO;
    private MovieDAO movieDAO;
    private ActorDAO actorDAO;

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
     * @param movie the movie to be added
     */
    public void addMovie(Movie movie) {
        movieDAO.addMovie(null, null, null, -1, -1, null, null, null, null, null, 3);
    }

    /**
     * This will add an actor and save it.
     * 
     * @param actor the actor to be added
     */
    public void addActor(Actor actor) {
        actorDAO.addActor(actor);
    }

    /**
     * This will save the changes made to this actor
     * 
     * @param actor the actor that has changed and needs to be
     * updated
     */
    public void updateActor(Actor actor) {
        actorDAO.addActor(actor);
    }
    
    /**
     * This will save the changes made to this movie
     * 
     * @param movie the movie that has changed and needs to be
     * updated
     */
    public void updateMovie(Movie movie){
        this.movieDAO.updateMovie(movie);
    }
    
    /**
     * This will return a list of all the people
     * 
     * @return a list of all the people
     */
    public List<Person> getAllPersons(){
        return this.personDAO.getAllPersons();
    }
    
    /**
     * This will return a list of all the actors
     * 
     * @return a list of all the actors
     */
    public List<Actor> getAllActors(){
        return this.actorDAO.getAllActors();
    }
    
    /**
     * This will return a list of all the movies
     * 
     * @return a list of all the movies
     */
    public List<Movie> getAllMovies(){
        return this.movieDAO.getAllMovies();
    }

}
