package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.MutableTreeNode;

import com.themangoproject.ui.Mango;

/**
 * MutableTreeNode with methods specific to the Mango Application. It
 * is a system invariant that all nodes in the navigator tree
 * implement this interface.
 * 
 * @author Paul Osborne
 */
public interface MangoMutableTreeNode extends MutableTreeNode {
    /**
     * Execute the primary action associated with the tree node. For
     * clicking on a set this would be to set the table model for the
     * main table to be a set table model with the set that was
     * clicked on.
     * 
     * @param mangoPanel
     *            A reference to the mango panel.
     */
    public void doYourThing(Mango mangoPanel);

    /**
     * Get the popup menu that should be associated with the node. If
     * no popup menu should show up when clicked, this returns null.
     * 
     * @return The popup menu for the item or null if there should not
     *         be one.
     */
    public JPopupMenu getPopupMenu();
}
