package movieTheater.main;

import java.awt.EventQueue;
import java.util.ArrayList;

//import movieTheater.GUI.TestWindow;
import movieTheater.Movie.Movie;
import movieTheater.SQL.SQLMovieLoad;
import movieTheater.Test.testSQLMovie;

public class Main {

	public static void main(String[] args)
	{
		testSQLMovie test = new testSQLMovie();
	
		test.runTest();

	
		
		
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
