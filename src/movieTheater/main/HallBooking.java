package movieTheater.main;

import java.sql.Timestamp;
import java.util.ArrayList;

public class HallBooking {

	private int hallNo;
	private ArrayList<ArrayList<Seat>> seatBooking;
	private Timestamp timeStart;
	private Timestamp timeEnd;
	
	public HallBooking(int hallNo, ArrayList<ArrayList<Seat>> seatBooking, Timestamp timeStart, Timestamp timeEnd)
	{
		this.hallNo = hallNo;
		this.seatBooking = seatBooking;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
	}
	
	public int getHalleNo()
	{
		return hallNo;
	}
	public Timestamp getTimeStart()
	{
		return timeStart;
	}
	public Timestamp getTimeEnd()
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
