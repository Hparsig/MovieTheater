package movieTheater.main;

import java.awt.EventQueue;
import java.util.ArrayList;

//import movieTheater.GUI.TestWindow;
import movieTheater.Movie.Movie;
import movieTheater.SQL.SQLMovieLoad;

//Test til Mads

public class Main {

	public static void main(String[] args)
	{
		ArrayList<Movie> movies = null;
		
		SQLMovieLoad load = new SQLMovieLoad();
		try
		{
		movies = load.LoadMovie(1);
		}
		catch (Exception e)
		{
			System.out.println("fejl");
		}
		
		for(Movie currentFilm : movies)
		{
			System.out.println(currentFilm.getMovieName());
		}
		
//		CinemaController cinemaController = new CinemaController();
//		final TestWindow testWindow = new TestWindow(cinemaController);
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try 
//				{  
//					testWindow.frame.setVisible(true);
//				} 
//				catch (Exception e) 
//				{
//					e.printStackTrace();
//				}
//			}
//		});

	}
}
