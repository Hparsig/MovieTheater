package movieTheater.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import movieTheater.Persons.Employee;
import movieTheater.Persons.Manager;
import movieTheater.Persons.SalesPerson;
import movieTheater.SQL.SQLEmployeeLoad;
import movieTheater.SQL.SQLEmployeeSave;

public class TestSQLEmployee {

	SQLEmployeeLoad employeeLoad;
	SQLEmployeeSave employeeSave;
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
				System.out.println(currentEmployee.getCity() + " " + currentEmployee.getEmployeeNo() + " " + currentEmployee.getfName() + 
						" " + currentEmployee.getHouseNo() + " " + currentEmployee.getlName() + " " + currentEmployee.getPhone() + 
						" " + currentEmployee.getPostCode() + " " + currentEmployee.getPW() + " " + currentEmployee.getRoad() + " " +
						currentEmployee.getUserName());	
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
			Manager m = new Manager(Generator(8, true), Generator(8, false), Integer.parseInt(Generator(8, false)), Generator(2, true), Generator(8, true), 
					Integer.parseInt(Generator(4, false)), Generator(8, true), Generator(8, true), Generator(8, true));
			SalesPerson s = new SalesPerson(Generator(8, true), Generator(8, false), Integer.parseInt(Generator(8, false)), Generator(2, true), Generator(8, true),
					Integer.parseInt(Generator(4, false)), Generator(8, true), Generator(8, true), Generator(8, true));
			employeeSave.createEmployee(m);
			employeeSave.createEmployee(s);
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