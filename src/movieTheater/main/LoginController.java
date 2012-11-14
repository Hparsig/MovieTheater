package movieTheater.main;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import movieTheater.GUI.LoginEmployee;
import movieTheater.Persons.Employee;
import movieTheater.Persons.Person;
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
			// TODO Auto-generated catch block
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
		catch(SQLException sql)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Fejl i load til databasen, prøv venligst igen");  
		}	
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Alle felterne skal udfyldes korrekt");  
		}		

		System.out.println("logget ind");

		return employee;
	}


}
