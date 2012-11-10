package movieTheater.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import movieTheater.GUI.CreateMovie;
import movieTheater.GUI.SearchMovie;
import movieTheater.Movie.Actor;
import movieTheater.Movie.Director;
import movieTheater.Movie.Genre;
import movieTheater.Movie.Movie;
import movieTheater.Movie.MoviePerson;
import movieTheater.SQL.SQLMovieLoad;
import movieTheater.SQL.SQLMovieSave;


public class MovieController 
{
	private SQLMovieLoad load;
	private SQLMovieSave save;
	private CreateMovie createMovie;
	private Movie movie;
	private ArrayList<Movie> movies;
	private ArrayList<Actor> actors;
	private ArrayList<Actor> newActors;
	private ArrayList<Director> directors;
	private ArrayList<Director> newDirectors;
	private ArrayList<Genre> genres;
	private ArrayList<Genre> newGenres;

	public MovieController()
	{
		load = new SQLMovieLoad();
		save = new SQLMovieSave();
		movies = new ArrayList<Movie>();
	}

	public void setMovie()
	{
		movie = new Movie();
		loadAttributes();
		createMovie = new CreateMovie(movie, actors, directors, genres);
		createMovie.setVisible(true);

		try
		{
			createMovie.latch.await();
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		movie = searchMovie.getMovie();
		loadAttributes();
		createMovie = new CreateMovie(movie, actors, directors, genres);
		createMovie.setVisible(true);
		searchMovie.dispose();

		try
		{
			createMovie.latch.await();
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (createMovie.areChangesMade())
		{
			checkAndSaveMovie();
		}
		createMovie.dispose();
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

		newDirectors = createMovie.getNewDirectors();
		for (Director currentDirector : newDirectors)
		{
			//check om der er gengangere
		}
		saveNewDirectors();									//Saves the created directors and adds directorID

		newActors = createMovie.getNewActors();
		saveNewActors();									//Saves the created actors and adds actorID

		newGenres = createMovie.getNewGenres();
		saveNewGenres();									//Saves the created genres and adds genreID

		movie = createMovie.getMovie();

		//Checks whether movie Genre og Director are new, if so they are replaced with the saved ones, who has gotten an ID. 
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
	
		movie = save.saveMovie(movie);				
	//FIXME virker ikke når man redigerer film. 
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}





//public ArrayList<Movie> getAvailableMovies(Date timeStart, Date timeEnd)
//{
//	ArrayList<Movie> availableMovies = new ArrayList<Movie>();
//
//	for(Movie currentMovie: movies)
//	{
//		if(timeStart.after(currentMovie.getReleaseDate()) && timeEnd.before(currentMovie.getTimeEnd()))
//		{
//			availableMovies.add(currentMovie);
//		}
//	}
//	return availableMovies;
//}
