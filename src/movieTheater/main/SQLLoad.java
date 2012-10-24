package movieTheater.main;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;

public class SQLLoad {
	//Henrik Parsig

	private ArrayList<Film> dataFilmArray;
	private static final String queryMovies = "SELECT * FROM Movies where genreID =";
	private static final String queryMoviesByTitle = "SELECT * FROM Movies where title =";
	private static final String queryCast = "SELECT * FROM Casts where movieID =";
	private static final String queryDirector = "SELECT * FORM Director where directID =";
	private static final String queryActors = "SELECT * from Actors where actorID =";
	private static final String queryRatings = "SELECT * from Reviews where filmID =";
	private static final String queryGenre = "SELECT * from Genres where genreID=";
	private static final String forName = "com.mysql.jdbc.Driver";
	private static final String connectionPath = "jdbc:mysql://localhost/MovieTheater";
	Statement statement ;
	Connection connection;

	//********************
	// Konstruktør
	//********************
	public SQLLoad()
	{
		dataFilmArray = new ArrayList<Film>();
		statement = null;
		connection = null;
	}

	/**
	 * Search movies using genre as parameter
	 * @param int genreID
	 * @return ArrayList<Film> 
	 * @throws SQLException
	 */
	public ArrayList<Film> LoadMovie(int genreID) throws SQLException {
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
			System.out.println("fejl i load"); //boundary TODO fix
		}
		finally
		{
			closeConnection();
		}
		return dataFilmArray;
	}
	/**
	 * 
	 * @param String title
	 * @return ArrayList<Film> 
	 * @throws SQLException
	 */
	public ArrayList<Film> LoadMovie(String title) throws SQLException {
		//
//				ArrayList<Actor> cast = new ArrayList<Actor>();
//				ArrayList<Rating> ratings = new ArrayList<Rating>();
//				boolean isThreeDim = false;
				ResultSet resultSet = null;
				openConnection();

				try
				{
					resultSet = statement.executeQuery((queryMoviesByTitle+title));
					setMovie(resultSet);			
				}
				catch (Exception e)
				{
					System.out.println("fejl i load"); //boundary TODO fix
				}
				finally
				{
					closeConnection();
				}
				return dataFilmArray;
			}

	/**
	 * 
	 * @param resultSet
	 * @return ArrayList<Film> dataFilmArray
	 */
	public ArrayList<Film> setMovie(ResultSet resultSet)
	{
		ArrayList<Actor> cast = new ArrayList<Actor>();
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		boolean isThreeDim = false;

		try
		{
			while (resultSet.next())
			{
				int filmID = resultSet.getInt("filmID");
				String title = resultSet.getString("title"); 		  
				int length = resultSet.getInt("length");
				int genreID = resultSet.getInt("genreID");
				int directID = resultSet.getInt("DirectID");	
				int threeDim = resultSet.getInt("threeDim");		
				String orgTitel = resultSet.getString("orgTitel"); 	
				Date premier = resultSet.getDate("premier");
				Date endDay = resultSet.getDate("endDay");

				Director director = LoadDirector(directID);
				cast = LoadCast(filmID);
				ratings = LoadRatings(filmID);
				String genre = LoadGenre(genreID);

				if(threeDim == 1)
				{
					isThreeDim = true;
				}

				dataFilmArray.add(new Film(title, director, length, genre, premier, endDay, orgTitel, isThreeDim, cast, ratings));
			}
		}
		catch (Exception e)
		{
			System.out.println("fejl i load"); //boundary TODO fix
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
			resultSet = statement.executeQuery(queryDirector+directID);

			String dirFirstName = resultSet.getString("fName");
			String dirLastName = resultSet.getString("lName");
			int dirGender = resultSet.getInt("gender");
			String dirDescription = resultSet.getString("descript");

			director = new Director(dirFirstName, dirLastName, dirGender, dirDescription);
		}
		catch (Exception e)
		{
			System.out.println("fejl i load"); //boundary TODO fix
		}
		finally
		{
			closeConnection();
		}

		return director;
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
		openConnection();

		try
		{
			resultSet = statement.executeQuery((queryGenre+genreID));

			genre = resultSet.getString("genreID");
		}
		catch (Exception e)
		{
			System.out.println("fejl i load"); //boundary TODO fix
		}
		finally
		{
			closeConnection();
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
		openConnection();

		try
		{
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
			System.out.println("fejl i load"); //boundary TODO fix
		}
		finally
		{
			closeConnection();
		}
		return ratings;
	}
	/**
	 * 
	 * @param filmID
	 * @return ArrayList<Actor> cast
	 * @throws SQLException
	 */
	public ArrayList<Actor> LoadCast(int filmID) throws SQLException {
		ArrayList<Integer> actorIDs = new ArrayList<Integer>();
		ArrayList<Actor> cast = new ArrayList<Actor>();
		ResultSet resultSet = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery(queryCast+filmID);					
			while (resultSet.next())
			{
				Integer actorID = resultSet.getInt("actorID");
				actorIDs.add(actorID);
			}

			for(int i=0; i<actorIDs.size(); i++ )
			{
				resultSet = statement.executeQuery(queryActors+actorIDs.get(i));

				String firstName = resultSet.getString("fName");
				String lastName = resultSet.getString("lName");
				int gender = resultSet.getInt("gender");
				String description = resultSet.getString("descript");

				cast.add(new Actor(firstName, lastName, gender, description));
			}
		}
		catch (Exception e)
		{
			System.out.println("fejl i load"); //boundary TODO fix
		}
		finally
		{
			closeConnection();
		}
		return cast;
	}
	//*********************************************************************************************
	// Metode der bruges af de ovenstående metoder, til at åbne en forbindelse til databasen.
	//*********************************************************************************************
	private void openConnection()
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
			System.out.println("fejl i load"); //boundary TODO fix
		}
	}
	//*********************************************************************************************
	// Metode der bruges af de ovenstående metoder, til at lukke forbindelsen til databasen. 
	//*********************************************************************************************
	private void closeConnection()
	{
		try
		{
			statement.close();
			connection.close();
		}
		catch (SQLException e)
		{
			System.out.println("fejl i load");//TODO håndter catch
		}
	}

}
