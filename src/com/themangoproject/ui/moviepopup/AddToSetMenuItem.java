package com.themangoproject.ui.moviepopup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;
import com.themangoproject.model.SetsDAO.MovieExistsInSetException;
import com.themangoproject.ui.UIController;
import com.themangoproject.ui.model.EditableMovieTableModel;

/**
 * Menu Item that contains the behaviour of adding a movie to a list
 * when selected
 * 
 * @author Paul Osborne
 */
public class AddToSetMenuItem extends JMenu implements ChangeListener {

    /** Generated serial UID */
    private static final long serialVersionUID = -6432079421561921122L;

    /** Construct the menu */
    public AddToSetMenuItem() {
        super("Add to Set");
        addSets();
        MangoController.getInstance().addSetsChangeListener(this);
    }

    /**
     * Call this method when the state changes.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        this.removeAll();
        addSets();
    }

    /**
     * Add items for each of the sets in the database. When this is
     * called they are grabbed afresh from the Controller (database).
     */
    private void addSets() {
        List<String> sets = MangoController.getInstance()
                .getAllSets();

        for (final String set : sets) {
            JMenuItem item = new JMenuItem(set);

            // add the action listener to each that performs that
            // adding action
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JTable table = UIController.getInstance()
                            .getViewTable();
                    int selectedRow = table.getRowSorter()
                            .convertRowIndexToModel(
                                    table.getSelectedRow());
                    Movie m = ((EditableMovieTableModel) table
                            .getModel()).getMovieForRow(selectedRow);
                    try {
                        MangoController.getInstance().addMovieToSet(
                                set, m);
                    } catch (MovieExistsInSetException e1) {
                        JOptionPane
                                .showMessageDialog(
                                        UIController.getInstance()
                                                .getMango(),
                                        "The movie \""
                                                + m.getTitle()
                                                + "\" already exists in that set.");
                    }
                }
            });
            this.add(item);
        }
    }

}
