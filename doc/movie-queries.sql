-- Update all fields for a Movie
UPDATE movie
SET director=?, title=?, rating=?, runtime=?, year=?, asin=?, purchase_date=?, 
    custom_description=?, condition=?, type=?, mango_rating=?, owner_id=?,
    borrower_id=?
WHERE id=?

-- Add a Movie to the database
INSERT INTO movie(director, title, rating, runtime, year, asin, purchase_date,
    custom_description, condition, type, mango_rating, owner_id, borrower_id)
VALUES (...);

-- Remove a movie from the database
DELETE FROM movie
WHERE id=?;

-- Get all movies (all columns)
SELECT * FROM movie;

-- Get My Movies
SELECT * FROM movie
WHERE owner_id=`ownerid`

-- Get Loaned Movies
SELECT * FROM movie
WHERE (borrower_id IS NOT NULL AND owner_id=`ownerid`);

-- Get Borrowed Movies
SELECT * FROM movie
WHERE (borrower_id=`ownerid`)

-- Get all movies with a certain genre
SELECT id FROM movie, genre
WHERE movie_id=id AND name=?;

-- Get all genres for a movie
SELECT name FROM movie, genre
WHERE movie_id=id AND id=?
