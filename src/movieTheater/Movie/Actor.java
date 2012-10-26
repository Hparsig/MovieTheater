package movieTheater.Movie;

public class Actor {
private String firstName;
private String lastName;
private int gender;
private String description;

public Actor(String firstName, String LastName, int gender, String description)
{
	this.firstName = firstName;
	this.lastName = LastName;
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
