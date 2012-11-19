package movieTheater.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import movieTheater.Persons.Costumer;
import movieTheater.Persons.Employee;
import movieTheater.Show.Booking;
import movieTheater.Show.Payment;
import movieTheater.Show.Seat;
import movieTheater.Show.SeatBookings;
import movieTheater.Show.Show;

public class SQLBookingLoad extends SQL {

	private static final String queryGetBooking = "SELECT * FROM bookings WHERE costNo = ";
	private static final String queryGetBookingByID = "SELECT * FROM bookings WHERE bookID = ";
	private static final String queryGetSeatBookings = "SELECT seat,rowID FROM seatbookings WHERE bookID =";
	private static final String queryGetPayments = "SELECT * FROM payments WHERE bookID =";
	private ArrayList<Booking> bookings;
	private SQLCustomerLoad loadCustomer;
	private SQLShowLoad loadShow;
	private SQLEmployeeLoad loadEmployee;

	public SQLBookingLoad()
	{
		statement = null;
		connection = null;
		bookings = new ArrayList<Booking>();
		loadShow = new SQLShowLoad();
		loadEmployee = new SQLEmployeeLoad();
		loadCustomer = new SQLCustomerLoad();
	
	}
	public Booking getBooking(int bookingID)
	{
		Booking booking=null;
		ResultSet resultSet = null;
		openConnection();
		
		try
		{
			resultSet = statement.executeQuery(queryGetBookingByID+bookingID);
			while (resultSet.next())
			{
				int bookID = resultSet.getInt("bookID");
				Integer costNo = resultSet.getInt("costNo");
				int pay = resultSet.getInt("payd");
				int showID = resultSet.getInt("showID");
				int employeeNum = resultSet.getInt("empNo");
				int pickedUp = resultSet.getInt("pickedUp");

				boolean picked = false;
				if(pickedUp==1)
				{
					picked = true;
				}
							
				Show show = loadShow.loadShowFromID(showID).get(0);
				Employee employee = loadEmployee.LoadEmployee(employeeNum, true).get(0);
				Map<Seat,Integer> seats =  getSeaBookings(bookID);

				if(costNo==0)
				{
					booking = new Booking(bookID,show,seats,null,employee,picked);
				}
				else
				{
					Costumer costumer = loadCustomer.LoadCustomer(costNo).get(0);
					booking = new Booking(bookID,show,seats,costumer,employee,picked);
				}
				if(pay==1)
				{
					Payment payment = getPayment(booking);
					booking.setPayed(payment);
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
	
	public ArrayList<Booking> getBookings(Costumer costumer)
	{
		bookings.clear();
		ResultSet resultSet = null;
		openConnection();
		try
		{
			resultSet = statement.executeQuery(queryGetBooking+costumer.getCostumerNo());
		
			while (resultSet.next())
			{	
				int  bookID= resultSet.getInt("bookID");
				int payd = resultSet.getInt("payd");
				int showID = resultSet.getInt("showID");
				int empNo = resultSet.getInt("empNo");
				int pickedUp = resultSet.getInt("pickedUp");
				
				boolean picked = true;
				if(pickedUp==0)
				{
					picked=false;
					Show show = loadShow.loadShowFromID(showID).get(0);
					Employee employee = loadEmployee.LoadEmployee(empNo, true).get(0); //TODO hvis han er null
					Map<Seat,Integer> seats = getSeaBookings(bookID);
					Booking booking = new Booking(bookID,show,seats,costumer,employee,picked);
					
					if(payd==1)
					{
						Payment payment = getPayment(booking);
						booking.setPayed(payment);
						booking.setSeatsPayed();
					}
					bookings.add(booking);	
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
		return bookings;
	}
	
	
	public Payment getPayment(Booking booking)
	{
		Payment payment = null;
		ResultSet resultSet = null;
		openConnection();
		try
		{
			resultSet = statement.executeQuery(queryGetPayments+booking.getBookingNo());
			while (resultSet.next())
			{	
				int payID = resultSet.getInt("payID");
				Timestamp date = resultSet.getTimestamp("payDate");
				double amount = resultSet.getDouble("amount");
				int paydWith = resultSet.getInt("paydWith");
				payment = new Payment(payID,amount,paydWith,date);
					
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
		return payment;
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
	
}
