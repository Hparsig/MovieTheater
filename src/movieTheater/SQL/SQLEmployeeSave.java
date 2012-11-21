package movieTheater.SQL;
import java.sql.SQLException;

import movieTheater.Persons.Admin;
import movieTheater.Persons.Employee;
import movieTheater.Persons.Manager;


public class SQLEmployeeSave extends SQL {
	private static final String createEmployee = "INSERT INTO employees(fName, lName, titelID, road, houseNo, postCode, phone, pW, username) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String updateEmployee = "UPDATE employees SET fName = ?, lName  = ?, titelID = ?, road = ?, houseNo = ?, postCode = ?, pW = ?, username = ? WHERE empNo=?";
	private static final String deleteEmployee = "DELETE FROM employees WHERE empNo=";
	
	/**
	 * Constructor
	 */
	public SQLEmployeeSave()
	{
		statement = null;
		connection = null;
	}

	/**
	 * @author Jesper
	 * Create one employe
	 * @param Employee employee
	 * @return void
	 * @throws SQLException 
	 */
	public void createEmployee(Employee employee)
	{
		openConnection();
		
		try
		{
			preparedStatement = connection.prepareStatement(createEmployee); 
			int titleID = 2;
			
			if (employee instanceof Manager)
			{
				titleID = 1; 
			}
			if (employee instanceof Admin)
			{
				titleID = 3;
			}
			System.out.println(employee.getPostCode());
			preparedStatement.setString(1,employee.getfName());				
			preparedStatement.setString(2,employee.getlName());
			preparedStatement.setInt(3, titleID);
			preparedStatement.setString(4, employee.getRoad());
			preparedStatement.setString(5, employee.getHouseNo());  
			preparedStatement.setInt(6, employee.getPostCode());
			preparedStatement.setInt(7,employee.getPhone());
			preparedStatement.setString(8, employee.getPW());
			preparedStatement.setString(9, employee.getUserName());

			preparedStatement.executeUpdate();                     

		}
		catch (Exception e)
		{
			System.out.println("fejl i save af medarbejder");
			e.printStackTrace();
		}
		finally
		{  

			closeConnectionPreparedStatement(); 
			
		} 
	}  
	
	/**
	 * @author Jesper
	 * Update one employee
	 * @param Employee employee
	 * @return void
	 * @throws SQLException 
	 */
	public void updateEmployee(Employee employee)
	{
		openConnection();
		
		try
		{
			preparedStatement = connection.prepareStatement(updateEmployee); 
			int titleID = 2;
			int employeeNo = employee.getEmployeeNo();
		
			if (employee instanceof Manager)
			{
				titleID = 1; 
			}
			if (employee instanceof Admin)
			{
				titleID = 3;
			}
			
			preparedStatement.setString(1,employee.getfName());				
			preparedStatement.setString(2,employee.getlName());
			preparedStatement.setInt(3, titleID);
			preparedStatement.setString(4, employee.getRoad());
			preparedStatement.setString(5, employee.getHouseNo());  
			preparedStatement.setInt(6, employee.getPostCode());
			preparedStatement.setInt(7,employee.getPhone());
			preparedStatement.setString(8, employee.getPW());
			preparedStatement.setString(9, employee.getUserName());
			preparedStatement.setInt(9, employeeNo);
			preparedStatement.executeUpdate();                     

		}
		catch (Exception e)
		{
			System.out.println("fejl i ændring af medarbejder"); 
		}
		finally
		{  

			closeConnectionPreparedStatement(); 
			
		} 
	}  

	/**
	 * @author Jesper
	 * delete one employee
	 * @param int emNum
	 * @return int rows
	 * @throws SQLException 
	 */
	public void deleteEmployee(int emNum)
	{
		openConnection();
		try 
		{                     
			statement.executeUpdate(deleteEmployee+emNum);      

		}
		catch (Exception e)
		{
			System.out.println("fejl i sletning af medarbejder"); 
			e.printStackTrace();
		}
		finally
		{   
			closeConnectionLoad();      
		} 
	}  

}
