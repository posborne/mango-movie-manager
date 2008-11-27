package com.themangoproject.model;

import com.themangoproject.db.h2.DBActor;
import java.util.List;


public interface ActorDAO {

    public List<Actor> getAllActors ();

    public List<Role> getRolesForActor (Actor actor);

    public DBActor addActor (Actor actor);

    public void updateActor (Actor actor);

}

