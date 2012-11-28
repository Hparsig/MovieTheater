package movieTheater.Show;

import java.sql.Timestamp;
import java.util.Map;

public class Ticket {

	Booking booking;
	
	public Ticket(Booking booking)
	{
		this.booking = booking;
	}
	
	public String toString()
	{
		Timestamp date = booking.getShow().getHallBooking().getStart();
		String titel = booking.getShow().getMovie().getTitle();
		int hallNo = booking.getShow().getHallBooking().getHalleNo();
		String seats = "";
		 Map<Seat,Integer> seatBookings = booking.getSeats();
			for (Map.Entry<Seat, Integer> entry : seatBookings.entrySet())
			{
				int seatNum = entry.getKey().getSeatNo();
				int row = entry.getValue();
				
				seats = seats+"R�kke: "+row+", s�de: "+seatNum+"\n";
			}
		
		return "Dato: "+date+"\n"+titel+"\nSal: "+hallNo+"\n"+seats;
	}
}
