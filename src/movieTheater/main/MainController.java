package movieTheater.main;

import movieTheater.GUI.CreateCostumer;
import movieTheater.GUI.MainWindow;
import movieTheater.GUI.SearchShow;
import movieTheater.Persons.Admin;
import movieTheater.Persons.Costumer;
import movieTheater.Persons.Employee;
import movieTheater.SQL.SQLCustomerSave;
import movieTheater.Show.Show;

public class MainController
{
	private ShowController showController;
	private MovieController movieController;
	private CreateCostumer createCostumer;
	private LoginController loginController;
	private int userChoice;
	public static Employee loggedOn;
	private boolean menuOn;
	private boolean programOn;
	private boolean isAdmin;


	public MainController()
	{
		loginController = new LoginController();
		menuOn = true;
		programOn = true;
	}

	public void run()
	{
		while (programOn)
		{
			loggedOn = loginController.employeeLogin();
			isAdmin = (loggedOn instanceof Admin);
			runMenu();
		}
	}
	public void runMenu()
	{
		while (menuOn)
		{
			MainWindow mainWindow = new MainWindow();
			mainWindow.runMainWindow(); 
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
				ShowController showController = new ShowController();
				Show show = showController.showSearchShow();
				if(show!=null)
				{
					BookingController bookingCon = new BookingController();
					bookingCon.showNewBookings(show);
				}
				break;
			}
			case MainWindow.GETORDER:
			{
				BookingController bookingCon = new BookingController();
				bookingCon.showLoadOrder();
				break;
			}
			case MainWindow.CREATECOSTUMER:
			{
				createCostumer = new CreateCostumer(); //TODO ændre fremgangs måden så den ligner employee..
				createCostumer.setVisible(true);
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
				EmployeeController employeeController = new EmployeeController();
				employeeController.setEmployee(isAdmin);
				break;
			}
			case MainWindow.EDITEMPLOYEE:
			{
				EmployeeController employeeController = new EmployeeController();
				employeeController.editEmployee(isAdmin);
				break;
			}
			case MainWindow.DELETEEMPLOYEE:
			{
				EmployeeController employeeController = new EmployeeController();
				employeeController.searchEmployees(isAdmin);
				break;
			}
			case MainWindow.CREATEMOVIE:
			{
				movieController = new MovieController();
				movieController.setMovie();
				break;
			}
			case MainWindow.EDITMOVIE:
			{
				movieController = new MovieController();
				movieController.EditMovie();
				break;
			}
			case MainWindow.DELETEMOVIE:
			{
				System.out.println("Slet film");
				break;
			}
			case MainWindow.CREATESHOW:
			{
				
				movieController = new MovieController();
				movieController.loadMovies();
				showController = new ShowController();
				showController.setShow();
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
				loggedOn = null;
				menuOn = false;
				mainWindow.dispose();
				break;
			}
			case MainWindow.CREATEMANAGER:
			{
				EmployeeController employeeController = new EmployeeController();
				employeeController.setEmployee(isAdmin);
				break;
			}
			case MainWindow.EDITMANAGER:
			{
				EmployeeController employeeController = new EmployeeController();
				employeeController.editEmployee(isAdmin);
				break;
			}
			case MainWindow.DELETEMANAGER:
			{
				EmployeeController employeeController = new EmployeeController();
				employeeController.searchEmployees(isAdmin);
				break;
			}
			}
		} 
	}
}
