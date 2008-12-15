package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;
import com.themangoproject.ui.SetOwnerDialog;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.UnsavedSearchEditabledTableModel;

/**
 * Tree node for making the main table display the set of all borrowed
 * movies.
 * 
 * TODO: this node contains code specific to a database application.
 * If possible this code should be moved down. This simplifies things
 * for this release.
 * 
 * @author Paul Osborne
 */
public class BorrowedMoviesMutableTreeNode extends
        DefaultMutableTreeNode implements MangoMutableTreeNode {
    /** Generated UID */
    private static final long serialVersionUID = -2668933816664112902L;

    /** Construct the node */
    public BorrowedMoviesMutableTreeNode() {
        super("Borrowed Movies");
    }

    /**
     * Set the table to display borrowed movies. Borrowed movies are
     * movies where the user is the borrower.
     */
    @Override
    public void doYourThing(Mango mangoPanel) {
        int ownerId = UIController.getInstance().getOwnerId();
        if (ownerId == -1) {
            SetOwnerDialog d = new SetOwnerDialog(UIController
                    .getInstance().getMango(), true);
            d.setLocationRelativeTo(UIController.getInstance()
                    .getMango());
            d.setVisible(true);
            ownerId = UIController.getInstance().getOwnerId();
        }
        String sql = "SELECT id FROM movie" + "	WHERE borrower_id = "
                + ownerId;
        UIController.getInstance().setViewTableModel(
                new UnsavedSearchEditabledTableModel(sql));
    }

    /** No popup menu */
    @Override
    public JPopupMenu getPopupMenu() {
        return null;
    }

}
