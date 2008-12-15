package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.PersonTableModel;

/**
 * Node for action of displaying all people in the main table
 * 
 * @author Paul Osborne
 */
public class AllPeopleMutableTreeNode extends DefaultMutableTreeNode
        implements MangoMutableTreeNode {
    /** Generated UID */
    private static final long serialVersionUID = 3865751213606263409L;

    /** Construct the node */
    public AllPeopleMutableTreeNode() {
        super("All People");
    }

    /** Set table to show model of all persons */
    @Override
    public void doYourThing(Mango mangoPanel) {
        UIController.getInstance().setViewTableModel(
                new PersonTableModel());
    }

    /** No popup menu */
    @Override
    public JPopupMenu getPopupMenu() {
        return null;
    }

}
