package movieTheater.main;

import java.awt.EventQueue;
import java.sql.Date;
import java.util.ArrayList;

import movieTheater.GUI.MainWindow;
//import movieTheater.GUI.TestWindow;
import movieTheater.Movie.Movie;
import movieTheater.SQL.SQLMovieLoad;
import movieTheater.Test.*;

public class Main {

	public static void main(String[] args)
	{
		//testSQLMovie test = new testSQLMovie();
		//test.runTest();
		
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
		
		SQLShowLoadTest test = new SQLShowLoadTest();
		Date dato = new Date(2012-1900,9,26);
		test.test("Skyfall",dato);
//		MovieController movieController = new MovieController();
//		HallController hallController = new HallController();
//		ShowController showC = new ShowController(movieController,hallController);
//		Date date = new Date(2012-1900,10-1,26);
//		ArrayList<Show> shows = showC.getShows("Skyfall", date);
//		System.out.println(date);
//		
//		System.out.println(shows.get(0).getMovie().getOriginalName());
		
		
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
