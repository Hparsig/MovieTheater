package movieTheater.main;

import java.util.ArrayList;
import java.util.Date;


public class MovieController {

	private ArrayList<Movie> Movies;
	
	public ArrayList<Movie> getAvailableMovies(Date timeStart, Date timeEnd)
	{
		ArrayList<Movie> availableMovies = new ArrayList<Movie>();
		
		for(Movie currentMovie: Movies)
		{
			if(timeStart.after(currentMovie.getReleaseDate()) && timeEnd.before(currentMovie.getTimeEnd()))
			{
				availableMovies.add(currentMovie);
			}
		}
		return availableMovies;
	}
}
