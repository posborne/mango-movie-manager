package com.themangoproject.ui.model.navigator;

import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;

/**
 * @author Paul Osborne
 */
public class ListMutableTreeNode extends DefaultMutableTreeNode implements
		MangoMutableTreeNode {

	private static final long serialVersionUID = -6490024778496666267L;
	private String listLabel;
	
	public ListMutableTreeNode(String listLabel) {
		super(listLabel);
		this.listLabel = listLabel;
	}
	
	@Override
	public void doYourThing(Mango mangoPanel) {
		// TODO: we want to update the main table with this list
		System.out.println("Show list: " + listLabel);
	}

}
