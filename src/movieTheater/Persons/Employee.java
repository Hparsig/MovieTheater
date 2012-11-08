package movieTheater.Persons;

public abstract class Employee extends Person
{
	protected int employeeNo;
	
	public Employee()
	{
		super();
	}
	public Employee(String fName, String lName, int phone, String road, String houseNo, int postCode, String city, String userName, 
			String PW, int employeeNo)
	{
		super(fName, lName, phone, road, houseNo, postCode, city, userName, PW);
		this.employeeNo = employeeNo;
	}
	public Employee(String fName, String lName, int phone, String road, String houseNo, int postCode, String city, String userName, 
			String PW)
	{
		super(fName, lName, phone, road, houseNo, postCode, city, userName, PW);
	}

	public int getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String toString(){
		return fName+" "+lName;
	}
}
