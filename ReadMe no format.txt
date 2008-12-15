INTRODUCTION:
    
Mango Movie Manager is a simple software system meant to be used to manage a user’s movie library.  It allows adding, deleting, and organizing of movies in a number of different ways.  Mango allows for the storage of information such as the owners of movies (you and your friends) and who is borrowing and lending them to whom.  For information on advanced features see the USE section.
    
INSTALLATION:
    
Simply run the jar file and Mango will be up and running.  NOTE: The movie data will be saved into a folder named db in the same directory the jar file is being run from.
    
USE:

Grouping Movies (Lists, Sets, and Saved Searches):

Lists, sets, and saved searches are all different ways Mango allows users to organize data.  

Definitions:
Set:  A set is a group of movies that has no specific order and requires each movie to be unique.  An example of a common set would be a wish list, where the order doesn't really matter and you wouldn't list a single movie more than once.  

List:  A list is a group of movies that has a specific order and allows users to have duplicates.  A common example of a list would be a playlist.  So if you were having an all night Disney party and wanted to plan the order of the movies you were going to watch, including watching some movies twice, you would want to use a list.

Saved search:  A saved search is similar to an iTunes smart playlist.  The idea here is that you set up a set of criteria that you are interested in.  Then anytime you add a new movie that fits the criteria for a saved search it will automatically be added to that group of movies.

Creation and management:
Sets and Lists: You can create a set or list in any number of ways.  There is an icon on the bottom bar, there are choices in the navigation panel (tree structure on the left), or there is an option in the menu on the top.  Once a list is created you can add movies to it by right clicking on a movie in the table and choosing add to the list or set you are interested in.  To remove a movie from a list or set, right click on that movie in the list or set and choose remove from set/list.  To change the ordering in a list simply change the value in the first column of the table (the # column) to the position in the list you would like that movie to be.

Saved search: You can create a saved search in any number of ways.  There is an icon on the bottom bar, there are choices in the navigation panel (tree structure on the left), or there is an option in the menu on the top.  Once the new pane pops up you will have the choice to name it and choose the attributes and constraints you wish to be a part of that search.  You cannot delete movies from a saved search, if you want to have new search criteria you must create a new search.

Deletion:
To delete any group in Mango simply right click on the list, set, or saved search in the navigation panel on the left and choose the delete option.



Adding, Editing, Deleting movies:

Adding a movie:  There are many ways of adding a movie in Mango.  There is a button on the bottom bar and there is an option in the file menu at the top.  Once you open the add movie dialog you have two options for adding information.  You can simply type in all the information or you can use the Amazon.com database to fill in the information for you.  In the add movie dialog there is a box for an ASIN and a button to "Get Information via Amazon."  You have to get the ASIN from Amazon, but if you put it in there it will fill out all of the information it can.  There are some fields that will always have to be filled in by hand (condition, mango rating, custom description, etc), but Amazon will fill in title, director, actors, runtime, rating, year, and artwork as it is available.  You can also add associations for a movie.  Here you can set who the owner of the movie is and who is borrowing it, if anyone.

Editing a movie:  To edit a movie simply right click on the movie and choose edit, or select the movie and choose the edit movie button on the bottom bar.  All of the information about the movie will be filled in and you can change any information you wish and click save to update the movie. 

Deleting a movie:  Right click on the movie in the table and choose delete.


Uploading an image:

There are 3 ways to upload a thumbnail image for a movie.  First you can use Amazon to get information about a movie (explained in the adding, editing, and deleting movies section) and it will automatically upload the Amazon thumbnail for that movie.  Second, when you are viewing the add or edit movie dialog if you go to the artwork tab and choose "change thumbnail" you can upload an image from your hard drive.  Third, when viewing movies in tables, you can select a movie and choose the button on the bottom bar for uploading thumbnails (far right).  This will allow you to upload an image from your hard drive.  

KNOWN BUG:  If you upload an image using the button on the bottom bar, the image pane on the left side of the screen will continue to display the old image.  To display the new image you must select another movie and then select the movie you just updated again.



Searching for a movie:

There are two basic searching techniques.  Searching using the search field and saved searches.  Saved searches are discussed in the grouping movies section.  Using the search field is simple.  Click in the field at the top of the screen and start typing.  This will ONLY search movies (or people, or actors) in the table that is currently being displayed.  The search will happen as you type removing items that do not match from the table displayed.  Currently this search method is case-sensitive, for example searching for die will not match a movie whose title is Die Hard (d does not match D).  The search field also supports regular expressions.



Adding and removing actors/people:

Adding and removing of an actor or person is done in a similar way.  Both are added indirectly through the add/edit movie dialog.  In that dialog there are tabs concerning both actors and people and in those respective tabs you can add new actors and people (Amazon will also automatically create actors if you ask Amazon to fill in information for you).  To delete an actor you can navigate to the all actors table using the navigation panel on the left then right click on the actor you wish to delete in the table and choose delete.

KNOWN BUG:  There is currently no way to delete a person from the database.

KNOWN BUG:  Attempting to edit edit a person or actor will throw exceptions.


Lending and Borrowing movies:

Mango allows you to set an owner and borrower of each movie.  You can set who “you" are (i.e. the owner) using the button on the bottom bar that is second from the right.  All of the searches based on "my" and "friends" will be based off of whom the "owner" is.  You can use the predefined searches in library actions in the navigation panel to find all movies you own (My Movies), all movies owned by others (Friends Movies), all the movies you are borrowing (Borrowed movies), and movies you are loaning out (Loaned Movies).  To change who the owner or borrower of any movie is, simply open the edit movie dialog (discussed in adding, editing, and deleting a movie), and navigate to the associations panel, there you will be able to edit information as necessary.

KNOWN BUGS:

KNOWN BUG:  There is currently no way to delete a person from the database.

KNOWN BUG:  Attempting to edit a person or actor will throw exceptions.
       
KNOWN BUG:  If you upload an image using the button on the bottom bar, the image pane on the left side of the screen will continue to display the old image.  To display the new image you must select another movie and then select the movie you just updated again.
    
ACKNOWLEDGMENTS:

This software was developed by Paul Osborne, Kyle Ronning, and Zachary Varberg.  They are all currently undergraduate students pursuing their B.S. degrees in computer science from Bethel University in Arden Hills Minnesota.  This was originally developed as an assignment for Database Systems, a class at Bethel University fall of 2008.  This class was taught by Dr. Benjamin Shults.
    
CONTACT:

Please e mail any generic questions, comments, or concerns to developers@themangoproject.com.  If you wish to contact a specific developer you can reach them at the following addresses:

Zachary Varberg
    zach@themangoproject.com
        
Kyle Ronning
    kyle@themangoproject.com
        
Paul Osborne
    paul@themangoproject.com
    
