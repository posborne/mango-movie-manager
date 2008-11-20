import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Zachary Varberg
 *
 */
public interface Movie {
	
	public void setDirector(String director);
	public String getDirector();
	public void setTitle(String title);
	public String getTitle();
	public void setRating(String rating);
	public String getRating();
	public void setLength(int length);
	public int getLength();
	public void setMangoRating(int mangoRating);
	public int getMangoRating();
	public ArrayList<Actor> getActors();
	public void addActor(Actor actor);
	public void removeActor(Actor actor);
	public boolean hasChanged();

}
