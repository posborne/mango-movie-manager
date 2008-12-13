package com.themangoproject.ui.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import com.themangoproject.model.Actor;
import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;
import com.themangoproject.model.Person;

/**
 * Table model encapsulating a view of the Actors contained in the Mango DB
 * backend.
 * 
 * @author Zach Varberg
 */
public class ActorTableModel extends AbstractTableModel implements MangoTableModelIF {

    /** Generated serial version UID */
    private static final long serialVersionUID = -392463901990643277L;
    private final String columns[] = {
            "#",
            "First Name",
            "Last Name"
    };

    /** List of actors in the storage backend */
    private List<Actor> actors;

    public ActorTableModel(){
        actors = MangoController.getInstance().getAllActors();
    }
    
	public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }
	
	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return this.actors.size();
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
	    Actor a = actors.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return rowIndex + 1;
        case 1:
            return a.getFirstName();
        case 2:
            return a.getLastName();
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
        Actor a = actors.get(rowIndex);
        switch (columnIndex) {
        case 1:
            a.setFirstName((String)value);
            MangoController.getInstance().updateActor(a);
            break;
        case 2:
            a.setLastName((String)value);
            MangoController.getInstance().updateActor(a);
            break;
        default:
            break; // Can't be edited
        }
    }

	@Override
	public void cleanup() {
		// TODO: cleanup
	}

}
