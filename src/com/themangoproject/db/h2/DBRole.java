package com.themangoproject.db.h2;

import com.themangoproject.model.*;

public class DBRole implements Role {

	private int actorId;
	private int movieId;

	public Actor getActor() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public String getCharacter() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public String getRole() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public Movie getMovie() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	void setActorId(int id) {
		this.actorId = id;
	}

	void setMovieId(int id) {
		this.movieId = id;
	}

	@Override
	public void setCharacter(String character) {
		// TODO: implement
	}

	@Override
	public void setRole(String roleName) {
		// TODO: implement
	}

}
