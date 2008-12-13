package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;

public class BorrowedMoviesMutableTreeNode extends DefaultMutableTreeNode
		implements MangoMutableTreeNode {
	private static final long serialVersionUID = -2668933816664112902L;

	public BorrowedMoviesMutableTreeNode() {
		super("Borrowed Movies");
	}
	
	@Override
	public void doYourThing(Mango mangoPanel) {
		// TODO Auto-generated method stub
		System.out.println("Borrowed Movies");
	}

	@Override
	public JPopupMenu getPopupMenu() {
		// TODO Auto-generated method stub
		return null;
	}

}
