package com.themangoproject.ui.model.navigator;

import com.themangoproject.model.MangoController;
import com.themangoproject.ui.Mango;
import com.themangoproject.ui.UIController;
import java.util.List;

import javax.swing.JPopupMenu;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Parent node of all the set nodes.
 * 
 * @author Paul Osborne
 */
public class SetsMutableTreeNode extends DefaultMutableTreeNode
        implements MangoMutableTreeNode {
    /** Generated UID */
    private static final long serialVersionUID = -2616587037657452968L;
    /** List of sets */
    private List<String> sets;

    /** Construct sets node */
    public SetsMutableTreeNode() {
        super("Sets", true);
        fetchSets();
        MangoController.getInstance().addSetsChangeListener(
                new SetsChangeListener());
    }

    /**
     * Fetch the sets from the database.
     */
    private void fetchSets() {
        sets = MangoController.getInstance().getAllSets();
        this.removeAllChildren();
        for (String set : sets) {
            this.add(new SetMutableTreeNode(set));
        }
        this.add(new AddSetMutableTreeNode());
    }

    /** No primary action as this is not a leaf in the tree */
    @Override
    public void doYourThing(Mango mangoPanel) {
    }

    /**
     * Listener for changes to the list of sets from the underlying
     * data model. If a change is detected, regrab and update.
     * 
     * @author Paul Osborne
     */
    public class SetsChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            SetsMutableTreeNode.this.fetchSets();
            DefaultTreeModel tm = (DefaultTreeModel) UIController
                    .getInstance().getNavigatorTree().getModel();
            tm.nodeStructureChanged(SetsMutableTreeNode.this);
        }
    }

    /** No popup menu */
    @Override
    public JPopupMenu getPopupMenu() {
        return null;
    }
}
