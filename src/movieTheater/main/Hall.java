package movieTheater.main;

import java.util.ArrayList;
import java.util.Date;


public class Hall {

	private int hallNo;
	private int[] seatsPrRow;
	private ArrayList<HallBooking> HallBookings; 
	
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
	public void addHallBooking(Date timeStart, Date timeEnd)
	{
		ArrayList<ArrayList<Seat>> seats = new ArrayList<ArrayList<Seat>>();	
		
		for(int currentRow: seatsPrRow)							//iterates over one row at the time
		{
			int noSeatsCurrentRow = seatsPrRow[currentRow];
			
			for(int i = 0; i < noSeatsCurrentRow; i++)			//adds seats according to the seatsPrRow-array. 
			{
				seats.get(currentRow).add(new Seat(i));
			}
		}
		HallBookings.add(new HallBooking(hallNo, seats, timeStart, timeEnd));	
	}
	public int getHallNo()
	{
		return hallNo;
	}
}
