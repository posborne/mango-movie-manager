package com.themangoproject.ui;

import com.themangoproject.webservice.AmazonMovieTitleQuery;
import com.themangoproject.webservice.AmazonMovieTitleQuery.AmazonMovieTitleResult;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * List model for results of query to amazon.  The list model itself is what
 * does the query.  The application just tells it the title to search on.
 * 
 * @author Paul Osborne
 * @version December 18, 2008
 */
public class AmazonQueryResultsListModel extends AbstractListModel
    implements ChangeListener {
    
    private List<AmazonMovieTitleResult> results;
    private AmazonMovieTitleQuery query;
    
    /**
     * Construct the list model.
     */
    public AmazonQueryResultsListModel() {
        results = new ArrayList<AmazonMovieTitleResult>();
    }
    
    public void setQuery(AmazonMovieTitleQuery q) {
        this.query = q;
        results = q.getMoviesList();
        fireContentsChanged(this, 0, getSize());
    }

    public int getSize() {
        return results.size();
    }

    public Object getElementAt(int rowIndex) {
        return results.get(rowIndex).getTitle();
    }

    public void stateChanged(ChangeEvent e) {
        results = query.getMoviesList();
        fireContentsChanged(this, 0, getSize());
    }
    
}
