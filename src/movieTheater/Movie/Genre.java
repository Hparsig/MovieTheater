package movieTheater.Movie;

public class Genre
{
	private int genreID;
	private String genreName;
	
	public Genre(int genreID, String genreName) 
	{
		this.genreName = genreName;
		this.genreID = genreID;
	}
	
	public int getGenreID()
	{
		return genreID;
	}
	public String getGenreName()
	{
		return genreName;
	}
	public String toString()
	{
		return genreID + " " + genreName;
	}

}
