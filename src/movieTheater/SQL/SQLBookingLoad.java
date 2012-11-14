package movieTheater.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import movieTheater.Persons.Costumer;
import movieTheater.Show.Booking;
import movieTheater.Show.Seat;
import movieTheater.Show.SeatBookings;
import movieTheater.Show.Show;

public class SQLBookingLoad extends SQL {

	private static final String queryGetBooking = "SELECT b.*, s.* FROM bookings b, seatbookings s WHERE b.bookID = s.bookID AND b.costNo IN (SELECT costNo FROM costumers WHERE phone=?)";
	private static final String queryGetLatestBooking = "SELECT * FROM bookings WHERE bookID = ( SELECT MAX(bookID) FROM bookings)";
	private static final String queryGetSeatBookings = "SELECT seat,rowID FROM seatbookings WHERE bookID =";
	private ArrayList<Booking> bookings;
	private SQLCustomerLoad loadCustomer;
	private SQLShowLoad loadShow;

	public SQLBookingLoad()
	{
		statement = null;
		connection = null;
		bookings = new ArrayList<Booking>();
		loadCustomer = new SQLCustomerLoad();
		loadShow = new SQLShowLoad();
	
	}
	public Booking getNewestBooking()
	{
		Booking booking=null;
		ResultSet resultSet = null;
		openConnection();
		
		try
		{
			resultSet = statement.executeQuery(queryGetLatestBooking);
			while (resultSet.next())
			{
				System.out.println("her");
				int bookID = resultSet.getInt("bookID");
				System.out.println(bookID);
				Integer costNo = resultSet.getInt("costNo");
				int pay = resultSet.getInt("payd");
				int showID = resultSet.getInt("showID");
				
				Show show = loadShow.loadShowFromID(showID).get(0);
				Map<Seat,Integer> seats =  getSeaBookings(bookID);
				if(costNo!=null)
				{
					booking = new Booking(bookID,show,seats,null);
				}
				else
				{
					Costumer costumer = loadCustomer.LoadCustomer(costNo).get(0);
					booking = new Booking(bookID,show,seats,costumer);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("fejl i load af senest booking"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return booking;
	}
	public Map<Seat,Integer> getSeaBookings(int bookID)
	{
		Map<Seat,Integer> seats = new HashMap<Seat,Integer>();
		ResultSet resultSet = null;
		openConnection();
		
		try
		{
			resultSet = statement.executeQuery(queryGetSeatBookings+bookID);
			while (resultSet.next())
			{
				int  seatID= resultSet.getInt("seat");
				int row = resultSet.getInt("rowID");

				Seat seat = new Seat(seatID);
				seats.put(seat, row);
			}
		}
		catch (Exception e)
		{
			System.out.println("fejl i load af senest booking"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
		return seats;
	}
		
	
//	public ArrayList<Booking> getBookings(int phone) throws SQLException{
//		bookings.clear();
//		openConnection();
//		preparedStatement = connection.prepareStatement(queryGetBooking); 
//		ResultSet resultSet = null;
//		
//		preparedStatement.setInt(1, phone);
//		
//	    resultSet = preparedStatement.executeQuery();
//	    setBooking(resultSet);
//		
//		return bookings;
//	}
//	
//	private void setBooking(ResultSet resultSet) throws SQLException
//	{
//		int showID = 0;
//		ArrayList<SeatBookings> seats = new ArrayList<SeatBookings>();
//		Show show=null;
//		int bookId = 0;
//		int payd=0;
//		int payID=0;
//		int costNo = 0;
//		while (resultSet.next())
//		{
//			bookId = resultSet.getInt("bookID");
//			costNo = resultSet.getInt("costNo");
//			payID = resultSet.getInt("payID");
//			payd = resultSet.getInt("payd");
//			showID = resultSet.getInt("showID");
//			int seat = resultSet.getInt("seat");
//			int rowID = resultSet.getInt("rowID");
//			
//			show  = showLoad.loadShowFromID(showID).get(0);
//			seats.add(new SeatBookings(show.getHallBooking().getSeats().get(rowID).get(seat)));
//		}
//		bookings.add(new Booking(show,seats,bookId));
		
		

	
}
