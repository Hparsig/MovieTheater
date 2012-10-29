package movieTheater.main;
import movieTheater.Movie.Movie;

public class Show {

	
	private HallBooking hallBooking;
	private Movie movie;
	private int showID;
	
	public Show(int showID, Movie movie, HallBooking hallBooking)
	{
		this.showID = showID;
		this.movie = movie;
		this.hallBooking = hallBooking;
	}
	
	public int getShowID(){
		return showID;
	}
	
	public Movie getMovie(){
		return movie;
	}
	
	public HallBooking getHallBooking(){
		return hallBooking;
	}
}
