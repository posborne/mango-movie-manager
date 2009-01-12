package com.themangoproject.ui.model;

import javax.swing.JPopupMenu;
import javax.swing.table.TableModel;

public interface MangoTableModelIF extends TableModel {
    /**
     * This method is called when a table model is being removed. If
     * any listeners need to be removed or anything like that, this
     * method will take care of it.
     */
    public void cleanup();

    /**
     * Get the popup menu associated with this model
     * 
     * @return the popup associated with this menu or null if none.
     */
    public JPopupMenu getPopupMenu();


}
