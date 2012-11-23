package movieTheater.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import movieTheater.Movie.Actor;
import movieTheater.Movie.Cast;
import movieTheater.Movie.Director;
import movieTheater.Movie.Genre;
import movieTheater.Movie.Movie;

/**
 * 
 * @author Henrik
 *
 */
public class SQLMovieSave extends SQL{


	private static final String createMovie = "INSERT INTO movies(title,length,genreID,directID,threeDim,orgTitel,premier,endDay) values(?,?,?,?,?,?,?,?)";
	private static final String findGenreID = "SELECT genreID FROM genres WHERE genre='";
	private static final String createCastList = "INSERT INTO casts(movieID,actorID,roleName) values(?,?,?)";
	private static final String getMovieID = "SELECT movieID FROM movies WHERE orgTitel LIKE '";
	private static final String createGenre = "INSERT INTO genres(genre) values(?)";
	private static final String createActor = "INSERT INTO actors(fName, lName, gender, descript) values (?,?,?,?)";
	private static final String createDirector = "INSERT INTO directors(fName, lName, gender, descript) values (?,?,?,?)";
	private static final String updateMovie = "UPDATE movies SET title = ?, length  = ?, genreID = ?, directID = ?, threeDim = ?, orgTitel = ?, premier = ?, endDay = ? WHERE movieID=?";
	private static final String deleteMovie = "DELETE FROM movies WHERE movieID=";


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

	public Movie saveMovie(Movie movie)
	{
		openConnection();

		try
		{
			preparedStatement = connection.prepareStatement(createMovie, Statement.RETURN_GENERATED_KEYS); 

			setStatement(movie);

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if(rs.next())															//Henter nøglen fra det forrige statement. Er der ikke lavet en ny nøgle returneres false 
			{
				movie.setMovieID(rs.getInt(1));							
			}
		}
		catch (Exception e)
		{
			System.out.println("fejl i save af film"); 
			e.printStackTrace();
		}
		finally
		{  
			closeConnectionPreparedStatement(); 
		} 
		return movie;
	}

	public void updateMovie(Movie movie)
	{
		openConnection();

		try
		{
			preparedStatement = connection.prepareStatement(updateMovie); 

			setStatement(movie);
		}
		catch (Exception e)
		{
			System.out.println("fejl i opdatering i film");
		}
		finally
		{  
			closeConnectionPreparedStatement(); 
		} 
	}

	public void deleteMovie(int movieID)
	{
		openConnection();
		try 
		{                     
			statement.executeUpdate(deleteMovie+movieID);      
		}
		catch (Exception e)
		{
			System.out.println("fejl i sletning af film");
			e.printStackTrace();
		}
		finally
		{   
			closeConnectionLoad();      
		} 
	}

	private void setStatement(Movie movie)
	{
		int movieID = movie.getMovieID();
		try
		{
			preparedStatement.setString(1, movie.getTitle());				
			preparedStatement.setInt(2,movie.getLength());
			preparedStatement.setInt(3, movie.getGenre().getGenreID());
			preparedStatement.setInt(4,movie.getDirector().getDirectorID());

			int tdim = 0;
			if(movie.getIs3D())
			{
				tdim = 1;
			}

			preparedStatement.setInt(5, tdim);
			preparedStatement.setString(6,movie.getOriginalTitle());
			preparedStatement.setDate(7, movie.getReleaseDate());
			preparedStatement.setDate(8, movie.getTimeEnd());
			if (movieID != 0)
			{
				preparedStatement.setInt(9, movieID);
			}
			preparedStatement.executeUpdate();      
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
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
			System.out.println("fejl i søgning af genre ID");
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
		String title = movie.getOriginalTitle();
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
				preparedStatement = connection.prepareStatement(createCastList); 

				preparedStatement.setInt(1,movieID);				
				preparedStatement.setInt(2,actorID);
				preparedStatement.setString(3, roleName);
				preparedStatement.executeUpdate();
			}
		}
		catch (Exception e)
		{
			System.out.println("fejl i save cast list");
			e.printStackTrace();
		}
		finally
		{
			closeConnectionPreparedStatement();
		}
	}
	public Genre saveGenre(Genre genre)
	{
		openConnection();

		try
		{

			preparedStatement = connection.prepareStatement(createGenre, Statement.RETURN_GENERATED_KEYS); 
			preparedStatement.setString(1, genre.getGenreName());				

			preparedStatement.executeUpdate();                     

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if(rs.next())															//Henter nøglen fra det forrige statement. Er der ikke lavet en ny nøgle returneres false 
			{
				genre.setGenreID(rs.getInt(1));							
			}

		}
		catch (Exception e)
		{
			System.out.println("fejl i save af genre");
			e.printStackTrace();
		}
		finally
		{  
			closeConnectionPreparedStatement(); 
		} 
		return genre;
	}
	public Actor saveActor(Actor actor)
	{
		openConnection();

		try
		{

			preparedStatement = connection.prepareStatement(createActor, Statement.RETURN_GENERATED_KEYS); 
			preparedStatement.setString(1, actor.getFName());
			preparedStatement.setString(2, actor.getLName());
			preparedStatement.setInt(3, actor.getGender());
			preparedStatement.setString(4, actor.getDescription());
			preparedStatement.executeUpdate();                     

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if(rs.next())															//Henter nøglen fra det forrige statement. Er der ikke lavet en ny nøgle returneres false 
			{
				actor.setActorID(rs.getInt(1));							
			}

		}
		catch (Exception e)
		{
			System.out.println("fejl i save af skuespiller");
			e.printStackTrace();
		}
		finally
		{  
			closeConnectionPreparedStatement(); 
		} 
		return actor;
	}

	public Director saveDirector(Director director)
	{
		openConnection();

		try
		{

			preparedStatement = connection.prepareStatement(createDirector, Statement.RETURN_GENERATED_KEYS); 
			preparedStatement.setString(1, director.getFName());
			preparedStatement.setString(2, director.getLName());
			preparedStatement.setInt(3, director.getGender());
			preparedStatement.setString(4, director.getDescription());
			preparedStatement.executeUpdate();                     

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if(rs.next())															//Henter nøglen fra det forrige statement. Er der ikke lavet en ny nøgle returneres false 
			{
				director.setDirectorID(rs.getInt(1));							
			}
		}
		catch (Exception e)
		{
			System.out.println("fejl i save af instruktører");
			e.printStackTrace();
		}
		finally
		{  
			closeConnectionPreparedStatement(); 
		} 
		return director;
	}

}





