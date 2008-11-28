package com.themangoproject.db.h2;

import com.themangoproject.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2ActorDAO implements ActorDAO {

    private static H2ActorDAO instance;
    private Connection conn;

    private PreparedStatement allActorsPS;
    private static final String allActorsQuery =
            "SELECT (id, first_name, last_name) FROM actor";

    private PreparedStatement rolesForActorPS;
    private static final String rolesForActorQuery =
            "SELECT title, role, character" +
            " FROM movie, acting_roles" +
            " WHERE movie_id=? AND actor_id=?";

    private PreparedStatement addActorPS;
    private static final String addActorQuery =
            "INSERT INTO actor (first_name, last_name)" +
            " VALUES(?, ?)";

    private PreparedStatement updateActorPS;
    private static final String updateActorQuery =
            "UPDATE actor" +
            " SET first_name=?, last_name=?" +
            " WHERE id=?";

    private PreparedStatement populatePersonPS;
    private static final String populatePersonQuery =
            "SELECT first_name, last_name" +
            " FROM actor" +
            " WHERE id=?";

    private H2ActorDAO() {
        conn = H2Util.getInstance().getConnection();
        try {
            allActorsPS = conn.prepareStatement(allActorsQuery);
            updateActorPS = conn.prepareStatement(updateActorQuery);
            rolesForActorPS = conn.prepareStatement(rolesForActorQuery);
            addActorPS = conn.prepareStatement(addActorQuery);
            updateActorPS = conn.prepareStatement(updateActorQuery);
            populatePersonPS = conn.prepareStatement(populatePersonQuery);
        } catch (SQLException ex) {
            // TODO; decide what to do here
            ex.printStackTrace();
        }

    }

    public static H2ActorDAO getInstance() {
        if (instance == null) {
            instance = new H2ActorDAO();
        }
        return instance;
    }

    public List<Actor> getAllActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        try {
            ResultSet results = allActorsPS.executeQuery();
            while (results.next()) {
                DBActor actor = new DBActor();
                actor.setId(results.getInt("id"));
                actor.setFirstName(results.getString("first_name"));
                actor.setLastName(results.getString("last_name"));
                actors.add(actor);
            }
        } catch (SQLException ex) {
            // TODO: decide what to do here
            ex.printStackTrace();
        }
        return actors;
    }

    public List<Role> getRolesForActor(Actor a) {
        if (!(a instanceof DBActor)) {
            throw new ClassCastException();
        }
        DBActor actor = (DBActor) a;

        ArrayList<Role> roles = new ArrayList<Role>();
        try {
            ResultSet results = rolesForActorPS.executeQuery();
            while (results.next()) {
                DBRole role = new DBRole();
                //role.setActorId(id);
                // TODO: finish this
            }
        } catch (SQLException ex) {
            // TODO: decide what to do here
            ex.printStackTrace();
        }
        return roles;
    }

    public void addActor(Actor actor) {
        try {
            addActorPS.setString(0, actor.getFirstName());
            addActorPS.setString(1, actor.getLastName());
            addActorPS.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateActor(Actor a) {
        if (!(a instanceof DBActor)) {
            throw new ClassCastException();
        }
        DBActor actor = (DBActor) a;

        try {
            updateActorPS.setString(0, actor.getFirstName());
            updateActorPS.setString(1, actor.getLastName());
            updateActorPS.setInt(3, actor.getId());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}

