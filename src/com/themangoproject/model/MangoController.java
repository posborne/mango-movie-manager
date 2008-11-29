package com.themangoproject.model;

import com.themangoproject.db.h2.DBPerson;
import com.themangoproject.db.h2.H2MovieDAO;
import com.themangoproject.db.h2.H2ActorDAO;
import com.themangoproject.db.h2.H2PersonDAO;
import java.util.ArrayList;
import java.util.List;

/**
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

    public static final MangoController getInstance() {
        if (instance == null) {
            instance = new MangoController();
        }
        return instance;
    }

    public void addMovie(Movie movie) {
        movieDAO.addMovie(movie);
    }

    public void addActor(Actor actor) {
        actorDAO.addActor(actor);
    }

    public Role addRole(Role role) {
        // TODO: implement
        return null;
    }

    public List<Movie> getMoviesForActor(Actor actor) {
        List<Role> roles = actorDAO.getRolesForActor(actor);
        ArrayList<Movie> movies = new ArrayList<Movie>();
        for (Role role : roles) {
            movies.add(role.getMovie());
        }
        return movies;
    }

    public List<Role> getRolesForActor(Actor actor) {
        return actorDAO.getRolesForActor(actor);
    }

    public void updateActor(Actor actor) {
        actorDAO.addActor(actor);
    }

    /**
     * This method will take as an argument a movie. It will then get
     * all the information about the movie and update the movie. It
     * will return nothing because it will just update the movie
     * passed to it.
     * 
     * @param movie
     *            the movie to find information for.
     */
    public void getMovieInfo(DBMovie movie) {
        // TODO Auto-generated method stub

    }

    /**
     * This will return the list of genres the movie passed to it are
     * a part of.
     * 
     * @param movie
     *            the movie to get the genres for.
     */
    public List<String> getGenresForMovie(DBMovie movie) {
        return this.movieDAO.getGenresForMovie(movie);
    }

    /**
     * This will add the genre given to the movie given.
     * 
     * @param movie
     *            the movie to add the genre
     * @param genre
     *            the genre to be added
     */
    public void addGenreToMovie(DBMovie movie, String genre) {
        // TODO:
        // this.movieDAO.addGenreToMovie(movie, genre);

    }

    /**
     * This will remove the specified genre from the movie specified
     * 
     * @param movie
     *            the movie to remove the genre from
     * @param genre
     *            the genre to be removed
     */
    public void removeGenreFromMovie(DBMovie movie, String genre) {
        // TODO
        // this.movieDAO.removeGenreFromMovie(movie, genre);

    }

    /**
     * This will retrieve all the information about this person from 
     * the database.
     * 
     * @param person the person to get data on.
     */
    public void getPersonInfo(DBPerson person) {
        // TODO
        // this.personDAO.getPersonInfo(person);
        
    }

}
