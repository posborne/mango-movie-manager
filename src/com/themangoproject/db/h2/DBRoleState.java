/**
 * 
 */
package com.themangoproject.db.h2;

/**
 * @author Zachary Varberg
 *
 */
public interface DBRoleState {
    /**
     * This will return the character name of the character played by
     * the actor in this role.
     * 
     * @return the character played by the actor in this role
     */
    public String getCharacter();

    /**
     * This will return the role of this role (e.g. leading actor, or supporting
     * actress).
     * 
     * @return the role of this role.
     */
    public String getRole();
}
