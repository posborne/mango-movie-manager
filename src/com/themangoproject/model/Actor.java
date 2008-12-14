package com.themangoproject.model;

import java.util.List;

/**
 * Generic Actor Interface.  An Actor consists of a first and last name.  More
 * than one actor may have the same name and it is up to the larger application
 * to distinguish between actors.
 * 
 * @author Paul Osborne
 */
public interface Actor {
    
    /**
     * @return An immutable (unmodifiable) list containing all the movies that
     * this actor has been in.
     */
    public List<Movie> getMovies();

    /**
     * @return An immutable (unmodifiable) list containing all the Roles of this
     * actor.
     */
    public List<Role> getRoles();

    /**
     * @return The actor's first name.
     */
    public String getFirstName();

    /**
     * @return The actor's last name.
     */
    public String getLastName();

    /**
     * Change the first name of the actor.
     * @param firstName The new first name for the actor.
     */
    public void setFirstName(String firstName);
    
    /**
     * Change the last name of tha actor.
     * @param lastName The new last name for the actor.
     */
    public void setLastName(String lastName);

    /**
     * @return A string representation of the Actor.
     */
    public String toString();
    
    /**
     * Tests to see if two actors are equal.
     * @param obj The actor to determine equality.
     * @return true if the actors are equal.
     */
    @Override
    public boolean equals(Object obj);
}
