package com.themangoproject.ui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			int index = (Integer) value - 1;
			if (index > -1 && index < movies.size()) {
				Movie m = movies.get(rowIndex);
				movies.remove(rowIndex);
				movies.add(index, m);
				MangoController.getInstance().updateListOrder(label, movies);
				retrieveMovies();
			} else {
				JOptionPane.showMessageDialog(UIController.getInstance().getMango(), 
						"The index must be between 1 and " + movies.size());
			}
		} else {
			super.setValueAt(value, rowIndex, columnIndex);
		}
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
