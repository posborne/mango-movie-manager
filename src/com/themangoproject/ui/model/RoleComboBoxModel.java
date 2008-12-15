package com.themangoproject.ui.model;

import javax.swing.DefaultComboBoxModel;

/**
 * RoleComboBoxModel is a DefaultComboBoxModel for roles in a movie.
 * 
 * @author Kyle Ronning
 */
public class RoleComboBoxModel extends DefaultComboBoxModel {
    private static final long serialVersionUID = 6997281484522311569L;
    /** List of constraints */
    private String[] roles = { "Lead Actor", "Lead Actress",
            "Supporting Actor", "Supporting Actress", "Cameo" };

    /**
     * Instantiate an instance of a RoleComboBoxModel.
     */
    public RoleComboBoxModel() {

    }

    /**
     * @return the number of <code>Roles</code> in the model.
     */
    @Override
    public int getSize() {
        return this.roles.length;
    }

    /**
     * @return the <code>Role</code> at the specified index in the
     *         model.
     */
    @Override
    public Object getElementAt(int index) {
        return this.roles[index];
    }

}
