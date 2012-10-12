package movieTheater.main;

import java.awt.EventQueue;

import movieTheater.GUI.TestWindow;

//Test til Mads

public class Main {

	public static void main(String[] args)
	{
		CinemaController cinemaController = new CinemaController();
		final TestWindow testWindow = new TestWindow(cinemaController);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{  
					testWindow.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});

	}
}
