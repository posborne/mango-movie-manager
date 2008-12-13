package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.MutableTreeNode;

import com.themangoproject.ui.Mango;

/**
 * @author Paul Osborne
 */
public interface MangoMutableTreeNode extends MutableTreeNode {
	public void doYourThing(Mango mangoPanel);
	public JPopupMenu getPopupMenu();
}
