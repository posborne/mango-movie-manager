package com.themangoproject.ui.moviepopup;

import javax.swing.JPopupMenu;

public class MoviePopupMenu extends JPopupMenu {

	private static final long serialVersionUID = -6756944460212073447L;
		
	public MoviePopupMenu() {
		DeleteMovieMenuItem deleteMovieMenuItem = new DeleteMovieMenuItem();
		AddToSetMenuItem addToSetMenuItem = new AddToSetMenuItem();
		AddToListMenuItem addToListMenuItem = new AddToListMenuItem();
		this.add(deleteMovieMenuItem);
		this.add(addToSetMenuItem);
		this.add(addToListMenuItem);
	}
	
}
