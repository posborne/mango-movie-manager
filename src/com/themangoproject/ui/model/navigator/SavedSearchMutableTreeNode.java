package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import com.themangoproject.model.MangoController;
import com.themangoproject.ui.Mango;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.SavedSearchEditableTableModel;
import com.themangoproject.ui.model.SearchEditableTableModel;

public class SavedSearchMutableTreeNode extends DefaultMutableTreeNode
		implements MutableTreeNode, MangoMutableTreeNode {

	/** Generated Serial UID */
	private static final long serialVersionUID = 6411768283400636797L;

	private String searchLabel;
	
	public SavedSearchMutableTreeNode(String searchLabel) {
		this.searchLabel = searchLabel;
	}
	
	@Override
	public void doYourThing(Mango mangoPanel) {
		UIController.getInstance().setViewTableModel(new SavedSearchEditableTableModel(searchLabel));
	}

	@Override
	public JPopupMenu getPopupMenu() {
		// TODO Auto-generated method stub
		return null;
	}

}
