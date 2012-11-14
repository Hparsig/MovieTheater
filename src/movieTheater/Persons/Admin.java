package movieTheater.Persons;

public class Admin extends Employee 
{

	public Admin(String fName, String lName, int phone, String road, String houseNo, int postCode, String city, String userName, 
			String PW, int employeeNo)
	{
		super(fName, lName, phone, road, houseNo, postCode, city, userName, PW, employeeNo);
	}
	public Admin(String fName, String lName, int phone, String road, String houseNo, int postCode, String city, String userName, 
			String PW)
	{
		super(fName, lName, phone, road, houseNo, postCode, city, userName, PW);
	}
}
