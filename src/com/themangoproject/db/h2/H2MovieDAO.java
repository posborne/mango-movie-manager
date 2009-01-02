package com.themangoproject.db.h2;

import com.themangoproject.model.*;
import java.util.*;
import java.awt.Image;
import java.io.*;
import java.net.MalformedURLException;
import java.sql.*;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.event.ChangeListener;

/**
 * The <code>H2MovieDAO</code> is an <code>MovieDAO</code> that
 * provides methods for communicating with the H2 database system,
 * specifically with regards to queries and updates that affect the
 * <code>Movie</code> business object.
 * 
 * The DAO is a singleton and obtains and manages its own database
 * connection.
 * 
 * @author Paul Osborne
 * @version November 28, 2008
 */
public class H2MovieDAO implements MovieDAO {

    /** Singleton instance of the object */
    private static H2MovieDAO instance;

    /** Database connection for the DAO */
    private Connection conn;

    private ArrayList<ChangeListener> moviesChangeListeners;

    /** Retrieve ids of all movies in the database */
    private PreparedStatement allMoviesPS;
    private static final String allMoviesSQL = "SELECT id FROM movie";

    /** Add a movie to the database */
    private PreparedStatement addMoviePS;
    private static final String addMovieSQL = "INSERT INTO movie "
            + "(director, title, rating, runtime, year, asin, purchase_date, "
            + "custom_description, condition, type, mango_rating, owner_id, borrower_id)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /** Update a movie an existing movie in the database */
    private PreparedStatement updateMoviePS;
    private static final String updateMoviesSQL = "UPDATE movie"
            + " SET director=?, title=?, rating=?, runtime=?, year=?, asin=?,"
            + " purchase_date=?, custom_description=?, condition=?, type=?,"
            + " mango_rating=?, owner_id=?, borrower_id=?"
            + " WHERE id=?";

    /** Delete movie with specified id from the database */
    private PreparedStatement deleteMoviePS;
    private static final String deleteMovieSQL = "DELETE FROM movie WHERE id=?";

    /** Retrieve all genres for a movie */
    private PreparedStatement genresForMoviePS;
    private static final String genresForMovieSQL = "SELECT name"
            + " FROM movie, genre" + " WHERE movie_id=id AND id=?";

    /** Retrieve all actors for a movie */
    private PreparedStatement actorsForMoviePS;
    private static final String actorsForMovieSQL = "SELECT actor_id"
            + " FROM movie, acting_roles"
            + " WHERE movie_id=id AND id=?";

    private PreparedStatement populateMoviePS;
    private static final String populateMovieSQL = "SELECT * FROM movie"
            + " WHERE id=?";

    private PreparedStatement addActorToMoviePS;
    private static final String addActorToMovieSQL = "INSERT INTO acting_roles"
            + " (movie_id, actor_id, role, character) VALUES (?, ?, ?, ?)";

    private PreparedStatement removeActorFromMoviePS;
    private static final String removeActorFromMovieSQL = "DELETE FROM acting_roles"
            + " WHERE movie_id=? AND actor_id=?";

    private PreparedStatement addGenreToMoviePS;
    private static final String addGenreToMovieSQL = "INSERT INTO genre "
            + "(movie_id, name)" + " VALUES (?, ?)";

    private PreparedStatement removeGenreFromMoviePS;
    private static final String removeGenreFromMovieSQL = "DELETE FROM genre"
            + " WHERE movie_id=? AND name=?";

    /** Remove tuples with reference to movie from genre table */
    private PreparedStatement removeMovieFromGenreTablePS;
    private static final String removeMovieFromGenreTableSQL = "DELETE FROM genre WHERE movie_id=?";

    /** Remove tuples with reference to movie from lists table */
    private PreparedStatement removeMovieFromListsPS;
    private static final String removeMovieFromListsSQL = "DELETE FROM list_contents WHERE movie_id=?";

    /** Remove tuples with reference to movie from sets table */
    private PreparedStatement removeMovieFromSetsPS;
    private static final String removeMovieFromSetsSQL = "DELETE FROM set_contents WHERE movie_id=?";

    /** Remove tuples with reference to movie from acting_roles table */
    private PreparedStatement removeMovieFromActingRolesPS;
    private static final String removeMovieFromActingRolesSQL = "DELETE FROM acting_roles WHERE movie_id=?";

