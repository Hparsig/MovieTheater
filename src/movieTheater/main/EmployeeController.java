package movieTheater.main;

import java.util.ArrayList;

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
		try
		{
		
			saveEmployee.createEmployee(fNavn, eNavn, tlf, password, rolle, vej, nr, postNr, brugernavn);
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
