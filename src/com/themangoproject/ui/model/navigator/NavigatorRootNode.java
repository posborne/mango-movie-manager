package com.themangoproject.ui.model.navigator;

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
        DefaultMutableTreeNode allMovies, myMovies, friendsMovies, borrowedMovies,
                loanedMovies, wishList;
        
        allMovies = new DefaultMutableTreeNode("All Movies");
        myMovies = new DefaultMutableTreeNode("My Movies");
        friendsMovies = new DefaultMutableTreeNode("Friend's Movies");
        borrowedMovies = new DefaultMutableTreeNode("Borrowed Movies");
        loanedMovies = new DefaultMutableTreeNode("Loaned Movies");
        wishList = new DefaultMutableTreeNode("Wish List");
        
        actions.add(allMovies);
        actions.add(myMovies);
        actions.add(borrowedMovies);
        actions.add(loanedMovies);
        actions.add(wishList);
        
        SetsMutableTreeNode setsNode = new SetsMutableTreeNode();
        ListsMutableTreeNode listsNode = new ListsMutableTreeNode();

        this.add(actions);
        this.add(setsNode);
        this.add(listsNode);
    }

}
