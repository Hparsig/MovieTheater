package movieTheater.Show;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Henrik
 *
 */
public class Hall {

	private int hallNo;
	private int[] seatsPrRow;
	private ArrayList<HallBooking> hallBookings; 
	private ArrayList<ArrayList<Seat>> seats;
	
	public Hall(int hallNo, int[] seatsPrRow)
	{
		this.hallNo = hallNo;
		this.seatsPrRow = seatsPrRow;
	}
	/**
	 * Adds an instance of the HallBooking class to the HallBookings-array. The method do not check whether the booking
	 * overlap and should therefore only be used after a check of availability.  
	 * @param timeStart - Date 
	 * @param timeEnd - Date 
	 */
	public void addHallBooking(Timestamp timeStart, Timestamp timeEnd)
	{
		seats = new ArrayList<ArrayList<Seat>>();	
		
		for(int currentRow: seatsPrRow)							//iterates over one row at the time
		{
			int noSeatsCurrentRow = seatsPrRow[currentRow];
			
			for(int i = 0; i < noSeatsCurrentRow; i++)			//adds seats according to the seatsPrRow-array. 
			{
				seats.get(currentRow).add(new Seat(i));
			}
		}
		hallBookings.add(new HallBooking(hallNo, seats, timeStart, timeEnd));	
	}
	 
	public HashMap<Integer,ArrayList<Seat>> getAvaliableSeats(HallBooking currentHallbooking)
	{
		HashMap<Integer,ArrayList<Seat>> availableSeats = currentHallbooking.getAvailableSeats();
		return availableSeats;
	}
	
	public int getHallNo()
	{
		return hallNo;
	}
	public ArrayList<ArrayList<Seat>> getSeats()
	{
		return seats;
	}
	public ArrayList<HallBooking> getBookings(){
		return hallBookings;
	}
	
}
