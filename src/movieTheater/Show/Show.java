package movieTheater.Show;
import movieTheater.Movie.Movie;

public class Show {

	
	private HallBooking hallBooking;
	private Movie movie;
	private int showID;
	private double priceCategory;

	/**
	 * 
	 * @param showID
	 * @param movie
	 * @param hallBooking
	 * @param priceCategory 1 = low period, 1.5 = standard, 2 = peak period. 
	 */
	public Show(int showID, Movie movie, HallBooking hallBooking, double priceCategory)
	{
		this.showID = showID;
		this.movie = movie;
		this.hallBooking = hallBooking;
		this.priceCategory = priceCategory;
	}
	
	public int getShowID()
	{
		return showID;
	}
	
	public Movie getMovie()
	{
		return movie;
	}

	public HallBooking getHallBooking()
	{
		return hallBooking;
	}
	
	public double getPriceCategory()
	{
		return priceCategory;
	}
	
	public String toString()
	{
		return "Film: "+movie.getMovieName()+"\nStart tid: "+ hallBooking.getTimeStart()+"\nSal:"+hallBooking.getHalleNo();
	}
}
