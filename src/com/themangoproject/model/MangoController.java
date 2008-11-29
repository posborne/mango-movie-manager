package com.themangoproject.model;

import com.themangoproject.db.h2.H2MovieDAO;
import com.themangoproject.db.h2.H2ActorDAO;
import com.themangoproject.db.h2.H2PersonDAO;
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
    

}
