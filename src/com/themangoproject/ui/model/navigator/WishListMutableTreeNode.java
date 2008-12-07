package com.themangoproject.ui.model.navigator;

import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;

public class WishListMutableTreeNode extends DefaultMutableTreeNode implements
		MangoMutableTreeNode {
	private static final long serialVersionUID = -5636817461368261287L;

	public WishListMutableTreeNode() {
		super("Wish List");
	}
	
	@Override
	public void doYourThing(Mango mangoPanel) {
		// TODO: display wish list
		System.out.println("Wish List");
	}

}
