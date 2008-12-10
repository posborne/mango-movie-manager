package com.themangoproject.ui.model;

import java.awt.Image;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import com.themangoproject.model.Movie;

/**
 *
 * @author Kyle Ronning, Paul Osborne
 */
public abstract class EditableMovieTableModel extends AbstractTableModel{

	private static final long serialVersionUID = -7581259670968084268L;
	private final String columns[] = {
			"#",
			"Title",
			"Director",
			"Rating",
			"Year",
			"Mango Rating",
			"Type"
	};

    public Movie getMovieForRow(int row) {
        return getMovies().get(row);
    }
	
	public abstract List<Movie> getMovies();
	
	public int getRowCount() {
        return getMovies().size();
    }

    public int getColumnCount() {
        return columns.length;
    }
    
    public String getColumnName(int columnIndex) {
    	return columns[columnIndex];
    }
    
    public Movie getMovieFromRow(int rowIndex) {
        return getMovies().get(rowIndex);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Movie m = getMovies().get(rowIndex);
    	switch (columnIndex) {
        case 0:
        	return rowIndex + 1;
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
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return !(columnIndex == 1);
    }
    
    public Class<? extends Object> getColumnClass(int columnIndex) {
    	return getValueAt(0, columnIndex).getClass();
    }
    
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
    	Movie m = getMovies().get(rowIndex);
    	switch (columnIndex) {
    	case 1:
    		m.setTitle((String)value);
    		break;
    	case 2:
    		m.setDirector((String)value);
    		break;
    	case 3:
    		m.setRating((String) value);
    		break;
    	case 4:
    		m.setYear((Integer) value);
    		break;
    	case 5:
    		m.setMangoRating((Integer) value);
    		break;
    	case 6:
    		m.setType((String) value);
    		break;
    	default:
    		break; // Can't be edited
    	}
    }

}
