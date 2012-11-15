package movieTheater.SQL;

import java.util.ArrayList;
import java.sql.*;
import movieTheater.Persons.Costumer;
import movieTheater.Persons.Manager;
import movieTheater.Persons.Person;

public class SQLCustomerLoad extends SQL{
	private ArrayList<Costumer> customerArray;	
	private static final String queryCustomer = "SELECT * FROM costumers where costNo =";
	private static final String queryCustomerByFirstName = "SELECT * FROM costumers where fName LIKE '%";
	private static final String queryGetCity = "SELECT city FROM postcode WHERE postCode =";
	private static final String queryCustomerByPhone = "SELECT * FROM costumers WHERE phone =";
	private static final String fnameQuery = " AND fName LIKE '%";
	private static final String lnameQuery = "%' AND lName LIKE '%";
	private static final String queryCustomerByFAndLName = "SELECT * FROM costumers WHERE fName =? AND lName = ?";
	
	public SQLCustomerLoad()
	{
		customerArray = new ArrayList<Costumer>();
		statement = null;
		connection = null;
	}

	public ArrayList<Costumer> setCustomer(ResultSet resultSet)
	{
		customerArray.clear();
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
					
				customerArray.add(new Costumer(fname, lname, telephone, street, houseNo, postCode, city, username, password,custNo));
			}
		}
		catch (Exception e)
		{
			System.out.println("Error loading customer"); //boundary TODO fix
			e.printStackTrace();
		}
		return customerArray;	
	}

	public ArrayList<Costumer> LoadCustomer(int custNo) throws SQLException {

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

	public ArrayList<Costumer> LoadCustomer(String fname) throws SQLException {
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
	
	public ArrayList<Costumer>  loadCostumer(int phone, String fName, String lName)
	{
		openConnection();
		ResultSet resultSet = null;
		
		try
		{
			if(phone!=0)
			{
				resultSet = statement.executeQuery(queryCustomerByPhone+phone+fnameQuery+fName+lnameQuery+lName+"%'"); 
				setCustomer(resultSet);	
			}
			else
			{
				preparedStatement = connection.prepareStatement(queryCustomerByFAndLName); 

				preparedStatement.setString(1, fName);
				preparedStatement.setString(2, lName);

				resultSet  = preparedStatement.executeQuery();    
				setCustomer(resultSet);	
			}

		}
		catch (Exception e)
		{
			System.out.println("fejl i load af kunde"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{  

			closeConnectionLoad(); 
			
		} 
		return customerArray;
	}  
}