package movieTheater.SQL;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import movieTheater.Movie.Movie;
import movieTheater.Show.HallBooking;
import movieTheater.Show.Seat;
import movieTheater.Show.Show;
import movieTheater.main.TheaterData;

public class SQLShowLoad extends SQL{
	
	
	private ArrayList<Show> showArray;
	private static final String queryAllShows = "SELECT * from Shows";
	private static final String queryShowByMovieID = "SELECT * FROM Shows where movieID =";
	private static final String queryShowByShowID = "SELECT * FROM Shows where showID =";
	private static final String queryShowByHallNo = "SELECT * FROM Shows where hallNo =";
	private static final String queryBooking = "SELECT * FROM SeatBookings WHERE showID=";
	private static final String queryLoadShowByDate = "SELECT s.* FROM shows s, movies m  WHERE s.timeS>now() AND DATE(s.timeS) = '";
	private static final String queryLoadShowsByTitle = "AND s.movieID = m.movieID AND m.title LIKE '%";
	private static final String queryLoadByTitle = "SELECT s.* FROM shows s, movies m  WHERE s.timeS>now() AND s.movieID = m.movieID AND m.title LIKE '%";
	private SQLMovieLoad loadMovie;
		
	public SQLShowLoad(){
		showArray = new ArrayList<Show>();
		statement = null;
		connection = null;
		loadMovie = new SQLMovieLoad();
	
	}
	/**
	 * @author Jesper
	 * @param ResultSet resultSet
	 * @return ArrayList<Show> 
	 */
	public ArrayList<Show> setShow(ResultSet resultSet)
	{
		showArray.clear();
		try
		{
			while (resultSet.next())
			{
				int showID = resultSet.getInt("showID");
				int hallNo = resultSet.getInt("hallNo");
				
				Timestamp timeS = resultSet.getTimestamp("timeS");
				Timestamp timeE = resultSet.getTimestamp("timeE");
				int movieID = resultSet.getInt("movieID");
				double priceCategory =  resultSet.getDouble("priceCategory");
				
				Movie movie = loadMovie.LoadMovieByID(movieID).get(0);
				HallBooking hallBooking = loadBooking(showID,hallNo,timeS, timeE);
				showArray.add(new Show(showID, movie,hallBooking,priceCategory));
				
			}
		}
		catch (Exception e)
		{
			System.out.println("fejl i set show");
			e.printStackTrace();
		}
		return showArray;	
	}
	
	public ArrayList<Show> loadShow(int movieID){
		openConnection();
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
	
	public ArrayList<Show> loadShowFromID(int showID)
	{
		openConnection();
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
	
	public ArrayList<Show> loadShowFromHallNo(int hallNo)
	{
		openConnection();
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
	
	public ArrayList<Show> loadAllShows()
	{
		ResultSet resultSet = null;
		openConnection();
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
	 * @author Jesper
	 * @param String title, Date date
	 * @return ArrayList<Show> 
	 * @throws SQLException
	 */
	public ArrayList<Show> loadShows(Date date, String title)
	{
		ResultSet resultSet = null;
		openConnection();
		try 
		{
			if(date==null)
			{
				resultSet  =statement.executeQuery(queryLoadByTitle+title+"%'");
			}
			else
			{
				resultSet = statement.executeQuery(queryLoadShowByDate+date+"' "+queryLoadShowsByTitle+title+"%'");
			}
			setShow(resultSet);
		} 
		catch (SQLException e) 
		{
			System.out.println("fejl i load af forestilling efter dato og titel");
			e.printStackTrace();
		} 
		finally
		{
			closeConnectionLoad();
		}
		return showArray;
	}
	
	public HallBooking loadBooking(int showID,int hallNo, Timestamp sTime, Timestamp eTime){
		
		ResultSet resultSet = null;
		HallBooking hallBooking=null;
		openConnection();
		ArrayList<ArrayList<Seat>> seats = new ArrayList<ArrayList<Seat>>();
		
		int[] seatsPrRow;
		
		if(hallNo==1)
		{
			seatsPrRow = TheaterData.seatsPrRowHall1;
		}
		else if(hallNo==2)
		{
			seatsPrRow = TheaterData.seatsPrRowHall2;
		}
		else{
			seatsPrRow = TheaterData.seatsPrRowHall3;
		}
		for(int i = 0; i < seatsPrRow.length; i++)						//iterates over one row at the time
		{
			int noSeatsCurrentRow = seatsPrRow[i];
			ArrayList<Seat> seatsOnRow = new ArrayList<Seat>();
			for(int j = 0; j < noSeatsCurrentRow; j++)			//adds seats according to the seatsPrRow-array. 
			{
				seatsOnRow.add(new Seat(j));
			}
			seats.add(seatsOnRow);
		}
		try
		{
			resultSet = statement.executeQuery((queryBooking+showID));
			while (resultSet.next())
			{
				
				int seatNr = resultSet.getInt("seat");
				int rowID = resultSet.getInt("rowID");
			
				seats.get(rowID).get(seatNr-1).setReservation();
			}
			
			hallBooking = new HallBooking(hallNo,seats,sTime,eTime);
						
		}
		catch (Exception e)
		{
			System.out.println("fejl i load af booking efter showID");
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return hallBooking;
	}
	
}
