package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;

public class LoanedMoviesMutableTreeNode extends DefaultMutableTreeNode
		implements MangoMutableTreeNode {
	private static final long serialVersionUID = -99871326010884580L;

	public LoanedMoviesMutableTreeNode() {
		super("Loaned Movies");
	}
	
	@Override
	public void doYourThing(Mango mangoPanel) {
		// TODO Auto-generated method stub
		System.out.println("Loaned Movies");
	}

	@Override
	public JPopupMenu getPopupMenu() {
		// TODO Auto-generated method stub
		return null;
	}

}
