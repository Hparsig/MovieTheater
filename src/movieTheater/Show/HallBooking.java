package movieTheater.Show;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Henrik
 *
 */

public class HallBooking {

	private int hallNo;
	private String start;
	private String end;
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
	
	public HallBooking(int hallNo, String start, String end){
		this.hallNo = hallNo;
//		this.timeStart = timeStart;
//		this.timeEnd = timeEnd;
		this.start = start;
		this.end = end;
	}
	
	public int getHalleNo()
	{
		return hallNo;
	}
	public String getTimeStart()
	{
		return start;
	}
	public String getTimeEnd()
	{
		return end;
	}
	public ArrayList<ArrayList<Seat>> getSeats(){
		return seatBooking;
	}
	
	public Timestamp getStart()
	{
		return timeStart;
	}
	public Timestamp getEndTime()
	{
		return timeEnd;
	}
	/**
	 * @author Jesper
	 * @return avaiable seats to the show
	 */
	public HashMap<Integer,ArrayList<Seat>> getAvailableSeats()
	{
		HashMap<Integer,ArrayList<Seat>> seatSuggestion = new HashMap<Integer,ArrayList<Seat>>();
		
		for (int row=0;row<seatBooking.size();row++) {
			ArrayList<Seat> seats = new ArrayList<Seat>();
	        for (int seat = 0; seat < seatBooking.get(row).size(); seat++)
	        {
	        	int status = seatBooking.get(row).get(seat).getStatus();
	        	if(status==0){
	        		seats.add(seatBooking.get(row).get(seat));
	        	}
	         } 
        	seatSuggestion.put(row, seats);	 
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
