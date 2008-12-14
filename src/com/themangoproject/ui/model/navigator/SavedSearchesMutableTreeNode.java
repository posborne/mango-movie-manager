package com.themangoproject.ui.model.navigator;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPopupMenu;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.themangoproject.model.MangoController;
import com.themangoproject.ui.Mango;
import com.themangoproject.ui.UIController;

public class SavedSearchesMutableTreeNode extends DefaultMutableTreeNode
		implements MangoMutableTreeNode, ChangeListener {

	/** Generated serial UID */
	private static final long serialVersionUID = -7843542011465053391L;

	private List<String> savedSearches;
	
	public SavedSearchesMutableTreeNode() {
        super("Saved Searches", true);
		savedSearches = new ArrayList<String>();
		MangoController.getInstance().addSaveSearchListener(this);
		setSearchesList();
	}
	
	private void setSearchesList() {
		this.removeAllChildren();
		savedSearches = MangoController.getInstance().getAllSavedSearches();
		for (String searchLabel : savedSearches) {
			this.add(new SavedSearchMutableTreeNode(searchLabel));
		}
        this.add(new CreateSavedSearchMutableTreeNode());
	}

	@Override
	public void doYourThing(Mango mangoPanel) {
		// do nothing on click
	}

	@Override
	public JPopupMenu getPopupMenu() {
		return null; // no popup menu
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		System.out.println("The state has changed");
		setSearchesList();
		DefaultTreeModel tm = (DefaultTreeModel) UIController.getInstance()
			.getNavigatorTree().getModel();
		tm.nodeStructureChanged(SavedSearchesMutableTreeNode.this);
	}

}
