package com.themangoproject.ui.model;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import com.themangoproject.model.*;

/**
 * Combo box model that encapsulates a model of all the people contained in the
 * database.
 * 
 * @author Paul Osborne
 */ 
public class PersonComboBoxModel extends DefaultComboBoxModel {

	/** Generated serial version uid */
	private static final long serialVersionUID = -164842922152566685L;

	/** List of persons in the database */
	private List<Person> persons;

	/**
	 * Instatiate an instance of a PersonComboBoxModel.
	 */
	public PersonComboBoxModel() {
		this.persons = MangoController.getInstance().getAllPersons();
	}

	/**
	 * @return the number of <code>People</code> in the model.
	 */
	@Override
	public int getSize() {
		return persons.size();
	}

	/**
	 * @return the <code>Person</code> at the specified index in the model.
	 */
	@Override
	public Object getElementAt(int index) {
		return persons.get(index).getName();
	}
        
        public Object getPersonAt(int index) {
            return persons.get(index);
        }

}
