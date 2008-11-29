package com.themangoproject.model;

import java.util.List;


public interface ActorDAO {

    public List<Actor> getAllActors ();
    public List<Role> getRolesForActor (Actor actor);
    public void addActor (Actor actor);
    public void updateActor (Actor actor);

}

