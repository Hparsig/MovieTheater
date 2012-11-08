package movieTheater.Persons;

public class Manager extends Employee
{

	public Manager(String fName, String lName, int phone, String road, String houseNo, int postCode, String city, String userName, 
			String PW, int employeeNo)
	{
		super(fName, lName, phone, road, houseNo, postCode, city, userName, PW, employeeNo);
	}
	public Manager(String fName, String lName, int phone, String road, String houseNo, int postCode, String city, String userName, 
			String PW)
	{
		super(fName, lName, phone, road, houseNo, postCode, city, userName, PW);
	}
	public String toString(){
		return super.toString()+": Manager";
	}
}
