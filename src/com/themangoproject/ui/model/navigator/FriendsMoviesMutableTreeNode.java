package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
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
		System.out.println("Friend's Movies");
	}

	@Override
	public JPopupMenu getPopupMenu() {
		// TODO Auto-generated method stub
		return null;
	}

}
