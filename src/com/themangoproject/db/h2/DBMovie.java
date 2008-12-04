package com.themangoproject.db.h2;

import java.util.Date;
import java.util.List;

import com.themangoproject.model.Actor;
import com.themangoproject.model.DAOFactory;
import com.themangoproject.model.Movie;
import com.themangoproject.model.MovieDAO;
import com.themangoproject.model.Person;
import com.themangoproject.model.PersonNotFoundException;

/**
 * This is a concrete implementation of the movie interface. This is an
 * implementation that has knowledge about some limited database knowledge (e.g.
 * the unique DB id). It models a movie containing all the information about
 * that movie.
 * 
 * @author Zachary Varberg
 * 
 */
public class DBMovie implements Movie {

	private Integer id, runtime, mangoRating, year;
	private String director, title, rating, ASIN, customDescription, type,
			condition;
	private Date purchaseDate;
	private int ownerId = -1, borrowerId = -1;
	// private MangoController controller;
	private MovieDAO movieDAO;
	private DBMovieState state;

	public DBMovie() {
		// controller = MangoController.getInstance();
		DAOFactory fact = new H2DAOFactory();
		movieDAO = fact.getMovieDAO();
		state = new NotfullMovieState();
	}

	/**
	 * Set the database id for the movie.
	 * 
	 * @param id
	 *            The new id for the movie
	 */
	void setId(int id) {
		// TODO: this might need to change to fit state pattern
		this.id = id;
	}

	/**
	 * This will return the Database unique ID of this movie
	 * 
	 * @return the DB ID of this movie
	 */
	public int getId() {
		return state.getId();
	}

	/**
	 * This will change the director of the movie.
	 * 
	 * @param director
	 *            the new director value for this movie.
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * This will return the director of the movie
	 * 
	 * @return the director of this movie
	 */
	public String getDirector() {
		return this.state.getDirector();
	}

	/**
	 * This will change the title of the movie to a new value
	 * 
	 * @param title
	 *            the new title of the movie
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This will return the title of this movie
	 * 
	 * @return the title of the movie
	 */
	public String getTitle() {
		return this.state.getTitle();
	}

	/**
	 * This will change the rating of the movie (e.g. G, PG, PG-13, R, etc,
	 * etc).
	 * 
	 * @param rating
	 *            the new rating of the movie (e.g. G, PG, PG-13, R, etc, etc).
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * This will return the rating of the movie (e.g. G, PG, PG-13, R, etc,
	 * etc).
	 */
	public String getRating() {
		return this.state.getRating();
	}

	/**
	 * This will change the run-time of the movie
	 * 
	 * @param length
	 *            the new run-time of the movie
	 */
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	/**
	 * This will return the run-time of the movie
	 * 
	 * @return the run-time of the movie
	 */
	public int getRuntime() {
		return this.state.getRuntime();
	}

	/**
	 * This will change the mango rating (similar to star ratings, e.g. 0-5
	 * mangoes is roughly the same as 0-5 stars).
	 * 
	 * @param mangoRating
	 *            the new mango rating, an int between 0-10
	 * @throws IllegalArgumentException
	 *             if the mango rating is not an integer between 0 and 10
	 */
	public void setMangoRating(int mangoRating) throws IllegalArgumentException {
		if (mangoRating <= 10 && mangoRating >= 0) {
			this.mangoRating = mangoRating;
		} else {
			throw new IllegalArgumentException(
					"Mango rating must be between 0 and 10");
		}
	}

	/**
	 * This will return the mango rating of this movie
	 * 
	 * @return the mango rating of this movie
	 */
	public int getMangoRating() {
		return this.state.getMangoRating();
	}

	/**
	 * This will return all the actors who were a part of this movie
	 * 
	 * @return all the actors in this movie
	 */
	public List<Actor> getActors() {
		return movieDAO.getActorsForMovie(this);
	}

	/**
	 * This will add an actor to be associated with this movie
	 * 
	 * @param actor
	 *            the actor to be associated with this movie
	 */
	public void addActor(Actor actor) {
		// TODO: Should this be a role instead of an actor?
		throw new UnsupportedOperationException("Not suppor ted yet.");
	}

