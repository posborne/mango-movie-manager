package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;

/**
 * Tree node for containing the various library actions.
 * 
 * @author Paul Osborne
 */
public class ActionsMutableTreeNode extends DefaultMutableTreeNode implements
		MangoMutableTreeNode {
	/** Generated serial UID */
	private static final long serialVersionUID = 5384745252254043000L;

	/** Construct the node */
	public ActionsMutableTreeNode() {
		super("Libray Actions");
	}
	
	@Override
	public void doYourThing(Mango mangoPanel) {
		// Do Nothing
	}

	@Override
	public JPopupMenu getPopupMenu() {
		return null; // Not a leaf, no need
	}

}
