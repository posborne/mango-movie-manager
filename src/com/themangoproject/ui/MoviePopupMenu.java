package com.themangoproject.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.themangoproject.db.h2.DBMovie;
import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;
import com.themangoproject.model.MovieDeleteConflict;
import com.themangoproject.ui.model.EditableMovieTableModel;
import com.themangoproject.ui.model.navigator.AddSetMutableTreeNode;

public class MoviePopupMenu extends JPopupMenu {
	private static final long serialVersionUID = -6756944460212073447L;
		
	public MoviePopupMenu() {
		DeleteMovieMenuItem deleteMovieMenuItem = new DeleteMovieMenuItem();
		AddToSetMenuItem addToSetMenuItem = new AddToSetMenuItem();
		this.add(deleteMovieMenuItem);
		this.add(addToSetMenuItem);
	}
	
	public class DeleteMovieMenuItem extends JMenuItem {
		private static final long serialVersionUID = -362159442828108680L;
		public DeleteMovieMenuItem() {
			super("Delete Movie");
	        this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JTable table = UIController.getInstance().getViewTable();
					EditableMovieTableModel tm = (EditableMovieTableModel)(table.getModel());
					Movie m = tm.getMovieForRow(table.getSelectedRow());
					try {
						MangoController.getInstance().deleteMovie(m);
					} catch (MovieDeleteConflict dc) {
						if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(
								UIController.getInstance().getViewTable(),
								"This movie is in multiple set, list, genre, or " +
								"actor relationships.  \nDeleting will remove all this " +
								"information.  Continue?", 
								"Confirm Delete", 
								JOptionPane.YES_NO_OPTION)) {
							MangoController.getInstance().forceDeleteMovie(m);
						}
					}
				}
	        });

		}
	}
	
	public class AddToSetMenuItem extends JMenu implements ChangeListener {
		private static final long serialVersionUID = -6432079421561921122L;
		public AddToSetMenuItem() {
			super("Add to Set");
			addSets();
		}
		
		@Override
		public void stateChanged(ChangeEvent arg0) {
			this.removeAll();
			addSets();
		}
		
		private void addSets() {
			List<String> sets = MangoController.getInstance().getAllSets();
			MangoController.getInstance().addSetsChangeListener(this);
			for (final String set : sets) {
				JMenuItem item = new JMenuItem(set);
				item.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JTable table = UIController.getInstance().getViewTable();
						int selectedRow = table.getSelectedRow();
						Movie m = ((EditableMovieTableModel)table.getModel()).getMovieForRow(selectedRow);
						MangoController.getInstance().addMovieToSet(set, m);
					}
				});
				this.add(item);
			}
		}
	}
}
