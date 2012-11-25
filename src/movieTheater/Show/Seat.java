package movieTheater.Show;

/**
 * 
 * @author Henrik
 *
 */
public class Seat {

	private int seatNo;
	private int seatStatus;
	private double catagoryFactor; 
	private static final int AVAILABLE = 0;								//erklærer disse konstanter, for en mere forklarende tekst
	private static final int RESERVED = 1;								 
	private static final int PAYED = 2;
	private static final double BUDGET = 1;
	private static final double STANDARD = 1.5;
	private static final double LUXURIOUS = 2;
	
	public Seat(int seatNo)
	{
		this.seatNo = seatNo+1;
		catagoryFactor = STANDARD;
		seatStatus = AVAILABLE;
	}

	public Seat(int seatNo, int catagory)
	{
		this.seatNo = seatNo+1; 	//+1 da der tælles fra 0 og opad. 
		this.catagoryFactor = catagory;
		seatStatus = AVAILABLE;
	}
	public void setReservation()
	{
		seatStatus = RESERVED;
	}
	public void setPayment()
	{
		seatStatus = PAYED;
	}
	public void cancelSeat()
	{
		seatStatus = AVAILABLE;
	}
	public int getStatus()
	{
		return seatStatus;
	}
	public int getSeatNo()
	{
		return seatNo;
	}
	public double getCatagory()
	{
		return catagoryFactor;
	}
	
	public void setSeatStatus(int seatStatus)
	{
		this.seatStatus = seatStatus;
	}
	public String toString()
	{
		return ""+seatNo;
	}
	
}
