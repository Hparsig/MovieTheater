package movieTheater.Persons;

public class City {

	private int postcode;
	private String city;
	
	public City(int postcode, String city) {
		this.postcode = postcode;
		this.city = city;
	}
	
	public int getPostcode()
	{
		return postcode;
	}
	public String getCity()
	{
		return city;
	}
	public String toString()
	{
		return postcode+" "+city;
	}

}
