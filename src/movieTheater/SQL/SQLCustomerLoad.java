package movieTheater.SQL;

import java.util.ArrayList;
import java.sql.*;
import movieTheater.Persons.Customer;

public class SQLCustomerLoad extends SQL{
	private ArrayList<Customer> customerArray;	
	private static final String queryCustomer = "SELECT * FROM costumers where costNo =";
	private static final String queryCustomerByFirstName = "SELECT * FROM costumers where fName LIKE '%";
	private static final String queryGetCity = "SELECT city FROM postcode WHERE postCode =";
	public SQLCustomerLoad()
	{
		customerArray = new ArrayList<Customer>();
		statement = null;
		connection = null;
	}

	public ArrayList<Customer> setCustomer(ResultSet resultSet)
	{
		
		try
		{
			while (resultSet.next())
			{
				int custNo = resultSet.getInt("costNo");
				String fname = resultSet.getString("fName");
				String lname = resultSet.getString("lName");
				String street = resultSet.getString("road");
				String houseNo = resultSet.getString("houseNo");
				int postCode = resultSet.getInt("postCode");
				int telephone = resultSet.getInt("phone");
				String username = resultSet.getString("username");
				String password = resultSet.getString("pW");
				
				String city = getCity(postCode);
					
				customerArray.add(new Customer(fname, lname, telephone, street, houseNo, postCode, city, username, password,custNo));
			}
		}
		catch (Exception e)
		{
			System.out.println("Error loading customer"); //boundary TODO fix
			e.printStackTrace();
		}
		return customerArray;	
	}

	public ArrayList<Customer> LoadCustomer(int custNo) throws SQLException {

		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery((queryCustomer+custNo));
			setCustomer(resultSet);			
		}
		catch (Exception e)
		{
			System.out.println("Error loading customer by customer-number"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return customerArray;
	}

	public ArrayList<Customer> LoadCustomer(String fname) throws SQLException {
		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery(queryCustomerByFirstName+fname+"%'");
			setCustomer(resultSet);			
		}
		catch (Exception e)
		{
			System.out.println("Error loading customer by first name"); //boundary TODO fix
		}
		finally
		{
			closeConnectionLoad();
		}
		return customerArray;
	}
	
	public String getCity(int postNr){
		
		ResultSet resultSet = null;
		openConnection();
		String city = "";

		try
		{
			resultSet = statement.executeQuery(queryGetCity+postNr);
			while (resultSet.next())
			{
				city = resultSet.getString("city");
			}
						
		}
		catch (Exception e)
		{
			System.out.println("Fejl i load af city"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return city;
	}
}