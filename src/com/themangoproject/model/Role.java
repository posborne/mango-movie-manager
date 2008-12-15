package com.themangoproject.model;

/**
 * Role is an interface that represents a role that an actor plays.  A role has
 * information about who the actor is and what character is being played.
 * 
 * @author Zach Varberg, Paul Osborne, Kyle Ronning
 */
public interface Role {

    /**
     * Gets the actor that plays this role.
     * 
     * @return The actor.
     */
    public Actor getActor();

    /**
     * Gets the character that the actor plays for this role.
     * 
     * @return The character
     */
    public String getCharacter();

    /**
     * Gets the role.
     * 
     * @return A string representation of the role.
     */
    public String getRole();

    /**
     * String representaion of the role.
     * 
     * @return A string representation of the role.
     */
    @Override
    public String toString();

    /**
     * Gets the movie that this role belongs to.
     * @return The movie.
     * 
     */
    public Movie getMovie();

    /**
     * Sets the role.
     * @param roleName The role to set for this role.
     */
    public void setRole(String roleName);

    /**
     * Sets the character for the role.
     * 
     * @param character The character for the role.
     */
    public void setCharacter(String character);

}
