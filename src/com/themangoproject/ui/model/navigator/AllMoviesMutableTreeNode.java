package com.themangoproject.ui.model.navigator;

import javax.swing.tree.DefaultMutableTreeNode;
import com.themangoproject.ui.Mango;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.AllMoviesEditableTableModel;

public class AllMoviesMutableTreeNode extends DefaultMutableTreeNode implements
		MangoMutableTreeNode {
	private static final long serialVersionUID = 6908475537472732561L;

	public AllMoviesMutableTreeNode() {
		super("All Movies");
	}
	
	@Override
	public void doYourThing(Mango mangoPanel) {
		UIController.getInstance().setViewTableModel(new AllMoviesEditableTableModel());
	}

}
