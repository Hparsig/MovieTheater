package movieTheater.main;

import java.util.ArrayList;

import movieTheater.Persons.Employee;
import movieTheater.SQL.SQLEmployeeSave;

public class EmployeeController {

	private ArrayList<Employee> employees;
	private Employee employee;
	private SQLEmployeeSave saveEmployee;
	
	public EmployeeController(){
		employees = new ArrayList<Employee>();
		saveEmployee = new SQLEmployeeSave();
	}
	
	public void createUser(String fNavn, String eNavn, int tlf, String password, int rolle, String vej, int nr, int postNr, String brugernavn)
	{
		employee = new Employee(fNavn, eNavn, tlf, rolle, vej, nr, postNr, brugernavn,password);
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
