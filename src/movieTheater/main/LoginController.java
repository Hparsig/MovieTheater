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

/**
 * @author Jesper
 * @return Employee currentEmployee
 * returns the user that logins
 */
	public Employee employeeLogin()
	{
		employee = null;
		LoginEmployee login = new LoginEmployee();
		login.setVisible(true);
		boolean doUserExcist = false;
		while(!doUserExcist)
		{
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
				doUserExcist = true;
			}
			catch(IndexOutOfBoundsException outOf)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Brugeren findes ikke");
				login.setLatch();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Alle felterne skal udfyldes korrekt");  
				login.setLatch();
			}	
			
		}
		login.dispose();
		System.out.println(employee.getfName());
		return employee;
	}


}
