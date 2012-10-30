package movieTheater.main;

import java.util.ArrayList;

import movieTheater.Persons.*;
import movieTheater.SQL.SQLEmployeeSave;

public class EmployeeController {

	private ArrayList<Employee> employees;
	private Employee employee;
	private SQLEmployeeSave saveEmployee;
	
	public EmployeeController(){
		employees = new ArrayList<Employee>();
		saveEmployee = new SQLEmployeeSave();
	}
	
	public void createEmployee(String fName, String eName, int phone, String pW, int titelID, String road, String houseNo, int postcode,String city, String username)
	{
		if(titelID==1)
		{
			employee = new Manager(fName, eName, phone, road, houseNo, postcode, city, username, pW);
		}
		else
		{
		employee = new SalesPerson(fName, eName, phone, road, houseNo, postcode, city, username, pW);
		}
		try		
		{
	
			saveEmployee.createEmployee(employee);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	public Employee getEmployee(int i)
	{
		employee = employees.get(i);
		return employee;
	}
	
}
