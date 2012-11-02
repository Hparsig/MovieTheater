package movieTheater.SQL;
import java.sql.*;
import java.util.Map;

import movieTheater.Movie.Actor;
import movieTheater.Movie.Cast;
import movieTheater.Movie.Director;
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
	private static final String createCastList = "INSERT INTO casts(movieID,actorID,roleName) values(?,?,?)";
	private static final String getMovieID = "SELECT movieID FROM movies WHERE orgTitel LIKE '";
		
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
	
	public int saveMovie(Movie movie)
	{
		openConnection();
		
		int rows=0;
		try
		{
			
			preparedStatement = connection.prepareStatement(createMovie); 
			
			preparedStatement.setString(1, movie.getMovieName());				
			preparedStatement.setInt(2,movie.getLength());
			
			int genreID = getGenreID(movie);
			preparedStatement.setInt(3, genreID);
			
			preparedStatement.setInt(4,movie.getInstructedBy().getDirectorID());
			
			int tdim = 0;
			if(movie.getIsIn3D())
			{
				tdim = 1;
			}
		
			preparedStatement.setInt(5, tdim);
			preparedStatement.setString(6,movie.getOriginalName());
			preparedStatement.setDate(7, movie.getReleaseDate());
			preparedStatement.setDate(8, movie.getTimeEnd());
			

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
		saveCastList(movie);
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
	
	public void saveCastList(Movie movie)
	{
		ResultSet resultSet = null;
		preparedStatement = null;
		openConnection();
		int movieID=0;
		String title = movie.getOriginalName();
		Cast cast = movie.getCast();
		Map<Actor, String> castMap = cast.getCast();
		
		try
		{
			resultSet = statement.executeQuery(getMovieID+title+"'");

			while(resultSet.next())
			{
				movieID = resultSet.getInt("movieID");
			}
			
			for(Map.Entry<Actor, String> entry : castMap.entrySet())
			{	
				int actorID = entry.getKey().getActorID();
				String roleName = entry.getValue();
//				String name = (entry.getKey().getFName() + " " + entry.getKey().getLName() + " ");
//				String role = entry.getValue();
				
//				castString.add("\n" + role+ ":" + name);
//			}
//			for (int i=0; i < movie.getCast().;i++)
//			{
//				
//				int actorID = movie.getCast().get(i).getActor().getActorID();
//				String roleName = movie.getCast().get(i).getRolename();
				preparedStatement = connection.prepareStatement(createCastList); 
				
				preparedStatement.setInt(1,movieID);				
				preparedStatement.setInt(2,actorID);
				preparedStatement.setString(3, roleName);
				preparedStatement.executeUpdate();
			}
		}
		catch (Exception e)
		{
			System.out.println("fejl i save cast list"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{
			closeConnectionSave();
		}
	}
	
	

	


}
