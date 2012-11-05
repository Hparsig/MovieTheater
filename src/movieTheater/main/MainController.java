package movieTheater.main;

import movieTheater.GUI.MainWindow;
import movieTheater.GUI.NewMovie;
import movieTheater.SQL.SQLMovieSave;

public class MainController
{
	private NewMovie newMovie;
	private SQLMovieSave saveMovie;
	private int userChoice;
	

	public void run()
	{
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
		try
		{
			mainWindow.latch.await();
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userChoice = mainWindow.getChoise();
		mainWindow.dispose();

		switch(userChoice)
		{
		case MainWindow.NEWORDER:
		{
			System.out.println("Ny bestilling");
			break;
		}
		case MainWindow.GETORDER:
		{
			System.out.println("Hent Bestilling");
			break;
		}
		case MainWindow.CREATECOSTUMER:
		{
			System.out.println("Opret kunde");
			break;
		}
		case MainWindow.EDITCOSTUMER:
		{
			System.out.println("Rediger kunde");
			break;
		}
		case MainWindow.DELETECOSTUMER:
		{
			System.out.println("Slet kunde");
			break;
		}
		case MainWindow.CREATEEMPLOYEE:
		{
			System.out.println("Opret medarbejder");
			break;
		}
		case MainWindow.EDITEMPLOYEE:
		{
			System.out.println("Rediger medarbejder");
			break;
		}
		case MainWindow.DELETEEMPLOYEE:
		{
			System.out.println("Slet medarbejder");
			break;
		}
		case MainWindow.CREATEMOVIE:
		{
			newMovie = new NewMovie();
			newMovie.setVisible(true);
			try
			{
				newMovie.latch.await();
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Opret film");
			
			saveMovie = new SQLMovieSave();
			saveMovie.saveMovie(newMovie.getMovie());
			newMovie.dispose();
			break;
		}
		case MainWindow.EDITMOVIE:
		{
			System.out.println("Rediger film");
			break;
		}
		case MainWindow.DELETEMOVIE:
		{
			System.out.println("Slet film");
			break;
		}
		case MainWindow.CREATESHOW:
		{
			System.out.println("Opret forestilling");
			break;
		}
		case MainWindow.EDITSHOW:
		{
			System.out.println("Rediger forestilling");
			break;
		}
		case MainWindow.DELETESHOW:
		{
			System.out.println("Slet forestilling");
			break;
		}
		case MainWindow.LOGOFF:
		{
			System.out.println("Slet forestilling");
			break;
		}
		}
	}
}