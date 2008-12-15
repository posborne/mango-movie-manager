package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;
import com.themangoproject.ui.SetListDialog;

/**
 * Item for throwing up the add set dialog.
 * 
 * @author Paul Osborne
 */
public class AddSetMutableTreeNode extends DefaultMutableTreeNode
        implements MangoMutableTreeNode {
    /** Generated UID */
    private static final long serialVersionUID = -7776990477125186361L;

    /** Construct the node */
    public AddSetMutableTreeNode() {
        super("Add New Set");
    }

    /** Throw up the add set dialog */
    @Override
    public void doYourThing(Mango mangoPanel) {
        SetListDialog sld = new SetListDialog(mangoPanel, true);
        sld.setLocationRelativeTo(mangoPanel);
        sld.setSetSelected();
        sld.setVisible(true);
    }

    /** No popup */
    @Override
    public JPopupMenu getPopupMenu() {
        return null;
    }

}
