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
	
	public int getActorID()
	{
		return actorID;
	}
	
	public void setActorID(int actorID)
	{
		this.actorID = actorID;
	}
	public String toString()
	{
		return (lName + ", " + fName);
	}
	public boolean equals(Object o)
	{
		boolean isEqual = false;
		
		if(o instanceof Actor)
		{
			isEqual = (this.fName.equals(((Actor)o).getFName()) && this.lName.equals(((Actor)o).getLName()));
		}
		return isEqual;
	}
}
