/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.themangoproject.ui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.themangoproject.model.Actor;
import com.themangoproject.model.MangoController;
import com.themangoproject.model.Person;

/**
 *Table model encapsulating a view of the People contained in the Mango DB
 * backend.
 *
 * @author Zachary Varberg
 */
public class PersonTableModel extends AbstractTableModel {
    
    private final String columns[] = {
            "#",
            "Name",
            "Address",
            "Email", 
            "Phone Number"
    };

    /** List of persons in the storage backend */
    private List<Person> persons;

    public PersonTableModel(){
        persons = MangoController.getInstance().getAllPersons();
    }
    
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }
    
    /**
     * The number of columns is the number of persons in the table.
     * 
     * @return The number of people who fit the current model criterium
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Get the number of rows in the model.
     * 
     * @return the number of rows in the model.
     */
    @Override
    public int getRowCount() {
        return this.persons.size();
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
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return !(columnIndex == 1);
    }
    
    public Class<? extends Object> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
    
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Person p = persons.get(rowIndex);
        switch (columnIndex) {
        case 1:
            p.setName((String)value);
            //TODO: write updatePerson method in controller.
//            MangoController.getInstance().updatePerson(p);
            break;
        case 2:
            p.setAddress((String)value);
            break;
        case 3:
            p.setEmail((String)value);
            break;
        case 4:
            p.setPhoneNumber((String)value);
            break;
        default:
            break; // Can't be edited
        }
    }
}
