package com.themangoproject.ui.model.navigator;

import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;

public class FriendsMoviesMutableTreeNode extends DefaultMutableTreeNode
		implements MangoMutableTreeNode {

	private static final long serialVersionUID = -7769093289119820242L;

	public FriendsMoviesMutableTreeNode() {
		super("Friends Movies");
	}
	
	@Override
	public void doYourThing(Mango mangoPanel) {
		// TODO Auto-generated method stub
		System.out.println("Friend's Movies");
	}

}
