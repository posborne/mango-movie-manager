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

public class MoviePopupMenu extends JPopupMenu {

	private static final long serialVersionUID = -6756944460212073447L;
		
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
