package movieTheater.SQL;
//Test
import java.sql.*;

public abstract class SQL {

	private static final String forName = "com.mysql.jdbc.Driver";
	private static final String connectionPath = "jdbc:mysql://localhost/MovieTheater";
	Statement statement ;
	Connection connection;
	PreparedStatement preparedStatement;
	//*********************************************************************************************
	// Metode der bruges af de ovenstående metoder, til at åbne en forbindelse til databasen.
	//*********************************************************************************************
	protected void openConnection()
	{
		try
		{
			Class.forName(forName);
			connection = DriverManager.getConnection(
					connectionPath, "root","");
			statement = connection.createStatement();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fejl i openConnection"); //boundary TODO fix
		}
	}
	//*********************************************************************************************
	// Metode der bruges af de ovenstående metoder, til at lukke forbindelsen til databasen. 
	//*********************************************************************************************
	protected void closeConnection()
	{
		try
		{
			statement.close();
			connection.close();
			preparedStatement.close();
		}
		catch (SQLException e)
		{
			System.out.println("fejl i closeConnection");//TODO håndter catch
		}
	}

}
