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

	public Show getShow() 
	{
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public ArrayList<Seat> getSeats() {
		return seats;
	}

	public void setSeats(ArrayList<Seat> seats) {
		this.seats = seats;
	}

	public int getBookingNo() 
	{
		return bookingNo;
	}

	public void setBookingNo(int bookingNo) 
	{
		this.bookingNo = bookingNo;
	}
	
}
