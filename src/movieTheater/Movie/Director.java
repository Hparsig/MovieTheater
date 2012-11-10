package movieTheater.Movie;

public class Director extends MoviePerson
{
		
	private int directorID;
	
	public Director(String fName, String lName, int gender, String description)
	{
		super(fName, lName, gender, description);
	}
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