	/**
	 * This will remove the association of an actor with this movie
	 * 
	 * @param actor
	 *            the actor to be removed
	 */
	public void removeActor(Actor actor) {
		// TODO: Should this be a role?
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * This will return the year the movie was released
	 * 
	 * @return the year the movie was released.
	 */
	public int getYear() {
		return this.state.getYear();
	}

	/**
	 * This will set the year the movie was released
	 * 
	 * @param year
	 *            the year the movie was released
	 * @throws IllegalArgumentException
	 *             if the year is not a valid year i.e. if it is not an int
	 *             between 1890 and 9999
	 */
	public void setYear(int year) throws IllegalArgumentException {
		if (year < 10000 && year > 1890) {
			DBMovie.this.year = year;
		} else {
			throw new IllegalArgumentException("Not a valid year;");
		}
	}

	/**
	 * This will return the ASIN (Amazon System? Indentification Number)
	 * 
	 * @return the ASIN of this movie
	 */
	public String getASIN() {
		return this.state.getASIN();
	}

	/**
	 * This will change the ASIN for this movie
	 * 
	 * @param ASIN
	 *            the ASIN to set for this movie
	 */
	public void setASIN(String ASIN) {
		this.ASIN = ASIN;
	}

	/**
	 * This will return the date this movie was purchased.
	 * 
	 * @return Date the date this movie was purchased
	 * 
	 */
	public Date getPurchaseDate() {
		return this.state.getPurchaseDate();
	}

	/**
	 * This will set the purchase date of this movie
	 * 
	 * @param purchaseDate
	 *            the date this movie was purchased
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * This will return the custom description of this movie as described by the
	 * owner
	 * 
	 * @return CustomDescription is the custom description of this movie
	 */
	public String getCustomDescription() {
		return this.state.getCustomDescription();
	}

	/**
	 * This will set the custom description of this movie
	 * 
	 * @param customDescription
	 *            the new custom description of this movie
	 */
	public void setCustomDescription(String customDescription) {
		this.customDescription = customDescription;
	}

	/**
	 * This will return the type of movie (i.e. VHS, DVD, BRAY, HDVD, etc)
	 * 
	 * @return the type of this movie
	 */
	public String getType() {
		return this.state.getType();
	}

	/**
	 * This will change the type of this movie
	 * 
	 * @param type
	 *            the new type of the movie.
	 */
	public void setType(String type) {
		// TODO: input validation
		this.type = type;
	}

	/**
	 * This will return the Owner of this movie
	 * 
	 * @return the owner of this movie
	 */
	public Person getOwner() {
		return this.state.getOwner();
	}

	/**
	 * This will set to owner of this movie
	 * 
	 * @param owner
	 *            the owner of this movie
	 */
	public void setOwner(Person owner) {
	    if(owner == null)
	        this.ownerId = -1;
	    else
	        this.ownerId = ((DBPerson) owner).getId();
	}

	/**
	 * This will return the borrower of this movie (i.e. the person currently in
	 * possession of the movie).
	 * 
	 * @return the person who is borrowing this movie
	 */
	public Person getBorrower() {
		return this.state.getBorrower();
	}

	/**
	 * This will set the current borrower (or possessor) of the movie.
	 * 
	 * @param borrower
	 *            the borrower of the movie
	 */
	public void setBorrower(Person borrower) {
	    if(borrower == null)
	        this.borrowerId = -1;
	    else
	        this.borrowerId = ((DBPerson) borrower).getId();
	}

	/**
	 * This will return a list of all of the genres that this movie fits into.
	 * 
	 * @return the list of genres this movie is a part of.
	 */
	public List<String> getGenres() {
		return DBMovie.this.movieDAO.getGenresForMovie(this);
	}

	/**
	 * Adds the genre specified to this movie
	 * 
	 * @param genre
	 *            the genre to be added
	 */
	public void addGenre(String genre) {
		this.movieDAO.addGenreToMovie(this, genre);
	}

	/**
	 * This will remove the specified genre from this movie.
	 * 
	 * @param genre
	 *            the genre to be removed from this movie.
	 */
	public void removeGenre(String genre) {
		this.movieDAO.removeGenreFromMovie(this, genre);
	}

	/**
	 * This will return the condition this movie is in (e.g. "It's got a large
	 * scratch, and the case is broken")
	 * 
	 * @return the condition of the movie.
	 * 
	 */
	@Override
	public String getCondition() {
		return this.state.getCondition();
	}

	/**
	 * This will change the condition of the movie
	 * 
	 * @param condition
	 *            the new condition of the movie
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * This will return the ID of the owner of this movie
	 * 
	 * @return the id of the owner of this movie
	 */
	public int getOwnerId() {
		return this.state.getOwnerId();
	}

	/**
	 * This will return the ID of the borrower of this movie
	 * 
	 * @return the id of the borrower of this movie
	 */
	public int getBorrowerId() {
		return this.state.getBorrowerId();
	}

	private class UpdatedMovieState implements DBMovieState {
		/**
		 * This will return the Database unique ID of this movie
		 * 
		 * @return the DB ID of this movie
		 */
		public int getId() {
			return id;
		}

		/**
		 * This will return the director of the movie
		 * 
		 * @return the director of this movie
		 */
		public String getDirector() {
			return director;
		}

		/**
		 * This will return
		 * 
		 * @return the title of the movie
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * This will return the rating of the movie (e.g. G, PG, PG-13, R, etc,
		 * etc).
		 */
		public String getRating() {
			return rating;
		}

		/**
		 * This will return the run-time of the movie
		 * 
		 * @return the run-time of the movie
		 */
		public int getRuntime() {
			return runtime;
		}

		/**
		 * This will return the mango rating of this movie
		 * 
		 * @return the mango rating of this movie
		 */
		public int getMangoRating() {
			return mangoRating;
		}

		/**
		 * This will return the year the movie was released
		 * 
		 * @return the year the movie was released.
		 */
		public int getYear() {
			return DBMovie.this.year;
		}

		/**
		 * This will return the ASIN (Amazon System? Indentification Number)
		 * 
		 * @return the ASIN of this movie
		 */
		public String getASIN() {
			return DBMovie.this.ASIN;
		}

		/**
		 * This will return the date this movie was purchased.
		 * 
		 * @return Date the date this movie was purchased
		 * 
		 */
		public Date getPurchaseDate() {
			return DBMovie.this.purchaseDate;
		}

		/**
		 * This will return the custom description of this movie as described by
		 * the owner
		 * 
		 * @return CustomDescription is the custom description of this movie
		 */
		public String getCustomDescription() {
			return DBMovie.this.customDescription;
		}

		/**
		 * This will return the type of movie (i.e. VHS, DVD, BRAY, HDVD, etc)
		 * 
		 * @return the type of this movie
		 */
		public String getType() {
			return DBMovie.this.type;
		}

		/**
		 * This will return the Owner of this movie
		 * 
		 * @return the owner of this movie
		 */
		public Person getOwner() {
			DBPerson owner = new DBPerson();
			owner.setId(DBMovie.this.ownerId);
			try {
                H2PersonDAO.getInstance().populatePerson(owner);
            } catch (PersonNotFoundException e){
                return null;
            }
			return owner;
		}

		/**
		 * This will return the borrower of this movie (i.e. the person
		 * currently in possession of the movie).
		 * 
		 * @return the person who is borrowing this movie
		 */
		// TODO: is that statement in the return correct?
		public Person getBorrower() {
		    DBPerson borrower = new DBPerson();
		    borrower.setId(DBMovie.this.borrowerId);
		    try {
		        H2PersonDAO.getInstance().populatePerson(borrower);
		    } catch (PersonNotFoundException e){
		        return null;
		    }
		    return borrower;
		}

		/**
		 * This will return the condition this movie is in (e.g. "It's got a
		 * large scratch, and the case is broken")
		 * 
		 * @return the condition of the movie.
		 * 
		 */
		@Override
		public String getCondition() {
			return DBMovie.this.condition;
		}

		/**
		 * This will return the ID of the owner of this movie
		 * 
		 * @return the id of the owner of this movie
		 */
		public int getOwnerId() {
			return DBMovie.this.ownerId;
		}

		/**
		 * This will return the ID of the borrower of this movie
		 * 
		 * @return the id of the borrower of this movie
		 */
		public int getBorrowerId() {
			return DBMovie.this.borrowerId;
		}
	}

	private class NotfullMovieState implements DBMovieState {
		/**
		 * This will return the Database unique ID of this movie
		 * 
		 * @return the DB ID of this movie
		 */
		public int getId() {
//			DBMovie.this.movieDAO.getMovieInfo(DBMovie.this);
//			DBMovie.this.state = new UpdatedMovieState();
			// TODO: the above creates StackOverflowErrors... this
			// temporarily solves the problem
			return DBMovie.this.id;
		}

		/**
		 * This will return the director of the movie
		 * 
		 * @return the director of this movie
		 */
		public String getDirector() {
			DBMovie.this.movieDAO.getMovieInfo(DBMovie.this);
			DBMovie.this.state = new UpdatedMovieState();
			return director;
		}

		/**
		 * This will return
		 * 
		 * @return the title of the movie
		 */
		public String getTitle() {
			DBMovie.this.movieDAO.getMovieInfo(DBMovie.this);
			DBMovie.this.state = new UpdatedMovieState();
			return title;
		}

		/**
		 * This will return the rating of the movie (e.g. G, PG, PG-13, R, etc,
		 * etc).
		 */
		public String getRating() {
			DBMovie.this.movieDAO.getMovieInfo(DBMovie.this);
			DBMovie.this.state = new UpdatedMovieState();
			return rating;
		}

		/**
		 * This will return the run-time of the movie
		 * 
		 * @return the run-time of the movie
		 */
		public int getRuntime() {
			DBMovie.this.movieDAO.getMovieInfo(DBMovie.this);
			DBMovie.this.state = new UpdatedMovieState();
			return runtime;
		}

		/**
		 * This will return the mango rating of this movie
		 * 
		 * @return the mango rating of this movie
		 */
		public int getMangoRating() {
			DBMovie.this.movieDAO.getMovieInfo(DBMovie.this);
			DBMovie.this.state = new UpdatedMovieState();
			return mangoRating;
		}

		/**
		 * This will return the year the movie was released
		 * 
		 * @return the year the movie was released.
		 */
		public int getYear() {
			DBMovie.this.movieDAO.getMovieInfo(DBMovie.this);
			DBMovie.this.state = new UpdatedMovieState();
			return DBMovie.this.year;
		}

		/**
		 * This will return the ASIN (Amazon System? Indentification Number)
		 * 
		 * @return the ASIN of this movie
		 */
		public String getASIN() {
			DBMovie.this.movieDAO.getMovieInfo(DBMovie.this);
			DBMovie.this.state = new UpdatedMovieState();
			return DBMovie.this.ASIN;
		}

		/**
		 * This will return the date this movie was purchased.
		 * 
		 * @return Date the date this movie was purchased
		 * 
		 */
		public Date getPurchaseDate() {
			DBMovie.this.movieDAO.getMovieInfo(DBMovie.this);
			DBMovie.this.state = new UpdatedMovieState();
			return DBMovie.this.purchaseDate;
		}

		/**
		 * This will return the custom description of this movie as described by
		 * the owner
		 * 
		 * @return CustomDescription is the custom description of this movie
		 */
		public String getCustomDescription() {
			DBMovie.this.movieDAO.getMovieInfo(DBMovie.this);
			DBMovie.this.state = new UpdatedMovieState();
			return DBMovie.this.customDescription;
		}

		/**
		 * This will return the type of movie (i.e. VHS, DVD, BRAY, HDVD, etc)
		 * 
		 * @return the type of this movie
		 */
		public String getType() {
			DBMovie.this.movieDAO.getMovieInfo(DBMovie.this);
			DBMovie.this.state = new UpdatedMovieState();
			return DBMovie.this.type;
		}

		/**
		 * This will return the Owner of this movie
		 * 
		 * @return the owner of this movie
		 */
		public Person getOwner() {
			DBMovie.this.movieDAO.getMovieInfo(DBMovie.this);
			DBMovie.this.state = new UpdatedMovieState();
			DBPerson owner = new DBPerson();
			owner.setId(DBMovie.this.ownerId);
			return owner;
		}

		/**
		 * This will return the borrower of this movie (i.e. the person
		 * currently in possession of the movie).
		 * 
		 * @return the person who is borrowing this movie (the owner if it isn't
		 *         being borrowed.
		 */
		// TODO: is that statement in the return correct?
		public Person getBorrower() {
			DBMovie.this.movieDAO.getMovieInfo(DBMovie.this);
			DBMovie.this.state = new UpdatedMovieState();
			DBPerson borrower = new DBPerson();
			borrower.setId(DBMovie.this.borrowerId);
			return borrower;
		}

		/**
		 * This will return a list of all of the genres that this movie fits
		 * into.
		 * 
		 * @return the list of genres this movie is a part of.
		 */
		public List<String> getGenres() {
			return DBMovie.this.movieDAO.getGenresForMovie(DBMovie.this);
		}

		/**
		 * This will return the condition this movie is in (e.g. "It's got a
		 * large scratch, and the case is broken")
		 * 
		 * @return the condition of the movie.
		 * 
		 */
		@Override
		public String getCondition() {
			DBMovie.this.movieDAO.getMovieInfo(DBMovie.this);
			DBMovie.this.state = new UpdatedMovieState();
			return DBMovie.this.condition;
		}

		/**
		 * This will return the ID of the owner of this movie
		 * 
		 * @return the id of the owner of this movie
		 */
		public int getOwnerId() {
			DBMovie.this.movieDAO.getMovieInfo(DBMovie.this);
			DBMovie.this.state = new UpdatedMovieState();
			return DBMovie.this.ownerId;
		}

		/**
		 * This will return the ID of the borrower of this movie
		 * 
		 * @return the id of the borrower of this movie
		 */
		public int getBorrowerId() {
			DBMovie.this.movieDAO.getMovieInfo(DBMovie.this);
			DBMovie.this.state = new UpdatedMovieState();
			return DBMovie.this.borrowerId;
		}
	}

}
