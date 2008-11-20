-- Get all actors
SELECT * FROM actor;

-- Get all actors in some movie
SELECT actor_id
FROM acting_roles
WHERE movie_id=?

-- Get roles for specific actor... A role consists of
-- a movie, role, and character played by an actor.
SELECT title, role, character
FROM movie, acting_roles
WHERE movie_id=id AND actor_id=`?`;