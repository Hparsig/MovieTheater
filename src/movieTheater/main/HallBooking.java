package movieTheater.main;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

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
	public HashMap<Integer,Integer> getAvailableSeats()
	{
		HashMap<Integer,Integer> seatSuggestion = new HashMap<Integer,Integer>();
		
		for (int row=0;row<seatBooking.size();row++) {
			
	         for (int seat=0;seat<seatBooking.get(row).size();seat++)
	         {
	        	 int status = seatBooking.get(row).get(seat).getStatus();
	        	 if(status==0){
	        		 seatSuggestion.put(row, seat);
	        	 }
	        		 
	         } 
		}
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
