package com.themangoproject.ui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import com.themangoproject.model.Movie;

/**
 *
 * @author Kyle Ronning, Paul Osborne
 */
public abstract class EditableMovieTableModel extends AbstractTableModel{

	private final String columns[] = {
			"#",
			"Title",
			"Director",
			"Rating",
			"Year",
			"Mango Rating",
			"Type"
	};
	
	public abstract List<Movie> getMovies();
	
	public int getRowCount() {
        return getMovies().size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Movie m = getMovies().get(rowIndex);
    	switch (columnIndex) {
        case 0:
        	return columnIndex + 1;
        case 1:
        	return m.getTitle();
        case 2:
        	return m.getDirector();
        case 3:
        	return m.getRating();
        case 4:
        	return m.getYear();
        case 5:
        	return m.getMangoRating();
        case 6:
        	return m.getType();
        default:
        	return "???";
        }
    }

}
