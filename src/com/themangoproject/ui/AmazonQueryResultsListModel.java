package com.themangoproject.ui;

import com.themangoproject.webservice.AmazonMovieTitleQuery;
import com.themangoproject.webservice.AmazonMovieTitleQuery.AmazonMovieTitleResult;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 * List model for results of query to amazon.  The list model itself is what
 * does the query.  The application just tells it the title to search on.
 * 
 * @author Paul Osborne
 * @version December 18, 2008
 */
public class AmazonQueryResultsListModel extends AbstractListModel {
    
    /** Generated UID */
	private static final long serialVersionUID = -5610840147317210789L;
	
	private List<AmazonMovieTitleResult> results;
	
    private AmazonMovieTitleQuery query;
    
    /**
     * Construct the list model.
     */
    public AmazonQueryResultsListModel() {
    	query = new AmazonMovieTitleQuery();
        results = new ArrayList<AmazonMovieTitleResult>();
    }
    
    public void setQuery(String titleQuery) {
        results = query.getMoviesList(titleQuery);
        fireContentsChanged(this, 0, getSize());
    }

    public int getSize() {
        return results.size();
    }

    public Object getElementAt(int rowIndex) {
        return results.get(rowIndex).getTitle();
    }
}
