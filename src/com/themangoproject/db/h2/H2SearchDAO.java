package com.themangoproject.db.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeListener;

import com.themangoproject.model.Movie;
import com.themangoproject.model.SearchDAO;

/**
 * @author Paul Osborne
 */
public class H2SearchDAO implements SearchDAO {

    private static H2SearchDAO instance;
    private Connection conn;
    private ArrayList<ChangeListener> changeListeners;

    private PreparedStatement getSavedSearchesPS;
    private static final String getSavedSearchesSQL = "SELECT label FROM saved_searches";

    private PreparedStatement getSavedSearchQueryPS;
    private static final String getSavedSearchQuerySQL = "SELECT query "
            + "	FROM saved_searches" + "	WHERE label LIKE ?";

    private PreparedStatement removeSavedSearchPS;
    private static final String removeSavedSearchSQL = "DELETE FROM saved_searches WHERE label=?";

    private PreparedStatement renameSavedSearchPS;
    private static final String renameSavedSearchSQL = "UPDATE saved_searches SET label=? WHERE label=?";

    private PreparedStatement saveSearchPS;
    private static final String saveSearchSQL = "INSERT INTO saved_searches (label, query) "
            + "	VALUES(?,?)";

    private H2SearchDAO() {
        conn = H2Util.getInstance().getConnection();
        changeListeners = new ArrayList<ChangeListener>();
        try {
            getSavedSearchesPS = conn
                    .prepareStatement(getSavedSearchesSQL);
            getSavedSearchQueryPS = conn
                    .prepareStatement(getSavedSearchQuerySQL);
            removeSavedSearchPS = conn
                    .prepareStatement(removeSavedSearchSQL);
            renameSavedSearchPS = conn
                    .prepareStatement(renameSavedSearchSQL);
            saveSearchPS = conn.prepareStatement(saveSearchSQL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static H2SearchDAO getInstance() {
        if (instance == null) {
            instance = new H2SearchDAO();
        }
        return instance;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.themangoproject.model.SearchDAO#addSavedSearchChangeListener
     * (javax.swing.event.ChangeListener)
     */
    @Override
    public void addSavedSearchChangeListener(ChangeListener l) {
        changeListeners.add(l);
    }

    public String getQueryForLabel(String label) {
        try {
            conn.setAutoCommit(true);
            Statement stat = conn.createStatement();
            String sql = "SELECT query " + "	FROM saved_searches "
                    + "	WHERE label='" + label + "'";
            System.out.println("SQL: " + sql);
            ResultSet results = stat.executeQuery(sql);
            while (results.next()) {
                return results.getString("query");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "WTF";
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.themangoproject.model.SearchDAO#executeSavedSearch(java
     * .lang.String)
     */
    @Override
    public List<Movie> executeSavedSearch(String searchLabel)
            throws SQLException {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        try {
            getSavedSearchQueryPS.setString(1, searchLabel);
            ResultSet rs = getSavedSearchQueryPS.executeQuery();
            rs.first();
            String query = rs.getString(1);
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(query);
            while (rs.next()) {
                DBMovie m = new DBMovie();
                m.setId(rs.getInt("id"));
                movies.add(m);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return movies;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.themangoproject.model.SearchDAO#executeSearch(java.lang
     * .String)
     */
    @Override
    public List<Movie> executeSearch(String query)
            throws SQLException {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(query);
        while (rs.next()) {
            DBMovie m = new DBMovie();
            m.setId(rs.getInt("id"));
            movies.add(m);
        }
        return movies;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.themangoproject.model.SearchDAO#getAllSavedSearches()
     */
    @Override
    public List<String> getAllSavedSearches() {
        ArrayList<String> labels = new ArrayList<String>();
        try {
            ResultSet rs = getSavedSearchesPS.executeQuery();
            while (rs.next()) {
                labels.add(rs.getString("label"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return labels;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.themangoproject.model.SearchDAO#removeSavedSearch(java.
     * lang.String)
     */
    @Override
    public void removeSavedSearch(String searchLabel) {
        try {
            removeSavedSearchPS.setString(1, searchLabel);
            removeSavedSearchPS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        fireSavedSearchesChange();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.themangoproject.model.SearchDAO#removeSavedSearchChangeListener
     * (javax.swing.event.ChangeListener)
     */
    @Override
    public void removeSavedSearchChangeListener(ChangeListener l) {
        changeListeners.remove(l);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.themangoproject.model.SearchDAO#renameSavedSearch(java.
     * lang.String, java.lang.String)
     */
    @Override
    public void renameSavedSearch(String oldLabel, String newLabel) {
        try {
            renameSavedSearchPS.setString(1, newLabel);
            renameSavedSearchPS.setString(2, oldLabel);
            renameSavedSearchPS.executeUpdate();
            fireSavedSearchesChange();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.themangoproject.model.SearchDAO#saveSearch(java.lang.String
     * , java.lang.String)
     */
    @Override
    public void saveSearch(String searchLabel, String query) {
        try {
            saveSearchPS.setString(1, searchLabel);
            saveSearchPS.setString(2, query);
            saveSearchPS.executeUpdate();
            fireSavedSearchesChange();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fireSavedSearchesChange() {
        System.out.println("Firing saved searches ChangeEvent");
        for (ChangeListener l : changeListeners) {
            l.stateChanged(null);
        }
    }

}
