package com.themangoproject.ui;

import javax.swing.RowFilter;

/**
 * @author Paul Osborne
 * 
 */
public class CaseInsensitiveRowFilter extends RowFilter<Object, Object> {

	private String searchTerm;

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