    /** Set the image data for the specified movie */
    private PreparedStatement setImageDataForMoviePS;
    private static final String setImageDataForMovieSQL = "UPDATE movie SET cover_art=? WHERE id=?";

    /** Get the image data for the specified movie */
    private PreparedStatement getImageForMoviePS;
    private static final String getImageForMovieSQL = "SELECT cover_art FROM movie WHERE id=?";

    /** Get last inserted id */
    private PreparedStatement lastInsertPS;
    private static final String lastInsertSQL = "SELECT last_insert_id()";

    /**
     * The private singleton constructor for the DAO initializes the
     * different prepared statements that will be used by the class.
     */
    private H2MovieDAO() {
        conn = H2Util.getInstance().getConnection();
        moviesChangeListeners = new ArrayList<ChangeListener>();
        try {
            allMoviesPS = conn.prepareStatement(allMoviesSQL);
            updateMoviePS = conn.prepareStatement(updateMoviesSQL);
            addMoviePS = conn.prepareStatement(addMovieSQL);
            deleteMoviePS = conn.prepareStatement(deleteMovieSQL);
            genresForMoviePS = conn
                    .prepareStatement(genresForMovieSQL);
            actorsForMoviePS = conn
                    .prepareStatement(actorsForMovieSQL);
            populateMoviePS = conn.prepareStatement(populateMovieSQL);
            addActorToMoviePS = conn
                    .prepareStatement(addActorToMovieSQL);
            removeActorFromMoviePS = conn
                    .prepareStatement(removeActorFromMovieSQL);
            addGenreToMoviePS = conn
                    .prepareStatement(addGenreToMovieSQL);
            removeGenreFromMoviePS = conn
                    .prepareStatement(removeGenreFromMovieSQL);
            removeMovieFromActingRolesPS = conn
                    .prepareStatement(removeMovieFromActingRolesSQL);
            removeMovieFromGenreTablePS = conn
                    .prepareStatement(removeMovieFromGenreTableSQL);
            removeMovieFromListsPS = conn
                    .prepareStatement(removeMovieFromListsSQL);
            removeMovieFromSetsPS = conn
                    .prepareStatement(removeMovieFromSetsSQL);
            setImageDataForMoviePS = conn
                    .prepareStatement(setImageDataForMovieSQL);
            getImageForMoviePS = conn
                    .prepareStatement(getImageForMovieSQL);
            lastInsertPS = conn.prepareStatement(lastInsertSQL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @return The singleton instance of the DAO.
     */
    public static H2MovieDAO getInstance() {
        if (instance == null) {
            instance = new H2MovieDAO();
        }
        return instance;
    }

    /**
     * @return A list of all the movies in the database.
     */
    public List<Movie> getAllMovies() {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        try {
            ResultSet results = allMoviesPS.executeQuery();
            while (results.next()) {
                DBMovie movie = new DBMovie();
                movie.setId(results.getInt("id"));
                movies.add(movie);
            }
            results.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    /**
     * Update the specified movie in the database. That is, make the
     * state of the database be the same as the state of the object.
     * 
     * @param m
     *            The movie that should be updated in the database.
     * @throws ClassCastException
     *             if the Movie specified is not a CD
     */
    public void updateMovie(Movie m) {
        if (!(m instanceof DBMovie)) {
            throw new ClassCastException();
        }
        DBMovie movie = (DBMovie) m;

        try {
            updateMoviePS.setString(1, movie.getDirector());
            updateMoviePS.setString(2, movie.getTitle());
            updateMoviePS.setString(3, movie.getRating());
            updateMoviePS.setInt(4, movie.getRuntime());
            updateMoviePS.setInt(5, movie.getYear());
            updateMoviePS.setString(6, movie.getASIN());
            updateMoviePS.setDate(7, (java.sql.Date) movie
                    .getPurchaseDate());
            updateMoviePS.setString(8, movie.getCustomDescription());
            updateMoviePS.setString(9, movie.getCondition());
            updateMoviePS.setString(10, movie.getType());
            updateMoviePS.setInt(11, movie.getMangoRating());

            if (movie.getOwnerId() != -1) {
                updateMoviePS.setInt(12, movie.getOwnerId());
            } else {
                updateMoviePS.setNull(12, Types.INTEGER);
            }

            if (movie.getBorrowerId() != -1) {
                updateMoviePS.setInt(13, movie.getBorrowerId());
            } else {
                updateMoviePS.setNull(13, Types.INTEGER);
            }
            updateMoviePS.setInt(14, movie.getId());

            // Do it!
            updateMoviePS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Add the specified movie to the database.
     */
    public Movie addMovie(String title, String director,
            String rating, int runtime, int year, String asin,
            Date purchaseDate, String customDescription,
            String condition, String type, int mangoRating) {
        int movieId = -1;
        try {
            conn.setAutoCommit(false);
            addMoviePS.setString(1, director);
            addMoviePS.setString(2, title);
            addMoviePS.setString(3, rating);

            if (runtime == -1) {
                addMoviePS.setNull(4, Types.INTEGER);
            } else {
                addMoviePS.setInt(4, runtime);
            }

            if (year == -1) {
                addMoviePS.setNull(5, Types.INTEGER);
            } else {
                addMoviePS.setInt(5, year);
            }
            addMoviePS.setString(6, asin);

            if (purchaseDate == null) {
                addMoviePS.setNull(7, Types.DATE);
            } else {
                addMoviePS.setDate(7, (java.sql.Date) purchaseDate);
            }

            addMoviePS.setString(8, customDescription);
            addMoviePS.setString(9, condition);
            addMoviePS.setString(10, type);
            addMoviePS.setInt(11, mangoRating);
            addMoviePS.setNull(12, Types.INTEGER);
            addMoviePS.setNull(13, Types.INTEGER);

            // Do it!
            addMoviePS.executeUpdate();
            ResultSet rs = lastInsertPS.executeQuery();
            if (rs.first()) {
                movieId = rs.getInt(1);
            }
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        fireMoviesChangedEvent();
        DBMovie m = new DBMovie();
        m.setId(movieId);
        return m;
    }

    /**
     * Delete the specified movie from the database.
     * 
     * @param m
     *            The movie that should be deleted from the database.
     * @throws ClassCastException
     *             if the movie specified is not a DBMovie
     * @throws MovieDeleteConflict
     *             if the movie cannot be deleted because there are
     *             references to the move in other tables. To override
     *             this, forceDeleteMovie must be called instead.
     */
    public void deleteMovie(Movie m) throws MovieDeleteConflict {
        if (!(m instanceof DBMovie)) {
            throw new ClassCastException();
        }
        DBMovie movie = (DBMovie) m;
        try {
            deleteMoviePS.setInt(1, movie.getId());
            deleteMoviePS.execute();
        } catch (SQLException ex) {
            throw new MovieDeleteConflict();
        }
        fireMoviesChangedEvent();
    }

    /**
     * Forcibly delete a movie from the database, removing any tuples
     * in other relations which contains references to the movie. This
     * involves removing from sets, lists, and genre relations.
     * 
     * @param m
     *            The movie that should be removed from the database,
     *            along with all associated data entries involving the
     *            movie.
     */
    public void forceDeleteMovie(Movie m) {
        if (!(m instanceof DBMovie)) {
            throw new ClassCastException();
        }
        DBMovie movie = (DBMovie) m;
        System.out.println("Force Deleting: " + m.getTitle());

        try {
            // We want to submit statements as transaction
            conn.setAutoCommit(false);

            // prepare the statements
            removeMovieFromActingRolesPS.setInt(1, movie.getId());
            removeMovieFromGenreTablePS.setInt(1, movie.getId());
            removeMovieFromListsPS.setInt(1, movie.getId());
            removeMovieFromSetsPS.setInt(1, movie.getId());
            deleteMoviePS.setInt(1, movie.getId());

            // execute the updates
            removeMovieFromActingRolesPS.executeUpdate();
            removeMovieFromGenreTablePS.executeUpdate();
            removeMovieFromListsPS.executeUpdate();
            removeMovieFromSetsPS.executeUpdate();
            deleteMoviePS.executeUpdate();

            // commit the transaction
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        fireMoviesChangedEvent();
    }

    /**
     * Retrieve a list of all genres for the specified movie.
     * 
     * @param m
     *            The movie for which genres should be retrieved.
     * @return A List of genres for the movie.
     * @throws ClassCastException
     *             if the specified movie is note a
     *             <code>DBMovie</code>.
     */
    public List<String> getGenresForMovie(Movie m) {
        if (!(m instanceof DBMovie)) {
            throw new ClassCastException();
        }
        ArrayList<String> genres = new ArrayList<String>();
        DBMovie movie = (DBMovie) m;

        try {
            genresForMoviePS.setInt(1, movie.getId());
            ResultSet results = genresForMoviePS.executeQuery();
            while (results.next()) {
                String genre = results.getString("name");
                genres.add(genre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return genres;
    }

    /**
     * Retrieve a list of all the actors for a movie.
     * 
     * @param m
     *            The movie whose actors we would like to retrieve
     * @return A list of all the actors for the movie
     * @throws ClassCastException
     *             if the movie is not a DBMovie
     */
    public List<Actor> getActorsForMovie(Movie m) {
        if (!(m instanceof DBMovie)) {
            throw new ClassCastException();
        }
        ArrayList<Actor> actors = new ArrayList<Actor>();
        DBMovie movie = (DBMovie) m;

        try {
            actorsForMoviePS.setInt(1, movie.getId());
            ResultSet results = actorsForMoviePS.executeQuery();
            while (results.next()) {
                DBActor actor = new DBActor();
                actor.setId(results.getInt("actor_id"));
                actors.add(actor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return actors;
    }

    /**
     * Query a DBMovie and update all its fields so that the state of
     * the object matches the state of the database.
     * 
     * @param m
     *            The movie which should be updated.
     */
    public void getMovieInfo(Movie m) {
        if (!(m instanceof DBMovie)) {
            throw new ClassCastException();
        }
        DBMovie movie = (DBMovie) m;

        try {
            populateMoviePS.setInt(1, movie.getId());
            ResultSet rs = populateMoviePS.executeQuery();
            while (rs.next()) {
                movie.setASIN(rs.getString("asin"));
                movie.setCondition(rs.getString("condition"));

                // set the borrower
                int borrowerId = rs.getInt("borrower_id");
                if (borrowerId <= 0) {
                    movie.setBorrower(null);
                } else {
                    DBPerson newPerson = new DBPerson();
                    newPerson.setId(borrowerId);
                    movie.setBorrower(newPerson);
                }

                movie.setCustomDescription(rs
                        .getString("custom_description"));
                movie.setDirector(rs.getString("director"));
                movie.setMangoRating(rs.getInt("mango_rating"));
                movie.setRating(rs.getString("rating"));

                // set the owner
                int ownerId = rs.getInt("owner_id");
                if (ownerId <= 0) {
                    movie.setOwner(null);
                } else {
                    DBPerson newOwner = new DBPerson();
                    newOwner.setId(ownerId);
                    movie.setOwner(newOwner);
                }

                movie.setPurchaseDate(rs.getDate("purchase_date"));
                movie.setRuntime(rs.getInt("runtime"));
                movie.setTitle(rs.getString("title"));
                movie.setType(rs.getString("type"));

                int year = rs.getInt("year");
                if (year == 0) {
                    year = -1;
                }
                movie.setYear(year);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Add the specified actor, role, and character to a movie
     * <code>m</code>.
     * 
     * @param m
     *            The movie to which an actor is being added.
     * @param a
     *            The actor to add to the movie.
     * @param role
     *            The role the actor plays in the movie.
     * @param character
     *            The character the actor plays in the movie.
     */
    public void addActorToMovie(Movie m, Actor a, String role,
            String character) {
        if (!(m instanceof DBMovie) && !(a instanceof DBActor)) {
            throw new ClassCastException();
        }
        DBMovie movie = (DBMovie) m;
        DBActor actor = (DBActor) a;
        try {
            addActorToMoviePS.setInt(1, movie.getId());
            addActorToMoviePS.setInt(2, actor.getId());
            addActorToMoviePS.setString(3, role);
            addActorToMoviePS.setString(4, character);
            addActorToMoviePS.executeUpdate();
        } catch (SQLException ex) {
//            ex.printStackTrace();
        }
    }

    /**
     * Removes an actor <code>a</code> from a movie <code>m</code>.
     * 
     * @param m
     *            The movie to remove the actor from.
     * @param a
     *            The actor to remove from the movie.
     */
    public void removeActorFromMovie(Movie m, Actor a) {
        if (!(m instanceof DBMovie) && !(a instanceof DBActor)) {
            throw new ClassCastException();
        }
        DBMovie movie = (DBMovie) m;
        DBActor actor = (DBActor) a;
        try {
            removeActorFromMoviePS.setInt(1, movie.getId());
            removeActorFromMoviePS.setInt(2, actor.getId());
            removeActorFromMoviePS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Add the specified genre to the movie if it does not exist.
     * 
     * @param m
     *            The movie to which the genre should be added.
     * @param genre
     *            The genre that should be added to the movie.
     * 
     * @throws GenreExistsException
     *             if the genre specified already exists for the
     *             specified movie
     */
    public void addGenreToMovie(Movie m, String genre) {
        if (!(m instanceof DBMovie)) {
            throw new ClassCastException();
        }
        DBMovie movie = (DBMovie) m;

        try {
            addGenreToMoviePS.setInt(1, movie.getId());
            addGenreToMoviePS.setString(2, genre);
            addGenreToMoviePS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Remove the specified genre from the specified movie.
     * 
     * @param m
     *            The movie from which the genre should be removed
     * @param genre
     *            The name of the genre that should be removed from
     *            the movie.
     * 
     * @throws GenreExistsException
     *             if the genre specified already exists for the
     *             specified movie
     */
    public void removeGenreFromMovie(Movie m, String genre) {
        if (!(m instanceof DBMovie)) {
            throw new ClassCastException();
        }
        DBMovie movie = (DBMovie) m;
        try {
            removeGenreFromMoviePS.setInt(1, movie.getId());
            removeGenreFromMoviePS.setString(2, genre);
            removeGenreFromMoviePS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Store the specified image in the database, associated with the
     * id of the provided movie.
     * 
     * @param m
     *            The movie the image should be associated with.
     * @param image
     *            The image that should be added to the database.
     * @throws MalformedURLException
     *             if the URL is not valid.
     */
    public void setImageForMovie(InputStream is, Movie m) {
        if (!(m instanceof DBMovie)) {
            throw new ClassCastException();
        }
        DBMovie movie = (DBMovie) m;
        try {
            setImageDataForMoviePS.setInt(2, movie.getId());
            if (is != null) {
                setImageDataForMoviePS.setBinaryStream(1, is);
            } else {
                setImageDataForMoviePS.setNull(1, Types.BLOB);
            }
            setImageDataForMoviePS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Get the cover art image for the specified movie from the
     * database. This will return null if there is no image stored.
     * 
     * @param m
     *            The movie whose cover art we would like to retrieve
     */
    public Image getImageForMovie(Movie m) {
        if (!(m instanceof DBMovie)) {
            throw new ClassCastException();
        }
        DBMovie movie = (DBMovie) m;
        ImageIcon image = null;

        try {
            getImageForMoviePS.setInt(1, movie.getId());
            ResultSet rs = getImageForMoviePS.executeQuery();
            if (rs.first()) {
                InputStream input = rs.getBinaryStream("cover_art");
                if (input == null) {
                    return null;
                }
                // setup the streams
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                // set read buffer size
                byte[] rb = new byte[1024];
                int ch = 0;
                while ((ch = input.read(rb)) != -1) {
                    // process blob
                    output.write(rb, 0, ch);
                }
                byte[] b = output.toByteArray();
                input.close();
                output.close();
                image = new ImageIcon(b);
            }
        } catch (SQLException ex) {
            image = null;
            ex.printStackTrace();
        } catch (IOException e) {
            image = null;
        }
        return image.getImage();
    }

    /**
     * Add a change listener that will be notified when movies are
     * added or deleted from the database.
     * 
     * @param l
     *            The change listener that should be added.
     */
    public void addMoviesChangeListener(ChangeListener l) {
        moviesChangeListeners.add(l);
    }

    /**
     * Remove all change listeners.
     */
    public void removeAllMoviesChangeListeners() {
        moviesChangeListeners.clear();
    }

    /**
     * Remove the specified change listener.
     * 
     * @param l
     *            The change listener that should be removed.
     */
    public void removeMoviesChangeListener(ChangeListener l) {
        moviesChangeListeners.remove(l);
    }

    /**
     * Inform all listeners that things have changed.
     */
    private void fireMoviesChangedEvent() {
        for (ChangeListener l : moviesChangeListeners) {
            l.stateChanged(null);
        }
    }

    public void removeAllRolesForMovie(Movie m) {
        for( Actor actor : this.getActorsForMovie(m)){
            this.removeActorFromMovie(m, actor);
        }
    }

}
