package movieTheater.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import movieTheater.SQL.SQLEmployeeLoad;
import movieTheater.SQL.SQLEmployeeSave;
import movieTheater.main.Employee;

public class TestSQLEmployee {

	SQLEmployeeLoad employeeLoad;
	SQLEmployeeSave employeeSave;
	Employee employee;
	ArrayList<Employee> employees;

	public TestSQLEmployee()
	{
		employeeLoad = new SQLEmployeeLoad();
	}

	public void runTest()
	{

		for(int i = 0; i < employees.size(); i++)
			testEmployeeLoad(i);
		for(int i = 0; i < 10; i++)
			testEmployeeSave();
	}
	public void testEmployeeLoad(int i)
	{
		try
		{
			employees = employeeLoad.LoadEmployee(i);	

			for(Employee currentEmployee: employees)
			{
				System.out.println(currentEmployee.getFirstName() + " " + currentEmployee.getLastName() + " " + currentEmployee.getRoad() + 
						" " + currentEmployee.getPostNr() + " " + currentEmployee.getCity() + " " + currentEmployee.getPassword());	
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void testEmployeeSave()
	{
		try {
			employeeSave.createEmployee(new Employee(0, Generator(8, true), Generator(8, true), 0, 0, Generator(8, true), 0, 0, Generator(8, true), Generator(8, true), Generator(8, true)));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	// String/int generator til brugerdata. Ascii == true til strings, og ascii == false til int. Huske at parse! 
	protected static String Generator(int length, boolean ascii){
		String available = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";; 
		String pass = "";
		int size;
		if(ascii)
			size = 62;
		else
			size = 10;			//Integer.parseInt(Generator(1, false))

		Random r = new Random();
		for(int i = 0; i < length; i++){
			int temp = r.nextInt(size);
			pass = pass + available.substring(temp, temp+1);			
		}
		return pass;
	}
}