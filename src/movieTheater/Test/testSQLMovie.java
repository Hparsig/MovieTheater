package movieTheater.Test;

import java.util.ArrayList;

import movieTheater.Movie.Actor;
import movieTheater.Movie.Director;
import movieTheater.Movie.Movie;
import movieTheater.Movie.Rating;
import movieTheater.SQL.SQLMovieLoad;

public class testSQLMovie {

	SQLMovieLoad movieLoad;
	Actor actor;
	ArrayList<Actor> actors;
	Director director;
	ArrayList<Director> directors;

	public testSQLMovie()
	{
		movieLoad = new SQLMovieLoad();
	}

	public void runTest()
	{
		testDirectorLoad();
		testDirectorLoad(1);
		testActorLoad();
		testMovieLoad(1);
	}
	public void testActorLoad()
	{
		try
		{
			actors = movieLoad.LoadActors();	

			for(Actor currentActor: actors)
			{
				System.out.println(currentActor.getFirstName() + " " + currentActor.getLastName() + " " + currentActor.getGender() + 
						" " + currentActor.getDescription());	
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	public void testDirectorLoad()
	{
		try
		{
			directors = movieLoad.LoadDirector();

			for(Director currentDirector: directors)
			{
				System.out.println(currentDirector.getFirstName() + " " + currentDirector.getLastName() + " " + currentDirector.getGender() +
						" " + currentDirector.getDescription());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void testDirectorLoad(int directID)
	{
		try
		{
			director = movieLoad.LoadDirector(directID);

			System.out.println(director.getFirstName() + " " + director.getLastName() + " " + director.getGender() +
					" " + director.getDescription());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void testMovieLoad(int genreID)
	{
		ArrayList<Movie> movies = null;

		SQLMovieLoad load = new SQLMovieLoad();
		try
		{
			movies = load.LoadMovie(genreID);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fejl");
		}

		for(Movie currentFilm : movies)
		{
			System.out.println("\n" + currentFilm.getMovieName() + " " + currentFilm.getLength() + " " 
					+ currentFilm.getInstructedBy().getFirstName() + " " + currentFilm.getInstructedBy().getLastName() + " " +
					currentFilm.getReleaseDate());

			for(Actor currentActor: currentFilm.getCast())
			{
				System.out.println(currentActor.getFirstName() + " " + currentActor.getLastName());
			}
			System.out.println();
			
			for(Rating currentRating: currentFilm.getRatings())
			{
				System.out.println(currentRating.getStars() + " " + currentRating.getReview());
			}
		}	
	}
}
