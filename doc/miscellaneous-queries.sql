-- Get all movies in a set
SELECT movie_id
FROM sets
WHERE label=?

-- Get all movies in a list
SELECT movie_id, order_id
FROM lists
WHERE label=?

-- Pull out a saved search
SELECT query
FROM saved_searches
WHERE label=?
