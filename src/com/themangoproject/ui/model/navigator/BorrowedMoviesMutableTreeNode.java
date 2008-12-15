package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;
import com.themangoproject.ui.SetOwnerDialog;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.UnsavedSearchEditabledTableModel;

public class BorrowedMoviesMutableTreeNode extends DefaultMutableTreeNode
		implements MangoMutableTreeNode {
	private static final long serialVersionUID = -2668933816664112902L;

	public BorrowedMoviesMutableTreeNode() {
		super("Borrowed Movies");
	}
	
	@Override
	public void doYourThing(Mango mangoPanel) {
		int ownerId = UIController.getInstance().getOwnerId();
		if (ownerId == -1) {
			SetOwnerDialog d = new SetOwnerDialog(UIController.getInstance().getMango(), true);
			d.setLocationRelativeTo(UIController.getInstance().getMango());
			d.setVisible(true);
			ownerId = UIController.getInstance().getOwnerId();
		}
		String sql = 
			"SELECT id FROM movie" +
			"	WHERE borrower_id = " + ownerId;
		UIController.getInstance().setViewTableModel(new UnsavedSearchEditabledTableModel(sql));
	}

	@Override
	public JPopupMenu getPopupMenu() {
		// TODO Auto-generated method stub
		return null;
	}

}
