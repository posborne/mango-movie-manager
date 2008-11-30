package com.themangoproject.db.h2;

import com.themangoproject.model.*;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

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

    /** Retrieve ids of all movies in the database */
    private PreparedStatement allMoviesPS;
    private static final String allMoviesQuery = "SELECT id FROM movie";

    /** Add a movie to the database */
    private PreparedStatement addMoviePS;
    private static final String addMovieQuery = "INSERT INTO movie (director, title, rating, runtime, year, asin,"
            + " purchase_date, custom_description, condition, type, mango_rating"
            + " owner_id, borrower_id"
            + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /** Update a movie an existing movie in the database */
    private PreparedStatement updateMoviePS;
    private static final String updateMoviesQuery = "UPDATE movie"
            + " SET director=?, title=?, rating=?, runtime=?, year=?, asin=?,"
            + " purchase_date=?, custom_description=?, condition=?, type=?,"
            + " mango_rating=?, owner_id=?, borrower_id=?"
            + " WHERE id=?";

    /** Delete movie with specified id from the database */
    private PreparedStatement deleteMoviePS;
    private static final String deleteMovieQuery = "DELTE FROM movie"
            + " WHERE id=?";

    /** Retrieve all genres for a movie */
    private PreparedStatement genresForMoviePS;
    private static final String genresForMovieQuery = "SELECT name"
            + " FROM movie, genre" + " WHERE movie_id=id AND id=?";

    /** Retrieve all actors for a movie */
    private PreparedStatement actorsForMoviePS;
    private static final String actorsForMovieQuery = "SELECT actor_id"
            + " FROM movie, acting_roles"
            + " WHERE movie_id=id AND id=?";

    private PreparedStatement populateMoviePS;
    private static final String populateMovieQuery = "SELECT * FROM movie"
            + " WHERE id=?";

    private PreparedStatement addGenreToMoviePS;
    private static final String addGenreToMovieQuery = "INSERT INTO genres (movie_id, name)"
            + " VALUES (?, ?)";

    private PreparedStatement removeGenreFromMoviePS;
    private static final String removeGenreFromMovieQuery = "DELETE FROM movie"
            + " WHERE movie_id=? AND genre=?";

    /**
     * The private singleton constructor for the DAO initializes the
     * different prepared statements that will be used by the class.
     */
    private H2MovieDAO() {
        conn = H2Util.getInstance().getConnection();
        try {
            allMoviesPS = conn.prepareStatement(allMoviesQuery);
            updateMoviePS = conn.prepareStatement(updateMoviesQuery);
            addMoviePS = conn.prepareStatement(addMovieQuery,
                    Statement.RETURN_GENERATED_KEYS);
            deleteMoviePS = conn.prepareStatement(deleteMovieQuery);
            genresForMoviePS = conn
                    .prepareStatement(genresForMovieQuery);
            actorsForMoviePS = conn
                    .prepareStatement(actorsForMovieQuery);
            populateMoviePS = conn
                    .prepareStatement(populateMovieQuery);
            addGenreToMoviePS = conn
                    .prepareStatement(addGenreToMovieQuery);
            removeGenreFromMoviePS = conn
                    .prepareStatement(removeGenreFromMovieQuery);
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
                movie.setTitle(results.getString("title"));
                movie.setASIN(results.getString("asin"));
                movie.setCustomDescription(results
                        .getString("custom_description"));
                movie.setDirector(results.getString("director"));
                // TODO: finish implementing
            }
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
            updateMoviePS.setString(0, movie.getDirector());
            updateMoviePS.setString(1, movie.getTitle());
            updateMoviePS.setString(2, movie.getRating());
            updateMoviePS.setInt(3, movie.getRuntime());
            updateMoviePS.setInt(4, movie.getYear());
            updateMoviePS.setString(5, movie.getASIN());
            // TODO: why does this need casting?
            updateMoviePS.setDate(6, (Date) movie.getPurchaseDate());
            updateMoviePS.setString(7, movie.getCustomDescription());
            updateMoviePS.setString(8, movie.getCondition());
            updateMoviePS.setString(9, movie.getType());
            updateMoviePS.setInt(10, movie.getMangoRating());
            updateMoviePS.setInt(11, movie.getOwnerId());
            updateMoviePS.setInt(12, movie.getBorrowerId());
            updateMoviePS.setInt(13, movie.getId());

            // Do it!
            updateMoviePS.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Add the specified movie to the database.
     * 
     * @param movie
     *            The movie to add to the database.
     */
    public void addMovie(Movie movie) {

        try {
            addMoviePS.setString(0, movie.getDirector());
            addMoviePS.setString(1, movie.getTitle());
            addMoviePS.setString(2, movie.getRating());
            addMoviePS.setInt(3, movie.getRuntime());
            addMoviePS.setInt(4, movie.getYear());
            addMoviePS.setString(5, movie.getASIN());
            addMoviePS.setDate(6, (Date) movie.getPurchaseDate());
            // TODO: why does this need casting?
            addMoviePS.setString(7, movie.getCustomDescription());
            addMoviePS.setString(8, movie.getCondition());
            addMoviePS.setString(9, movie.getType());
            addMoviePS.setInt(10, movie.getMangoRating());
            addMoviePS.setNull(11, Types.INTEGER);
            addMoviePS.setNull(12, Types.INTEGER);

            // Do it!
            addMoviePS.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Delete the specified movie from the database.
     * 
     * @param m
     *            The movie that should be deleted from the database.
     * @throws ClassCastException
     *             if the movie specified is not a DBMovie
     */
    public void deleteMovie(Movie m) {
        if (!(m instanceof DBMovie)) {
            throw new ClassCastException();
        }
        DBMovie movie = (DBMovie) m;
        try {
            deleteMoviePS.setInt(0, movie.getId());
            deleteMoviePS.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
            genresForMoviePS.setInt(0, movie.getId());
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
            actorsForMoviePS.setInt(0, movie.getId());
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
                DBPerson newPerson = new DBPerson();
                newPerson.setId(rs.getInt("borrower_id"));
                movie.setBorrower(newPerson);
                movie.setCustomDescription(rs
                        .getString("custom_description"));
                movie.setDirector(rs.getString("director"));
                movie.setMangoRating(rs.getInt("mango_rating"));

                // set the owner
                DBPerson newOwner = new DBPerson();
                newOwner.setId(rs.getInt("owner_id"));
                movie.setOwner(newOwner);
                movie.setPurchaseDate(rs.getDate("purchase_date"));
                movie.setRuntime(rs.getInt("runtime"));
                movie.setTitle(rs.getString("title"));
                movie.setType(rs.getString("type"));
                movie.setYear(rs.getInt("year"));
                rs.close();
            }
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // TODO: More work for you Paul!! I don't know
    // if this is the best way to handle it, but it was the best
    // I could think of right now.
    public Movie getMovieFromId(int movieID) {
        // TODO Auto-generated method stub
        return null;
    }

}
