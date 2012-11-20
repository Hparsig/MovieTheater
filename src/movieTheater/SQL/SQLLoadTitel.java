package movieTheater.SQL;

import java.sql.ResultSet;
import java.util.ArrayList;
import movieTheater.main.Title;

public class SQLLoadTitel extends SQL {
	
	private ArrayList<Title> titel;
	private static final String getAllTitels = "SELECT * FROM titel";
	
	public SQLLoadTitel() {
		titel = new ArrayList<Title>();
		statement = null;
		connection = null;
	}
	
	public ArrayList<Title> getTitels()
	{
		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery(getAllTitels);
			while (resultSet.next())
			{
				String titelName = resultSet.getString("titelName");
				int titelID = resultSet.getInt("titelID");
				titel.add(new Title(titelID,titelName));
			}			
		}
		catch (Exception e)
		{
			System.out.println("Fejl i load af titler"); 
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return titel;
	}
}
