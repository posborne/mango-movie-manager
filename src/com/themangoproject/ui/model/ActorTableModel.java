package com.themangoproject.ui.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.themangoproject.model.Person;

/**
 * Table model encapsulating a view of the People contained in the Mango DB
 * backend.
 * 
 * @author Paul Osborne
 */
public class ActorTableModel extends AbstractTableModel {

	/** Generated serial version UID */
	private static final long serialVersionUID = -392463901990643277L;
        private final String columns[] = {
			"#",
			"First Name",
			"Last Name"
	};

	/** List of people in the storage backend */
	private List<Person> persons;

	/**
	 * The number of columns is the number of persons in the table.
	 * 
	 * @return The number of people who fit the current model criterium
	 */
	@Override
	public int getColumnCount() {
		return 1; // TODO: implement getColumnCount
	}

	/**
	 * Get the number of rows in the model.
	 * 
	 * @return the number of rows in the model.
	 */
	@Override
	public int getRowCount() {
		return persons.size();
	}

	/**
	 * Get the value of the object at the specified row and column position in
	 * the table model.
	 * 
	 * @param rowIndex
	 *            the row index
	 * @param columnIndex
	 *            the column index
	 * @return The value of the Object corresponding to the specified row and
	 *         column index.
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
