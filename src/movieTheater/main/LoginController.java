package movieTheater.main;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import movieTheater.GUI.LoginEmployee;
import movieTheater.Persons.Employee;
import movieTheater.SQL.SQLLogin;

public class LoginController {
	private SQLLogin sqlLogin;
	private Employee employee;

	public LoginController() 
	{
		sqlLogin = new SQLLogin();
	}

	public Employee employeeLogin()
	{
		employee = null;
		LoginEmployee login = new LoginEmployee();
		login.setVisible(true);
		try
		{
			login.latch.await();
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}	
		String username = login.getUsername();
		String password = login.getPassword();

		try 
		{
			employee = sqlLogin.checkEmployee(username, password);
		}
		catch(IndexOutOfBoundsException outOf)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Brugeren findes ikke");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Alle felterne skal udfyldes korrekt");  
		}		

		System.out.println("logget ind");

		return employee;
	}


}
