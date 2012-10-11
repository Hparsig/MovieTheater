package movieTheater.main;

import java.util.ArrayList;


public class Booking {

	private Show show;
	private ArrayList<Seat> seats;
	private int bookingNo;
	
	public Booking(Show show, ArrayList<Seat> seats, int bookingNo)
	{
		this.show = show;
		this.seats = seats;
		this.bookingNo = bookingNo;
	}
	
}
