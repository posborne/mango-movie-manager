package com.themangoproject.db.h2;

import com.themangoproject.model.*;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class H2MovieDAO implements MovieDAO {

    private static H2MovieDAO instance;
    private Connection conn;

    private PreparedStatement allMoviesPS;
    private static final String allMoviesQuery =
            "SELECT id FROM movie";

    private PreparedStatement updateMoviePS;
    private static final String updateMoviesQuery =
            "UPDATE movie" +
            " SET director=?, title=?, rating=?, runtime=?, year=?, asin=?," +
            " purchase_date=?, custom_description=?, condition=?, type=?," +
            " mango_rating=?, owner_id=?, borrower_id=?" +
            " WHERE id=?";

    private PreparedStatement addMoviePS;
    private static final String addMovieQuery =
            "INSERT INTO movie (director, title, rating, runtime, year, asin," +
            " purchase_date, custom_description, condition, type, mango_rating" +
            " owner_id, borrower_id" +
            " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

    private H2MovieDAO() {

    }

    public static H2MovieDAO getInstance() {
        if (instance == null) {
            instance = new H2MovieDAO();
        }
        return instance;
    }

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

    public Movie updateMovie(Movie movie) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Movie addMovie(Movie movie) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteMovie(Movie movie) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List getGenresForMovie(Movie movie) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List getActorsForMovie(Movie movie) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

