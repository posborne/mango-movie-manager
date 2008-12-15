package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;
import com.themangoproject.ui.SetOwnerDialog;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.UnsavedSearchEditabledTableModel;

/**
 * Node for setting the table to display all movies on loan from the
 * owner.
 * 
 * TODO: this node contains code specific to a database application.
 * If possible this code should be moved down. This simplifies things
 * for this release.
 * 
 * @author Paul Osborne
 */
public class LoanedMoviesMutableTreeNode extends
        DefaultMutableTreeNode implements MangoMutableTreeNode {
    /** Generated UID */
    private static final long serialVersionUID = -99871326010884580L;

    /** Construct the node */
    public LoanedMoviesMutableTreeNode() {
        super("Loaned Movies");
    }

    /**
     * Set the table to display a model with an underlying query of
     * all movies on loan from the owner.
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
        String sql = "SELECT id FROM movie" + "	WHERE owner_id = "
                + ownerId + "  AND borrower_id IS NOT NULL";
        UIController.getInstance().setViewTableModel(
                new UnsavedSearchEditabledTableModel(sql));

    }

    /** No popup menu */
    @Override
    public JPopupMenu getPopupMenu() {
        return null;
    }

}
