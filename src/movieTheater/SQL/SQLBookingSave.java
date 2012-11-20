package movieTheater.SQL;
import java.sql.ResultSet;
import java.sql.Statement;

import movieTheater.Persons.Costumer;
import movieTheater.Show.Booking;
import movieTheater.Show.Seat;

public class SQLBookingSave extends SQL {
	private static final String queryInsertToBooking = "INSERT INTO bookings(costNo,payd,showID,empNo,pickedUp) VALUES(?,?,?,?,?)";
	private static final String queryInserSeatBookings = "INSERT INTO seatBookings(showID,seat,rowID,bookID) VALUES (?,?,?,?)" ;
	private static final String queryUpdateBookings = "UPDATE bookings SET payd= ?, pickedUp =? WHERE bookID=?" ;
	private static final String queryCreatePayment = "INSERT INTO payments (bookID,amount,paydWith) VALUES(?,?,?)";
	private static final String queryDelteBooking = "DELETE FROM bookings WHERE bookID = ";
	
	public SQLBookingSave()
	{
		statement = null;
		connection = null;
	}
	
   /**
    * @author Jesper
    * @param booking
    * save the new booking to the database, an returns the booking id
    */
	public int createNewBooking(Booking booking)
	{
		int bookingID = 0;
		openConnection();
       try
       {
    	   preparedStatement = connection.prepareStatement(queryInsertToBooking,Statement.RETURN_GENERATED_KEYS); 
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
	   	   preparedStatement.setInt(4, booking.getEmployee().getEmployeeNo()); 
	   	   int pickedUp = 0;
	   	   if(booking.getPickedUp()==true)
	   	   {
	   		   pickedUp = 1;
	   	   }
	   	   preparedStatement.setInt(5, pickedUp);
	   	   preparedStatement.executeUpdate();
	   	   
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if(rs.next())//Henter nøglen fra det forrige statement. Er der ikke lavet en ny nøgle returneres false 
			{
				bookingID = rs.getInt(1);							
			}
	   	   
       }
       catch (Exception e)
       {
    	   System.out.println("Fejl i save at ny booking"); 
    	   e.printStackTrace();
       }
       finally
       {  
    	   closeConnectionSave();      
       } 
       return bookingID;
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
			preparedStatement = connection.prepareStatement(queryInserSeatBookings);

			preparedStatement.setInt(1, booking.getShow().getShowID());
			preparedStatement.setInt(2, seat.getSeatNo());
			preparedStatement.setInt(3, row);
			preparedStatement.setInt(4, booking.getBookingNo());
			preparedStatement.executeUpdate();                     

		}
		catch (Exception e)
		{
			System.out.println("Fejl i save af seat booking");
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
	 * Update the excisting booking so its now payd and pickedUp.
	 */
	public void updateBooking(Booking booking)
	{
		openConnection();
		
       try
       {
    	  
    	   preparedStatement = connection.prepareStatement(queryUpdateBookings);
	   	   int payd = 0;
    	   if(booking.getPayment()!=null)
	   	   {
	   		  payd = 1;
	   	   }
    	   preparedStatement.setInt(1, payd); 
    	   
    	   int pickedUp = 0;
	   	   if(booking.getPickedUp()==true)
	   	   {
	   		   pickedUp = 1;
	   	   }
	   	   preparedStatement.setInt(2, pickedUp);
	   	   preparedStatement.setInt(3, booking.getBookingNo());
	   	   preparedStatement.executeUpdate();                     
       }
       catch (Exception e)
       {
    	   System.out.println("Fejl i save af ny booking");
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
	 * create a new payment in the database.
	 */
	public void createPayment(Booking booking)
	{
		openConnection();
		
       try
       {
    	  
    	   preparedStatement = connection.prepareStatement(queryCreatePayment); 
    	   preparedStatement.setInt(1, booking.getBookingNo());
    	   preparedStatement.setDouble(2,booking.getPayment().getAmount()); 
	   	   preparedStatement.setInt(3, booking.getPayment().getPaymentMethod());
	   	   preparedStatement.executeUpdate();                     
       }
       
       catch (Exception e)
       {
    	   System.out.println("Fejl i save af ny payment");
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
			System.out.println("Fejl i sletning af booking");
			e.printStackTrace();
		}
		finally
		{
			closeConnectionLoad();
		}
	}
	
}