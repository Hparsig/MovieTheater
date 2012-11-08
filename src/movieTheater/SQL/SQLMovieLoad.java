package movieTheater.SQL;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;

import movieTheater.Movie.Actor;
import movieTheater.Movie.Director;
import movieTheater.Movie.Genre;
import movieTheater.Movie.Movie;
import movieTheater.Movie.Rating;
import movieTheater.Movie.Cast;
import movieTheater.Persons.Employee;
import movieTheater.main.Title;
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
	private static final String queryActor = "SELECT * FROM Actors ORDER BY lName ASC";
	private static final String queryDirectorByID = "SELECT * FROM Directors where directID =";
	private static final String queryDirector = "SELECT * FROM Directors ORDER BY lName ASC";
	private static final String queryDirectorBylName = "SELECT * FROM Directors where lName LIKE '";
	private static final String queryRatings = "SELECT * FROM Reviews where filmID =";
	private static final String queryGenre = "SELECT * FROM Genres where genreID=";
	private static final String queryAllGenre = "SELECT * FROM genres";
	private static final String queryCast = "SELECT c.*, a.* FROM casts c, actors a WHERE c.movieID =";
	private static final String queryCastTwo = " AND c.actorID = a.actorID";
	private static final String queryLoadMaleActors = "SELECT * FROM actors WHERE gender=1";
	private static final String queryLoadFemaleActors = "SELECT * FROM actors WHERE gender=0";
	private static final String queryMovieByTitle = "SELECT * FROM Movies WHERE title LIKE '%";
	private static final String queryMoviesByOrgTitle = " AND orgTitel LIKE '%";
//	private static final String queryMovieByDirectorsFName = " AND username LIKE '%";
//	private static final String queryMoviesByeDirectorLName = " AND empNo =";

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
				dataFilmArray.add(new Movie(movieID,title, director, length, genre, premier, endDay, orgTitel, isThreeDim, cast, ratings));
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
				int directID = resultSet.getInt("directID") ;
				String dirFirstName = resultSet.getString("fName") ;
				String dirLastName = resultSet.getString("lName");
				int dirGender = resultSet.getInt("gender");
				String dirDescription = resultSet.getString("descript");

				directors.add(new Director(dirFirstName, dirLastName, dirGender, dirDescription,directID));
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
	public ArrayList<Actor> LoadFemaleActors() throws SQLException 
	{
		openConnection();
		ArrayList<Actor> actors = new ArrayList<Actor>();
		ResultSet resultSet = null;

		try
		{
			resultSet = statement.executeQuery(queryLoadFemaleActors);
			
		
			while (resultSet.next())
			{
				int actorID = resultSet.getInt("actorID");
				String firstName = resultSet.getString("fName");
				String lastName = resultSet.getString("lName");
				int gender = resultSet.getInt("gender");
				String description = resultSet.getString("descript");
				actors.add(new Actor(firstName,lastName,gender,description,actorID));
			}	
		}
		catch (Exception e)
		{
			System.out.println("fejl i load af mandlige skuespillere"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return actors;
	}
	
	public ArrayList<Actor> LoadMaleActors() throws SQLException 
	{
		openConnection();
		ArrayList<Actor> actors = new ArrayList<Actor>();
		ResultSet resultSet = null;

		try
		{
			resultSet = statement.executeQuery(queryLoadMaleActors);
			
		
			while (resultSet.next())
			{
				int actorID = resultSet.getInt("actorID");
				String firstName = resultSet.getString("fName");
				String lastName = resultSet.getString("lName");
				int gender = resultSet.getInt("gender");
				String description = resultSet.getString("descript");
				actors.add(new Actor(firstName,lastName,gender,description,actorID));
			}	
		}
		catch (Exception e)
		{
			System.out.println("fejl i load af mandlige skuespillere"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return actors;
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
	
	public ArrayList<Genre> LoadGenres() throws SQLException 
	{
		ArrayList<Genre> genres = new ArrayList<Genre>();
		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery(queryAllGenre);
			while (resultSet.next())
			{
				String genreName = resultSet.getString("genre");
				int genreID = resultSet.getInt("genreID");
				genres.add(new Genre(genreID,genreName));
			}			
		}
		catch (Exception e)
		{
			System.out.println("Fejl i load af genre"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return genres;

	}
	public ArrayList<String> LoadGenresString() throws SQLException 
	{
		ArrayList<String> genres = new ArrayList<String>();
		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery(queryAllGenre);
			while (resultSet.next())
			{
				genres.add(resultSet.getString("genre"));
			}			
		}
		catch (Exception e)
		{
			System.out.println("Fejl i load af genre"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return genres;

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
				int actorID = resultSet.getInt("actorID");
				String firstName = resultSet.getString("fName");
				String lastName = resultSet.getString("lName");
				int gender = resultSet.getInt("gender");
				String description = resultSet.getString("descript");

				actors.add(new Actor(firstName, lastName, gender, description,actorID));
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
	public ArrayList<Movie> searchMovie(String title, String orgTitle, String actorFName, String directorFName) throws SQLException 
	{
		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery(queryMovieByTitle+title+"%'"+queryMoviesByOrgTitle+orgTitle+"%'");
			//FIXME skal udvides til også at søge på skuespiller og instruktør. 
			setMovie(resultSet);			
		}
		catch (Exception e)
		{
			System.out.println("fejl i søgning af film"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return dataFilmArray;
	}

}
