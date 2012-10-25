package movieTheater.main;


public class Show {

	private int showID;
	private String timeS;
	private String timeE;
	private int movieID;
	private int hallNo;
	
	
	public Show(int showID, int hallNo, String timeS, String timeE, int movieID)
	{
		this.showID = showID;
		this.hallNo = hallNo;
		this.timeS = timeS;
		this.timeE = timeE;
		this.movieID = movieID;
		
	}
	
	public int getShowID(){
		return showID;
	}
	
	public String getTimeS(){
		return timeS;
	}
	
	public String getTimeE(){
		return timeE;
	}
	
	public int getHallno(){
		return hallNo;
	}
	
	public int getMovieID(){
		return movieID;
	}
	
	//public  getAvailableSeats
	
	//public getAvailableSeats(int seatsTogether)
}
