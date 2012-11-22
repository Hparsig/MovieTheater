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
	public Show(){
		showID = 0;
		movie = null;
		hallBooking = null;
		priceCategory = 0;
		
	}
	public Show(Movie movie, HallBooking hallBooking, double priceCategory)
	{
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
		return "Film: "+movie.getTitle()+", Start tid: "+ hallBooking.getStart()+", Sal: "+hallBooking.getHalleNo();
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public void setHallBooking(HallBooking hallBooking) {
		this.hallBooking = hallBooking;
	}
	public void setPrice(double price) {
		this.priceCategory = price;
	}
}
