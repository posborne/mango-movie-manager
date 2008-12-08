package com.themangoproject.ui.model.navigator;

import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;

public class MyMoviesMutableTreeNode extends DefaultMutableTreeNode implements
		MangoMutableTreeNode {
	private static final long serialVersionUID = 7060486692062839309L;

	public MyMoviesMutableTreeNode() {
		super("My Movies");
	}
	
	@Override
	public void doYourThing(Mango mangoPanel) {
		// TODO Auto-generated method stub
		System.out.println("My Movies");
	}

}
