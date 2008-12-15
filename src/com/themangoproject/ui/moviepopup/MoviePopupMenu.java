package com.themangoproject.ui.moviepopup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import com.themangoproject.model.Movie;
import com.themangoproject.ui.MovieAddEditDialog;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.EditableMovieTableModel;

/**
 * Popup menu for a movie item in a movie JTable.  This has generic options
 * and actions that apply for any movies.  Items can be added on in addition
 * to these.
 * 
 * @author Paul Osborne
 */
public class MoviePopupMenu extends JPopupMenu {
	/** Generated serial UID */
	private static final long serialVersionUID = -6756944460212073447L;
	
	/** Construct the menu and add the subitems */
	public MoviePopupMenu() {
		JMenuItem editMenuItem = new JMenuItem("Edit Movie");
		editMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTable table = UIController.getInstance().getViewTable();
				EditableMovieTableModel tm = (EditableMovieTableModel) (table
						.getModel());
				Movie m = tm.getMovieForRow(table.getRowSorter().convertRowIndexToModel(table.getSelectedRow()));
				MovieAddEditDialog maed = 
		            new MovieAddEditDialog(UIController.getInstance().getMango(), true);
				maed.setLocationRelativeTo(UIController.getInstance().getMango());
				maed.populateData(m);
				maed.setVisible(true);
			}
		});
		
		DeleteMovieMenuItem deleteMovieMenuItem = new DeleteMovieMenuItem();
		AddToSetMenuItem addToSetMenuItem = new AddToSetMenuItem();
		AddToListMenuItem addToListMenuItem = new AddToListMenuItem();
		this.add(editMenuItem);
		this.add(deleteMovieMenuItem);
		this.add(addToSetMenuItem);
		this.add(addToListMenuItem);
	}
	
}
