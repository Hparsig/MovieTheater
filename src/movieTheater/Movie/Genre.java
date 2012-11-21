package movieTheater.Movie;
/**
 * 
 * @author Henrik
 *
 */
public class Genre
{
	private int genreID;
	private String genreName;
	/**
	 * This constructor is to be used when genres are being loaded from the database, with their genreID
	 * @param genreID
	 * @param genreName
	 */
	public Genre(int genreID, String genreName) 
	{
		this.genreName = genreName;
		this.genreID = genreID;
	}
	/**
	 * This constructor is to be used when creating new Genres. 
	 * @param genreName
	 */
	public Genre(String genreName)
	{
		this.genreName = genreName;
		genreID = 0;
	}

	public int getGenreID()
	{
		return genreID;
	}
	public void setGenreID(int genreID)
	{
		this.genreID = genreID;
	}
	public String getGenreName()
	{
		return genreName;
	}
	public String toString()
	{
		return (genreID + " " + genreName);
	}
	public boolean equals(Object o)
	{
		boolean isEqual = false;

		if(o instanceof Genre)
		{
			isEqual = this.genreName.equals(((Genre)o).getGenreName());
		}
		return isEqual;
	}

}
