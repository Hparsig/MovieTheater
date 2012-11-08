package movieTheater.SQL;
import java.sql.SQLException;

import movieTheater.Persons.Employee;
import movieTheater.Persons.Manager;


public class SQLEmployeeSave extends SQL {
	private static final String createEmployee = "insert into employees(fName, lName, titelID, road, houseNo, postCode, phone, pW, username) values(?,?,?,?,?,?,?,?,?)";
	private static final String compareID =" WHERE empNo=";
	private static final String changePW = "UPDATE employees SET pW ='"; 
	private static final String changeUsername = "UPDATE employees SET username ='";
	private static final String changeRoad = "UPDATE employees SET road='";
	private static final String changeNumber = ",houseNo=";
	private static final String changePostcode = ",postCode=";
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
	 * @return int rows
	 * @throws SQLException 
	 */
	public int createEmployee(Employee employee)
	{
		openConnection();
		
		int rows=0;
		try
		{
			preparedStatement = connection.prepareStatement(createEmployee); 
			int titleID = 2;
			
			if (employee instanceof Manager)
			{
				titleID = 1; 
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

			rows = preparedStatement.executeUpdate();                     

		}
		catch (Exception e)
		{
			System.out.println("fejl i save af medarbejder"); //boundary TODO fix
		}
		finally
		{  

			closeConnectionSave(); 
			
		} 
		return rows;
	}  

	/**
	 * @author Jesper
	 * change password for one employee
	 * @param int employeeID, String newPassword
	 * @return int rows
	 * @throws SQLException 
	 */
	public int changePassword(int employeeID, String newPassword) throws SQLException 
	{
		openConnection();
		int rows = 0;
		try 
		{                     
			rows = statement.executeUpdate(changePW+newPassword+"'"+compareID+employeeID);      

		}
		catch (Exception e)
		{
			System.out.println("fejl i ændring af password"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{  
			closeConnectionSave(); 

		} 
		return rows;
	}  
	/**
	 * @author Jesper
	 * change username for one employee
	 * @param int employeeID, String newUsername
	 * @return int rows
	 * @throws SQLException 
	 */
	public int changeUsername(int employeeID, String newUsername) throws SQLException 
	{
		openConnection();
		int rows = 0;
		try 
		{                     
			rows = statement.executeUpdate(changeUsername+newUsername+"'"+compareID+employeeID);      

		}
		catch (Exception e)
		{
			System.out.println("fejl i ændring af username"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{  
			closeConnectionSave(); 

		} 
		return rows;
	}
	/**
	 * @author Jesper
	 * change adress for one employee
	 * @param int employeeID, String Raod, int number, int postcode
	 * @return int rows
	 * @throws SQLException 
	 */
	public int changeAdress(int employeeID,String road, int number, int postcode) throws SQLException 
	{
		openConnection();
		int rows = 0;
		try 
		{                     
			rows = statement.executeUpdate(changeRoad+road+"'"+changeNumber+number+changePostcode+postcode+compareID+employeeID);      

		}
		catch (Exception e)
		{
			System.out.println("fejl i ændring af adresse"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{   

			closeConnectionSave();      
		} 
		return rows;
	}
	/**
	 * @author Jesper
	 * change adress for one employee
	 * @param int employeeID, String Raod, int number, int postcode
	 * @return int rows
	 * @throws SQLException 
	 */
	public void deleteEmployee(int emNum) throws SQLException 
	{
		openConnection();
		try 
		{                     
			statement.executeUpdate(deleteEmployee+emNum);      

		}
		catch (Exception e)
		{
			System.out.println("fejl i sletning af medarbejder"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{   

			closeConnectionSave();      
		} 
	}  

}
