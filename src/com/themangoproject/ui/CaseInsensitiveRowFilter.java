package com.themangoproject.ui;

import javax.swing.RowFilter;

/**
 * This row filters based on the string contents of the table it is tied to,
 * matching all items that contain the search text.  
 * 
 * @author Paul Osborne
 * @version December 22, 2008
 */
public class CaseInsensitiveRowFilter extends RowFilter<Object, Object> {
	/** The search term for the filter */
	private String searchTerm;

	/**
	 * Create a new case insensitive row filter with the specified search
	 * text.
	 * @param searchTerm The starting search term.
	 */
	public CaseInsensitiveRowFilter(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	/**
 	 * Change the search term.
	 * 
	 * @param searchTerm
	 *            The new search term.
	 */
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.RowFilter#include(javax.swing.RowFilter.Entry)
	 */
	@Override
	public boolean include(
			Entry<? extends Object, ? extends Object> entry) {
		for (int i = entry.getValueCount() - 1; i >= 0; i--) {
			if (entry.getStringValue(i).toLowerCase().contains(searchTerm.toLowerCase())) {
				// The value starts with "a", include it
				return true;
			}
		}
		// None of the columns start with "a"; return false so that this
		// entry is not shown
		return false;
	}

}
