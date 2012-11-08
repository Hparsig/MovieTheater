package movieTheater.main;

import java.sql.SQLException;
import java.util.ArrayList;

import movieTheater.Persons.Employee;
import movieTheater.SQL.SQLEmployeeLoad;
import movieTheater.SQL.SQLEmployeeSave;

public class EmployeeController {

	private SQLEmployeeLoad load;
	private SQLEmployeeSave save;
	private ArrayList<Employee> employees;
	
	public EmployeeController(SQLEmployeeLoad load, SQLEmployeeSave save) 
	{
		this.load = load;
		this.save = save;
		employees = new ArrayList<Employee>();
	}
	public void deleteEmployee(int choose,Employee employee) throws SQLException
	{
		if(choose==0)
		{
			save.deleteEmployee(employee.getEmployeeNo());
		}
	}
	
	public ArrayList<Employee> searchEmployees(String fName, String lName, String username, String employeeID)throws SQLException
	{
		employees.clear();
		try
		{
			int num = Integer.parseInt(employeeID);
			employees = load.LoadEmployee(fName, lName, username,num);
		}catch(java.lang.NumberFormatException e)
		{
			employees = load.LoadEmployee(fName, lName, username);
		}
		return employees;
				
	}


}
