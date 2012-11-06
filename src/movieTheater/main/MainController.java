package movieTheater.main;

import movieTheater.GUI.CreateCostumer;
import movieTheater.GUI.CreateEmployee;
import movieTheater.GUI.MainWindow;
import movieTheater.GUI.NewMovie;
import movieTheater.Movie.Movie;
import movieTheater.Persons.Costumer;
import movieTheater.Persons.Employee;
import movieTheater.SQL.SQLCustomerSave;
import movieTheater.SQL.SQLEmployeeSave;
import movieTheater.SQL.SQLMovieSave;

public class MainController
{
	private NewMovie newMovie;
	private SQLMovieSave saveMovie;
	
	private CreateEmployee createEmployee;
	private SQLEmployeeSave saveEmployee;
	
	private CreateCostumer createCostumer;
	private SQLCustomerSave saveCostumer;
	
	private int userChoice;
	
	private Movie movie;
	private Employee employee;
	private Costumer costumer;
	

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
			createCostumer = new CreateCostumer();
			createCostumer.setVisible(true);
			
			try
			{
				createCostumer.latch.await();
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Opret kunde");
			
			saveCostumer = new SQLCustomerSave();
			costumer = createCostumer.getCostumer();
			
			if (costumer != null)
			{
				saveCostumer.createCustomer(costumer);
			}
			createCostumer.dispose();
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
			createEmployee = new CreateEmployee();
			createEmployee.setVisible(true);
			
			try
			{
				createEmployee.latch.await();
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Opret medarbejder");
			
			saveEmployee = new SQLEmployeeSave();
			employee = createEmployee.getEmployee();
			
			if (employee != null)
			{
				saveEmployee.createEmployee(employee);
			}
			createEmployee.dispose();
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
			movie = newMovie.getMovie();
			if (movie != null)
			{
				//TODO valider data før det gemmes
				saveMovie.saveMovie(movie);
			}
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
			System.out.println("Log af");
			break;
		}
		}
		run(); //TODO holder ikke hvis der logges af, så skal findes en anden måde at køre i ring.
	}
}