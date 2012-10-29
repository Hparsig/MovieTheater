package movieTheater.Movie;

import java.sql.Timestamp;
import java.util.ArrayList;
/**
 * 
 * @author Henrik
 *
 */

public class Movie {
	private int movieID;
	private String title;
	private Director director;
	private int length;
	private String genre;
	private Timestamp releaseDate;
	private Timestamp timeEnd;
	private String originalTitle;
	private boolean is3D;
	private ArrayList<Actor> cast;
	private ArrayList<Rating> ratings;

	/**
	 * 
	 * @param movieName String
	 * @param instructedBy String
	 * @param length int
	 * @param genre String
	 * @param releaseDate int
	 * @param isDoubleLength boolean
	 * @param is3D boolean
	 * @param cast ArrayList<String>
	 * @param ratings ArrayList<Rating>
	 */

	public Movie(String movieName, Director directedBy, int length, String genre, Timestamp releaseDate, Timestamp timeEnd,
			String originalName, boolean is3D, ArrayList<Actor> cast, 
			ArrayList<Rating> ratings)
	{
		this.title = movieName;
		this.director = directedBy;
		this.length = length;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.timeEnd = timeEnd;
		this.originalTitle = originalName;
		this.is3D = is3D;
		this.cast = cast;
		this.ratings = ratings;
	}
	public Movie(int movieID,String movieName, Director directedBy, int length, String genre, Timestamp releaseDate, Timestamp timeEnd,
			String originalName, boolean is3D, ArrayList<Actor> cast, 
			ArrayList<Rating> ratings)
	{
		this.movieID = movieID;
		this.title = movieName;
		this.director = directedBy;
		this.length = length;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.timeEnd = timeEnd;
		this.originalTitle = originalName;
		this.is3D = is3D;
		this.cast = cast;
		this.ratings = ratings;
	}
	/**
	 * @return String movieName, the name of the movie. 
	 */
	public String getMovieName()
	{
		return title;
	}
	public Director getInstructedBy()
	{
		return director;
	}
	/**
	 * @return int length, the length of the movie. 
	 */
	public int getLength()
	{
		return length;
	}
	public String getGenre()
	{
		return genre;
	}
	public Timestamp getReleaseDate()
	{
		return releaseDate;
	}
	public Timestamp getTimeEnd()
	{
		return timeEnd;
	}
	public String getOriginalName()
	{
		return originalTitle;
	}
	public boolean getIsIn3D()
	{
		return is3D;
	}
	public ArrayList<Actor> getCast()
	{
		return cast;
	}
	public Rating getRating(int userID)
	{
		Rating chosenRating = null;
		
		for (Rating currentRating: ratings)
		{
			if (userID == currentRating.getUserID())
			{
				chosenRating = currentRating;
			}
		}
		return chosenRating;
	}
	public ArrayList<Rating> getRatings()
	{
		return ratings;
	}
	/**
	 * Goes trough the rewievs, sums the number of stars given and devides by the total number of rewievs. 
	 * @return int starsAvarage. 
	 */
	public void setRating(Rating rating)
	{
		ratings.add(rating);
	}
	public int getStarsAvarage()
	{
		int stars = 0;
		for(Rating i: ratings)
		{
			stars = stars + i.getStars();
		}
		stars = stars/ratings.size();

		return stars;
	}
}
