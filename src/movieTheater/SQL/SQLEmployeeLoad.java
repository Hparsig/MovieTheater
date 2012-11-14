package movieTheater.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import movieTheater.Persons.Admin;
import movieTheater.Persons.Employee;
import movieTheater.Persons.Manager;
import movieTheater.Persons.Person;
import movieTheater.Persons.SalesPerson;

public class SQLEmployeeLoad extends SQL{
	private ArrayList<Employee> employeeArray;	
	
	private static final String queryEmployeeByFirstName = "SELECT emp.*, post.city FROM employees emp, postcode post WHERE fName LIKE '%";
	private static final String queryEmployeeLastname = " AND lName LIKE '%";
	private static final String queryEmployeeByUsername = " AND username LIKE '%";
	private static final String queryEmployeeByEmpNo = " AND empNo =";
	private static final String comparePostcode = " AND emp.postCode = post.postCode";
	

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
	public ArrayList<Employee> setEmployee(ResultSet resultSet, boolean isAdmin)
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
				
				if (isAdmin && titel == 1)
				{
				employeeArray.add(new Manager(fName, lName, phone, road, houseNo, postCode, city, userName, 
						pW, employeeNo));
				}
				if (titel == 2)
				{
					employeeArray.add(new SalesPerson(fName, lName, phone, road, houseNo, postCode, city, userName, 
							pW, employeeNo));
				}
				if (isAdmin && titel == 3)
				{
					employeeArray.add(new Admin(fName, lName, phone, road, houseNo, postCode, city, userName, pW));
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
	public ArrayList<Employee> LoadEmployee(String fName, String lName, String username, int empID, boolean isAdmin) throws SQLException 
	{
		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery((queryEmployeeByFirstName+fName+"%'"+queryEmployeeLastname+lName+"%'"+queryEmployeeByUsername+username+"%'"+queryEmployeeByEmpNo+empID+comparePostcode));
			setEmployee(resultSet, isAdmin);			
		}
		catch (Exception e)
		{
			System.out.println("fejl i load af medarbejdere 1"); //boundary TODO fix
		}
		finally
		{
			closeConnectionLoad();
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
	public ArrayList<Employee> LoadEmployee(String fName, String lName, String username, boolean isAdmin) throws SQLException {

		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery((queryEmployeeByFirstName+fName+"%'"+queryEmployeeLastname+lName+"%'"+queryEmployeeByUsername+username+"%'"+comparePostcode));
			setEmployee(resultSet, isAdmin);			
		}
		catch (Exception e)
		{
			System.out.println("fejl i load af medarbejdere 2"); //boundary TODO fix
		}
		finally
		{
			closeConnectionLoad();
		}
		return employeeArray;
	}


}
