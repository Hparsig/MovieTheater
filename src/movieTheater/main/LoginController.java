package movieTheater.main;
import java.sql.SQLException;

import movieTheater.Persons.Employee;
import movieTheater.SQL.SQLLogin;

public class LoginController {
	private SQLLogin sqlLogin;
	private Employee employee;
	
	public LoginController() {
		sqlLogin = new SQLLogin();
	}
	
	public Employee employeeLogin(String password, String username) throws SQLException { 
		
		employee = sqlLogin.checkEmployee(username, password);
		System.out.println("logget ind");
	
		return employee;
	}


}
