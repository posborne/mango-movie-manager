/**
 * 
 */
package com.themangoproject.db.h2;

import java.util.Date;
import java.util.List;

import com.themangoproject.model.Actor;
import com.themangoproject.model.Person;

/**
 * @author Zachary Varberg
 * 
 */
public interface DBMovieState {

    public int getId();

    public String getDirector();

    public String getTitle();

    public String getRating();

    public int getRuntime();

    public int getMangoRating();

    public int getYear();

    public String getASIN();

    public Date getPurchaseDate();

    public String getCustomDescription();

    public String getType();

    public Person getOwner();

    public Person getBorrower();

    public String getCondition();

    public int getOwnerId();

    public int getBorrowerId();
}
