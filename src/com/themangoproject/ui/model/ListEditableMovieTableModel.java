package com.themangoproject.ui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;
import com.themangoproject.ui.UIController;

public class ListEditableMovieTableModel extends EditableMovieTableModel {

	private static final long serialVersionUID = -2313799858998027018L;
	private List<Movie> movies;
	private ChangeListener moviesChangeListener;
	String label;
	
	public ListEditableMovieTableModel(final String label) {
		this.label = label;
		retrieveMovies();
		moviesChangeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				retrieveMovies();
			}
		};
		MangoController.getInstance().addMoviesChangeListener(moviesChangeListener);
	}
	
	@Override
	public List<Movie> getMovies() {
		return movies;
	}

	@Override
	public void cleanup() {
		MangoController.getInstance().removeListsChangeListener(moviesChangeListener);
	}
	
	public JPopupMenu getPopupMenu() {
		JMenuItem removeFromSetItem = new JMenuItem("Remove From List");
		removeFromSetItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTable table = UIController.getInstance().getViewTable();
				Movie m = ListEditableMovieTableModel.this.getMovieForRow(
						table.getRowSorter().convertRowIndexToModel(table.getSelectedRow()));
				MangoController.getInstance().removeMovieFromList(label, m);
				ListEditableMovieTableModel.this.retrieveMovies();
			}
		});
		JPopupMenu menu = super.getPopupMenu();
		menu.add(removeFromSetItem);
		return menu;
	}

	protected void retrieveMovies() {
		movies = MangoController.getInstance().getListWithLabel(label);
		fireTableStructureChanged();
	}

}
