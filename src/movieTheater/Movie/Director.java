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
	
	public int getDirectorID(){
		return directorID;
	}
	
	public String toString()
	{
		return (lName + ", " + fName);
	}
}

