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
 * Parent node of all the list nodes. This node listens for changes to
 * the set of lists and updates as needed.
 * 
 * @author Paul Osborne
 */
public class ListsMutableTreeNode extends DefaultMutableTreeNode
        implements MangoMutableTreeNode {
    /** Generated UID */
    private static final long serialVersionUID = -2392203223093098396L;
    /** List of lists */
    private List<String> lists;

    /** Construct the node and popuplate */
    public ListsMutableTreeNode() {
        super("Lists", true);
        fetchLists();
        MangoController.getInstance().addListsChangeListener(
                new ListsChangeListener());
    }

    /** Fetch the list of lists from the database and refresh */
    private void fetchLists() {
        this.removeAllChildren();
        lists = MangoController.getInstance().getAllLists();
        for (String list : lists) {
            this.add(new ListMutableTreeNode(list));
        }
        this.add(new AddListMutableTreeNode());
    }

    /** No primary action */
    @Override
    public void doYourThing(Mango mangoPanel) {
    }

    /**
     * Change listener on changes to the set of lists from the
     * underlying data model.
     */
    public class ListsChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            ListsMutableTreeNode.this.fetchLists();
            DefaultTreeModel tm = (DefaultTreeModel) UIController
                    .getInstance().getNavigatorTree().getModel();
            tm.nodeStructureChanged(ListsMutableTreeNode.this);
        }
    }

    /** No popup menu. This is not a leaf */
    @Override
    public JPopupMenu getPopupMenu() {
        return null;
    }
}
