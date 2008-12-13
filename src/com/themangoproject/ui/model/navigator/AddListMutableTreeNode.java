/**
 * 
 */
package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;
import com.themangoproject.ui.SetListDialog;

/**
 * @author Paul Osborne
 */
public class AddListMutableTreeNode extends DefaultMutableTreeNode implements MangoMutableTreeNode {
	private static final long serialVersionUID = 3734416231901383559L;

	public AddListMutableTreeNode() {
		super("Create New List");
	}

	@Override
	public void doYourThing(Mango mangoPanel) {
		SetListDialog sld = new SetListDialog(mangoPanel, true);
		sld.setLocationRelativeTo(mangoPanel);
		sld.setVisible(true);
	}

	@Override
	public JPopupMenu getPopupMenu() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
