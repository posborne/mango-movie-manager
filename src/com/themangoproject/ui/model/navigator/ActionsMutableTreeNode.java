package com.themangoproject.ui.model.navigator;

import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;

public class ActionsMutableTreeNode extends DefaultMutableTreeNode implements
		MangoMutableTreeNode {
	private static final long serialVersionUID = 5384745252254043000L;

	public ActionsMutableTreeNode() {
		super("Libray Actions");
	}
	
	@Override
	public void doYourThing(Mango mangoPanel) {
		// Do Nothing
	}

}
