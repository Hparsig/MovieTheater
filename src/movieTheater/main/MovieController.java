package movieTheater.main;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import movieTheater.Movie.Cast;
import movieTheater.Movie.Director;
import movieTheater.Movie.Movie;
import movieTheater.Persons.Employee;
import movieTheater.SQL.SQLMovieLoad;
import movieTheater.SQL.SQLMovieSave;


public class MovieController 
{
	private SQLMovieLoad load;
	private SQLMovieSave save;
	private ArrayList<Movie> movies;
	
	public MovieController(SQLMovieLoad load, SQLMovieSave save)
	{
	this.load = load;
	this.save = save;
	movies = new ArrayList<Movie>();
	}
	
	public ArrayList<Movie> getAvailableMovies(Date timeStart, Date timeEnd)
	{
		ArrayList<Movie> availableMovies = new ArrayList<Movie>();

		for(Movie currentMovie: movies)
		{
			if(timeStart.after(currentMovie.getReleaseDate()) && timeEnd.before(currentMovie.getTimeEnd()))
			{
				availableMovies.add(currentMovie);
			}
		}
		return availableMovies;
	}

	public ArrayList<Movie> searchMovie(String title, String orgTitle, String actorFName, String directorFName)throws SQLException
	{
		movies.clear();
		try
		{
			movies = load.searchMovie(title, orgTitle, actorFName, directorFName);
		}
		catch(java.lang.NumberFormatException e)
		{
			e.getStackTrace();
		}
		return movies;

	}
}
