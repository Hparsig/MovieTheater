package movieTheater.Movie;
/**
 * 
 * @author Henrik
 *
 */
public class Director extends MoviePerson
{
	private int directorID;

	/**
	 * This constructor is to be used when a new Director is created. 
	 * @param String fName
	 * @param String lName
	 * @param int gender
	 * @param String description
	 */
	public Director(String fName, String lName, int gender, String description)
	{
		super(fName, lName, gender, description);
	}
	/**
	 * This constructor is to be used when a Director is being loaded from the database, with an directorID. 
	 * @param String fName
	 * @param String lName
	 * @param int gender
	 * @param String description
	 * @param int directorID
	 */
	public Director(String fName, String lName, int gender, String description, int directorID)
	{
		super(fName, lName, gender, description);
		this.directorID = directorID;
	}

	public int getDirectorID()
	{
		return directorID;
	}

	public void setDirectorID(int directorID)
	{
		this.directorID = directorID;
	}

	public String toString()
	{
		return (lName + ", " + fName);
	}
	public boolean equals(Object o)
	{
		boolean isEqual = false;

		if(o instanceof Director)
		{
			isEqual = (this.fName.equals(((Director)o).getFName()) && this.lName.equals(((Director)o).getLName()));
		}
		return isEqual;
	}
}

