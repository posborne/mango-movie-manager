package com.themangoproject.ui.moviepopup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.EditableMovieTableModel;

public class AddToListMenuItem extends JMenu implements ChangeListener {

	/**
	 * ActionListener for handling the action of having a list with a specific
	 * label clicked on... That is, add the movie to the list.
	 */
	public class AddToListActionListener implements ActionListener {
		private String label;

		public AddToListActionListener(String listLabel) {
			this.label = listLabel;
		}

		public void actionPerformed(ActionEvent e) {
			// Get the selected movie
			JTable table = UIController.getInstance().getViewTable();
			int selectedRow = table.getSelectedRow();
			Movie m = ((EditableMovieTableModel) table.getModel())
					.getMovieForRow(selectedRow);
			
			// add the movie
			MangoController.getInstance().addMovieToList(this.label, m);
		}
	}

	/** Generated serial UID */
	private static final long serialVersionUID = -2087640290190972545L;

	public AddToListMenuItem() {
		super("Add to List");
		addLists();
		MangoController.getInstance().addListsChangeListener(this);
	}

	/**
	 * All all lists to the menu as action items that add the selected movie to
	 * the end of the list.
	 */
	private void addLists() {
		List<String> lists = MangoController.getInstance().getAllLists();
		for (String list : lists) {
			JMenuItem item = new JMenuItem(list);
			item.addActionListener(new AddToListActionListener(list));
			this.add(item);
		}
	}

	/**
	 * ChangeListener event listening to changes to the set of lists. When a
	 * change is detected, remove and add again all from the database.
	 * 
	 * @param e
	 *            The change event
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		this.removeAll();
		this.addLists();
	}

}
