package movieTheater.Movie;

/**
 * 
 * @author Henrik
 *
 */
public class Rating {

	private int stars;
	private String review;
	private int userID;
	/**
	 * 
	 * @param stars
	 * @param review
	 * @param userID
	 */
	public Rating(int stars, String review, int userID)
	{
		this.stars = stars;
		this.review = review;
		this.userID = userID;
	}
	/**
	 * 
	 * @return int number of stars. 
	 */
	public int getStars()
	{
		return stars;
	}
	/**
	 * 
	 * @return String review. 
	 */
	public String getReview()
	{
		return review;
	}
	public int getUserID()
	{
		return userID;
	}
}
