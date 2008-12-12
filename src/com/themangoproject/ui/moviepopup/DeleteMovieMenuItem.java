package com.themangoproject.ui.moviepopup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;
import com.themangoproject.model.MovieDeleteConflict;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.EditableMovieTableModel;

/**
 * Menu Item for deleting a selected movie from a list.
 * 
 * @author Paul Osborne
 */
public class DeleteMovieMenuItem extends JMenuItem {
	/** Generated serial UID */
	private static final long serialVersionUID = -362159442828108680L;

	/** Set up the menu item */
	public DeleteMovieMenuItem() {
		super("Delete Movie");
		this.addActionListener(new DeleteActionListener());
	}

	/**
	 * Action listener for dealing with when the delete menu item is clicked.
	 * 
	 * @author Paul Osborne
	 */
	public class DeleteActionListener implements ActionListener {

		/**
		 * Handle the action of the delete button click.
		 * 
		 * @param e
		 *            The action event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// Get the movie that they want to delete
			JTable table = UIController.getInstance().getViewTable();
			EditableMovieTableModel tm = (EditableMovieTableModel) (table
					.getModel());
			Movie m = tm.getMovieForRow(table.getSelectedRow());
			
			// Attempt to delete, prompt for force delete
			try {
				MangoController.getInstance().deleteMovie(m);
			} catch (MovieDeleteConflict dc) {
				if (JOptionPane.YES_OPTION == JOptionPane
						.showConfirmDialog(
								UIController.getInstance().getViewTable(),
								"This movie is in multiple set, list, genre, or "
										+ "actor relationships.  \nDeleting will remove all this "
										+ "information.  Continue?",
								"Confirm Delete", JOptionPane.YES_NO_OPTION)) {
					MangoController.getInstance().forceDeleteMovie(m);
				}
			}
		}

	}
}
