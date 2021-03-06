package com.themangoproject.ui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;
import com.themangoproject.ui.UIController;

/**
 * SetEditableMovieTableModel is a table model for sets.
 * 
 * @author Paul Osborne
 */
public class SetEditableMovieTableModel extends
        EditableMovieTableModel {
    private static final long serialVersionUID = 8384297114625361568L;
    private String label;
    private List<Movie> movies;
    private ChangeListener moviesChangeListener;
    private ChangeListener singleMovieChangeListener;

    /**
     * Constructs a SetEditableMovieTableModel from a given label.
     * 
     * @param label
     *            The label to set up the table model.
     */
    public SetEditableMovieTableModel(final String label) {
        this.label = label;
        movies = new ArrayList<Movie>();
        moviesChangeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                retrieveMovies();
                fireTableStructureChanged();
            }
        };
        singleMovieChangeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                fireTableDataChanged();
            }
        };
        MangoController.getInstance().addMoviesChangeListener(
                moviesChangeListener);
        retrieveMovies();
    }

    /**
     * Retrieves for the given set. Updates listeners.
     */
    private void retrieveMovies() {
        // remove change listeners from old movies
        for (Movie m : movies) {
            m.removeChangeListener(singleMovieChangeListener);
        }
        // add change listeners to new movies
        movies = MangoController.getInstance().getSetWithLabel(label);
        for (Movie m : movies) {
            m.addChangeListener(singleMovieChangeListener);
        }
        fireTableStructureChanged();
    }

    /**
     * Gets the movies that belong to the set.
     * 
     * @return The movies that belong to the set
     */
    @Override
    public List<Movie> getMovies() {
        return movies;
    }

    /**
     * Cleans up listeners for all movies.
     */
    @Override
    public void cleanup() {
        MangoController.getInstance().removeSetsChangeListener(
                moviesChangeListener);
        for (Movie m : movies) {
            m.removeChangeListener(singleMovieChangeListener);
        }
    }

    /**
     * Gets the popup menu for this table model.
     * 
     * @return The popup menu.
     */
    @Override
    public JPopupMenu getPopupMenu() {
        JMenuItem removeFromSetItem = new JMenuItem("Remove From Set");
        removeFromSetItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTable table = UIController.getInstance()
                        .getViewTable();
            int[] selectedRows = table.getSelectedRows();
            int selectedRow2;
            for(int i =0; i < selectedRows.length; i++){
                selectedRow2 = table.getRowSorter().
                    convertRowIndexToModel(selectedRows[i]);
                Movie m = ((EditableMovieTableModel) table
                    .getModel()).getMovieForRow(selectedRow2);
                MangoController.getInstance().removeMovieFromSet(label,
                    m);
            }
                SetEditableMovieTableModel.this.retrieveMovies();
            }
        });
        JPopupMenu menu = super.getPopupMenu();
        menu.add(removeFromSetItem);
        return menu;
    }

}
