package com.themangoproject.ui.model.navigator;

import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;

public class AllMoviesMutableTreeNode extends DefaultMutableTreeNode implements
		MangoMutableTreeNode {

	public AllMoviesMutableTreeNode() {
		super("All Movies");
	}
	
	@Override
	public void doYourThing(Mango mangoPanel) {
		// TODO Auto-generated method stub
		System.out.println("All Movies");
	}

}
