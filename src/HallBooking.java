import java.util.ArrayList;
import java.util.Date;


public class HallBooking {

	private ArrayList<ArrayList<Seat>> seatBooking;
	private Date timeStart;
	private Date timeEnd;
	
	public HallBooking(ArrayList<ArrayList<Seat>> seatBooking, Date timeStart, Date timeEnd)
	{
		this.seatBooking = seatBooking;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
	}
	
	public Date getTimeStart()
	{
		return timeStart;
	}
	public Date getTimeEnd()
	{
		return timeEnd;
	}
	public ArrayList<Seat> getAvailableSeats(int noOfSeatsTogether)
	{
		ArrayList<Seat> seatSuggestion = new ArrayList<Seat>();
		return  seatSuggestion;
	}
	public void setReservationOnSeat(int row, int seat)
	{
		seatBooking.get(row).get(seat).setReservation();
	}
	public void setPaymentOnSeat(int row, int seat)
	{
		seatBooking.get(row).get(seat).setPayment();
	}
	public void cancelSeat(int row, int seat)
	{
		seatBooking.get(row).get(seat).cancelSeat();
	}
}
