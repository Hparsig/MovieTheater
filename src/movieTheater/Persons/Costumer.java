package movieTheater.Persons;

public class Costumer extends Person 
{
	private int costumerNo;
	
	public Costumer()
	{
		
	}
	public Costumer(String fName, String lName, int phone, String road, String houseNo, int postCode, String city, String userName, 
			String PW, int costumerNo)
	{
		super(fName, lName, phone, road, houseNo, postCode, city, userName, PW);
		this.costumerNo = costumerNo;
	}
	public Costumer(String fName, String lName, int phone, String road, String houseNo, int postCode, String city, String userName, 
			String PW)
	{
		super(fName, lName, phone, road, houseNo, postCode, city, userName, PW);
	}

	public int getCostumerNo() {
		return costumerNo;
	}

	public void setCostumerNo(int costumerNo) {
		this.costumerNo = costumerNo;
	}
	
	
}
