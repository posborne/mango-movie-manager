package com.themangoproject.model;

import java.util.List;

import javax.swing.event.ChangeListener;

import com.themangoproject.db.h2.ActorExistsInOtherRelationsException;


public interface ActorDAO {

    public List<Actor> getAllActors ();
    public List<Role> getRolesForActor (Actor actor);
    public boolean addActor (String firstName, String lastName);
    public void updateActor (Actor actor);
    public void forceDeleteActor (Actor actor);
    public void deleteActor (Actor actor) throws ActorExistsInOtherRelationsException;
    public void populateActor (Actor actor);
    public List<Movie> getMoviesForActor(Actor actor);
    public void populateRole(Role role) throws RoleNotFoundException;
	public void addActorsChangeListener(ChangeListener l);
	public void removeActorsChangeListener(ChangeListener l);
}

