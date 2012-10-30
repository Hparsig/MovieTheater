package movieTheater.Movie;

public class Actor extends MoviePerson 
{
	private int actorID;
	public Actor(String fName, String LName, int gender, String description)
	{
		super(fName, LName, gender, description);
	}
	public Actor(String fName, String LName, int gender, String description,int actorID)
	{
		super(fName, LName, gender, description);
		this.actorID = actorID;
	}
	
	public int getActorID(){
		return actorID;
	}
}
