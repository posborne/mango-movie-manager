package com.themangoproject.model;

public interface Role {

	public Actor getActor();

	public String getCharacter();

	public String getRole();

	@Override
	public String toString();

	public Movie getMovie();

	public void setRole(String roleName);

	public void setCharacter(String character);

}
