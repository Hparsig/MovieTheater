package movieTheater.SQL;
import java.util.ArrayList; 
import java.sql.*;

import movieTheater.Persons.Employee;
import movieTheater.Persons.Manager;
import movieTheater.Persons.SalesPerson;

public class SQLEmployeeLoad extends SQL{
	private ArrayList<Employee> employeeArray;	
	private ArrayList<Manager> managerArray;
	private static final String queryEmployee = "SELECT emp.*, post.city FROM employees emp, postcode post WHERE empNo =";
	private static final String queryEmployeeByFirstName = "SELECT emp.*, post.city FROM employees emp, postcode post WHERE fName LIKE '%";
	private static final String queryEmployeeByUsername = "SELECT emp.*, post.city FROM employees emp, postcode post WHERE username LIKE '%";
	private static final String comparePostcode = " AND emp.postCode = post.postCode";
	

	/**
	 * constructor
	 */
	public SQLEmployeeLoad()
	{
		employeeArray = new ArrayList<Employee>();
		managerArray = new ArrayList<Manager>();
		statement = null;
		connection = null;
	}
	/**
	 * @author Jesper
	 * Create arrayList with employees
	 * @param ResultSet resultSet
	 * @return ArrayList<Employee> 
	 */
	public ArrayList<Employee> setEmployee(ResultSet resultSet)
	{
		
		try
		{
			while (resultSet.next())
			{
				int employeeNo = resultSet.getInt("empNo");
				String fName = resultSet.getString("fName");
				String lName = resultSet.getString("lName");
				int phone = resultSet.getInt("phone");
				String pW = resultSet.getString("pW");
				int titel = resultSet.getInt("titelID");
				String road = resultSet.getString("road");
				String houseNo = resultSet.getString("houseNo");
				int postCode = resultSet.getInt("postCode");
				String userName = resultSet.getString("username");
				String city = resultSet.getString("city");
				
				if (titel == 1)
				{
				employeeArray.add(new Manager(fName, lName, phone, road, houseNo, postCode, city, userName, 
						pW, employeeNo));
				}
				else
				{
					employeeArray.add(new SalesPerson(fName, lName, phone, road, houseNo, postCode, city, userName, 
							pW, employeeNo));
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("fejl i set medarbejder"); //boundary TODO fix
			
		}
		return employeeArray;	
	}

	/**
	 * @author Jesper
	 * Search employees using employee number
	 * @param int emNum
	 * @return ArrayList<Employee> 
	 * @throws SQLException
	 */
	public ArrayList<Employee> LoadEmployee(int emNum) throws SQLException {

		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery((queryEmployee+emNum+comparePostcode));
			setEmployee(resultSet);			
		}
		catch (Exception e)
		{
			System.out.println("fejl i load af medarbejdere ud fra medarbejder nummer"); //boundary TODO fix
		}
		finally
		{
			closeConnectionLoad();
		}
		return employeeArray;
	}
	/**
	 * @author Jesper
	 * Search employees using firstname
	 * @param String firstName
	 * @return ArrayList<Employee> 
	 * @throws SQLException
	 */
	public ArrayList<Employee> LoadEmployee(String firstName) throws SQLException {

		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery((queryEmployeeByFirstName+firstName+"%'"+comparePostcode));
			setEmployee(resultSet);			
		}
		catch (Exception e)
		{
			System.out.println("fejl i load af medarbejdere efter fornavn"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return employeeArray;
	}
	
	/**
	 * @author Jesper
	 * Search employees using username
	 * @param String username
	 * @return ArrayList<Employee> 
	 * @throws SQLException
	 */
	public ArrayList<Employee> LoadEmployeeByUsername(String username) throws SQLException {

		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery((queryEmployeeByUsername+username+"%'"+comparePostcode));
			setEmployee(resultSet);			
		}
		catch (Exception e)
		{
			System.out.println("fejl i load af medarbejdere efter username"); //boundary TODO fix
		}
		finally
		{
			closeConnectionLoad();
		}
		return employeeArray;
	}	

}
