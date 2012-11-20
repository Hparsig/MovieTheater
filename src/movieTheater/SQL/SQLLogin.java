package movieTheater.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import movieTheater.Persons.Admin;
import movieTheater.Persons.Employee;
import movieTheater.Persons.Manager;
import movieTheater.Persons.SalesPerson;

public class SQLLogin extends SQL {


	private static final String employeeLogin = "SELECT emp.*, post.city FROM employees emp, postcode post WHERE pW = ? AND username = ? AND emp.postCode = post.postCode";
	private ArrayList<Employee> employeeArray;

	public SQLLogin() 
	{
		employeeArray = new ArrayList<Employee>();
		statement = null;
		connection = null;
	}

	/**
	 * @author Jesper
	 * login check of employee
	 * @param String username, String Password
	 * @return Employee
	 * @throws SQLException
	 */
	public Employee checkEmployee(String username, String password)
	{
		openConnection();
		 
		ResultSet resultSet = null;

		try
		{
			preparedStatement = connection.prepareStatement(employeeLogin);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, username);

			resultSet = preparedStatement.executeQuery();
			setEmployee(resultSet);
		}
		catch (Exception e)
		{
			System.out.println("fejl i login af medarbejder"); 
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return employeeArray.get(0);
	}

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

				employeeArray.clear();
				if (titel == 1)
				{
					employeeArray.add(new Manager(fName, lName, phone, road, houseNo, postCode, city, userName, 
							pW, employeeNo));
				}
				if (titel == 3)
				{
					employeeArray.add(new Admin(fName, lName, phone, road, houseNo, postCode, city, userName, 
							pW,employeeNo));
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
			System.out.println("fejl i set medarbejder"); 
			e.printStackTrace();

		}
		return employeeArray;	
	}


}
