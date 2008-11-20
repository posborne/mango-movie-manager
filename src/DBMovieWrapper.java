import java.util.ArrayList;


public class DBMovieWrapper implements Movie{

	private String title, director, rating;
	private int mangoRating, length, id;
	
	private boolean changed;
	
	public DBMovieWrapper(int id){
		this.id = id;
		changed = false;
	}
	
	@Override
	public String getDirector() {
		// TODO Auto-generated method stub
		return director;
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return length;
	}

	@Override
	public int getMangoRating() {
		// TODO Auto-generated method stub
		return mangoRating;
	}

	@Override
	public String getRating() {
		// TODO Auto-generated method stub
		return rating;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	@Override
	public void setDirector(String director) {
		// TODO Auto-generated method stub
		changed = true;
	}

	@Override
	public void setLength(int length) {
		// TODO Auto-generated method stub
		changed = true;
		
	}

	@Override
	public void setMangoRating(int mangoRating) {
		// TODO Auto-generated method stub
		changed = true;
		
	}

	@Override
	public void setRating(String rating) {
		// TODO Auto-generated method stub
		changed = true;
		
	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		changed = true;
		
	}

	@Override
	public ArrayList<Actor> getActors() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addActor(Actor actor) {
		// TODO Auto-generated method stub
		changed = true;
	}

	public boolean hasChanged() {
		return changed;
	}

	public void removeActor(Actor actor) {
		// TODO Auto-generated method stub
		changed = true;
	}

}
