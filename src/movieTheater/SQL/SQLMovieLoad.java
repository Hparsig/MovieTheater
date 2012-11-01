package movieTheater.SQL;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;

import movieTheater.Movie.Actor;
import movieTheater.Movie.Director;
import movieTheater.Movie.Movie;
import movieTheater.Movie.Rating;
import movieTheater.Movie.Cast;
/**
 * 
 * @author Henrik
 *
 */
public class SQLMovieLoad extends SQL{
	//Henrik Parsig

	private ArrayList<Movie> dataFilmArray;
	private static final String queryMovies = "SELECT * FROM Movies where genreID =";
	private static final String queryMoviesByTitle = "SELECT * FROM Movies where title like '";
	private static final String queryActor = "SELECT * FROM Actors";
	private static final String queryDirectorByID = "SELECT * FROM Directors where directID =";
	private static final String queryDirector = "SELECT * FROM Directors";
	private static final String queryDirectorBylName = "SELECT * FROM Directors where lName LIKE '";
	private static final String queryRatings = "SELECT * FROM Reviews where filmID =";
	private static final String queryGenre = "SELECT * FROM Genres where genreID=";
	private static final String queryCast = "SELECT c.*, a.* FROM casts c, actors a WHERE c.movieID =";
	private static final String queryCastTwo = " AND c.actorID = a.actorID";
/**
 * 	public SQLMovieLoad()
 */
	{
		dataFilmArray = new ArrayList<Movie>();
		statement = null;
		connection = null;
	}

