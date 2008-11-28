package com.themangoproject.ui;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Paul Osborne
 */
class NavigatorRootNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 6737021383031998889L;

	public NavigatorRootNode() {
        super("Invisible Root Node", true);
        DefaultMutableTreeNode actions = new DefaultMutableTreeNode("Library", true);
        actions.add(new DefaultMutableTreeNode("All Movies"));
        actions.add(new DefaultMutableTreeNode("My Movies"));
        actions.add(new DefaultMutableTreeNode("Friend's Movies"));
        actions.add(new DefaultMutableTreeNode("Borrowed Movies"));
        actions.add(new DefaultMutableTreeNode("Loaned Movies"));
        actions.add(new DefaultMutableTreeNode("Wish List"));
        
        DefaultMutableTreeNode sets = new DefaultMutableTreeNode("Sets", true);
        sets.add(new DefaultMutableTreeNode("Create New Set"));
        
        DefaultMutableTreeNode lists = new DefaultMutableTreeNode("Lists", true);
        lists.add(new DefaultMutableTreeNode("Create New List"));
        
        DefaultMutableTreeNode savedSearches = new DefaultMutableTreeNode("Saved Searches", true);
        savedSearches.add(new DefaultMutableTreeNode("Create New Saved Search"));
        
        this.add(actions);
        this.add(savedSearches);
        this.add(lists);
        this.add(sets);
    }

}
