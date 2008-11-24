package com.themangoproject.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paul Osborne
 */
public class MangoController {

    private static MangoController instance;
    private PersonDAO personDAO;
    private MovieDAO movieDAO;
    private ActorDAO actorDAO;
    
    private MangoController() {
        personDAO = new H2PersonDAO();
        movieDAO = new H2MovieDAO();
        actorDAO = new H2ActorDAO();
    }
    
    public static final MangoController getInstance() {
        if (instance == null) {
            instance = new MangoController();
        }
        return instance;
    }
    
    public Movie addMovie(Movie movie) {
        return movieDAO.addMovie(movie);
    }
    
    public Actor addActor(Actor actor) {
        return actorDAO.addActor(actor);
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
    

}
