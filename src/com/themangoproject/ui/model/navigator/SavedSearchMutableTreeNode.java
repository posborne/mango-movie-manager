package com.themangoproject.ui.model.navigator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import com.themangoproject.model.MangoController;
import com.themangoproject.ui.Mango;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.SavedSearchEditableTableModel;

public class SavedSearchMutableTreeNode extends DefaultMutableTreeNode
		implements MutableTreeNode, MangoMutableTreeNode {

	/** Generated Serial UID */
	private static final long serialVersionUID = 6411768283400636797L;

	private String searchLabel;
	
	public SavedSearchMutableTreeNode(String searchLabel) {
        super(searchLabel);
		this.searchLabel = searchLabel;
	}
	
	@Override
	public void doYourThing(Mango mangoPanel) {
		UIController.getInstance().setViewTableModel(new SavedSearchEditableTableModel(this.searchLabel));
	}

	@Override
	public JPopupMenu getPopupMenu() {
		JMenuItem deleteItem = new JMenuItem("Delete Saved Search");
		deleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MangoController.getInstance().removeSavedSearch(searchLabel);
			}
		});
		
		JPopupMenu menu = new JPopupMenu();
		menu.add(deleteItem);
		return menu;
	}

}
