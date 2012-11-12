package movieTheater.Show;

import java.util.ArrayList;

import movieTheater.main.HallData;


public class Booking {

	private Show show;
	ArrayList<Seat> seatBookings;
	private int bookingNo;
	private Payment payment;

	public Booking(Show show, ArrayList<Seat> seatBookings, int bookingNo)
	{
		this.show = show;
		this.seatBookings = seatBookings;
		this.bookingNo = bookingNo;
		payment = null;
	}
	public Booking(Show show, ArrayList<Seat> seatBookings)
	{
		this.show = show;
		this.seatBookings = seatBookings;
		payment = null;
	}

	public Show getShow() 
	{
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public ArrayList<Seat> getSeats() {
		return seatBookings;
	}

	public void setSeats(ArrayList<Seat> seatBookings) 
	{
		this.seatBookings = seatBookings;
	}

	public int getBookingNo() 
	{
		return bookingNo;
	}

	public void setBookingNo(int bookingNo) 
	{
		this.bookingNo = bookingNo;
	}

	public void setReservation()
	{
		for(Seat currentSeat: seatBookings)
		{
			currentSeat.setReservation();
		}
	}

	public void setPayed(Payment payment)
	{
		this.payment = payment;
	}
	
	public Payment getPayment()
	{
		return payment;
	}
	
	public double getPrice()
	{
		double price = 0;
		double showCategory = show.getPriceCategory();
		
		for(Seat currentSeat: seatBookings)
		{
			price = price + ( HallData.MAINPRICE * showCategory * currentSeat.getCatagory());
		}

		return price;
	}
//	public void setPayed(int amount)
//	{
//		setSeatsPayed();
//		payment = new Payment(amount);
//	}
//
//	public void setPayed(int amount, Costumer costumer)
//	{
//		setSeatsPayed();
//		payment = new Payment(amount, costumer);
//	}
		
	public void setSeatsPayed()
	{
		for(Seat currentSeat: seatBookings)
		{
			currentSeat.setPayment();
		}
	}
}
