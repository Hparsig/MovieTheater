package movieTheater.SQL;
import java.util.ArrayList;
import java.sql.*;

import movieTheater.main.Employee;

public class SQLUserLoad extends SQL{
	private ArrayList<Employee> employeeArray;	
	private static final String queryEmployee = "SELECT * FROM Employees where empNo =";
	private static final String queryEmployeeByFirstName = "SELECT * FROM Employees where fName LIKE ";
	//private static final String queryEmployeeWork = "SELECT * FROM TicketCheck where empNo =";

	public SQLUserLoad()
	{
		employeeArray = new ArrayList<Employee>();
		statement = null;
		connection = null;
	}

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
				int titel = resultSet.getInt("titelID");
				String road = resultSet.getString("raod");
				int nr = resultSet.getInt("houseNo");
				int postNr = resultSet.getInt("postCode");
				String username = resultSet.getString("username");
				String password = resultSet.getString("pW");
							
				employeeArray.add(new Employee(employeeNr, firstName, lastName,tlf,titel,road,nr,postNr,username,password));
			}
		}
		catch (Exception e)
		{
			System.out.println("fejl i set medarbejder"); //boundary TODO fix
		}
		return employeeArray;	
	}

	public ArrayList<Employee> LoadEmployee(int emNum) throws SQLException {

		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery((queryEmployee+emNum));
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

	public ArrayList<Employee> LoadEmployee(String firstName) throws SQLException {
		//
		//				ArrayList<Actor> cast = new ArrayList<Actor>();
		//				ArrayList<Rating> ratings = new ArrayList<Rating>();
		//				boolean isThreeDim = false;
		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery((queryEmployeeByFirstName+firstName));
			setEmployee(resultSet);			
		}
		catch (Exception e)
		{
			System.out.println("fejl i load af medarbejdere efter fornavn"); //boundary TODO fix
		}
		finally
		{
			closeConnectionLoad();
		}
		return employeeArray;
	}
}