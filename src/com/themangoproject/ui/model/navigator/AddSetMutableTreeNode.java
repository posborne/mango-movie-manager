package com.themangoproject.ui.model.navigator;

import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;
import com.themangoproject.ui.SetListDialog;

/**
 * @author Paul Osborne
 */
public class AddSetMutableTreeNode extends DefaultMutableTreeNode implements
		MangoMutableTreeNode {

	private static final long serialVersionUID = -7776990477125186361L;

	public AddSetMutableTreeNode() {
		super("Add New Set");
	}
	
	@Override
	public void doYourThing(Mango mangoPanel) {
		SetListDialog sld = new SetListDialog(mangoPanel, true);
		sld.setLocationRelativeTo(mangoPanel);
		sld.setSetSelected();
		sld.setVisible(true);
	}

}
