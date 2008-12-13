package com.themangoproject.ui.model;

import java.util.List;

import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;

import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;

/**
 * 
 * 
 * @author Kyle Ronning, Paul Osborne
 */
public abstract class EditableMovieTableModel extends
		AbstractTableModel implements MangoTableModelIF {

	/** Generated serial UID */
	private static final long serialVersionUID = -7581259670968084268L;

	/** Columns in the movie table */
	private final String columns[] = { "#", "Title", "Director",
			"Rating", "Year", "Mango Rating", "Type" };

	/**
	 * Get the movie associated with the given row index in model indices.
	 * 
	 * @param row
	 *            The row we are interested in.
	 * @return a reference to the Movie associated with the row.
	 */
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
		if (getRowCount() > 0) {
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
		} else {
			return null;
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return !(columnIndex == 0);
	}

	public Class<? extends Object> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Movie m = getMovies().get(rowIndex);
		switch (columnIndex) {
		case 1:
			m.setTitle((String) value);
			break;
		case 2:
			m.setDirector((String) value);
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
		MangoController.getInstance().updateMovie(m);
	}

	public abstract ChangeListener getMoviesChangeListener();

}
