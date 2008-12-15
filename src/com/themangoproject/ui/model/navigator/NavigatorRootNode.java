package com.themangoproject.ui.model.navigator;

import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import com.themangoproject.ui.Mango;

/**
 * The root node for the tree contains all the top level nodes.
 * 
 * @author Paul Osborne
 */
class NavigatorRootNode extends DefaultMutableTreeNode implements
        MangoMutableTreeNode {
    /** Generated UID */
    private static final long serialVersionUID = 6737021383031998889L;

    /** Construct the ndoe and add all subnodes */
    public NavigatorRootNode() {
        super("Invisible Root Node", true);
        DefaultMutableTreeNode actions = new ActionsMutableTreeNode();
        DefaultMutableTreeNode allMovies, allPeople, allActors, myMovies, friendsMovies, borrowedMovies, loanedMovies;

        allMovies = new AllMoviesMutableTreeNode();
        myMovies = new MyMoviesMutableTreeNode();
        friendsMovies = new FriendsMoviesMutableTreeNode();
        borrowedMovies = new BorrowedMoviesMutableTreeNode();
        loanedMovies = new LoanedMoviesMutableTreeNode();
        allPeople = new AllPeopleMutableTreeNode();
        allActors = new AllActorsMutableTreeNode();

        actions.add(allMovies);
        actions.add(myMovies);
        actions.add(friendsMovies);
        actions.add(borrowedMovies);
        actions.add(loanedMovies);
        actions.add(allPeople);
        actions.add(allActors);

        SetsMutableTreeNode setsNode = new SetsMutableTreeNode();
        ListsMutableTreeNode listsNode = new ListsMutableTreeNode();
        SavedSearchesMutableTreeNode savedSearchesNode = new SavedSearchesMutableTreeNode();

        this.add(actions);
        this.add(setsNode);
        this.add(listsNode);
        this.add(savedSearchesNode);
    }

    /** Do nothing here */
    @Override
    public void doYourThing(Mango mangoPanel) {
    }

    /** No popup menu */
    @Override
    public JPopupMenu getPopupMenu() {
        return null;
    }

}
