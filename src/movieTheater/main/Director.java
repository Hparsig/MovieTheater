package movieTheater.main;

public class Director 
{
	private String firstName;
	private String lastName;
	private int gender;
	private String description;
	
	public Director(String firstName, String lastName, int gender, String description)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.description = description;		
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public int getGender()
	{
		return gender;
	}
	public String getDescription()
	{
		return description;
	}
}

