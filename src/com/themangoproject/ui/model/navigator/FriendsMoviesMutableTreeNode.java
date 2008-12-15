package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;
import com.themangoproject.ui.SetOwnerDialog;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.UnsavedSearchEditabledTableModel;

/**
 * Node for display friends movies (movies not owned by the user)
 * 
 * TODO: this node contains code specific to a database application.
 * If possible this code should be moved down. This simplifies things
 * for this release.
 * 
 * @author Paul Osborne
 */
public class FriendsMoviesMutableTreeNode extends
        DefaultMutableTreeNode implements MangoMutableTreeNode {
    /** Generated UID */
    private static final long serialVersionUID = -7769093289119820242L;

    /** Construct the node */
    public FriendsMoviesMutableTreeNode() {
        super("Friends Movies");
    }

    /**
     * Set the table to display the results of a query for all movies
     * not owned by the user.
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
        String sql = "SELECT id FROM movie" + "	WHERE owner_id <> "
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
