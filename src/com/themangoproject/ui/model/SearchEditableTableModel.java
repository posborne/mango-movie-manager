package com.themangoproject.ui.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.themangoproject.model.MangoController;
import com.themangoproject.model.Movie;

/**
 * SearchEditableTableModel is an abstract table model for saved
 * searches.
 * 
 * @author Paul Osborne
 */
public abstract class SearchEditableTableModel extends
        EditableMovieTableModel implements MangoTableModelIF {

    /** Serial UID */
    private static final long serialVersionUID = -3378374611042190618L;
    private List<Movie> movies;
    private ChangeListener moviesChangeListener;
    private ChangeListener singleMovieChangeListener;

    /**
     * Constructs a SeachEditableTableModel
     */
    public SearchEditableTableModel() {
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
    }

    // Package visible method that retrieves all movies
    void retrieveMovies() {
        // remove change listeners from old movies
        for (Movie m : movies) {
            m.removeChangeListener(singleMovieChangeListener);
        }
        // add change listeners to new movies
        try {
            movies = executeSearch();
            for (Movie m : movies) {
                m.addChangeListener(singleMovieChangeListener);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes a search and returns a list of movies.
     * 
     * @return A list of movies meeting the search parameters.
     * @throws SQLException
     *             If the search is bad.
     */
    public abstract List<Movie> executeSearch() throws SQLException;

    /**
     * Gets all the movies
     * 
     * @return Gets all the movies
     */
    @Override
    public List<Movie> getMovies() {
        return movies;
    }

    /**
     * Removes movie change listeners.
     */
    @Override
    public void cleanup() {
        MangoController.getInstance().removeSetsChangeListener(
                moviesChangeListener);
        for (Movie m : movies) {
            m.removeChangeListener(singleMovieChangeListener);
        }
    }

}
