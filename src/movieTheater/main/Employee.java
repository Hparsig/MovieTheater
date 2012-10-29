package movieTheater.main;

public class Employee {
	private int employeeNo;
	private String firstName;
	private String lastName;
	private int tlf;
	private int titel;
	private String road;
	private int nr;
	private int postNr;
	private String username;
	private String password;
	private String city;

	public Employee(int employeeNr,String firstName, String LastName, int tlf, int titel, String road, int nr, int postNr, String username, String password, String city)
	{
		this.employeeNo = employeeNo;
		this.firstName = firstName;
		this.lastName = LastName;
		this.tlf = tlf;
		this.titel = titel;
		this.road = road;
		this.nr = nr;
		this.postNr = postNr;
		this.username = username;
		this.password = password;
		this.city = city;
	}
	
	public Employee(String firstName, String LastName, int tlf, int titel, String road, int nr, int postNr, String username, String password)
	{
		this.firstName = firstName;
		this.lastName = LastName;
		this.tlf = tlf;
		this.titel = titel;
		this.road = road;
		this.nr = nr;
		this.postNr = postNr;
		this.username = username;
		this.password = password;
	}
	
		
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public int getTlf()
	{
		return tlf;
	}
	public int getTitel()
	{
		return titel;
	}
	public String getRoad(){
		return road;
	}
	public int getNr()
	{
		return nr;
	}
	public int getPostNr()
	{
		return postNr;
	}
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public String getCity(){
		return city;
	}
	

}
