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
                loanedMovies, wishList, allPeople, allActors;
        
        allMovies = new AllMoviesMutableTreeNode();
        myMovies = new MyMoviesMutableTreeNode();
        friendsMovies = new FriendsMoviesMutableTreeNode();
        borrowedMovies = new BorrowedMoviesMutableTreeNode();
        loanedMovies = new LoanedMoviesMutableTreeNode();
        allPeople = new AllPeopleMutableTreeNode();
        allActors = new AllActorsMutableTreeNode();
        wishList = new WishListMutableTreeNode();
        
        actions.add(allMovies);
        actions.add(myMovies);
        actions.add(friendsMovies);
        actions.add(borrowedMovies);
        actions.add(loanedMovies);
        actions.add(allPeople);
        actions.add(allActors);
        actions.add(wishList);
        
        SetsMutableTreeNode setsNode = new SetsMutableTreeNode();
        ListsMutableTreeNode listsNode = new ListsMutableTreeNode();
        SavedSearchesMutableTreeNode savedSearchesNode = new SavedSearchesMutableTreeNode();

        this.add(actions);
        this.add(setsNode);
        this.add(listsNode);
        this.add(savedSearchesNode);
    }

}
