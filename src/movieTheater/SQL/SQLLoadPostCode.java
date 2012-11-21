package movieTheater.SQL;

import java.sql.ResultSet;
import java.util.ArrayList;

import movieTheater.Persons.City;

public class SQLLoadPostCode extends SQL {
	
	private ArrayList<City> citys;
	private static final String getAllCitys = "SELECT * FROM postcode";
	
	public SQLLoadPostCode() 
	{
		citys = new ArrayList<City>();
		statement = null;
		connection = null;
	}
	
	public ArrayList<City> getCitys()
	{
		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery(getAllCitys);
			while (resultSet.next())
			{
				String city = resultSet.getString("city");
				int postcode = resultSet.getInt("postCode");
				citys.add(new City(postcode,city));
			}			
		}
		catch (Exception e)
		{
			System.out.println("fejl i load af post numre"); 			
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return citys;
	}
	

}
