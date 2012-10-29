package movieTheater.SQL;
import java.sql.*;

import movieTheater.Movie.Movie;
import movieTheater.Persons.Manager;

/**
 * 
 * @author Jesper
 *
 */
public class SQLMovieSave extends SQL{
	

	private static final String createMovie = "INSERT INTO movies(title,length,genreID,directID,threeDim,orgTitel,premier,endDay) values(?,?,?,?,?,?,?,?)";
	private static final String findGenreID = "SELECT genreID FROM genres WHERE genre='";
	public SQLMovieSave()
	{
		
		statement = null;
		connection = null;
		preparedStatement = null;
	}
	
	/**
	 * @author Jesper
	 * Save one movie to the database
	 * @param Movie movie
	 * @return int rows
	 * @throws SQLException 
	 */
	
	public int saveMovie(Movie movie, int genreID){
		openConnection();
		
		int rows=0;
		try
		{
			
			preparedStatement = connection.prepareStatement(createMovie); 
			
			preparedStatement.setString(1, movie.getMovieName());				
			preparedStatement.setInt(2,movie.getLength());
			preparedStatement.setInt(3, genreID);
			preparedStatement.setInt(4,genreID);
			
			boolean threedim = movie.getIsIn3D();
			int tdim = 0;
			if(threedim==true){
				tdim = 1;
			}
		
			preparedStatement.setInt(5, tdim);
			preparedStatement.setString(6,movie.getOriginalName());
			preparedStatement.setTimestamp(7, movie.getReleaseDate());
			preparedStatement.setTimestamp(8, movie.getTimeEnd());
			

			rows = preparedStatement.executeUpdate();                     

		}
		catch (Exception e)
		{
			System.out.println("fejl i save af film"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{  

			closeConnectionSave(); 
		} 
		return rows;
	}
	
	public int getGenreID(Movie movie){
		openConnection();
		ResultSet resultSet = null;
		int genreID=0;
		try 
		{                     
			resultSet = statement.executeQuery(findGenreID+movie.getGenre()+"'");

			while(resultSet.next())
			{
				genreID = resultSet.getInt("genreID");      
			}
		}
		catch (Exception e)
		{
			System.out.println("fejl i søgning af genre ID"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{  
			closeConnectionLoad(); 

		} 
		return genreID;
		
	}
	

	


}
