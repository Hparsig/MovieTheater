package movieTheater.SQL;
import java.util.ArrayList; 
import java.sql.*;

import movieTheater.main.Employee;

public class SQLEmployeeLoad extends SQL{
	private ArrayList<Employee> employeeArray;	
	private static final String queryEmployee = "SELECT emp.*, post.city FROM employees emp, postcode post WHERE empNo =";
	private static final String queryEmployeeByFirstName = "SELECT emp.*, post.city FROM employees emp, postcode post WHERE fName LIKE '%";
	private static final String queryEmployeeByUsername = "SELECT emp.*, post.city FROM employees emp, postcode post WHERE username LIKE '%";
	private static final String comparePostcode = " AND emp.postCode = post.postCode";
	private static final String employeeLogin = "SELECT * FROM employees WHERE pW = ? AND username = ?";

	/**
	 * constructor
	 */
	public SQLEmployeeLoad()
	{
		employeeArray = new ArrayList<Employee>();
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
				int employeeNr = resultSet.getInt("empNo");
				String firstName = resultSet.getString("fName");
				String lastName = resultSet.getString("lName");
				int tlf = resultSet.getInt("phone");
				String password = resultSet.getString("pW");
				int titel = resultSet.getInt("titelID");
				String road = resultSet.getString("road");
				int nr = resultSet.getInt("houseNo");
				int postNr = resultSet.getInt("postCode");
				String username = resultSet.getString("username");
				String city = resultSet.getString("city");
				
							
				employeeArray.add(new Employee(employeeNr, firstName, lastName,tlf,titel,road,nr,postNr,username,password,city));
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
	
	/**
	 * @author Jesper
	 * login check of employee
	 * @param String username, String Password
	 * @return Employee
	 * @throws SQLException
	 */
	public Employee checkEmployee(String username, String password) throws SQLException {
		openConnection();
		preparedStatement = connection.prepareStatement(employeeLogin); 
		ResultSet resultSet = null;
		
		try
		{
			preparedStatement.setString(1, password);
		    preparedStatement.setString(2, username);
		      
		    resultSet = preparedStatement.executeQuery();
		    setEmployee(resultSet);
		}
		catch (Exception e)
		{
			System.out.println("fejl i login af medarbejder"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
			preparedStatement.close();
		}
		return employeeArray.get(0);
	}


}
