package movieTheater.Movie;


import java.sql.Date;
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
	private Date releaseDate;
	private Date timeEnd;
	private String originalTitle;
	private boolean is3D;
	private Cast cast;
	private ArrayList<Rating> ratings;

	/**
	 * 
	 * @param movieName String
	 * @param instructedBy String
	 * @param length int
	 * @param genre String
	 * @param releaseDate Date
	 * @param timeEnd Date
	 * @param isDoubleLength boolean
	 * @param is3D boolean
	 * @param cast ArrayList<Cast>
	 * @param ratings ArrayList<Rating>
	 */
	public Movie()
	{
		title = "";
		director = null;
		length = 0;
		genre = null;
		releaseDate = null;
		timeEnd = null;
		originalTitle = "";
		is3D = false;
		cast = null;
		ratings = null; 
	
	}

	public Movie(String movieName, Director directedBy, int length, String genre, Date releaseDate, Date timeEnd,
			String originalName, boolean is3D, Cast cast)
	{
		this(0, movieName, directedBy, length, genre, releaseDate, timeEnd, originalName, is3D, cast, null); //Kalder 2. konstruktør
	}
	public Movie(int movieID,String movieName, Director directedBy, int length, String genre, Date releaseDate, Date timeEnd,
			String originalName, boolean is3D, Cast cast, 
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
		return originalTitle;
	}
	public boolean getIsIn3D()
	{
		return is3D;
	}
	public Cast getCast()
	{
		return cast;
	}
	public ArrayList<String> getCastNames()
	{
		return cast.getActorNames();
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

	public void setMovieID(int movieID)
	{
		this.movieID = movieID;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setDirector(Director director)
	{
		this.director = director;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public void setReleaseDate(Date releaseDate)
	{
		this.releaseDate = releaseDate;
	}

	public void setTimeEnd(Date timeEnd)
	{
		this.timeEnd = timeEnd;
	}

	public void setOriginalTitle(String originalTitle)
	{
		this.originalTitle = originalTitle;
	}

	public void setIs3D(boolean is3d)
	{
		is3D = is3d;
	}

	public void setCast(Cast cast)
	{
		this.cast = cast;
	}

	public void setRatings(ArrayList<Rating> ratings)
	{
		this.ratings = ratings;
	}
	
	public String toString()
	{
		return (title + " - " + genre);
	}
	
}
