package com.themangoproject.model;

import java.util.ArrayList;

/**
 * This is a class that is used to build and save advanced search criteria for
 * movies using an H2 database.
 * 
 * @author Paul Osborne
 */
public class H2AdvancedMovieSearch {
    ArrayList<SearchCondition> searchConditions;
    
    /**
     * This will create a new H2AdvancedMovieSearch object.
     */
    public H2AdvancedMovieSearch() {
        searchConditions = new ArrayList<SearchCondition>();
    }
    
    /**
     * This will add a new SearchCondition to this advanced search
     * 
     * @param condition the new SearchCondition to be considered in this
     * H2AdvancedMovieSearch.
     */
    public void addSearchCondition(SearchCondition condition) {
        searchConditions.add(condition);
    }
    
    /**
     * This will return a String that is an H2 query that will retrieve all
     * items in the movie table that match the SearchConditions that have been 
     * added as a part of this H2AdvancedMovieSearch
     * 
     * @return a String that is an H2 query
     */
    public String getSearchQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id " +
                "FROM movie " +
                "WHERE ");
        for (int i = 0; i < searchConditions.size(); i++) {
            SearchCondition sc = searchConditions.get(i);
            sb.append(sc.conditionAsSQL());
            if (i < searchConditions.size() - 1) {
                sb.append(" AND ");
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
