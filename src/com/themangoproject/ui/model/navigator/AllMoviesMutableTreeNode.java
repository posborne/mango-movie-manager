package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import com.themangoproject.ui.Mango;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.AllMoviesEditableTableModel;

/**
 * Node that for action of displaying table containing all movies
 *  
 * @author Paul Osborne
 */
public class AllMoviesMutableTreeNode extends DefaultMutableTreeNode implements
		MangoMutableTreeNode {
	/** Generated UID */
	private static final long serialVersionUID = 6908475537472732561L;

	/** Construct the node */
	public AllMoviesMutableTreeNode() {
		super("All Movies");
	}
	
	/** Set the table to display all movies */
	@Override
	public void doYourThing(Mango mangoPanel) {
		UIController.getInstance().setViewTableModel(new AllMoviesEditableTableModel());
	}

	/** No popup menu */
	@Override
	public JPopupMenu getPopupMenu() {
		return null;
	}

}
