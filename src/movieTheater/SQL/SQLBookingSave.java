package movieTheater.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import movieTheater.Persons.Costumer;
import movieTheater.Show.Booking;
import movieTheater.Show.Seat;

public class SQLBookingSave extends SQL {
	private static final String queryInsertToBooking = "INSERT INTO bookings(costNo,payd,showID) VALUES(?,?,?)";
	private static final String queryInserSeatBookings = "INSERT INTO seatBookings(showID,seat,rowID,bookID) VALUES (?,?,?,?)" ;
	private static final String queryUpdateBookings = "UPDATE bookings SET payd=? WHERE bookID=?" ;
	private static final String queryCreatePayment = "INSERT INTO payments (bookID,amount,paydWith,empNo) VALUES(?,?,?,?)";
	private static final String queryDelteBooking = "DELETE FROM bookings WHERE bookID = ";
	
	public SQLBookingSave()
	{
		statement = null;
		connection = null;
	}
	
   /**
    * @author Jesper
    * @param booking
    * save the new booking to the database, so we get the booking id. 
    */
	public void createNewBooking(Booking booking)
	{
		openConnection();
       try
       {
    	   preparedStatement = connection.prepareStatement(queryInsertToBooking); // create statement object
    	   Costumer costumer = booking.getCostumer();
    	   if(costumer!=null)
	   	   {
	   		   preparedStatement.setInt(1, costumer.getCostumerNo());
	   	   }
	   	   else
	   	   {
	   		   preparedStatement.setNull(1, java.sql.Types.NUMERIC);
	   	   }
	   	   preparedStatement.setInt(2, 0);
	   	   preparedStatement.setInt(3, booking.getShow().getShowID());
	   	   preparedStatement.executeUpdate();                     
	   	   
       }
       catch (Exception e)
       {
    	   System.out.println("Fejl i save at ny booking"); //boundary TODO fix
    	   e.printStackTrace();
       }
       finally
       {  
    	   closeConnectionSave();      
       } 
	}  

	/**
	 * @author Jesper
	 * @param row
	 * @param seat
	 * @param booking
	 * save the seatBookings, while they are being selected by the user. 
	 */
	public void saveSeatBookings(int row, Seat seat, Booking booking)
	{

		openConnection();

		try
		{
			preparedStatement = connection.prepareStatement(queryInserSeatBookings); // create statement object

			preparedStatement.setInt(1, booking.getShow().getShowID());
			preparedStatement.setInt(2, seat.getSeatNo());
			preparedStatement.setInt(3, row);
			preparedStatement.setInt(4, booking.getBookingNo());
			preparedStatement.executeUpdate();                     

		}
		catch (Exception e)
		{
			System.out.println("Fejl i save af seat booking"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{  
			closeConnectionSave();      
		}  
	}
	
	/**
	 * @author Jesper
	 * @param booking
	 * Update the excisting booking so it now payd.
	 */
	public void updateBooking(Booking booking)
	{
		openConnection();
		
       try
       {
    	   preparedStatement = connection.prepareStatement(queryUpdateBookings); // create statement object
	   	   preparedStatement.setInt(1, 1); //The booking are now paid..
	   	   preparedStatement.setInt(2, booking.getBookingNo());
	   	   preparedStatement.executeUpdate();                     
       }
       catch (Exception e)
       {
    	   System.out.println("Fejl i save af ny booking"); //boundary TODO fix
    	   e.printStackTrace();
       }
       finally
       {  
    	   closeConnectionSave();      
       } 
       createPayment(booking);
	}  
	
	/**
	 * @author Jesper
	 * @param booking
	 * create a new payment in the databse.
	 */
	public void createPayment(Booking booking)
	{
		openConnection();
		
       try
       {
    	   preparedStatement = connection.prepareStatement(queryCreatePayment); // create statement object
    	   preparedStatement.setInt(1, booking.getBookingNo());
    	   preparedStatement.setDouble(2,booking.getPayment().getAmount()); 
	   	   preparedStatement.setInt(3, booking.getPayment().getPaymentMethod());
	   	   preparedStatement.setInt(4, booking.getPayment().getEmployee().getEmployeeNo());
	   	   preparedStatement.executeUpdate();                     
       }
       catch (Exception e)
       {
    	   System.out.println("Fejl i save af ny payment"); //boundary TODO fix
    	   e.printStackTrace();
       }
       finally
       {  
    	   closeConnectionSave();      
       } 
	}  
	
	public void delteBooking(Booking booking) {

		openConnection();
		int bookID = booking.getBookingNo();
		try
		{
			statement.executeUpdate(queryDelteBooking+bookID);			
		}
		catch (Exception e)
		{
			System.out.println("Fejl i sletning af booking"); //boundary TODO fix
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
	}
	
}