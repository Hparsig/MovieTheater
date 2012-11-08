package movieTheater.main;

import movieTheater.GUI.EditEmployee;
import movieTheater.GUI.CreateCostumer;
import movieTheater.GUI.CreateEmployee;
import movieTheater.GUI.DeleteEmployee;
import movieTheater.GUI.SearchEmployee;
import movieTheater.GUI.MainWindow;
import movieTheater.GUI.CreateMovie;
import movieTheater.GUI.SearchMovie;
import movieTheater.GUI.SearchShow;
import movieTheater.Movie.Movie;
import movieTheater.Persons.Costumer;
import movieTheater.Persons.Employee;
import movieTheater.SQL.SQLCustomerSave;
import movieTheater.SQL.SQLEmployeeLoad;
import movieTheater.SQL.SQLEmployeeSave;
import movieTheater.SQL.SQLMovieLoad;
import movieTheater.SQL.SQLMovieSave;

public class MainController
{
	private CreateMovie newMovie;
	private SQLMovieSave saveMovie;
	private SQLMovieLoad loadMovie;
	
	private CreateEmployee createEmployee;
	private DeleteEmployee deleteEmployee;
	private SearchEmployee searchEmployee;
	private EditEmployee editEmployee;
	private EmployeeController employeeController;
	private SQLEmployeeSave saveEmployee;
	private SQLEmployeeLoad loadEmployee;
	private MovieController movieController;
	
	private CreateCostumer createCostumer;
	private SQLCustomerSave saveCostumer;
	
	private SearchShow searchShow;
	
	private int userChoice;
	
	private Movie movie;
	private Employee employee;
	private Costumer costumer;
	

	
	public MainController()
	{
		saveMovie = new SQLMovieSave();
		loadMovie = new SQLMovieLoad();
		saveCostumer = new SQLCustomerSave();
		saveEmployee = new SQLEmployeeSave();
		loadEmployee = new SQLEmployeeLoad();
		employeeController = new EmployeeController(loadEmployee,saveEmployee);
		
	}

	public void run()
	{
		MainWindow mainWindow = new MainWindow();
		mainWindow.runMainWindow(); //FIXME skal udbygges til at tage en Employee med (den der er logget på)
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
			searchShow = new SearchShow();
			searchShow.setVisible(true);
			
			try
			{
				searchShow.latch.await();
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			
			createEmployee = new CreateEmployee(employeeController);
			createEmployee.setVisible(true);
			
			try
			{
				createEmployee.latch.await();
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			System.out.println("Opret medarbejder");
			

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
			searchEmployee = new SearchEmployee(employeeController);
			searchEmployee.setVisible(true);
			
			try
			{
				searchEmployee.latch.await();

			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			employee = searchEmployee.getEmployee();
			editEmployee = new EditEmployee(employee,employeeController);
			editEmployee.setVisible(true);
			searchEmployee.dispose();
			try
			{
				editEmployee.latch.await();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			System.out.println("Ændre medarbejder");
			
			
			break;
		}
		case MainWindow.DELETEEMPLOYEE:
		{
			deleteEmployee = new DeleteEmployee(employeeController);
			deleteEmployee.setVisible(true);
			
			try
			{
				deleteEmployee.latch.await();
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Slet medarbejder");
			
			deleteEmployee.dispose();
			break;
		}
		case MainWindow.CREATEMOVIE:
		{
			movie = new Movie();
			newMovie = new CreateMovie(movie);
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
			
			movie = newMovie.getMovie();
			if (movie.getInstructedBy() != null)
			{
				//TODO valider data før det gemmes
				saveMovie.saveMovie(movie);
			}
			newMovie.dispose();
			break;
		}
		case MainWindow.EDITMOVIE:
		{
			movieController = new MovieController(loadMovie, saveMovie);
			SearchMovie searchMovie = new SearchMovie(movieController);
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
			newMovie = new CreateMovie(movie);
			newMovie.setVisible(true);
			searchMovie.dispose();
			
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
			
			movie = newMovie.getMovie();
			if (newMovie.areChangesMade())
			{
				//TODO valider data før det gemmes
				saveMovie.saveMovie(movie);
			}
			newMovie.dispose();
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
