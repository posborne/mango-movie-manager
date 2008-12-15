package com.themangoproject.model;

import java.util.ArrayList;

/**
 * 
 * @author Paul Osborne
 */
public class AdvancedSearch {
    ArrayList<SearchCondition> searchConditions;

    public AdvancedSearch() {
        searchConditions = new ArrayList<SearchCondition>();
    }

    public void addSearchCondition(SearchCondition condition) {
        searchConditions.add(condition);
    }

    public String getSearchQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id " + "FROM movie " + "WHERE ");
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
