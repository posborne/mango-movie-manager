package com.themangoproject.ui.model.navigator;

import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;

/**
 * @author Paul Osborne
 */
public class SetMutableTreeNode extends DefaultMutableTreeNode implements
		MangoMutableTreeNode {

	private static final long serialVersionUID = -7036137034290049259L;
	private String setLabel;

	public SetMutableTreeNode(String setLabel) {
		super(setLabel);
		this.setLabel = setLabel;
	}
	
	@Override
	public void doYourThing(Mango mangoPanel) {
		// TODO update the table to display the list.
		System.out.println("Displaying set: " + setLabel);
	}

}
