
package com.themangoproject.ui.model;

import javax.swing.DefaultComboBoxModel;

/**
 * AttributesComboBoxModel is a DefaultComboBoxModel for the ComboBox in a
 * SavedSearchDialog.  The model displays all attributes that are searchable.
 * 
 * @author Kyle Ronning
 */
public class AttributesComboBoxModel extends DefaultComboBoxModel {
	private static final long serialVersionUID = -5574393541155232995L;
	/** List of attributes */
    private String[] attributes = 
        {"Character", "Condition", "Director", "Genre", "Mango Rating", 
         "Purchase Date", "Rating", "Role", "Runtime", "Title", "Type", "Year"};

    /**
     * @return the number of <code>Attributes</code> in the model.
     */
    @Override
    public int getSize() {
            return this.attributes.length;
    }

    /**
     * @return the <code>Attribute</code> at the specified index in the model.
     */
    @Override
    public Object getElementAt(int index) {
            return this.attributes[index];
    }
}
