package com.themangoproject.ui.model.navigator;

import com.themangoproject.ui.Mango;
import com.themangoproject.ui.SavedSearchDialog;
import com.themangoproject.ui.UIController;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Node for creating a saved search
 * 
 * @author Paul Osborne
 */
public class CreateSavedSearchMutableTreeNode extends
        DefaultMutableTreeNode implements MangoMutableTreeNode {

    /** Generated UID */
    private static final long serialVersionUID = -8441600372441319836L;

    /** Create the node */
    public CreateSavedSearchMutableTreeNode() {
        super("Create Saved Search");
    }

    /**
     * Throw up the dialog for creating a saved search.
     */
    public void doYourThing(Mango mangoPanel) {
        SavedSearchDialog ssd = new SavedSearchDialog(UIController
                .getInstance().getMango(), true);
        ssd.setVisible(true);
    }

    /** No popup menu */
    public JPopupMenu getPopupMenu() {
        return null; // no popup
    }

}
