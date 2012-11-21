package movieTheater.Movie;

/**
 * 
 * @author Henrik
 *
 */
public class Actor extends MoviePerson 
{
	private int actorID;
	/**
	 * This constructor is to be used, when a new Actor is created)
	 * @param String fName
	 * @param String LName
	 * @param int gender
	 * @param String description
	 */
	public Actor(String fName, String LName, int gender, String description)
	{
		super(fName, LName, gender, description);
	}
	/**
	 * This constructor is to be used when a Actor is loaded with the actorID
	 * @param String fName
	 * @param String LName
	 * @param int gender
	 * @param String description
	 * @param int actorID
	 */
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
