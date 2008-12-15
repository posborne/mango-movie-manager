package com.themangoproject.model;

import java.util.List;

import javax.swing.event.ChangeListener;

import com.themangoproject.db.h2.ActorExistsInOtherRelationsException;

/**
 * This is an interface for an actor data access object (DAO).  This is an
 * interface that provides methods to manipulate actors however they are stored.
 * A concrete implementation of this class would have methods that are 
 * particular to the way the information is stored (e.g. an H2 database, or a 
 * derby databse, etc.)
 * 
 * @author Paul Osborne, Zachary Varberg
 */
public interface ActorDAO {

    /**
     * This will return a list of all the actors stored.
     * @return all the actors stored
     */
    public List<Actor> getAllActors ();
    
    /**
     * This will return all of the roles played by a specific actor
     * @param actor the Actor whose roles this will return
     * @return a list of roles actor was a part of.
     */
    public List<Role> getRolesForActor (Actor actor);
    
    /**
     * This will save a new actor
     * @param firstName the first name of the actor to be saved
     * @param lastName the last name of the actor to be saved
     * @return false if there was an error saving the actor true otherwise.
     */
    public boolean addActor (String firstName, String lastName);
    
    /**
     * This will update all of the information stored about a given actor.
     * @param actor the Actor to be updated
     */
    public void updateActor (Actor actor);
    
    /**
     * This will forcably delete the actor from all associations it is a part of
     * @param actor the actor to be forcably deleted
     */
    public void forceDeleteActor (Actor actor);
    
    /**
     * This will delete an actor if it is not a part of any other relationships.
     * If the actor exists elsewhere this will throw an 
     * ActorExistsInOtherRelationsException.
     * 
     * @param actor the actor to attempt to delete
     * 
     * @throws com.themangoproject.db.h2.ActorExistsInOtherRelationsException if
     * the actor exists in other relations.
     */
    public void deleteActor (Actor actor) throws ActorExistsInOtherRelationsException;
    public void populateActor (Actor actor);
    
    /**
     * This will return all the movies this actor played a part in.
     * @param actor the actor for whom the movies are wanted.
     * @return a list of all the movies actor was a part of.
     */
    public List<Movie> getMoviesForActor(Actor actor);
    
    /**
     * This will add a role for this acto
     * @param role the new role this actor is a part of
     * @throws com.themangoproject.model.RoleNotFoundException if the role 
     * passed doesn't already exist in the storage model.
     */
    public void populateRole(Role role) throws RoleNotFoundException;
    
    /**
     * This will add an ActorsChangeListener to this DAO.
     * 
     * @param l the ActorsChangeListener to be added.
     */
    public void addActorsChangeListener(ChangeListener l);
    
    /**
     * This will remove an ActorsChangeListener from this DAO
     * 
     * @param l the ActorsChangeListener to be removed.
     */
    public void removeActorsChangeListener(ChangeListener l);
}

