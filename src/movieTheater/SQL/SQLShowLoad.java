package movieTheater.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import movieTheater.main.Show;

public class SQLShowLoad extends SQL{
	
	private ArrayList<Show> showArray;
	private static final String queryShowByMovieID = "SELECT * FROM Shows where movieID =";
	private static final String queryShowByShowID = "SELECT * FROM Shows where showID =";
	private static final String queryShowByHallNo = "SELECT * FROM Shows where hallNo =";
	
	
	public SQLShowLoad(){
		
		showArray = new ArrayList<Show>();
		statement = null;
		connection = null;
	
	}
	public ArrayList<Show> setShow(ResultSet resultSet)
	{
		
		try
		{
			while (resultSet.next())
			{
				int showID = resultSet.getInt("showID");
				int hallNo = resultSet.getInt("hallNo");
				String timeS = resultSet.getString("timeS");
				String timeE = resultSet.getString("timeE");
				int movieID = resultSet.getInt("movieID");
				
				
				
		
				
				
				showArray.add(new Show(showID, hallNo, timeS, timeE, movieID));
			}
		}
		catch (Exception e)
		{
			System.out.println("fejl i set show"); //boundary TODO fix
		}
		return showArray;	
	}
	
	public ArrayList<Show> loadShow(int movieID){
		ResultSet resultSet = null;
		
		try {
			resultSet = statement.executeQuery(queryShowByMovieID+movieID);
			setShow(resultSet);
		} catch (SQLException e) {
			System.out.println("fejl i load forestilling via movieID");
		} 
		finally{
			closeConnectionLoad();
		}
		
		
		
		return showArray;
	}
	
	public ArrayList<Show> loadShowFromID(int showID) throws SQLException{
		ResultSet resultSet = null;
		
		try {
			resultSet = statement.executeQuery(queryShowByShowID+showID);
			setShow(resultSet);
		} catch (SQLException e) {
			System.out.println("fejl i load forestilling via showID");
		} 
		finally{
			closeConnectionLoad();
		}
		
		
		
		return showArray;
	}
	
	public ArrayList<Show> loadShowFromHallNo(int hallNo) throws SQLException{
		ResultSet resultSet = null;
		
		try {
			resultSet = statement.executeQuery(queryShowByHallNo+hallNo);
			setShow(resultSet);
		} catch (SQLException e) {
			System.out.println("fejl i load forestilling via hallNo");
		} 
		finally{
			closeConnectionLoad();
		}
		
		
		
		return showArray;
	}
}
