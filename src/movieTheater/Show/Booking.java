package movieTheater.Show;

import java.util.ArrayList;

import movieTheater.main.HallData;


public class Booking {

	private Show show;
	ArrayList<SeatBookings> seatBookings;
	private int bookingNo;
	private Payment payment;

	public Booking(Show show, ArrayList<SeatBookings> seatBookings, int bookingNo)
	{
		this.show = show;
		this.seatBookings = seatBookings;
		this.bookingNo = bookingNo;
		payment = null;
	}

	public Show getShow() 
	{
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public ArrayList<SeatBookings> getSeats() {
		return seatBookings;
	}

	public void setSeats(ArrayList<SeatBookings> seatBookings) 
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
		for(SeatBookings currentSeat: seatBookings)
		{
			currentSeat.getSeat().setReservation();
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
		
		for(SeatBookings currentSeat: seatBookings)
		{
			price = price + ( HallData.MAINPRICE * showCategory * currentSeat.getSeat().getCatagory());
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
		for(SeatBookings currentSeat: seatBookings)
		{
			currentSeat.getSeat().setPayment();
		}
	}
}
