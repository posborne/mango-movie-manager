package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;
import com.themangoproject.ui.SetListDialog;

/**
 * Node for adding a new list (launches add list dialog)
 * 
 * @author Paul Osborne
 */
public class AddListMutableTreeNode extends DefaultMutableTreeNode implements MangoMutableTreeNode {
	/** Generated UID */
	private static final long serialVersionUID = 3734416231901383559L;

	/** Construct the node */
	public AddListMutableTreeNode() {
		super("Create New List");
	}

	/** Throw up the add set/list dialog */
	@Override
	public void doYourThing(Mango mangoPanel) {
		SetListDialog sld = new SetListDialog(mangoPanel, true);
		sld.setLocationRelativeTo(mangoPanel);
		sld.setVisible(true);
	}

	/** No popup menu */
	@Override
	public JPopupMenu getPopupMenu() {
		return null;
	}
	
}
