package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.ListEditableMovieTableModel;

/**
 * @author Paul Osborne
 */
public class ListMutableTreeNode extends DefaultMutableTreeNode implements
		MangoMutableTreeNode {

	private static final long serialVersionUID = -6490024778496666267L;
	private String listLabel;

	public ListMutableTreeNode(String listLabel) {
		super(listLabel);
		this.listLabel = listLabel;
	}

	@Override
	public void doYourThing(Mango mangoPanel) {
		UIController.getInstance().setViewTableModel(
				new ListEditableMovieTableModel(listLabel));
	}

	@Override
	public JPopupMenu getPopupMenu() {
		// TODO Auto-generated method stub
		return null;
	}

}
