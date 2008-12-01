package com.themangoproject.model;

import java.util.List;

import com.themangoproject.db.h2.ActorExistsInOtherRelationsException;
import com.themangoproject.db.h2.DBActor;


public interface ActorDAO {

    public List<Actor> getAllActors ();
    public List<Role> getRolesForActor (Actor actor);
    public void addActor (Actor actor);
    public void updateActor (Actor actor);
    public void forceDeleteActor (Actor actor);
    public void deleteActor (Actor actor) throws ActorExistsInOtherRelationsException;
    public void populateActor (Actor actor);
    public List<Movie> getMoviesForActor(Actor actor);
    public Actor getActorFromId(int actorID);
}

