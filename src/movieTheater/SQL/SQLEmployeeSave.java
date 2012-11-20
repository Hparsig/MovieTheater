package movieTheater.SQL;
import java.sql.SQLException;

import movieTheater.Persons.Admin;
import movieTheater.Persons.Employee;
import movieTheater.Persons.Manager;
import movieTheater.Persons.Person;


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
	public void createEmployee(Person person)
	{
		openConnection();
		
		try
		{
			preparedStatement = connection.prepareStatement(createEmployee); 
			int titleID = 2;
			
			if (person instanceof Manager)
			{
				titleID = 1; 
			}
			if (person instanceof Admin)
			{
				titleID = 3;
			}
			System.out.println(person.getPostCode());
			preparedStatement.setString(1,person.getfName());				
			preparedStatement.setString(2,person.getlName());
			preparedStatement.setInt(3, titleID);
			preparedStatement.setString(4, person.getRoad());
			preparedStatement.setString(5, person.getHouseNo());  
			preparedStatement.setInt(6, person.getPostCode());
			preparedStatement.setInt(7,person.getPhone());
			preparedStatement.setString(8, person.getPW());
			preparedStatement.setString(9, person.getUserName());

			preparedStatement.executeUpdate();                     

		}
		catch (Exception e)
		{
			System.out.println("fejl i save af medarbejder");
			e.printStackTrace();
		}
		finally
		{  

			closeConnectionSave(); 
			
		} 
	}  
	
	/**
	 * @author Jesper
	 * Update one employee
	 * @param Employee employee
	 * @return void
	 * @throws SQLException 
	 */
	public void updateEmployee(Person person)
	{
		openConnection();
		
		try
		{
			preparedStatement = connection.prepareStatement(updateEmployee); 
			int titleID = 2;
			int employeeNo = 0;
			
			if (person instanceof Employee)
			{
				employeeNo = ((Employee)person).getEmployeeNo();
			}
			if (person instanceof Manager)
			{
				titleID = 1; 
			}
			if (person instanceof Admin)
			{
				titleID = 3;
			}
			
			preparedStatement.setString(1,person.getfName());				
			preparedStatement.setString(2,person.getlName());
			preparedStatement.setInt(3, titleID);
			preparedStatement.setString(4, person.getRoad());
			preparedStatement.setString(5, person.getHouseNo());  
			preparedStatement.setInt(6, person.getPostCode());
			preparedStatement.setInt(7,person.getPhone());
			preparedStatement.setString(8, person.getPW());
			preparedStatement.setString(9, person.getUserName());
			preparedStatement.setInt(9, employeeNo);
			preparedStatement.executeUpdate();                     

		}
		catch (Exception e)
		{
			System.out.println("fejl i ændring af medarbejder"); 
		}
		finally
		{  

			closeConnectionSave(); 
			
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