	/**
	 * Search movies using genre as parameter
	 * @param int genreID
	 * @return ArrayList<Film> 
	 * @throws SQLException
	 */
	public ArrayList<Movie> LoadMovie(int genreID) throws SQLException {
		//
		//		ArrayList<Actor> cast = new ArrayList<Actor>();
		//		ArrayList<Rating> ratings = new ArrayList<Rating>();
		//		boolean isThreeDim = false;
		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery((queryMovies+genreID));
			setMovie(resultSet);			
		}
		catch (Exception e)
		{
			System.out.println("fejl i load movie by genre"); //boundary TODO fix
		}
		finally
		{
			closeConnectionLoad();
		}
		return dataFilmArray;
	}
	/**
	 * 
	 * @param String title
	 * @return ArrayList<Film> 
	 * @throws SQLException
	 */
	public ArrayList<Movie> LoadMovie(String title) throws SQLException {
		//
		//				ArrayList<Actor> cast = new ArrayList<Actor>();
		//				ArrayList<Rating> ratings = new ArrayList<Rating>();
		//				boolean isThreeDim = false;
		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery((queryMoviesByTitle+title+"'"));
			setMovie(resultSet);			
		}
		catch (Exception e)
		{
			System.out.println("fejl i load movie by title"); //boundary TODO fix
		}
		finally
		{
			closeConnectionLoad();
		}
		return dataFilmArray;
	}

	/**
	 * 
	 * @param resultSet
	 * @return ArrayList<Film> dataFilmArray
	 */
	public ArrayList<Movie> setMovie(ResultSet resultSet)
	{
		HashMap<Actor, String> castHash = new HashMap<Actor, String>();
		Cast cast;
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		boolean isThreeDim = false;

		try
		{
			while (resultSet.next())
			{
				isThreeDim = false;

				int movieID = resultSet.getInt("movieID");
				String title = resultSet.getString("title"); 		  
				int length = resultSet.getInt("length");
				int genreID = resultSet.getInt("genreID");
				int directID = resultSet.getInt("directID");	
				int threeDim = resultSet.getInt("threeDim");		
				String orgTitel = resultSet.getString("orgTitel"); 	
				Date premier = resultSet.getDate("premier");
				Date endDay = resultSet.getDate("endDay");

				Director director = LoadDirector(directID);
				castHash = LoadCast(movieID);
				ratings = LoadRatings(movieID);
				String genre = LoadGenre(genreID);

				if(threeDim == 1)
				{
					isThreeDim = true;
				}
				cast = new Cast(castHash);
				dataFilmArray.add(new Movie(title, director, length, genre, premier, endDay, orgTitel, isThreeDim, cast, ratings));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fejl i set movie"); //boundary TODO fix
		}
		return dataFilmArray;	
	}
	/**
	 * 
	 * @param directID
	 * @return Director
	 * @throws SQLException
	 */
	public Director LoadDirector(int directID) throws SQLException
	{
		Director director = null;
		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery(queryDirectorByID+directID);

			while(resultSet.next())
			{
				String dirFirstName = resultSet.getString("fName") ;
				String dirLastName = resultSet.getString("lName");
				int dirGender = resultSet.getInt("gender");
				String dirDescription = resultSet.getString("descript");

				director = new Director(dirFirstName, dirLastName, dirGender, dirDescription);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fejl i load director"); //boundary TODO fix
		}
		finally
		{
			closeConnectionLoad();
		}
		return director;
	}
	public ArrayList<Director> LoadDirector() throws SQLException
	{
		ArrayList<Director> directors = new ArrayList<Director>();
		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery(queryDirector);

			while(resultSet.next())
			{
				String dirFirstName = resultSet.getString("fName") ;
				String dirLastName = resultSet.getString("lName");
				int dirGender = resultSet.getInt("gender");
				String dirDescription = resultSet.getString("descript");

				directors.add(new Director(dirFirstName, dirLastName, dirGender, dirDescription));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fejl i load director"); //boundary TODO fix
		}
		finally
		{
			closeConnectionLoad();
		}
		return directors;
	}

	public ArrayList<Director> LoadDirector(String lName) throws SQLException
	{
		ArrayList<Director> directors = new ArrayList<Director>();
		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery(queryDirectorBylName+lName+"'");

			while(resultSet.next())
			{
				String dirFirstName = resultSet.getString("fName") ;
				String dirLastName = resultSet.getString("lName");
				int dirGender = resultSet.getInt("gender");
				String dirDescription = resultSet.getString("descript");

				directors.add(new Director(dirFirstName, dirLastName, dirGender, dirDescription));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fejl i load director via lName"); //boundary TODO fix
		}
		finally
		{
			closeConnectionLoad();
		}
		return directors;
	}
	/**
	 * 
	 * @param genreID
	 * @return String genreName
	 * @throws SQLException
	 */
	public String LoadGenre(int genreID) throws SQLException 
	{
		String genre = "";
		ResultSet resultSet = null;

		try
		{
			openConnection();
			resultSet = statement.executeQuery((queryGenre+genreID));

			while(resultSet.next())
			{
				genre = resultSet.getString("genre");
			}
		}
		catch (Exception e)
		{
			System.out.println("fejl i load genre"); //boundary TODO fix
		}
		finally
		{
			closeConnectionLoad();
		}
		return genre;
	}
	/**
	 * 
	 * @param filmID
	 * @return ArrayList<Rating>
	 * @throws SQLException
	 */
	public ArrayList<Rating> LoadRatings(int filmID) throws SQLException {
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		ResultSet resultSet = null;

		try
		{
			openConnection();
			resultSet = statement.executeQuery(queryRatings+filmID);

			while (resultSet.next())
			{
				int stars = resultSet.getInt("stars");
				String review = resultSet.getString("review");
				int costNo = resultSet.getInt("costNo");

				ratings.add(new Rating(stars, review, costNo));
			}
		}
		catch (Exception e)
		{
			System.out.println("fejl i load ratings"); //boundary TODO fix
		}
		finally
		{
			closeConnectionLoad();
		}
		return ratings;
	}
	/**
	 * 
	 * @param filmID
	 * @return ArrayList<Actor> cast
	 * @throws SQLException
	 */
	public ArrayList<Actor> LoadActors() throws SQLException
	{
		ArrayList<Actor> actors = new ArrayList<Actor>();
		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery(queryActor);					
			while (resultSet.next())
			{
				String firstName = resultSet.getString("fName");
				String lastName = resultSet.getString("lName");
				int gender = resultSet.getInt("gender");
				String description = resultSet.getString("descript");

				actors.add(new Actor(firstName, lastName, gender, description));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return actors;

	}

	public HashMap<Actor, String> LoadCast(int filmID) throws SQLException 
	{
		openConnection();
		Actor actor;
		HashMap<Actor, String> cast = new HashMap<Actor, String>(); //FIXME undersøg om hashmap og map virker sammen
		ResultSet resultSet = null;

		try
		{
			resultSet = statement.executeQuery(queryCast+filmID+queryCastTwo);
			
		
			while (resultSet.next())
			{
				int actorID = resultSet.getInt("actorID");
				String rolename = resultSet.getString("roleName");
				String firstName = resultSet.getString("fName");
				String lastName = resultSet.getString("lName");
				int gender = resultSet.getInt("gender");
				String description = resultSet.getString("descript");
				actor = new Actor(firstName, lastName, gender, description, actorID);
				cast.put(actor, rolename);
			}	
		}
		catch (Exception e)
		{
			System.out.println("fejl i load cast"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return cast;
	}

}
