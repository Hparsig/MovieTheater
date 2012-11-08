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
	public Genre(String genreName)
	{
		this.genreName = genreName;
		genreID = 0;
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
	public boolean equals(Object o)
	{
		boolean isEqual = false;
		
		if(o instanceof Director)
		{
			isEqual = this.genreName.equals(((Genre)o).getGenreName());
//		isEqual = (this.directorID == ((Director) o).getDirectorID());
		}
		return isEqual;
	}

}
