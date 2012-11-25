package movieTheater.main;

import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import movieTheater.GUI.CreateMovie;
import movieTheater.GUI.SearchMovie;
import movieTheater.Movie.Actor;
import movieTheater.Movie.Director;
import movieTheater.Movie.Genre;
import movieTheater.Movie.Movie;
import movieTheater.SQL.SQLMovieLoad;
import movieTheater.SQL.SQLMovieSave;

/**
 * 
 * @author Henrik
 *
 */
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
	public static Map<Actor, String> addToCast;

	/**
	 * 
	 */
	public MovieController()
	{
		load = new SQLMovieLoad();
		save = new SQLMovieSave();
		newActors = new ArrayList<Actor>();
		newDirectors = new ArrayList<Director>();
		newGenres = new ArrayList<Genre>(); 
		addToCast = new HashMap<Actor, String>();
		movies = new ArrayList<Movie>();
	}
	/**
	 * Getting parameters from GUI, creates an Movie object and saves its data in the database. 
	 */
	public void setMovie()
	{
		movie = new Movie();					//creates new movie, so movieID is == 0;
		loadAttributes();
		showPanel();
	}

	/**
	 * searches via GUI for the movie to edit. Gets new parameters via GUI, alter the Movie object and saves its data in the database. 
	 */
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
		if(movie!=null)
		{
			loadAttributes();
			showPanel();
		}

	}

	private void showPanel()
	{
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

	/**
	 * 	 searches via GUI for the movie to delete. Ask whether the user i sure and then deletes the data in the database. 
	 */
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
		if(movie!=null)
		{
			int result = JOptionPane.showConfirmDialog((Component) null, "Er du sikker på du vil slette "+movie.getTitle(),"Advarsel", JOptionPane.OK_CANCEL_OPTION);

			if (result == 0)
			{
				save.deleteMovie(movie.getMovieID());
			}
		}
	}
	/**
	 * Searches for movies in the database that have some of the parameters attached. Movie objects are created accordingly and placed in an ArrayList 
	 * @param title
	 * @param orgTitle
	 * @param actorFName
	 * @param directorFName
	 * @return ArrayList<Movie>
	 * @throws SQLException
	 */
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
		// Checks whether a title is selected
		if (movie.getTitle().isEmpty() || movie.getTitle() == null)
		{
			JOptionPane.showMessageDialog(null,
					"Der skal indtastes en title",
					"Advarsel",
					JOptionPane.PLAIN_MESSAGE);
			createMovie.dispose();
			showPanel();
		}
		// Checks whether premier date is before TimeEnd. 
		if (movie.getReleaseDate().after(movie.getTimeEnd()))
		{
			JOptionPane.showMessageDialog(null,
					"Udløbsdato skal ligge efter premierdato",
					"Advarsel",
					JOptionPane.PLAIN_MESSAGE);
			createMovie.dispose();
			showPanel();
		}
		// Checks whether a director is selected
		if (movie.getDirector() == null)
		{
			JOptionPane.showMessageDialog(null,
					"Der skal vælges en instruktør",
					"Advarsel",
					JOptionPane.PLAIN_MESSAGE);
			createMovie.dispose();
			showPanel();
		}
		// Checks wheter a gerne is selected
		if (movie.getGenre() == null)
		{
			JOptionPane.showMessageDialog(null,
					"Der skal vælges en genre",
					"Advarsel",
					JOptionPane.PLAIN_MESSAGE);
			createMovie.dispose();
			showPanel();
		}
		//
		for (Director currentNewDirector : newDirectors) //check om der er gengangere og fjern dem. 
		{
			for (Director currentDirector : directors)
			{
				if (currentNewDirector.equals(currentDirector) && currentDirector.getDirectorID() != 0)
				{
					JOptionPane.showMessageDialog(null,
							"Den oprettede instruktør findes allerede.",
							"Advarsel",
							JOptionPane.PLAIN_MESSAGE);
					if (movie.getDirector().equals(currentDirector))
					{
						movie.setDirector(currentDirector);			//Changes movies director to the one allready in the database. 
					}
				}
				if (!currentNewDirector.equals(currentDirector) && currentDirector.getDirectorID() == 0)
				{   
					save.saveDirector(currentNewDirector);
				}
			}
		}

		//FIXME kontrol af om der er doubletter bland skuespillere. 
		saveNewActors();									//Saves the created actors and adds actorID

		for (Genre currentNewGenre : newGenres) //check om der er gengangere og fjern dem. 
		{
			for (Genre currentGenre : genres)
			{
				if (currentNewGenre.equals(currentGenre))
				{
					JOptionPane.showMessageDialog(null,
							"Genren findes allerede og erstattes.",
							"Advarsel",
							JOptionPane.PLAIN_MESSAGE);
					//FIXME advarslen kommer to gange. 
					if (movie.getGenre().equals(currentGenre))
					{
						movie.setGenre(currentGenre);			//Changes movies genre to the one allready in the database. 
					}
				}
				else
				{	//FIXME kontrol, gemmer flere gange pt. 
					save.saveGenre(currentNewGenre);
				}
			}
		}

		movie = createMovie.getMovie();

		for (Genre currentGenre: newGenres)
		{
			if (currentGenre.equals(movie.getGenre()))
				movie.setGenre(currentGenre);
		}

		if (movie.getMovieID() != 0)				//Movie being edited
		{
			save.updateMovie(movie);
			if(!addToCast.isEmpty())
			{
				for (Actor actor: addToCast.keySet())
				{
					//FIXME løb map igennem og tjek for dubletter. 						
				}	
				save.saveCastList(addToCast, movie.getMovieID());
			}
		}
		else										//New movie being created
		{
			movie = save.saveMovie(movie); 			// returns with a created movieID. 
			if (!movie.getCast().getCast().isEmpty())
			{
				save.saveCastList(movie.getCast().getCast(), movie.getMovieID());
			}
		}
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
	private void loadAttributes()
	{
		actors = load.LoadActors();
		directors = load.LoadDirector();
		genres = load.LoadGenres();
	}

	public void loadMovies()
	{
		movies = load.LoadMovie();
	}
}

