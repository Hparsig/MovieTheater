package movieTheater.Movie;

public class Cast {

	private int movieID;
	private Actor actor;
	private String rolename;
	
	public Cast(int movieID, Actor actor, String rolename) {
		this.movieID = movieID;
		this.actor = actor;
		this.rolename = rolename;
	}
	public Cast(Actor actor, String rolename) {
		this.actor = actor;
		this.rolename = rolename;
	}
	
	public int getMovieID(){
		return movieID;
	}
	public Actor getActor(){
		return actor;
	}
	public String getRolename(){
		return rolename;
	}
	

}
