package movieTheater.SQL;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import movieTheater.Movie.Actor;
import movieTheater.Movie.Director;
import movieTheater.Movie.Movie;
import movieTheater.Movie.Rating;
import movieTheater.Movie.Cast;
import movieTheater.main.Hall;
import movieTheater.main.HallBooking;
import movieTheater.main.Seat;
import movieTheater.main.Show;

public class SQLShowLoad extends SQL{
	
	private ArrayList<Show> showArray;
	private static final String queryAllShows = "SELECT * from Shows";
	private static final String queryMoviesByID = "SELECT * from Movies WHERE movieID =";
	private static final String queryShowByMovieID = "SELECT * FROM Shows where movieID =";
	private static final String queryShowByShowID = "SELECT * FROM Shows where showID =";
	private static final String queryShowByHallNo = "SELECT * FROM Shows where hallNo =";
	private static final String queryDirectorByID = "SELECT * FROM Directors where directID =";
	private static final String queryRatings = "SELECT * FROM Reviews where filmID =";
	private static final String queryGenre = "SELECT * FROM Genres where genreID=";
	private static final String queryCast = "SELECT * FROM Casts where movieID =";
	private static final String queryActors = "SELECT * FROM Actors where actorID =";
	private static final String queryBooking = "SELECT * FROM SeatBookings WHERE showID=";
	private Movie movie;
		
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
				Timestamp timeS = resultSet.getTimestamp("timeS");
				Timestamp timeE = resultSet.getTimestamp("timeE");
				int movieID = resultSet.getInt("movieID");
				
				loadMovie(movieID);
				HallBooking hallBooking = loadBooking(showID,hallNo,timeS, timeE);
				showArray.add(new Show(showID, movie,hallBooking));
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
	
	public ArrayList<Show> loadAllShows() throws SQLException{
		ResultSet resultSet = null;
		
		try {
			resultSet = statement.executeQuery(queryAllShows);
			setShow(resultSet);
		} catch (SQLException e) {
			System.out.println("fejl i load af alle forestilling");
		} 
		finally{
			closeConnectionLoad();
		}
		return showArray;
	}
	
	/**
	 * 
	 * @param String title
	 * @return ArrayList<Film> 
	 * @throws SQLException
	 */
	public Movie loadMovie(int movieID) throws SQLException {

		ResultSet resultSet = null;
		movie = null;
		openConnection();

		try
		{
			resultSet = statement.executeQuery((queryMoviesByID+movieID));
			setMovie(resultSet);			
		}
		catch (Exception e)
		{
			System.out.println("fejl i load movie by ID"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return movie;
	}

	/**
	 * 
	 * @param resultSet
	 * @return ArrayList<Film> dataFilmArray
	 */
	public Movie setMovie(ResultSet resultSet)
	{
		movie = null;
		ArrayList<Cast> cast = new ArrayList<Cast>();
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
				cast = LoadCast(movieID);
				ratings = LoadRatings(movieID);
				String genre = LoadGenre(genreID);

				if(threeDim == 1)
				{
					isThreeDim = true;
				}
				
				movie = new Movie(movieID,title, director, length, genre, premier, endDay, orgTitel, isThreeDim, cast, ratings);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fejl i set movie"); //boundary TODO fix
		}
		return movie;	
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
	
	public ArrayList<Cast> LoadCast(int filmID) throws SQLException 
	{
		ArrayList<Actor> actors = new ArrayList<Actor>();
		ArrayList<Cast> cast = new ArrayList<Cast>();
		ResultSet resultSet = null;

		try
		{
			openConnection();
			resultSet = statement.executeQuery(queryCast+filmID);					
		
			while (resultSet.next())
			{
				int movieID = resultSet.getInt("movieID");
				int actorID = resultSet.getInt("actorID");
				String rolename = resultSet.getString("roleName");
			
				ResultSet resultSet2 = statement.executeQuery(queryActors+actorID);
				while(resultSet2.next())
				{
					String firstName = resultSet.getString("fName");
					String lastName = resultSet.getString("lName");
					int gender = resultSet.getInt("gender");
					String description = resultSet.getString("descript");
					cast.add(new Cast(movieID,new Actor(firstName, lastName, gender, description) ,rolename));
				}	
		
			}
		}
		catch (Exception e)
		{
			System.out.println("fejl i load cast"); //boundary TODO fix
		}
		finally
		{
			closeConnectionLoad();
		}
		return cast;
	}
		
		
	
	public HallBooking loadBooking(int showID,int hallNo, Timestamp sTime, Timestamp eTime){
		
		ResultSet resultSet = null;
		HallBooking hallBooking = null;
		openConnection();
		ArrayList<ArrayList<Seat>> seats = new ArrayList<ArrayList<Seat>>();
		
		try
		{
			resultSet = statement.executeQuery((queryBooking+showID));
			int seatNr = resultSet.getInt("seat");
			int rowID = resultSet.getInt("rowID");
			
			seats.get(rowID).get(seatNr).setReservation();
						
		}
		catch (Exception e)
		{
			System.out.println("fejl i load af booking efter showID"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		hallBooking = new HallBooking(hallNo,seats,sTime,eTime);
		return hallBooking;
		
	}
	
}
