package movieTheater.Test;

import java.util.ArrayList;

import movieTheater.Movie.Actor;
import movieTheater.Movie.Director;
import movieTheater.Movie.Movie;
import movieTheater.Movie.Rating;
import movieTheater.SQL.SQLEmployeeLoad;
import movieTheater.SQL.SQLMovieLoad;
import movieTheater.main.Employee;

public class TestSQLShow{

	SQLEmployeeLoad employeeLoad;
	Employee employee;
	ArrayList<Employee> employees;
//	Director director;
//	ArrayList<Director> directors;

	public TestSQLShow()
	{
		employeeLoad = new SQLEmployeeLoad();
	}

	public void runTest()
	{
		
		testEmployeeLoad();
		
		
//		testDirectorLoad();
//		testDirectorLoad(1);
//		testMovieLoad(1);
	}
	public void testEmployeeLoad()
	{
		for(int i = 0; i < employees.size(); i++){
			try
			{
				employees = employeeLoad.LoadEmployee(i);	

				for(Employee currentEmployee: employees)
				{
					System.out.println(currentEmployee.getFirstName() + " " + currentEmployee.getLastName() + " " + currentEmployee.getRoad() + 
							" " + currentEmployee.getPostNr() + " " + currentEmployee.getCity() + " " + currentEmployee.getPassword());	
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}
//	public void testDirectorLoad()
//	{
//		try
//		{
//			directors = employeeLoad.LoadDirector();
//
//			for(Director currentDirector: directors)
//			{
//				System.out.println(currentDirector.getFirstName() + " " + currentDirector.getLastName() + " " + currentDirector.getGender() +
//						" " + currentDirector.getDescription());
//			}
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//
//	public void testDirectorLoad(int directID)
//	{
//		try
//		{
//			director = employeeLoad.LoadDirector(directID);
//
//			System.out.println(director.getFirstName() + " " + director.getLastName() + " " + director.getGender() +
//					" " + director.getDescription());
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//
//	public void testMovieLoad(int genreID)
//	{
//		ArrayList<Movie> movies = null;
//
//		SQLMovieLoad load = new SQLMovieLoad();
//		try
//		{
//			movies = load.LoadMovie(genreID);
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//			System.out.println("fejl");
//		}
//
//		for(Movie currentFilm : movies)
//		{
//			System.out.println("\n" + currentFilm.getMovieName() + " " + currentFilm.getLength() + " " 
//					+ currentFilm.getInstructedBy().getFirstName() + " " + currentFilm.getInstructedBy().getLastName() + " " +
//					currentFilm.getReleaseDate());
//
//			for(Actor currentActor: currentFilm.getCast())
//			{
//				System.out.println(currentActor.getFirstName() + " " + currentActor.getLastName());
//			}
//			System.out.println();
//
//			for(Rating currentRating: currentFilm.getRatings())
//			{
//				System.out.println(currentRating.getStars() + " " + currentRating.getReview());
//			}
//		}	
//	}
}
