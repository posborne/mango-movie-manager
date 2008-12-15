package com.themangoproject.ui.model;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.themangoproject.model.*;

/**
 * Combo box model that encapsulates a model of all the people
 * contained in the database.
 * 
 * TODO: a reference to this object is not removed from listeners if
 * is removed from everywhere else (memory leak)
 * 
 * @author Paul Osborne, Kyle Ronning
 */
public class PersonComboBoxModel extends DefaultComboBoxModel {

    /** Generated serial version uid */
    private static final long serialVersionUID = -164842922152566685L;

    /** List of persons in the database */
    private List<Person> persons;

    /** Change listener on list of persons */
    private ChangeListener personsChangeListener;

    /**
     * Instatiate an instance of a PersonComboBoxModel.
     */
    public PersonComboBoxModel() {
        personsChangeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                retrievePersons();
            }
        };
        MangoController.getInstance().addPersonsChangeListener(
                personsChangeListener);
        retrievePersons();
    }

    /**
     * Regrab the persons from the database and fire a contents
     * changed event.
     */
    private void retrievePersons() {
        this.persons = MangoController.getInstance().getAllPersons();
        this.fireContentsChanged(persons, 0, persons.size());
    }

    /**
     * @return the number of <code>People</code> in the model.
     */
    @Override
    public int getSize() {
        return persons.size();
    }

    /**
     * @return the <code>Person</code> at the specified index in the
     *         model.
     */
    @Override
    public Object getElementAt(int index) {
        return persons.get(index).getName();
    }

    /**
     * Get the person at the specified index.
     * 
     * @param index
     *            The index we are interested in
     * @return a reference to the person at the specified index.
     */
    public Person getPersonAt(int index) {
        return persons.get(index);
    }

}
