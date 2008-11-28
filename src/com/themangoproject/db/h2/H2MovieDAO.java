package com.themangoproject.db.h2;

import com.themangoproject.model.*;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Paul Osborne
 */
public class H2MovieDAO implements MovieDAO {

    private static H2MovieDAO instance;
    private Connection conn;

    private PreparedStatement allMoviesPS;
    private static final String allMoviesQuery =
            "SELECT id FROM movie";

    private PreparedStatement addMoviePS;
    private static final String addMovieQuery =
            "INSERT INTO movie (director, title, rating, runtime, year, asin," +
            " purchase_date, custom_description, condition, type, mango_rating" +
            " owner_id, borrower_id" +
            " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private PreparedStatement updateMoviePS;
    private static final String updateMoviesQuery =
            "UPDATE movie" +
            " SET director=?, title=?, rating=?, runtime=?, year=?, asin=?," +
            " purchase_date=?, custom_description=?, condition=?, type=?," +
            " mango_rating=?, owner_id=?, borrower_id=?" +
            " WHERE id=?";

    private PreparedStatement deleteMoviePS;
    private static final String deleteMovieQuery =
            "DELTE FROM movie" +
            " WHERE id=?";

    private PreparedStatement genresForMoviePS;
    private static final String genresForMovieQuery =
            "SELECT name" +
            " FROM movie, genre" +
            " WHERE movie_id=id AND id=?";

    private PreparedStatement actorsForMoviePS;
    private static final String actorsForMovieQuery =
            "SELECT actor_id" +
            " FROM movie, acting_roles" +
            " WHERE movie_id=id AND id=?";

    /**
     *
     */
    private H2MovieDAO() {
        conn = H2Util.getInstance().getConnection();
        try {
            allMoviesPS = conn.prepareStatement(allMoviesQuery);
            updateMoviePS = conn.prepareStatement(updateMoviesQuery);
            addMoviePS = conn.prepareStatement(addMovieQuery);
            deleteMoviePS = conn.prepareStatement(deleteMovieQuery);
            genresForMoviePS = conn.prepareStatement(genresForMovieQuery);
            actorsForMoviePS = conn.prepareStatement(actorsForMovieQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public static H2MovieDAO getInstance() {
        if (instance == null) {
            instance = new H2MovieDAO();
        }
        return instance;
    }

    /**
     *
     * @return
     */
    public List<Movie> getAllMovies() {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        try {
            ResultSet results = allMoviesPS.executeQuery();
            while (results.next()) {
                DBMovie movie = new DBMovie();
                movie.setTitle(results.getString("title"));
                movie.setASIN(results.getString("asin"));
                movie.setCustomDescription(results.getString("custom_description"));
                movie.setDirector(results.getString("director"));
                // TODO: finish implementing
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }


    /**
     * Update the specified movie in the database.  That is, make the state of
     * the database be the same as the state of the object.
     *
     * @param m The movie that should be updated in the database.
     * @throws ClassCastException if the Movie specified is not a CD
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
            updateMoviePS.setDate(6,(Date) movie.getPurchaseDate()); // TODO: why does this need casting?
            updateMoviePS.setString(7,movie.getCustomDescription());
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
     *
     * @param movie
     */
    public void addMovie(Movie movie) {

        try {
            updateMoviePS.setString(0, movie.getDirector());
            updateMoviePS.setString(1, movie.getTitle());
            updateMoviePS.setString(2, movie.getRating());
            updateMoviePS.setInt(3, movie.getRuntime());
            updateMoviePS.setInt(4, movie.getYear());
            updateMoviePS.setString(5, movie.getASIN());
            updateMoviePS.setDate(6,(Date) movie.getPurchaseDate()); // TODO: why does this need casting?
            updateMoviePS.setString(7,movie.getCustomDescription());
            updateMoviePS.setString(8, movie.getCondition());
            updateMoviePS.setString(9, movie.getType());
            updateMoviePS.setInt(10, movie.getMangoRating());
            updateMoviePS.setNull(11, Types.INTEGER);
            updateMoviePS.setNull(12, Types.INTEGER);

            // Do it!
            updateMoviePS.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 
     *
     * @param m The movie that should be deleted from the database.
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
     *
     * @param m
     * @return
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
     * 
     * @param m
     * @return
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

}

