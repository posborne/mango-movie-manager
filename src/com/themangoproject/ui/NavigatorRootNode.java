package com.themangoproject.ui;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Paul Osborne
 */
class NavigatorRootNode extends DefaultMutableTreeNode {

    public NavigatorRootNode() {
        super("Invisible Root Node", true);
        DefaultMutableTreeNode actions = new DefaultMutableTreeNode("Basic Actions?", true);
        actions.add(new DefaultMutableTreeNode("One"));
        actions.add(new DefaultMutableTreeNode("Two"));
        actions.add(new DefaultMutableTreeNode("Three"));
        actions.add(new DefaultMutableTreeNode("Four"));

        DefaultMutableTreeNode savedSearches = new DefaultMutableTreeNode("Saved Searches", true);
        savedSearches.add(new DefaultMutableTreeNode("One"));
        savedSearches.add(new DefaultMutableTreeNode("Two"));
        savedSearches.add(new DefaultMutableTreeNode("Three"));
        savedSearches.add(new DefaultMutableTreeNode("Four"));
        
        DefaultMutableTreeNode lists = new DefaultMutableTreeNode("Lists", true);
        lists.add(new DefaultMutableTreeNode("One"));
        lists.add(new DefaultMutableTreeNode("Two"));
        lists.add(new DefaultMutableTreeNode("Three"));
        lists.add(new DefaultMutableTreeNode("Four"));

        DefaultMutableTreeNode sets = new DefaultMutableTreeNode("Sets", true);
        sets.add(new DefaultMutableTreeNode("One"));
        sets.add(new DefaultMutableTreeNode("Two"));
        sets.add(new DefaultMutableTreeNode("Three"));
        sets.add(new DefaultMutableTreeNode("Four"));

        this.add(actions);
        this.add(savedSearches);
        this.add(lists);
        this.add(sets);
    }

}
