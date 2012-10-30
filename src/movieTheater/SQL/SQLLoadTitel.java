package movieTheater.SQL;

import java.sql.ResultSet;
import java.util.ArrayList;
import movieTheater.main.Titel;

public class SQLLoadTitel extends SQL {
	
	private ArrayList<Titel> titel;
	private static final String getAllTitels = "SELECT * FROM titel";
	
	public SQLLoadTitel() {
		titel = new ArrayList<Titel>();
		statement = null;
		connection = null;
	}
	
	public ArrayList<Titel> getTitels(){
		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery(getAllTitels);
			while (resultSet.next())
			{
				String titelName = resultSet.getString("titelName");
				int titelID = resultSet.getInt("titelID");
				titel.add(new Titel(titelID,titelName));
			}			
		}
		catch (Exception e)
		{
			System.out.println("Fejl i load af titler"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return titel;
	}
	

}
