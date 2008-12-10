package com.themangoproject.ui.model.navigator;

import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.ActorTableModel;

public class AllActorsMutableTreeNode extends DefaultMutableTreeNode implements
		MangoMutableTreeNode {

	private static final long serialVersionUID = 3785514275551609337L;

	public AllActorsMutableTreeNode() {
		super("All Actors");
	}
	
	@Override
	public void doYourThing(Mango mangoPanel) {
		UIController.getInstance().setViewTableModel(new ActorTableModel());
	}

}
