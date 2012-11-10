package movieTheater.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import movieTheater.Show.Booking;
import movieTheater.Show.Seat;
import movieTheater.Show.SeatBookings;
import movieTheater.Show.Show;

public class SQLBookingLoad extends SQL {

	private static final String queryGetBooking = "SELECT b.*, s.* FROM bookings b, seatbookings s WHERE b.bookID = s.bookID AND b.costNo IN (SELECT costNo FROM costumers WHERE phone=?)";
	private ArrayList<Booking> bookings;
	private SQLShowLoad showLoad;
	
	public SQLBookingLoad(SQLShowLoad showLoad){
		statement = null;
		connection = null;
		bookings = new ArrayList<Booking>();
		this.showLoad = showLoad;
	}
	
	public ArrayList<Booking> getBookings(int phone) throws SQLException{
		bookings.clear();
		openConnection();
		preparedStatement = connection.prepareStatement(queryGetBooking); 
		ResultSet resultSet = null;
		
		preparedStatement.setInt(1, phone);
		
	    resultSet = preparedStatement.executeQuery();
	    setBooking(resultSet);
		
		return bookings;
	}
	
	private void setBooking(ResultSet resultSet) throws SQLException
	{
		int showID = 0;
		ArrayList<SeatBookings> seats = new ArrayList<SeatBookings>();
		Show show=null;
		int bookId = 0;
		int payd=0;
		int payID=0;
		int costNo = 0;
		while (resultSet.next())
		{
			bookId = resultSet.getInt("bookID");
			costNo = resultSet.getInt("costNo");
			payID = resultSet.getInt("payID");
			payd = resultSet.getInt("payd");
			showID = resultSet.getInt("showID");
			int seat = resultSet.getInt("seat");
			int rowID = resultSet.getInt("rowID");
			
			show  = showLoad.loadShowFromID(showID).get(0);
			seats.add(new SeatBookings(show.getHallBooking().getSeats().get(rowID).get(seat)));
		}
		bookings.add(new Booking(show,seats,bookId));
		
		

	}
}
