package com.themangoproject.ui.model.navigator;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author Paul Osborne
 */
class NavigatorRootNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 6737021383031998889L;
        
	public NavigatorRootNode() {
        super("Invisible Root Node", true);
        DefaultMutableTreeNode actions = new ActionsMutableTreeNode();
        DefaultMutableTreeNode allMovies, myMovies, friendsMovies, borrowedMovies,
                loanedMovies, wishList;
        
        allMovies = new AllMoviesMutableTreeNode();
        myMovies = new MyMoviesMutableTreeNode();
        friendsMovies = new FriendsMoviesMutableTreeNode();
        borrowedMovies = new BorrowedMoviesMutableTreeNode();
        loanedMovies = new LoanedMoviesMutableTreeNode();
        wishList = new WishListMutableTreeNode();
        
        actions.add(allMovies);
        actions.add(myMovies);
        actions.add(friendsMovies);
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