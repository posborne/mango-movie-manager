
package com.themangoproject.ui.model;

import javax.swing.DefaultComboBoxModel;

/**
 * ConstraintComboBoxModel is a DefaultComboBoxModel for the ComboBox in a 
 * SavedSearchDialog.
 * 
 * @author Kyle Ronning
 */
public class ConstraintComboBoxModel extends DefaultComboBoxModel {
	private static final long serialVersionUID = -7327178317367492600L;
	/** List of constraints */
    private String[] constraints = {"Contains", "Does Not Contain", "is", "Is Not",
                "Is Less Than", "Is Greater Than"};

    /**
     * @return the number of <code>Constraints</code> in the model.
     */
    @Override
    public int getSize() {
            return this.constraints.length;
    }

    /**
     * @return the <code>Constraint</code> at the specified index in the model.
     */
    @Override
    public Object getElementAt(int index) {
            return this.constraints[index];
    }
}
