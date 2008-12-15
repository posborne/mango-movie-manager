package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.ActorTableModel;

/**
 * Node for setting the table to display all actors
 * 
 * @author Paul Osborne
 */
public class AllActorsMutableTreeNode extends DefaultMutableTreeNode implements
		MangoMutableTreeNode {
	/** Generated UID */
	private static final long serialVersionUID = 3785514275551609337L;

	/** Construct the node */
	public AllActorsMutableTreeNode() {
		super("All Actors");
	}
	
	/**
	 * Set the table model to display all actors.
	 */
	@Override
	public void doYourThing(Mango mangoPanel) {
		UIController.getInstance().setViewTableModel(new ActorTableModel());
	}

	/** No popup menu */
	@Override
	public JPopupMenu getPopupMenu() {
		return null;
	}

}
