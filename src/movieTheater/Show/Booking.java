package movieTheater.Show;

import java.util.Map;

import movieTheater.Persons.Costumer;
import movieTheater.Persons.Employee;
import movieTheater.main.HallData;


public class Booking {

	private Show show;
	private Map<Seat,Integer> seatBookings;
	private int bookingNo;
	private Payment payment;
	private Costumer costumer;
	private Employee employee;
	private boolean pickedUp;

	public Booking(int bookingNo,Show show, Map<Seat,Integer> seatBookings,Costumer costumer, Employee employee,boolean pickedUp)
	{
		this.show = show;
		this.seatBookings = seatBookings;
		this.bookingNo = bookingNo;
		this.costumer = costumer;
		this.employee = employee;
		this.pickedUp = pickedUp;
		payment = null;
	}
	
	public Booking(Show show, Map<Seat,Integer> seatBookings, Costumer costumer, Employee employee,boolean pickedUp)
	{
		this.show = show;
		this.seatBookings = seatBookings;
		this.costumer = costumer;
		this.employee = employee;
		this.pickedUp = pickedUp;
		payment = null;
		
	}
	
	public Show getShow() 
	{
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public Map<Seat,Integer> getSeats() {
		return seatBookings;
	}

	public void setSeats(Map<Seat,Integer> seatBookings) 
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
		for (Map.Entry<Seat, Integer> entry : seatBookings.entrySet())
		{
			entry.getKey().setReservation();
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
		
		for (Map.Entry<Seat, Integer> entry : seatBookings.entrySet())
		{
			price = price + ( HallData.MAINPRICE * showCategory * entry.getKey().getCatagory());
			
		}
		return price;
	}

	public void setSeatsPayed()
	{
		for (Map.Entry<Seat, Integer> entry : seatBookings.entrySet())
		{
			entry.getKey().setPayment();	
		}
	}
	
	public Costumer getCostumer()
	{
		return costumer;
	}
	public Employee getEmployee()
	{
		return employee;
	}
	public boolean getPickedUp()
	{
		return pickedUp;
	}
	public void pickedUp()
	{
		pickedUp = true;
	}
	
	public String toString()
	{
		return "Film: "+show.getMovie().getTitle()+", Dato: "+ show.getHallBooking().getStart()+", Sal: "+show.getHallBooking().getHalleNo();
	}
	
}
