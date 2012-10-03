import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author Henrik
 *
 */

public class Film {

	private String movieName;
	private String instructedBy;
	private int length;
	private String genre;
	private Date releaseDate;
	private Date timeEnd;
	private String originalName;
	private boolean isDoubleLength;
	private boolean is3D;
	private ArrayList<String> cast;
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

	public Film(String movieName, String instructedBy, int length, String genre, Date releaseDate, Date timeEnd,
			String originalName, boolean isDoubleLength, boolean is3D, ArrayList<String> cast, 
			ArrayList<Rating> ratings)
	{
		this.movieName = movieName;
		this.instructedBy = instructedBy;
		this.length = length;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.timeEnd = timeEnd;
		this.originalName = originalName;
		this.isDoubleLength = isDoubleLength;
		this.is3D = is3D;
		this.cast = cast;
		this.ratings = ratings;
	}
	/**
	 * @return String movieName, the name of the movie. 
	 */
	public String getMovieName()
	{
		return movieName;
	}
	public String getInstructedBy()
	{
		return instructedBy;
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
	public Date getReleaseDate()
	{
		return releaseDate;
	}
	public Date getTimeEnd()
	{
		return timeEnd;
	}
	public String getOriginalName()
	{
		return originalName;
	}
	public boolean getIsDoubleLength()
	{
		return isDoubleLength;
	}
	public boolean getIsIn3D()
	{
		return is3D;
	}
	public ArrayList<String> getCast()
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
