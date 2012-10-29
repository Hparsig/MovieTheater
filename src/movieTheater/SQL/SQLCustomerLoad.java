package movieTheater.SQL;

import java.util.ArrayList;
import java.sql.*;
import movieTheater.main.Customer;

public class SQLCustomerLoad extends SQL{
	private ArrayList<Customer> customerArray;	
	private static final String queryCustomer = "SELECT * FROM Customers where custNo =";
	private static final String queryCustomerByFirstName = "SELECT * FROM Customers where fName LIKE ";

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
				int custNo = resultSet.getInt("custNo");
				String fname = resultSet.getString("fName");
				String lname = resultSet.getString("lName");
				String street = resultSet.getString("street");
				int houseNo = resultSet.getInt("houseNo");
				int postCode = resultSet.getInt("postCode");
				int telephone = resultSet.getInt("phone");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
						
				customerArray.add(new Customer(custNo, fname, lname, street, houseNo, postCode, telephone, username, password));
			}
		}
		catch (Exception e)
		{
			System.out.println("Error loading customer"); //boundary TODO fix
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
		}
		finally
		{
			closeConnection();
		}
		return customerArray;
	}

	public ArrayList<Customer> LoadCustomer(String fname) throws SQLException {
		//
		//				ArrayList<Actor> cast = new ArrayList<Actor>();
		//				ArrayList<Rating> ratings = new ArrayList<Rating>();
		//				boolean isThreeDim = false;
		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery((queryCustomerByFirstName+fname));
			setCustomer(resultSet);			
		}
		catch (Exception e)
		{
			System.out.println("Error loading customer by first name"); //boundary TODO fix
		}
		finally
		{
			closeConnection();
		}
		return customerArray;
	}
}