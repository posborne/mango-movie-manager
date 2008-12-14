package com.themangoproject.ui.model.navigator;

import com.themangoproject.ui.Mango;
import com.themangoproject.ui.SavedSearchDialog;
import com.themangoproject.ui.UIController;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Paul Osborne
 */
public class CreateSavedSearchMutableTreeNode extends DefaultMutableTreeNode
    implements MangoMutableTreeNode {

    public CreateSavedSearchMutableTreeNode() {
        super("Create Saved Search");
    }

    public void doYourThing(Mango mangoPanel) {
        SavedSearchDialog ssd = new SavedSearchDialog(UIController.getInstance().getMango(), true);
        ssd.setVisible(true);
    }

    public JPopupMenu getPopupMenu() {
        return null; // no popup
    }

}
