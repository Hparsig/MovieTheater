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
		boolean isExitChosen = false;

		do
		{
			try
			{
				login.latch.await();
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			isExitChosen = login.getIsExitChosen();
			String username = login.getUsername();
			String password = login.getPassword();

			if(!isExitChosen)
			{
				try 
				{
					employee = sqlLogin.checkEmployee(username, password);
					doUserExcist = true;
				}
				catch(IndexOutOfBoundsException outOf)
				{
					login.showDiaglog("Forkert kombination af bruger og password");
					login.setLatch();
				}
				catch(Exception e)
				{
					login.showDiaglog("Alle felterne skal udfyldes korrekt");  
					login.setLatch();
				}	
			}
		}
		while(!doUserExcist && !isExitChosen);

		login.dispose();
		return employee;
	}
}
