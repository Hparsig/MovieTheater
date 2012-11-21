package movieTheater.Movie;
/**
 * 
 * @author Henrik
 *
 */
public abstract class MoviePerson {

	protected String fName;
	protected String lName;
	private int gender;
	private String description;

	public MoviePerson(String fName, String LName, int gender, String description)
	{
		this.fName = fName;
		this.lName = LName;
		this.gender = gender;
		this.description = description;
	}

	public String getFName()
	{
		return fName;
	}
	public String getLName()
	{
		return lName;
	}
	public int getGender()
	{
		return gender;
	}
	public String getDescription()
	{
		return description;
	}
	void setfName(String fName)
	{
		this.fName = fName;
	}
	void setlName(String lName)
	{
		this.lName = lName;
	}
	void setGender(int gender)
	{
		this.gender = gender;
	}
	void setDescription(String description)
	{
		this.fName = description;
	}
	public String toString()
	{
		return fName+" "+lName;
	}
}
