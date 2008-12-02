/**
 * 
 */
package com.themangoproject.db.h2;

import java.util.List;

import com.themangoproject.model.Movie;

/**
 * @author Zachary Varberg
 *
 */
public interface DBPersonState {

    /**
     * This will return the database ID of this person
     * 
     * @return the database ID of this person.
     */
    public int getId();
    
    /**
     * This will return the name of this person
     * 
     * @return the name of this person
     */
    public String getName();
    
    /**
     * This will return this persons address
     * 
     * @return this persons address
     */
    public String getAddress();
    
    /**
     * This will return this persons phone number
     * 
     * @return this persons phone number
     */
    public String getPhoneNumber();
    
    /**
     * This will return this persons email
     * 
     * @return this persons email
     */
    public String getEmail();
    
}
