package movieTheater.SQL;

import java.sql.*;

/**
 * 
 * @author Henrik
 *
 */
public abstract class SQL {

	private static final String forName = "com.mysql.jdbc.Driver";
	private static final String connectionPath = "jdbc:mysql://localhost/MovieTheater";
	Statement statement ;
	Connection connection;
	PreparedStatement preparedStatement;
	/**
	 * Opens connection to the database. 
	 */
	protected void openConnection()
	{
		try
		{
			Class.forName(forName);
			connection = DriverManager.getConnection(
					connectionPath, "admin_MT","admin_pw");
			statement = connection.createStatement();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fejl i openConnection"); 
		}
	}
	/**
	 * closes connection
	 */
	protected void closeConnectionLoad()
	{
		try
		{
			statement.close();
			connection.close();
		}
		catch (SQLException e)
		{
			System.out.println("fejl i closeConnection");
		}
	}
	/**
	 * closes connections that have used a prepared statement. 
	 */
	protected void closeConnectionPreparedStatement()
	{
		try
		{
			statement.close();
			connection.close();
			preparedStatement.close();
		}
		catch (SQLException e)
		{
			System.out.println("fejl i closeConnection");
		}
	}
}
