package movieTheater.Persons;

public abstract class Person {
	protected String fName;
	protected String lName;
	protected int phone;
	protected String road;
	protected String houseNo;
	protected int postCode;
	protected String city;
	protected String userName;
	protected String pW;
	
	public Person(){
		fName = "";
		lName = "";
		phone = 0;
		houseNo = "";
		postCode = 0;
		city = "";
		userName = "";
		pW = "";
	}
	public Person(String fName, String lName, int phone, String road, String houseNo, int postCode, String city, String userName, 
			String pW)
	{
		this.fName = fName;
		this.lName = lName;
		this.phone = phone;
		this.road = road;
		this.houseNo = houseNo;
		this.postCode = postCode;
		this.city = city;
		this.userName = userName;
		this.pW = pW;
	}
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPW() {
		return pW;
	}
	public void setPW(String pW) {
		this.pW = pW;
	}	
}
