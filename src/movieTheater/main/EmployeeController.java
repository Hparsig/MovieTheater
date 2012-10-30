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
	
	public void createSalesPerson(String fName, String eName, int phone, String road, String houseNo, int postCode, String city, String userName, String pW)
	{
		
		employee = new SalesPerson(fName, eName, phone, road, houseNo, postCode, city, userName, pW);
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
