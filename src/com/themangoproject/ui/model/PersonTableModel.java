/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.themangoproject.ui.model;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import com.themangoproject.model.MangoController;
import com.themangoproject.model.Person;
import com.themangoproject.model.PersonDAO.PersonHasMoviesException;
import com.themangoproject.ui.UIController;

/**
 * Table model encapsulating a view of the People contained in the
 * Mango DB backend.
 * 
 * @author Kyle Ronning, Zachary Varberg, Paul Osborne
 */
public class PersonTableModel extends AbstractTableModel implements
        MangoTableModelIF {

    private static final long serialVersionUID = 1574693016889051787L;

    private final String columns[] = { "#", "Name", "Address",
            "Email", "Phone Number" };

    /** List of persons in the storage backend */
    private List<Person> persons;

    private ChangeListener personsChangeListener;

    /**
     * Constructs the table model.
     */
    public PersonTableModel() {
        persons = new ArrayList<Person>();
        personsChangeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                PersonTableModel.this.retrievePersons();
            }
        };
        MangoController.getInstance().addPersonsChangeListener(
                personsChangeListener);
        retrievePersons();
    }

    private void retrievePersons() {
        persons = MangoController.getInstance().getAllPersons();
        fireTableStructureChanged();
    }

    /**
     * Gets the column name at index <code>columnIndex</code>
     * 
     * @param columnIndex
     *            The index of the column.
     * @return The column name.
     */
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    /**
     * Gets the number of columns in the table.
     * 
     * @return The number of columns
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Gets the number of rows
     * 
     * @return
     */
    @Override
    public int getRowCount() {
        return this.persons.size();
    }

    /**
     * Get the value of the object at the specified row and column
     * position in the table model.
     * 
     * @param rowIndex
     *            the row index
     * @param columnIndex
     *            the column index
     * @return The value of the Object corresponding to the specified
     *         row and column index.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person p = persons.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return rowIndex + 1;
        case 1:
            return p.getName();
        case 2:
            return p.getAddress();
        case 3:
            return p.getEmail();
        case 4:
            return p.getPhoneNumber();
        default:
            return "???";
        }
    }

    /**
     * Returns true if the cell is editable
     * 
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return !(columnIndex == 1);
    }

    /**
     * Gets the class of a given column.
     * 
     * @param columnIndex
     * @return
     */
    public Class<? extends Object> getColumnClass(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return Integer.class;
        case 1:
            return String.class;
        case 2:
            return String.class;
        case 3:
            return String.class;
        case 4:
            return String.class;
        default:
            return String.class;
        }
    }

    /**
     * Sets the value of a cell to <code>value</code>
     * 
     * @param value
     * @param rowIndex
     * @param columnIndex
     */
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Person p = persons.get(rowIndex);
        switch (columnIndex) {
        case 1:
            p.setName((String) value);
            break;
        case 2:
            p.setAddress((String) value);
            break;
        case 3:
            p.setEmail((String) value);
            break;
        case 4:
            p.setPhoneNumber((String) value);
            break;
        default:
            break; // Can't be edited
        }
        MangoController.getInstance().updatePerson(p);
    }

    /**
     * Removes any listeners.
     */
    @Override
    public void cleanup() {
        MangoController.getInstance().removePersonsChangeListener(
                personsChangeListener);
    }

    /**
     * Gets the popup menu for this table model
     * 
     * @return
     */
    @Override
    public JPopupMenu getPopupMenu() {
        JMenuItem deleteItem = new JMenuItem("Delete Person");
        JTable table = UIController.getInstance().getViewTable();
        int rowIndex = table.getRowSorter().convertRowIndexToModel(
                table.getSelectedRow());
        final Person p = persons.get(rowIndex);
        deleteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    MangoController.getInstance().deletePerson(p);
                } catch (PersonHasMoviesException ex) {
                    if (JOptionPane.YES_OPTION == JOptionPane
                            .showConfirmDialog(
                                    UIController.getInstance()
                                            .getMango(),
                                    "This person is the owner or borrower of movies in the database.\nAre you sure you want to delete?",
                                    "Confirm Delete",
                                    JOptionPane.YES_NO_OPTION)) {
                        MangoController.getInstance()
                                .forceDeletePerson(p);
                    }
                }
            }
        });
        JPopupMenu popup = new JPopupMenu();
        popup.add(deleteItem);
        return popup;
    }

    /**
     * No image for a person
     * 
     * @param modelRow
     * @return null
     */
    @Override
    public Image getImageForRow(int modelRow) {
        return null; // no images for persons
    }
}
