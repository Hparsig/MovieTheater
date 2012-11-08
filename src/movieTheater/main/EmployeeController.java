package movieTheater.main;

import java.sql.SQLException;
import java.util.ArrayList;

import movieTheater.Persons.Employee;
import movieTheater.Persons.Manager;
import movieTheater.Persons.SalesPerson;
import movieTheater.SQL.SQLEmployeeLoad;
import movieTheater.SQL.SQLEmployeeSave;

public class EmployeeController {

	private SQLEmployeeLoad load;
	private SQLEmployeeSave save;
	private ArrayList<Employee> employees;
	private Employee employee;
	
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
	public Employee createEmployee(int titleID, String name, String lastname,int phone, String road, String houseNr, int postcode, String cityChoosen,String username, String pWord){
		
		if(titleID==1)
		{
			employee = new Manager(name,lastname,phone,road,houseNr,postcode,cityChoosen,username,pWord);
		}
		else
		{
			employee = new SalesPerson(name,lastname,phone,road,houseNr,postcode,cityChoosen,username,pWord);
		}
		
		return employee;
	}
	public void EditEmployee(int titleID, String name, String lastname,int phone, String road, String houseNr, int postcode, String cityChoosen,String username, String pWord, int employeeNum){
		if(titleID==1)
		{
			employee = new Manager(name,lastname,phone,road,houseNr,postcode,cityChoosen,username,pWord,employeeNum);
		}
		else
		{
			employee = new SalesPerson(name,lastname,phone,road,houseNr,postcode,cityChoosen,username,pWord,employeeNum);
		}
		save.editEmployee(employee);
	}


}
