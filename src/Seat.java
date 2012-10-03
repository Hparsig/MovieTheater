
public class Seat {

	private int seatNo;
	private int seatStatus;
	private int catagory; 
	private static final int AVAILABLE = 0;								//erklærer disse konstanter, for en mere forklarende tekst
	private static final int RESERVED = 1;								 
	private static final int PAYED = 2;
	private static final int BUDGET = 1;
	private static final int STANDARD = 2;
	private static final int LUXURIOUS = 3;
	
	public Seat(int seatNo)
	{
		this.seatNo = seatNo;
		catagory = STANDARD;
		seatStatus = AVAILABLE;
	}

	public Seat(int seatNo, int catagory)
	{
		this.seatNo = seatNo+1; 	//+1 da der tælles fra 0 og opad. 
		this.catagory = catagory;
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
	public int getCatagory()
	{
		return catagory;
	}
	
	public void setSeatStatus(int seatStatus)
	{
		this.seatStatus = seatStatus;
	}
	
}
