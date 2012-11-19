package movieTheater.main;

import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;

import movieTheater.GUI.CreateMovie;
import movieTheater.GUI.SearchMovie;
import movieTheater.Movie.Actor;
import movieTheater.Movie.Director;
import movieTheater.Movie.Genre;
import movieTheater.Movie.Movie;
import movieTheater.SQL.SQLMovieLoad;
import movieTheater.SQL.SQLMovieSave;


public class MovieController 
{
	private SQLMovieLoad load;
	private SQLMovieSave save;
	private CreateMovie createMovie;
	private Movie movie;
	public static ArrayList<Movie> movies;
	public static ArrayList<Actor> actors;
	public static ArrayList<Actor> newActors;
	public static ArrayList<Director> directors;
	public static ArrayList<Director> newDirectors;
	public static ArrayList<Genre> genres;
	public static ArrayList<Genre> newGenres;

	public MovieController()
	{
		load = new SQLMovieLoad();
		save = new SQLMovieSave();
		newActors = new ArrayList<Actor>();
		newDirectors = new ArrayList<Director>();
		newGenres = new ArrayList<Genre>(); 
		movies = new ArrayList<Movie>();
	}

	public void setMovie()
	{
		movie = new Movie();
		loadAttributes();
		createMovie = new CreateMovie(movie);
		createMovie.setVisible(true);

		try
		{
			createMovie.latch.await();
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		if (createMovie.areChangesMade())
		{
			checkAndSaveMovie();
		}		
		createMovie.dispose();
	}

	public void EditMovie()
	{
		SearchMovie searchMovie = new SearchMovie(this);
		searchMovie.setVisible(true);
		try
		{
			searchMovie.latch.await();
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		movie = searchMovie.getMovie();
		loadAttributes();
		createMovie = new CreateMovie(movie);
		createMovie.setVisible(true);
		searchMovie.dispose();

		try
		{
			createMovie.latch.await();
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		if (createMovie.areChangesMade())
		{
			checkAndSaveMovie();
		}
		createMovie.dispose();
	}

	public void deleteMovie()
	{
		SearchMovie searchMovie = new SearchMovie(this);
		searchMovie.setVisible(true);
		try
		{
			searchMovie.latch.await();
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		movie = searchMovie.getMovie();
		int result = JOptionPane.showConfirmDialog((Component) null, "Er du sikker på du vil slette "+movie.getMovieName(),"Advarsel", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == 0)
		{
			save.deleteMovie(movie.getMovieID());
		}
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
	private void checkAndSaveMovie()
	{

		for (Director currentDirector : newDirectors)
		{
			//check om der er gengangere
		}
		saveNewDirectors();									//Saves the created directors and adds directorID

		saveNewActors();									//Saves the created actors and adds actorID

		saveNewGenres();									//Saves the created genres and adds genreID

		movie = createMovie.getMovie();

		//Checks whether movie Genre or Director are new, if so they are replaced with the saved ones, who has gotten an ID. 
		for (Director currentDirector: newDirectors)
		{
			if (currentDirector.equals(movie.getInstructedBy()))
				movie.setDirector(currentDirector);
		}
		for (Genre currentGenre: newGenres)
		{
			if (currentGenre.equals(movie.getGenre()))
				movie.setGenre(currentGenre);
		}
		
		if (movie.getMovieID() != 0)
		{
			save.updateMovie(movie);
		}
		else
		{
		movie = save.saveMovie(movie); // returns with a created movieID. 
		}

		for(Map.Entry<Actor, String> entry : movie.getCast().getCast().entrySet())
		{
			Actor currentActor = entry.getKey();

			for (Actor currentNewActor : newActors)
			{
				if (currentActor.equals(currentNewActor))
					currentActor = currentNewActor;
			}
		}

		save.saveCastList(movie);
		//TODO valider data før det gemmes
	}

	private void saveNewActors()
	{
		if (!newActors.isEmpty())
		{
			for (Actor currentActor : newActors)
			{
				currentActor = save.saveActor(currentActor);
			}
		}
	}
	private void saveNewDirectors()
	{
		if (!newDirectors.isEmpty())
		{
			for (Director currentDirector : newDirectors)
			{
				currentDirector = save.saveDirector(currentDirector);
			}
		}
	}
	private void saveNewGenres()
	{
		if (!newGenres.isEmpty())
		{
			for (Genre currentGenre : newGenres)
			{
				currentGenre = save.saveGenre(currentGenre);
			}
		}
	}
	private void loadAttributes()
	{
		try
		{
			actors = load.LoadActors();
			directors = load.LoadDirector();
			genres = load.LoadGenres();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void loadMovies()
	{
		try
		{
			movies = load.LoadMovie();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

